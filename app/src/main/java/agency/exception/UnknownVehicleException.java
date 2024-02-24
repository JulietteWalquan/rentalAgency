package agency.exception;

import agency.interfaceAndAbstract.Vehicle;

public class UnknownVehicleException extends Exception {
    private final Vehicle vehicle;

    public UnknownVehicleException(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String getMessage() {
        return "Vehicle " + vehicle.toString() + " not found in the agency";
    }
}
