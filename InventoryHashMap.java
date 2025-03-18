import java.util.HashMap;
public class InventoryHashMap {
    private HashMap<String, Inventory> hashMap;
    public InventoryHashMap() {
        hashMap = new HashMap<String, Inventory>();
    }
    
    public void addHash(Inventory inventory) {
        hashMap.put(inventory.toString(), inventory);
    }
    public HashMap<String, Inventory> getHashMap() {
        return hashMap;
    }
    public Inventory get(String key) {
        return hashMap.get(key);
    }
}
