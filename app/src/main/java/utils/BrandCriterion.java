package utils;

import agency.abstractAndInterface.Vehicle;

import java.util.function.Predicate;

public class BrandCriterion implements Predicate<Vehicle> {
    private final String brand;

    /**
     * Create a new brand criterion with the following parameters
     *
     * @param brand the brand to match
     */
    public BrandCriterion(String brand) {
        this.brand = brand;
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param vehicle the input argument
     * @return {@code true} if the input argument matches the brand criterion,
     * otherwise {@code false}
     */
    @Override
    public boolean test(Vehicle vehicle) {
        return vehicle.getBrand().equals(brand);
    }


    /**
     * Get the brand to match
     *
     * @return a string representing the brand to match
     */
    public String getBrand() {
        return brand;
    }
}
