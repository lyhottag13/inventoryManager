import java.util.HashMap;
public class InventoryHashMap {
    private HashMap<Integer, Inventory> hashMap;
    public InventoryHashMap() {
        hashMap = new HashMap<Integer, Inventory>();
    }
    
    public void addInventory(Inventory inventory) {
        System.out.println(inventory);
        hashMap.put(inventory.getIndex(), inventory);
    }
    public void removeInventory(int inventoryIndex) {
        hashMap.remove(inventoryIndex);
    }
    public HashMap<Integer, Inventory> getHashMap() {
        return hashMap;
    }
    public Inventory get(int index) {
        return hashMap.get(index);
    }
    public String toString() {
        return hashMap.toString();
    }
}
