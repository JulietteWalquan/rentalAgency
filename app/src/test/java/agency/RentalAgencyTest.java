package agency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("agency")
class RentalAgencyTest {

    @Nested
    class CreateRentalAgency {
        @Test
        void test_default_constructor() {
            // Given
            RentalAgency agency = new RentalAgency();

            // Then
            assertNotNull(agency.getVehicles());
        }

        @Test
        void test_constructor_with_vehicles() {
            // Given
            Car car = mock(Car.class);
            Motorbike motorbike = mock(Motorbike.class);
            RentalAgency agency = new RentalAgency(List.of(car, motorbike));

            // Then
            assertThat(agency.getVehicles())
                    .isNotNull()
                    .containsExactly(car, motorbike);
        }
    }


    @Nested
    class AddVehicle {
        @Test
        void test_add_vehicle() {
            // Given
            Car car = mock(Car.class);
            RentalAgency agency = new RentalAgency();

            // When
            boolean result = agency.add(car);

            // Then
            assertTrue(result);
            assertThat(agency.getVehicles())
                    .isNotNull()
                    .containsExactly(car);
        }

        @Test
        void test_add_existing_vehicle() {
            // Given
            Motorbike motorbike = mock(Motorbike.class);
            RentalAgency agency = new RentalAgency(List.of(motorbike));

            // When
            boolean result = agency.add(motorbike);

            // Then
            assertFalse(result);
            assertThat(agency.getVehicles())
                    .isNotNull()
                    .containsExactly(motorbike);
        }
    }


    @Nested
    class RemoveVehicle {
        private RentalAgency agency;
        private Car car;
        private Motorbike motorbike;

        @BeforeEach
        void setUp() {
            car = mock(Car.class);
            motorbike = mock(Motorbike.class);
            agency = new RentalAgency(new ArrayList<>(List.of(car, motorbike)));
        }

        @Test
        void test_remove_vehicle() throws UnknownVehicleException {
            // When
            agency.remove(car);

            // Then
            assertThat(agency.getVehicles())
                    .isNotNull()
                    .containsExactly(motorbike);
        }

        @Test
        void test_remove_unknown_vehicle() {
            // When
            Car car2 = mock(Car.class);
            when(car2.toString()).thenReturn("Car : Toyota Corolla 2020 (5 seats) : 200€/day");
            UnknownVehicleException exception = assertThrows(UnknownVehicleException.class, () -> agency.remove(car2));

            // Then
            assertEquals("Vehicle Car : Toyota Corolla 2020 (5 seats) : 200€/day not found in the agency", exception.getMessage());
        }
    }
}