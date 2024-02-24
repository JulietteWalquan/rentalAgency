package agency.exception;

import agency.abstractAndInterface.Vehicle;

public class UnknownVehicleException extends Exception {
    private final Vehicle vehicle;

    public UnknownVehicleException(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Returns the message of the exception if the vehicle doesn't in the agency
     * @return the message of the exception
     */
    @Override
    public String getMessage() {
        return "Vehicle " + vehicle.toString() + " not found in the agency";
    }
}
