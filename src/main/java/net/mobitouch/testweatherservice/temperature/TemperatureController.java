package net.mobitouch.testweatherservice.temperature;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperatures")
class TemperatureController {

  private final TemperatureFacade temperatureFacade;

  TemperatureController(final TemperatureFacade temperatureFacade) {
    this.temperatureFacade = temperatureFacade;
  }

  @GetMapping("/test")
  TemperatureInRangeReportDto testTemperatures(@RequestParam Integer fromDay, @RequestParam Integer toDay) {
    return TemperatureInRangeReportDto.of(temperatureFacade.testTemperatures(fromDay, toDay));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  ResponseEntity<String> handleClientError(IllegalArgumentException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }
}