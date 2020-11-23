package net.mobitouch.testweatherservice.temperature;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static net.mobitouch.testweatherservice.common.numbers.RoundDouble.round;

@Value
@AllArgsConstructor
class TemperatureInRangeReportDto {
  List<TemperatureDto> days;
  List<TemperatureDto> validDays;
  List<TemperatureDto> invalidDays;
  int validDaysCount;
  int invalidDaysCount;
  Double average;

  public static TemperatureInRangeReportDto of(List<TemperatureDto> days) {
    return new TemperatureInRangeReportDto(days);
  }

  private TemperatureInRangeReportDto(final List<TemperatureDto> days) {
    if (days == null || days.isEmpty()) throw new IllegalArgumentException("cannot prepare report from empty list");
    this.days = days;
    this.validDays = days.stream().filter(TemperatureDto::isValid).collect(toList());
    this.invalidDays = days.stream().filter(e -> !e.isValid()).collect(toList());
    this.validDaysCount = this.validDays.size();
    this.invalidDaysCount = this.invalidDays.size();
    this.average = round((double) days.stream().mapToInt(TemperatureDto::getValue).sum() / (double) days.size());
  }
}