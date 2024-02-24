package utils;

import agency.interfaceAndAbstract.Vehicle;

import java.util.function.Predicate;

public class MaxPriceCriterion implements Predicate<Vehicle> {
    private final double maxPrice;

    public MaxPriceCriterion(double maxPrice) throws IllegalArgumentException {
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


    public double getMaxPrice() {
        return maxPrice;
    }
}
