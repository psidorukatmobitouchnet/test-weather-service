package net.mobitouch.testweatherservice.temperature;

import lombok.Getter;

@Getter
enum ValidTemperaturesRange {
  MIN_VALID_TEMPERATURE(21.5d),
  MAX_VALID_TEMPERATURE(25d);

  public final double value;

  ValidTemperaturesRange(final double value) {
    this.value = value;
  }
}