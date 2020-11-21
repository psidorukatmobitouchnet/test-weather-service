package net.mobitouch.testweatherservice.common.date;

import org.springframework.stereotype.Component;

@Component
public class CurrentDatePolicy {
  //this is preparation for using external weather services
  //that shares different url addresses for historical & forecast data

  //the current day is considered as the forecast day
  public boolean forecastToday() {
    return true;
  }
}