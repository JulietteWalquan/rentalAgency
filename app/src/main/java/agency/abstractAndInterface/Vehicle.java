package agency.abstractAndInterface;

public interface Vehicle {
    /**
     * Get the brand of the vehicle
     *
     * @return a string representing the brand of the vehicle
     */
    String getBrand();

    /**
     * Get the model of the vehicle
     *
     * @return a string representing the model of the vehicle
     */
    String getModel();

    /**
     * Get the production year of the vehicle
     *
     * @return an integer representing the production year of the vehicle
     */
    int getProductionYear();

    /**
     * Calculate the daily rental price of the vehicle
     *
     * @return a double representing the daily rental price of the vehicle
     */
    double dailyRentalPrice();
}
