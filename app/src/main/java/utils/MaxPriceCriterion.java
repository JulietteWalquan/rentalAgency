package utils;

import agency.abstractAndInterface.Vehicle;

import java.util.function.Predicate;

public class MaxPriceCriterion implements Predicate<Vehicle> {
    private final double maxPrice;

    /**
     * Create a new max price criterion with the following parameters
     *
     * @param maxPrice the maximum price to match
     */
    public MaxPriceCriterion(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param vehicle the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    @Override
    public boolean test(Vehicle vehicle) {
        return vehicle.dailyRentalPrice() <= maxPrice;
    }


    /**
     * Get the maximum price to match
     *
     * @return a double representing the maximum price to match
     */
    public double getMaxPrice() {
        return maxPrice;
    }
}
