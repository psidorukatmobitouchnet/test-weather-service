package net.mobitouch.testweatherservice.common.date;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder(toBuilder = true)
@Getter
public class DateRange {
  private final LocalDate startDate;
  private final LocalDate endDate;
  private final List<LocalDate> historicalDates;
  private final List<LocalDate> forecastDates;
}