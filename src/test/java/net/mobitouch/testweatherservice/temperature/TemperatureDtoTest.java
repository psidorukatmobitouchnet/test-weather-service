package net.mobitouch.testweatherservice.temperature;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class TemperatureDtoTest {

  @Test
  void shouldCreatedTemperatureDtoBeValid() {
    TemperatureDto dto = TemperatureDto.from(LocalDate.now(), 25);

    assertTrue(dto.isValid());
  }

  @Test
  void shouldCreatedTemperatureDtoBeInvalidTooHigh() {
    TemperatureDto dto = TemperatureDto.from(LocalDate.now(), 26);

    assertFalse(dto.isValid());
  }

  @Test
  void shouldCreatedTemperatureDtoBeInvalidTooLow() {
    TemperatureDto dto = TemperatureDto.from(LocalDate.now(), 21);

    assertFalse(dto.isValid());
  }
}