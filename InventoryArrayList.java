import java.util.ArrayList;
import java.util.Iterator;

public class InventoryArrayList<E> extends ArrayList<Inventory>{
    private ArrayList<Inventory> list;
    
    public InventoryArrayList() {
        list = new ArrayList<Inventory>();
    }
    public Inventory getInventory(int index) {
        return list.get(index);
    }
    public void addInventory(Inventory inventory) {
        list.add(inventory);
    }
    public void removeInventory(int index) {
        list.remove(index);
    }
    public void setInventory(String name, String ID, String category, int quantity, double price, int index) {
        Inventory current = list.get(index);
        current.setName(name);
        current.setID(ID);
        current.setCategory(category);
        current.setQuantity(quantity);
        current.setPrice(price);
    }
    public int indexOf(Inventory inventory) {
        for (int i = 0; i < list.size(); i++) {
            if (inventory == list.get(i)) {
                return i;
            }
        }
        return -1;
    }
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Inventory i : list) {
            output.append(i + "\n");
        }
        return output.toString();
    }
    public Iterator<Inventory> iterator() {
            return list.iterator();
    }
}
