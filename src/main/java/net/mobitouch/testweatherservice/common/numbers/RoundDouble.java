package net.mobitouch.testweatherservice.common.numbers;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundDouble {
  public static Double round(Double source) {
    return round(source, 2);
  }

  public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = BigDecimal.valueOf(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }
}