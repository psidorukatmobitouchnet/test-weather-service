package net.mobitouch.testweatherservice.temperature;

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
  String testTemperatures(@RequestParam Integer fromDay, @RequestParam Integer toDay) {
    temperatureFacade.testTemperatures(fromDay, toDay);
    throw new UnsupportedOperationException("");
  }
}