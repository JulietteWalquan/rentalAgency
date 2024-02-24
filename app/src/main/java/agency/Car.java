package agency;

import agency.interfaceAndAbstract.AbstractVehicle;
import utils.TimeProvider;

public class Car extends AbstractVehicle {
    private final int numberOfSeats;

    /**
     * Create a new car
     *
     * @param brand          the brand of the car
     * @param model          the model of the car
     * @param productionYear the production year of the car
     * @param numberOfSeats  the number of seats of the car
     * @throws IllegalArgumentException if the production year is not between 1900 and 2024 or if the number of seats is less than 1
     */
    public Car(String brand, String model, int productionYear, int numberOfSeats) throws IllegalArgumentException {
        super(brand, model, productionYear);

        if (numberOfSeats < 1) {
            throw new IllegalArgumentException("Invalid number of seats : " + numberOfSeats + "\n Number of seats must be greater than 0");
        }

        this.numberOfSeats = numberOfSeats;
    }

    /**
     * Check if the car is new
     *
     * @return true if the car is less than 5 years old, false otherwise
     */
    public boolean isNew() {
        return TimeProvider.currentYearValue() - getProductionYear() < 5;
    }

    public double dailyRentalPrice() {
        double price;

        if (isNew()) {
            price = 40 * numberOfSeats;
        } else {
            price = 20 * numberOfSeats;
        }

        return price;
    }

    @Override
    public String toString() {
        String seats = (numberOfSeats == 1) ? " seat" : " seats";
        return "Car : " + getBrand() + " " + getModel() + " " + getProductionYear() + " (" + numberOfSeats + seats + ") : " + dailyRentalPrice() + "€/day";
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == Car.class) {
            return this.getBrand().equals(((Car) o).getBrand()) && this.getModel().equals(((Car) o).getModel()) && this.getProductionYear() == ((Car) o).getProductionYear();
        }
        return false;
    }
}
