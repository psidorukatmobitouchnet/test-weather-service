package net.mobitouch.testweatherservice.temperature;

import io.restassured.config.EncoderConfig;
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig;
import net.mobitouch.testweatherservice.TestWeatherServiceApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = TestWeatherServiceApplication.class)
class TemperatureControllerTest {

  @Autowired
  private MockMvc mvc;

  private RestAssuredMockMvcConfig restAssuredConfig;

  @BeforeEach
  public void setup() {
    restAssuredConfig = RestAssuredMockMvcConfig.config()
        .encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
  }

  @Test
  void testTemperatures() {
    var result = given().mockMvc(mvc)
        .param("fromDay", 3)
        .param("toDay", 12)
        .get("temperatures/test")
        .then()
        .assertThat()
        .statusCode(200)
        .extract()
        .as(TemperatureInRangeReportDto.class);
    assertThat(result.getDays()).hasSize(10);
    assertThat(result.getValidDaysCount()).isEqualTo(3);
    assertThat(result.getInvalidDaysCount()).isEqualTo(7);
    assertThat(result.getAverage()).isEqualTo(21.7d);
  }

  @Test
  void testTemperatures_withIncorrectDays() {
    given().mockMvc(mvc)
        .param("fromDay", 25)
        .param("toDay", 33)
        .get("temperatures/test")
        .then()
        .assertThat()
        .statusCode(400);
  }

  @Test
  void testTemperatures_withToDaysOnly() {
    given().mockMvc(mvc)
        .param("toDay", 5)
        .get("temperatures/test")
        .then()
        .assertThat()
        .statusCode(400);
  }

  @Test
  void testTemperatures_withFromDaysOnly() {
    given().mockMvc(mvc)
        .param("fromDay", 5)
        .get("temperatures/test")
        .then()
        .assertThat()
        .statusCode(400);
  }

  @Test
  void testTemperatures_noParams() {
    given().mockMvc(mvc)
        .get("temperatures/test")
        .then()
        .assertThat()
        .statusCode(400);
  }
}