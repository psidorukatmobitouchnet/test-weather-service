package net.mobitouch.testweatherservice.common.date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Component
public class DateRangeFactory {

  @Autowired
  private CurrentDatePolicy currentDatePolicy;

  @Autowired
  private DateFactory clock;

  public DateRange of(int fromDay, int toDay) {
    if (fromDay < 0 || toDay < 0) {
      throw new IllegalArgumentException("both days must be positive values");
    }
    if (fromDay > toDay) {
      throw new IllegalArgumentException("startDate cannot be set after endDate in date range");
    }

    LocalDate currentDay = clock.now();
    LocalDate startDate;
    LocalDate endDate;

    try {
      startDate = currentDay.withDayOfMonth(fromDay);
      endDate = currentDay.withDayOfMonth(toDay);
    } catch (DateTimeException e) {
      throw new IllegalArgumentException("toDay must be higher number than fromDay");
    }

    if (currentDatePolicy.forecastToday()) {
      return DateRange.builder()
          .startDate(startDate)
          .endDate(endDate)
          .forecastDates(
              IntStream
                  .rangeClosed(
                      Math.max(startDate.getDayOfMonth(), clock.now().getDayOfMonth()),
                      endDate.getDayOfMonth()
                  )
                  .boxed()
                  .map(dayOfMonth -> clock.now().withDayOfMonth(dayOfMonth))
                  .collect(toList())
          )
          .historicalDates(
              IntStream
                  .rangeClosed(
                      startDate.getDayOfMonth(), Math.min(endDate.getDayOfMonth(),
                          clock.now().getDayOfMonth() - 1)
                  )
                  .boxed()
                  .map(dayOfMonth -> clock.now().withDayOfMonth(dayOfMonth))
                  .collect(toList())
          )
          .build();
    } else {
      throw new UnsupportedOperationException("currentDatePolicy unsupported");
    }
  }
}