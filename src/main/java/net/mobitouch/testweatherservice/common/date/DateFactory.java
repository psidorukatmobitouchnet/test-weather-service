package net.mobitouch.testweatherservice.common.date;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateFactory {
  public LocalDate now() {
    return LocalDate.now();
  }
}