package net.mobitouch.testweatherservice.temperature;

import net.mobitouch.testweatherservice.common.date.DateRange;
import net.mobitouch.testweatherservice.common.date.DateRangeFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TemperatureFacade {

  private final DateRangeFactory dateRangeFactory;

  public TemperatureFacade(final DateRangeFactory dateRangeFactory) {
    this.dateRangeFactory = dateRangeFactory;
  }

  void testTemperatures(final Integer fromDay, final Integer toDay) {
    DateRange dateRange = dateRangeFactory.of(fromDay, toDay);
    List<LocalDate> historicalDates = dateRange.getHistoricalDates();
    List<LocalDate> forecastDates = dateRange.getForecastDates();

  }
}