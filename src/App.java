import java.util.ArrayList;
import java.util.List;

public class App {
    private static List<Supply> supplies;
    static GestorCSV gestorCsv = new GestorCSV();

    public static void main(String[] args) throws Exception {

        GestorIO gestorIO = new GestorIO();
        boolean running = true;

        supplies = gestorCsv.loadSupplies();

        while (running) {
            gestorIO.printMenu();
            int option = gestorIO.readOption();

            switch (option) {
                case 1:
                    gestorIO.printAllSupplies(supplies);
                    break;
                case 2:
                    Supply newSupply = gestorIO.newSupply();
                    addSupply(newSupply);
                    System.out.println("Supply added successfully!");
                    break;
                case 3:
                    Object[] updateInfo = gestorIO.forStockUpdate();
                    String supplyId = (String) updateInfo[0];
                    int amount = (int) updateInfo[1];

                    boolean success = updateStock(supplyId, amount);

                    if (success) {
                        System.out.println("Stock successfully updated");
                    } else {
                        System.out.println("Supply ID '" + supplyId + "' not found");
                    }
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting... App closed!");
                    break;
                default:
                    System.out.println("Invalid option. Please write one of the available options");
            }

            gestorIO.printLowStockAlert(getLowStockAlerts());
        }

    }

    /**
     * filter supplies running low stock
     */
    public static List<Supply> getLowStockAlerts() {
        List<Supply> lowStockList = new ArrayList<>();
        for (Supply supply : supplies) {
            if (supply.isLowStock()) {
                lowStockList.add(supply);
            }
        }
        return lowStockList;
    }

    /**
     * Update stock and persist changes
     */
    public static boolean updateStock(String id, int amountChange) {
        for (Supply supply : supplies) {
            if (supply.getId().equalsIgnoreCase(id)) {
                supply.updateQuantity(amountChange);
                gestorCsv.saveSupplies(supplies);
                return true;
            }
        }
        return false;
    }

    /**
     * adds a new supply
     *
     * @param newSupply the Supply to be added
     */
    public static void addSupply(Supply newSupply) {
        if (newSupply != null) {
            supplies.add(newSupply);
            gestorCsv.saveSupplies(supplies);
            System.out.println("Supply '" + newSupply.getName() + "' added");
        }
    }
}
