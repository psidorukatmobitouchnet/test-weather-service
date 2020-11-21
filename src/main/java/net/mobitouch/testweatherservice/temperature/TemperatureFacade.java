package net.mobitouch.testweatherservice.temperature;

import net.mobitouch.testweatherservice.common.date.DateRange;
import net.mobitouch.testweatherservice.common.date.DateRangeFactory;
import net.mobitouch.testweatherservice.weather.WeatherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class TemperatureFacade {

  private final WeatherRepository weatherRepository;
  private final DateRangeFactory dateRangeFactory;

  public TemperatureFacade(final WeatherRepository weatherRepository, final DateRangeFactory dateRangeFactory) {
    this.weatherRepository = weatherRepository;
    this.dateRangeFactory = dateRangeFactory;
  }

  List<TemperatureDto> testTemperatures(final Integer fromDay, final Integer toDay) {
    DateRange dateRange = dateRangeFactory.of(fromDay, toDay);
    List<LocalDate> historicalDates = dateRange.getHistoricalDates();
    List<LocalDate> forecastDates = dateRange.getForecastDates();
    return Stream.concat(
        historicalDates
            .stream()
            .map(weatherRepository::findByDate)
        , forecastDates
            .stream()
            .map(weatherRepository::findByDate))
        .collect(toList());
  }
}