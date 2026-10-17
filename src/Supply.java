public class Supply {

    private String id;
    private String name;
    private int currentQuantity;
    private int minAlert;
    private String unit;

    /**
     * Constructs a new Supply item.
     *
     * @param id              unique id
     * @param name            descriptive item name
     * @param currentQuantity current available amount
     * @param minAlert        stock level triggering alert
     * @param unit            unit of measurement, like boxes, packs, bottles, etc.
     */
    public Supply(String id, String name, int currentQuantity, int minAlert, String unit) {
        this.id = id;
        this.name = name;
        this.currentQuantity = currentQuantity;
        this.minAlert = minAlert;
        this.unit = unit;
    }

    /**
     * checks if the supply level is low.
     *
     * @return true if current quantity is less than or equal to the minimum
     *         threshold
     */
    public boolean isLowStock() {
        return this.currentQuantity <= this.minAlert;
    }

    /**
     * Updates the stock quantity
     *
     * @param amountChange positive int to restock and negative to use stock
     * 
     */
    public void updateQuantity(int amountChange) {
        this.currentQuantity += amountChange;
        if (this.currentQuantity < 0) {
            this.currentQuantity = 0; // Prevents negative inventory
        }
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public int getMinAlert() {
        return minAlert;
    }

    public void setMinAlert(int minThreshold) {
        this.minAlert = minThreshold;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Supply: " + name + " (ID: " + id + ") - Quantity: " + currentQuantity + " " + unit + " [Min limit: "
                + minAlert + "]";
    }
}
