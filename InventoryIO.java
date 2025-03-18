import java.io.FileWriter;
import java.io.IOException;

public class InventoryIO {
    public InventoryIO () {

    }
    public static void enterInventory(Inventory inventory) {
        try (FileWriter writer = new FileWriter("Inventory.txt", true)) {
            writer.write(inventory.toString() + "\n");
        } catch (IOException exception) {
            System.err.println("IO");
            exception.printStackTrace();
        }
    }
}
