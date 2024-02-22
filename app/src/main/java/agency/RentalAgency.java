package agency;

import java.util.ArrayList;
import java.util.List;

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
}
