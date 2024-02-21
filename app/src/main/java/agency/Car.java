package agency;

import util.TimeProvider;

public class Car implements Vehicle {
    private final String brand;
    private final String model;
    private final int productionYear;
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
        if (productionYear < 1900 || productionYear > 2024) {
            throw new IllegalArgumentException("Invalid production year : " + productionYear + "\n Production year must be between 1900 and 2024");
        }
        if (numberOfSeats < 1) {
            throw new IllegalArgumentException("Invalid number of seats : " + numberOfSeats + "\n Number of seats must be greater than 0");
        }

        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.numberOfSeats = numberOfSeats;
    }

    /**
     * Check if the car is new
     *
     * @return true if the car is less than 5 years old, false otherwise
     */
    public boolean isNew() {
        return TimeProvider.currentYearValue() - productionYear < 5;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getProductionYear() {
        return productionYear;
    }

    @Override
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
        return "Car : " + brand + " " + model + " " + productionYear + " (" + numberOfSeats + seats + ") : " + dailyRentalPrice() + "€/day";
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == Car.class) {
            return this.brand.equals(((Car) o).brand) && this.model.equals(((Car) o).model) && this.productionYear == ((Car) o).productionYear;
        }
        return false;
    }
}
