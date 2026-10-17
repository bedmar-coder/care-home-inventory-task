import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GestorCSV {

    private final String CSV_FILE = Path.of("src","resources", "supplies.csv").toString();

    /**
     * Reads supplies from CSV file
     *
     * @return list of Supply
     */
    public List<Supply> loadSupplies() {
        List<Supply> supplies = new ArrayList<>();
        File file = new File(CSV_FILE);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            supplies = parseCsvContent(br);
        } catch (IOException e) {
            System.err.println("Failed to load CSV file: " + e.getMessage());
        }

        return supplies;
    }

    /**
     * Parses lines from a BufferedReader into a list of Supply
     */
    private List<Supply> parseCsvContent(BufferedReader br) throws IOException {
        List<Supply> list = new ArrayList<>();
        String line;
        boolean isHeader = true;

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) {
                continue;
            }
            if (isHeader) {
                isHeader = false;
                continue;
            }

            String[] data = line.split(",");
            if (data.length >= 5) {
                String id = data[0];
                String name = data[1];
                int currentQuantity = Integer.parseInt(data[2]);
                int minThreshold = Integer.parseInt(data[3]);
                String unit = data[4];

                list.add(new Supply(id, name, currentQuantity, minThreshold, unit));
            }
        }

        return list;
    }

    /**
     * Saves the list of Supply objects to the CSV
     *
     * @param supplies list of supplies to persist
     */
    public void saveSupplies(List<Supply> supplies) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE))) {
            bw.write("ID,NAME,CURRENT_QUANTITY,MIN_THRESHOLD,UNIT");
            bw.newLine();

            for (Supply supply : supplies) {
                String line = supply.getId() + "," +
                        supply.getName() + "," +
                        supply.getCurrentQuantity() + "," +
                        supply.getMinAlert() + "," +
                        supply.getUnit();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Could not save inventory to CSV: " + e.getMessage());
        }
    }

}
