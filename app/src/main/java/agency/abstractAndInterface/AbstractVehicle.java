package agency.abstractAndInterface;

public abstract class AbstractVehicle implements Vehicle {
    private final String brand;
    private final String model;
    private final int productionYear;

    /**
     * Create a new abstract vehicle with the following parameters
     *
     * @param brand          the brand of the car
     * @param model          the model of the car
     * @param productionYear the production year of the car
     */
    public AbstractVehicle(String brand, String model, int productionYear) {
        if (productionYear < 1900 || productionYear > 2024) {
            throw new IllegalArgumentException("Invalid production year : " + productionYear + "\n Production year must be between 1900 and 2024");
        }

        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
    }

    /**
     * Get the brand of the vehicle
     *
     * @return a string representing the brand of the vehicle
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Get the model of the vehicle
     *
     * @return a string representing the model of the vehicle
     */
    public String getModel() {
        return model;
    }

    /**
     * Get the production year of the vehicle
     *
     * @return an integer representing the production year of the vehicle
     */
    public int getProductionYear() {
        return productionYear;
    }

    /**
     * Calculate the daily rental price of the vehicle
     * This method is abstract and must be implemented by the subclasses
     *
     * @return a double representing the daily rental price of the vehicle
     */
    public abstract double dailyRentalPrice();
}
