package net.mobitouch.testweatherservice.weather;

import net.mobitouch.testweatherservice.temperature.TemperatureDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Repository
public class WeatherRepository {

  public TemperatureDto findByDate(final LocalDate date) {
    return TemperatureDto.from(date, temperatures.get(date.getDayOfMonth()));
  }

  private final Map<Integer, Integer> temperatures = new HashMap<>() {{
    put(1, 23);
    put(2, 19);
    put(3, 20);
    put(4, 22);
    put(5, 26);
    put(6, 24);
    put(7, 30);
    put(8, 25);
    put(9, 18);
    put(10, 19);
    put(11, 17);
    put(12, 16);
    put(13, 20);
    put(14, 16);
    put(15, 10);
    put(16, 19);
    put(17, 24);
    put(18, 23);
    put(19, 22);
    put(20, 22);
    put(21, 25);
    put(22, 21);
    put(23, 22);
    put(24, 19);
    put(25, 20);
    put(26, 25);
    put(27, 24);
    put(28, 22);
    put(29, 26);
    put(30, 23);
    put(31, 19);
  }};
}