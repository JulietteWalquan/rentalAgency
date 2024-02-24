package agency;

import agency.exception.UnknownVehicleException;
import agency.interfaceAndAbstract.Vehicle;
import org.junit.jupiter.api.*;
import utils.BrandCriterion;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("agency")
class RentalAgencyTest {

    static RentalAgency agencyWithVehicles;
    static Car car1;
    static Car car2;
    static Car car3;
    static Motorbike motorbike1;
    static Motorbike motorbike2;
    static Motorbike motorbike3;

    @BeforeAll
    static void setUpAll() {
        // Create cars and motorbikes
        car1 = new Car("Toyota", "Corolla", 2020, 5);
        car2 = new Car("Toyota", "Yaris", 2022, 5);
        car3 = new Car("Renault", "Clio", 2019, 5);
        motorbike1 = new Motorbike("Yamaha", "MT-07", 2021, 890);
        motorbike2 = new Motorbike("Honda", "CBR650R", 2020, 750);
        motorbike3 = new Motorbike("Yamaha", "MT-09", 2023, 890);
    }

    @BeforeEach
    void setUp() {
        // Create agency with vehicles
        agencyWithVehicles = new RentalAgency(new ArrayList<>(List.of(car1, car2, car3, motorbike1, motorbike2, motorbike3)));
    }

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
            Car carToRemove = mock(Car.class);
            when(carToRemove.toString()).thenReturn("Car : Toyota Corolla 2020 (5 seats) : 200.0€/day");
            UnknownVehicleException exception = assertThrows(UnknownVehicleException.class, () -> agency.remove(carToRemove));

            // Then
            assertEquals("Vehicle Car : Toyota Corolla 2020 (5 seats) : 200.0€/day not found in the agency", exception.getMessage());
        }
    }


    @Nested
    class Criterion {

        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

        @BeforeEach
        void setUp() {
            // Redirect output stream
            System.setOut(new PrintStream(outputStreamCaptor));
        }

        @AfterEach
        public void tearDown() {
            System.setOut(standardOut);
        }

        @Test
        void test_select_car() {
            // When
            List<Vehicle> vehicles = agencyWithVehicles.select(new BrandCriterion("Renault"));

            // Then
            assertThat(vehicles)
                    .isNotEmpty()
                    .hasSize(1)
                    .contains(car3)
                    .doesNotContain(motorbike1, car1);
        }

        @Test
        void test_select_motorbike() {
            // When
            List<Vehicle> vehicles = agencyWithVehicles.select(new BrandCriterion("Yamaha"));

            // Then
            assertThat(vehicles)
                    .isNotEmpty()
                    .hasSize(2)
                    .contains(motorbike1, motorbike3)
                    .doesNotContain(motorbike2, car2);
        }

        @Test
        void test_print_selected_cars() {
            // When
            agencyWithVehicles.printSelectedVehicles(new BrandCriterion("Toyota"));

            // Then
            assertEquals("Car : Toyota Corolla 2020 (5 seats) : 200.0€/day" +
                            System.lineSeparator() +
                            "Car : Toyota Yaris 2022 (5 seats) : 200.0€/day",
                    outputStreamCaptor.toString().trim());
        }

        @Test
        void test_print_selected_motorbikes() {
            // When
            agencyWithVehicles.printSelectedVehicles(new BrandCriterion("Honda"));

            // Then
            assertEquals("Motorbike : Honda CBR650R 2020 (750cm3) : 187.5€/day",
                    outputStreamCaptor.toString().trim());
        }
    }


    @Nested
    class RentVehicle {
        @Test
        void test_rent_vehicle() throws UnknownVehicleException {
            // Given
            Client client = new Client("Doe", "John", 2000);

            // When
            double price = agencyWithVehicles.rentVehicle(client, car1);


            // Then
            assertThat(agencyWithVehicles.allRentedVehicles())
                    .isNotNull()
                    .containsExactly(car1);
            assertEquals(200.0, price);
            assertTrue(agencyWithVehicles.aVehicleIsRentedBy(client));
            assertTrue(agencyWithVehicles.vehicleIsRented(car1));
            assertFalse(agencyWithVehicles.vehicleIsRented(car2));
        }

        @Test
        void test_rent_unknown_vehicle() {
            // Given
            Client client = new Client("Doe", "John", 2000);
            Car car = mock(Car.class);
            when(car.toString()).thenReturn("Car : Ford Fiesta 2019 (5 seats) : 200.0€/day");

            // When
            UnknownVehicleException exception = assertThrows(UnknownVehicleException.class, () -> agencyWithVehicles.rentVehicle(client, car));

            // Then
            assertEquals("Vehicle Car : Ford Fiesta 2019 (5 seats) : 200.0€/day not found in the agency", exception.getMessage());
        }

        @Test
        void test_rent_already_rented_vehicle() throws UnknownVehicleException {
            // Given
            Client client1 = new Client("Doe", "John", 2000);
            Client client2 = new Client("Doe", "Jane", 2000);
            agencyWithVehicles.rentVehicle(client1, car1);

            // When
            IllegalStateException exception = assertThrows(IllegalStateException.class, () -> agencyWithVehicles.rentVehicle(client2, car1));

            // Then
            assertEquals("The vehicle is already rented", exception.getMessage());
        }

        @Test
        void test_rent_client_already_rent_vehicle() throws UnknownVehicleException {
            // Given
            Client client = new Client("Doe", "John", 2000);
            agencyWithVehicles.rentVehicle(client, car1);

            // When
            IllegalStateException exception = assertThrows(IllegalStateException.class, () -> agencyWithVehicles.rentVehicle(client, car2));

            // Then
            assertEquals("The client already rent a vehicle", exception.getMessage());
        }

        @Test
        void test_return_vehicle() throws UnknownVehicleException {
            // Given
            Client client = new Client("Doe", "John", 2000);
            agencyWithVehicles.rentVehicle(client, car1);

            // When
            agencyWithVehicles.returnVehicle(client);

            // Then
            assertFalse(agencyWithVehicles.aVehicleIsRentedBy(client));
        }
    }
}