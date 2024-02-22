package agency;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class RentalAgency {

    private final List<Vehicle> vehicles;

    public RentalAgency() {
        this(new ArrayList<>());
    }

    public RentalAgency(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
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
}
