import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GestorIO {

    private final Scanner SCANNER;
    private final String MENU_SEPARATOR = "----------------------------------------";

    public GestorIO() {
        this.SCANNER = new Scanner(System.in);
    }

    /**
     * Displays the main menu
     */
    public void printMenu() {
        System.out.println("\n" + MENU_SEPARATOR);
        System.out.println("CARE HOME INVENTORY");
        System.out.println(MENU_SEPARATOR);
        System.out.println("1. View all inventory");
        System.out.println("2. Add new supply");
        System.out.println("3. Update supply quantity (use / restock)");
        System.out.println("0. Exit");
        System.out.println(MENU_SEPARATOR);
        System.out.print("Select an option: ");
    }

    /**
     * Reads and validates the user's menu choice
     * 
     * @return option number selected, or -1 if input is invalid
     */
    public int readOption() {
        try {
            int option = SCANNER.nextInt();
            SCANNER.nextLine();
            return option;
        } catch (InputMismatchException e) {
            SCANNER.nextLine();
            return -1;
        }
    }

    /**
     * To read textual input
     * 
     * @param text the message displayed to the user
     * @return String entered by the user
     */
    public String readString(String text) {
        System.out.print(text);
        return SCANNER.nextLine().trim();
    }

    /**
     * To read numeric quantity inputs.
     * Continuously prompts until a valid is entered.
     *
     * @param text the message displayed to the user
     * @return valid int entered by the user
     */
    public int readInt(String text) {
        while (true) {
            System.out.print(text);
            try {
                int value = SCANNER.nextInt();
                SCANNER.nextLine(); 
                return value;
            } catch (InputMismatchException e) {
                SCANNER.nextLine(); 
                System.out.println("Invalid input, please enter a valid number");
            }
        }
    }

    /**
     * Displays all supplies in the inventory
     *
     * @param supplies the list of Supply 
     */
    public void printAllSupplies(List<Supply> supplies) {
        System.out.println("\n" + MENU_SEPARATOR);
        System.out.println("CURRENT INVENTORY");
        System.out.println(MENU_SEPARATOR);

        if (supplies == null || supplies.isEmpty()) {
            System.out.println("No supplies in the inventory");
        } else {
            for (Supply supply : supplies) {
                System.out.println(supply); 
            }
        }

        System.out.println(MENU_SEPARATOR);
    }

    /**
     * To enter details for a new Supply
     * 
     * @return a new Supply instance
     */
    public Supply newSupply() {
        System.out.println("\n" + MENU_SEPARATOR);
        System.out.println("ADD NEW SUPPLY");
        System.out.println(MENU_SEPARATOR);
        String id = readString("Enter supply ID: ");
        String name = readString("Enter supply name: ");
        int quantity = readInt("Enter initial quantity: ");
        int minThreshold = readInt("Enter minimum stock: ");
        String unit = readString("Enter Unit (like boxes, units, bottles...): ");

        return new Supply(id, name, quantity, minThreshold, unit);
    }

    /**
     * For a Supply ID and the quantity to update.
     * 
     * @return an array where index 0 is the Supply ID and index 1 is the amount change
     */
    public Object[] forStockUpdate() {
        System.out.println("\n" + MENU_SEPARATOR);
        System.out.println("UPDATE SUPPLY STOCK");
        System.out.println(MENU_SEPARATOR);
        String id = readString("Enter Supply ID to update: ");
        System.out.println("Use positive numbers to restock and negative to use");
        int amountChange = readInt("Enter quantity change: ");

        return new Object[]{id, amountChange};
    }

    /**
     * Checks and displays a warning if any supplies are low on stock
     * 
     * @param lowStockSupplies list of items currently equal to or below min stock
     */
    public void printLowStockAlert(List<Supply> lowStockSupplies) {
        if (lowStockSupplies != null && !lowStockSupplies.isEmpty()) {
            System.out.println("\n[WARNING] LOW STOCK ALERT!");
            System.out.println("The following " + lowStockSupplies.size() + " item(s) require restocking:");
            
            for (Supply supply : lowStockSupplies) {
                System.out.println("   * " + supply.getName() + 
                                   " (ID: " + supply.getId() + ") - Current: " + 
                                   supply.getCurrentQuantity() + " " + supply.getUnit() + 
                                   " [Min limit: " + supply.getMinAlert() + "]");
            }
            System.out.println(MENU_SEPARATOR);
        }
    }

}

