package net.mobitouch.testweatherservice.common.date;

import org.springframework.stereotype.Component;

@Component
public class CurrentDatePolicy {
  public boolean forecastToday() {
    return true;
  }
}