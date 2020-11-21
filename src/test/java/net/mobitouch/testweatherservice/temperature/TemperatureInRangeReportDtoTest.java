package net.mobitouch.testweatherservice.temperature;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureInRangeReportDtoTest {

  @Test
  void shouldCreateReport() {
    List<TemperatureDto> days = Arrays.asList(
        TemperatureDto.from(LocalDate.now().minusDays(1), 21),
        TemperatureDto.from(LocalDate.now(), 23),
        TemperatureDto.from(LocalDate.now().plusDays(1), 28)
        );

    TemperatureInRangeReportDto dto = TemperatureInRangeReportDto.of(days);

    assertEquals(1, dto.getValidDaysCount());
    assertEquals(2, dto.getInvalidDaysCount());
    assertEquals(24.0, dto.getAverage());
  }

  @Test
  void shouldNotCreateReportFromNull() {
    assertThatThrownBy(() -> TemperatureInRangeReportDto.of(null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("cannot prepare report from empty list");
  }

  @Test
  void shouldNotCreateReportFromEmptyList() {
    assertThatThrownBy(() -> TemperatureInRangeReportDto.of(Collections.emptyList()))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("cannot prepare report from empty list");
  }

}