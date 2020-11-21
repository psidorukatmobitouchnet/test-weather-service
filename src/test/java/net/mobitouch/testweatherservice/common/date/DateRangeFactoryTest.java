package net.mobitouch.testweatherservice.common.date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DateRangeFactoryTest {

  private static final LocalDate NOW = LocalDate.of(2000, 1, 15);

  @InjectMocks
  private DateRangeFactory factory;

  @Mock
  private CurrentDatePolicy currentDatePolicy;

  @Mock
  private DateFactory clock;

  @Test
  void shouldCreateDateRange() {
    initMocks();
    DateRange dateRange = factory.of(10, 20);

    assertThat(dateRange.getStartDate())
        .isEqualTo(clock.now().withDayOfMonth(10));
    assertThat(dateRange.getEndDate())
        .isEqualTo(clock.now().withDayOfMonth(20));
    assertThat(dateRange.getHistoricalDates())
        .contains(clock.now().withDayOfMonth(10))
        .contains(clock.now().withDayOfMonth(12))
        .contains(clock.now().withDayOfMonth(14));
    assertThat(dateRange.getForecastDates())
        .contains(clock.now().withDayOfMonth(15))
        .contains(clock.now().withDayOfMonth(18))
        .contains(clock.now().withDayOfMonth(20));
  }

  @Test
  void shouldNotCreateDateRange_toDayBeforeFromDay() {
    assertThatThrownBy(() -> factory.of(2, 1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("startDate cannot be set after endDate in date range");
  }

  @Test
  void shouldNotCreateDateRange_toDayNegative() {
    assertThatThrownBy(() -> factory.of(1, -1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("both days must be positive values");
  }

  @Test
  void shouldNotCreateDateRange_fromDayNegative() {
    assertThatThrownBy(() -> factory.of(-1, 1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("both days must be positive values");
  }

  private void initMocks() {
    when(currentDatePolicy.forecastToday()).thenReturn(true);
    when(clock.now()).thenReturn(NOW);
  }
}