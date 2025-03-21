import java.io.FileWriter;
import java.io.IOException;

public class InventoryIO {
    public static void writeInventoryToFile(Inventory inventory) {
        try (FileWriter writer = new FileWriter("Inventory.txt", true)) {
            write(writer, inventory);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public static void rewrite(InventoryArrayList list) {
        try (FileWriter writer = new FileWriter("Inventory.txt")) {
            for (int i = 0; i < list.size(); i++) {
                write(writer, list.getInventory(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void write(FileWriter writer, Inventory inventory) {
        try {
            writer.write(inventory.getName() + "#PROPERTYBREAK#" + inventory.getID() + "#PROPERTYBREAK#" + inventory.getCategory() + "#PROPERTYBREAK#" + inventory.getQuantity() + "#PROPERTYBREAK#" + inventory.getPrice() + "\n");
        } catch (Exception e) {}
    }
}
