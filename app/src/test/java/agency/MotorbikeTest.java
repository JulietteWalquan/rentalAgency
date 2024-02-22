package agency;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import utils.TimeProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

@Tag("agency")
class MotorbikeTest {

    @Nested
    class CreateMotorbike {
        @Test
        void test_create_normal_motorbike() {
            // Given
            Motorbike motorbike = new Motorbike("Yamaha", "XSR900 GP", 2024, 890);
            try (MockedStatic<TimeProvider> timeProvider = mockStatic(TimeProvider.class)) {
                timeProvider.when(TimeProvider::currentYearValue).thenReturn(2024);

                // Then
                assertThat(motorbike.getBrand())
                        .isEqualTo("Yamaha")
                        .isNotEqualTo("Suzuki");
                assertThat(motorbike.getModel())
                        .isEqualTo("XSR900 GP")
                        .isNotEqualTo("XSR900");
                assertThat(motorbike.getProductionYear())
                        .isEqualTo(2024)
                        .isGreaterThan(1900)
                        .isLessThanOrEqualTo(TimeProvider.currentYearValue());
            }
        }

        @Test
        void test_create_motorbike_with_greater_production_year() {
            // Given
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Motorbike("Yamaha", "XSR900 GP", 2025, 890));

            // Then
            assertThat(exception.getMessage())
                    .isEqualTo("Invalid production year : 2025\n Production year must be between 1900 and 2024");
        }

        @Test
        void test_create_motorbike_with_lower_production_year() {
            // Given
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Motorbike("Yamaha", "XSR900 GP", 1899, 890));

            // Then
            assertThat(exception.getMessage())
                    .isEqualTo("Invalid production year : 1899\n Production year must be between 1900 and 2024");
        }

        @Test
        void test_create_motorbike_with_less_cylinder_capacity() {
            // Given
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Motorbike("Yamaha", "XSR900 GP", 2024, 20));

            // Then
            assertThat(exception.getMessage())
                    .isEqualTo("Invalid cylinder capacity : 20\n Cylinder capacity must be greater than 50");
        }
    }


    @Test
    void test_daily_rental_price_motorbike() {
        // Given
        Motorbike motorbike = new Motorbike("Yamaha", "XSR900 GP", 2024, 890);

        // When
        double price = motorbike.dailyRentalPrice();

        // Then
        assertEquals(222.5, price);
    }


    @Test
    void test_toString_many_seats() {
        // Given
        Motorbike motorbike = new Motorbike("Yamaha", "XSR900 GP", 2024, 890);

        // When
        String motorbikeString = motorbike.toString();

        // Then
        assertThat(motorbikeString)
                .startsWith("Motorbike : ")
                .contains("Yamaha ")
                .contains("XSR900 GP ")
                .contains("2024 ")
                .contains("(890cm3) ")
                .endsWith("222.5€/day");
    }


    @Test
    void test_equals() {
        // Given
        Motorbike motorbike1 = new Motorbike("Yamaha", "XSR900 GP", 2024, 890);
        Motorbike motorbike2 = new Motorbike("Yamaha", "XSR900 GP", 2024, 890);
        Motorbike motorbike3 = new Motorbike("Yamaha", "XSR900 GP", 2024, 790);
        Motorbike motorbike4 = new Motorbike("Suzuki", "GSX-S950", 2019, 890);
        Car car = mock(Car.class);

        // Then
        assertTrue(motorbike1.equals(motorbike2));
        assertTrue(motorbike1.equals(motorbike3));
        assertFalse(motorbike1.equals(motorbike4));
        assertFalse(motorbike1.equals(car));
    }
}