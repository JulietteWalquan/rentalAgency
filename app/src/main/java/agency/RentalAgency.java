package agency;

import agency.exception.UnknownVehicleException;
import agency.interfaceAndAbstract.Vehicle;

import java.util.*;
import java.util.function.Predicate;

public class RentalAgency {

    private final List<Vehicle> vehicles;

    private final Map<Client, Vehicle> rentedVehicles;

    public RentalAgency() {
        this(new ArrayList<>());
    }

    public RentalAgency(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        this.rentedVehicles = new HashMap<>();
    }

    public boolean add(Vehicle vehicle) {
        if (vehicles.contains(vehicle)) {
            return false;
        }

        vehicles.add(vehicle);
        return true;
    }

    public void remove(Vehicle vehicle) throws UnknownVehicleException {
        if (!vehicles.contains(vehicle)) {
            throw new UnknownVehicleException(vehicle);
        }

        vehicles.remove(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }


    /**
     * Returns the list of vehicles of this agency that satisfy the specified criterion
     * The returned vehicles are then << filtered >> by the criterion.
     *
     * @param criterion the criterion that the selected cars must satisfy
     * @return the list of cars of this agency that satisfy the given criterion
     */
    public List<Vehicle> select(Predicate<Vehicle> criterion) {
        return getVehicles().stream().filter(criterion).toList();
    }


    /**
     * Prints the vehicles (one by line) of this agency that satisfy the specified criterion
     *
     * @param criterion the criterion that the selected cars must satisfy
     */
    public void printSelectedVehicles(Predicate<Vehicle> criterion) {
        select(criterion).forEach(System.out::println);
    }


    /**
     * Create an association between the client and the rented vehicle
     *
     * @param client  the client who will rent the vehicle
     * @param vehicle the vehicle that will be rent by the client
     * @return the price the client will pay
     * @throws UnknownVehicleException if the vehicle does not exist in the agency
     * @throws IllegalStateException   if the vehicle is already rented or the client already rent a vehicle
     */
    public double rentVehicle(Client client, Vehicle vehicle) throws UnknownVehicleException, IllegalStateException {
        if (!getVehicles().contains(vehicle)) {
            throw new UnknownVehicleException(vehicle);
        }
        if (rentedVehicles.containsValue(vehicle)) {
            throw new IllegalStateException("The vehicle is already rented");
        }
        if (rentedVehicles.containsKey(client)) {
            throw new IllegalStateException("The client already rent a vehicle");
        }

        rentedVehicles.put(client, vehicle);
        return vehicle.dailyRentalPrice();
    }


    /**
     * Returns if the client is renting a vehicle
     *
     * @param client the client to check
     * @return true if the client is renting a vehicle, false otherwise
     */
    public boolean aVehicleIsRentedBy(Client client) {
        return rentedVehicles.containsKey(client);
    }


    /**
     * Returns if the vehicle is rented
     *
     * @param v the vehicle to check
     * @return true if the vehicle is rented, false otherwise
     */
    public boolean vehicleIsRented(Vehicle v) {
        return rentedVehicles.containsValue(v);
    }


    /**
     * Removes the association between the client and the rented vehicle
     *
     * @param client the client who will return the vehicle
     */
    public void returnVehicle(Client client) {
        rentedVehicles.remove(client);
    }


    /**
     * Returns the collection of vehicles rented by the clients
     *
     * @return the collection of vehicles rented by the clients
     */
    public Collection<Vehicle> allRentedVehicles() {
        return rentedVehicles.values();
    }
}
