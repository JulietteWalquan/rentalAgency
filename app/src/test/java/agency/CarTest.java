package agency;

import org.junit.jupiter.api.DisplayName;
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
class CarTest {

    @Nested
    class CreateCar {
        @DisplayName("Create a car with normal values")
        @Test
        void test_create_normal_car() {
            // Given
            Car car = new Car("Toyota", "Corolla", 2020, 5);

            try (MockedStatic<TimeProvider> timeProvider = mockStatic(TimeProvider.class)) {
                timeProvider.when(TimeProvider::currentYearValue).thenReturn(2024);

                // Then
                assertThat(car.getBrand())
                        .isEqualTo("Toyota")
                        .isNotEqualTo("Corolla");
                assertThat(car.getModel())
                        .isEqualTo("Corolla")
                        .isNotEqualTo("Toyota");
                assertThat(car.getProductionYear())
                        .isEqualTo(2020)
                        .isGreaterThan(1900)
                        .isLessThanOrEqualTo(TimeProvider.currentYearValue());
            }
        }

        @DisplayName("Create a car with a greater production year than authorized")
        @Test
        void test_create_car_with_greater_production_year() {
            // Given
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Car("Toyota", "Corolla", 2025, 5));

            // Then
            assertThat(exception.getMessage())
                    .isEqualTo("Invalid production year : 2025\n Production year must be between 1900 and 2024");
        }

        @DisplayName("Create a car with a lower production year than authorized")
        @Test
        void test_create_car_with_lower_production_year() {
            // Given
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Car("Toyota", "Corolla", 1899, 5));

            // Then
            assertThat(exception.getMessage())
                    .isEqualTo("Invalid production year : 1899\n Production year must be between 1900 and 2024");
        }

        @DisplayName("Create a car with zero seats")
        @Test
        void test_create_car_with_zero_seats() {
            // Given
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Car("Toyota", "Corolla", 2020, 0));

            // Then
            assertThat(exception.getMessage())
                    .isEqualTo("Invalid number of seats : 0\n Number of seats must be greater than 0");
        }
    }


    @Nested
    class DailyRentalPrice {
        @DisplayName("Test for the daily rental price for a new car")
        @Test
        void test_daily_rental_price_new_car() {
            // Given
            Car car = new Car("Toyota", "Corolla", 2020, 5);

            // When
            double price = car.dailyRentalPrice();

            // Then
            assertEquals(200, price);
        }

        @DisplayName("Test for the daily rental price for an old car")
        @Test
        void test_daily_rental_price_old_car() {
            // Given
            Car car = new Car("Toyota", "Corolla", 2015, 5);

            // When
            double price = car.dailyRentalPrice();

            // Then
            assertEquals(100, price);
        }
    }


    @Nested
    class ToString {
        @DisplayName("Test for the toString method when a car has more than 1 seat")
        @Test
        void test_toString_many_seats() {
            // Given
            Car car = new Car("Toyota", "Corolla", 2020, 5);

            // When
            String carString = car.toString();

            // Then
            assertThat(carString)
                    .startsWith("Car : ")
                    .contains("Toyota ")
                    .contains("Corolla ")
                    .contains("2020 ")
                    .contains("(5 seats) ")
                    .endsWith("200.0€/day");
        }

        @DisplayName("Test for the toString method when a car has only 1 seat")
        @Test
        void test_toString_one_seat() {
            // Given
            Car car = new Car("Toyota", "Corolla", 2020, 1);

            // When
            String carString = car.toString();

            // Then
            assertThat(carString)
                    .startsWith("Car : ")
                    .contains("Toyota ")
                    .contains("Corolla ")
                    .contains("2020 ")
                    .contains("(1 seat) ")
                    .endsWith("40.0€/day");
        }
    }


    @DisplayName("Test for the equals method")
    @Test
    void test_equals() {
        // Given
        Car car1 = new Car("Toyota", "Corolla", 2020, 5);
        Car car2 = new Car("Toyota", "Corolla", 2020, 5);
        Car car3 = new Car("Toyota", "Corolla", 2020, 4);
        Car car4 = new Car("Citroën", "C3", 2019, 5);
        Motorbike motorbike = mock(Motorbike.class);

        // Then
        assertTrue(car1.equals(car2));
        assertTrue(car1.equals(car3));
        assertFalse(car1.equals(car4));
        assertFalse(car1.equals(motorbike));
    }
}