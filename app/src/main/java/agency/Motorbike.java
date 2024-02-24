package agency;

import agency.abstractAndInterface.AbstractVehicle;

public class Motorbike extends AbstractVehicle {
    private final int cylinderCapacity;

    /**
     * Create a new motorbike with the following parameters
     *
     * @param brand            the brand of the motorbike
     * @param model            the model of the motorbike
     * @param productionYear   the production year of the motorbike
     * @param cylinderCapacity the cylinder capacity of the motorbike
     * @throws IllegalArgumentException if the cylinder capacity is less than 50
     */
    public Motorbike(String brand, String model, int productionYear, int cylinderCapacity) throws IllegalArgumentException {
        super(brand, model, productionYear);

        if (cylinderCapacity < 50) {
            throw new IllegalArgumentException("Invalid cylinder capacity : " + cylinderCapacity + "\n Cylinder capacity must be greater than 50");
        }

        this.cylinderCapacity = cylinderCapacity;
    }

    @Override
    public double dailyRentalPrice() {
        return 0.25 * cylinderCapacity;
    }

    @Override
    public String toString() {
        return "Motorbike : " + getBrand() + " " + getModel() + " " + getProductionYear() + " (" + cylinderCapacity + "cm3) : " + dailyRentalPrice() + "€/day";
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == Motorbike.class) {
            return this.getBrand().equals(((Motorbike) o).getBrand()) && this.getModel().equals(((Motorbike) o).getModel()) && this.getProductionYear() == ((Motorbike) o).getProductionYear();
        }
        return false;
    }
}
