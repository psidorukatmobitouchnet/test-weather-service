package net.mobitouch.testweatherservice.temperature;

import lombok.Value;

import java.time.LocalDate;

import static net.mobitouch.testweatherservice.temperature.ValidTemperaturesRange.MAX_VALID_TEMPERATURE;
import static net.mobitouch.testweatherservice.temperature.ValidTemperaturesRange.MIN_VALID_TEMPERATURE;

@Value
public class TemperatureDto {
  LocalDate date;
  Integer value;
  boolean valid;

  public static TemperatureDto from(LocalDate date, Integer value) {
    return new TemperatureDto(date, value);
  }

  private TemperatureDto(final LocalDate date, final Integer value) {
    this.date = date;
    this.value = value;
    this.valid =
        value.doubleValue() <= MAX_VALID_TEMPERATURE.getValue()
            && value.doubleValue() >= MIN_VALID_TEMPERATURE.getValue();
  }
}