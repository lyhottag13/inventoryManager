import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Scanner;
import java.io.File;

public class InventoryGUI extends JFrame implements ActionListener {
    private DefaultListModel<Inventory> listModel;
    private InventoryArrayList<Inventory> inventoryArrayList;
    private InventoryHashMap inventoryHashMap;
    private JFrame frame;
    private JPanel backgroundPanel, fieldPanel, buttonPanel, panePanel;
    private JTextField nameField, IDField, categoryField, quantityField, priceField;
    private JList<Inventory> list;
    private JLabel[] fieldLabels;
    private JButton newButton, deselectButton, editButton, deleteButton;

    public InventoryGUI() {
        listModel = new DefaultListModel<Inventory>();
        inventoryHashMap = new InventoryHashMap();
        inventoryArrayList = new InventoryArrayList<Inventory>();
        fillList();
        frame = new JFrame("Inventory Manager");
        backgroundPanel = new JPanel(new FlowLayout());
        fieldPanel = new JPanel(new GridLayout(2, 5));
        buttonPanel = new JPanel(new GridLayout());
        panePanel = new JPanel(new GridLayout());
        nameField = new JTextField(5);
        IDField = new JTextField(5);
        categoryField = new JTextField(5);
        quantityField = new JTextField(5);
        priceField = new JTextField(5);
        list = new JList<Inventory>(listModel);
        fieldLabels = new JLabel[5];
        for (int i = 0; i < fieldLabels.length; i++) {
            fieldLabels[i] = new JLabel("", SwingConstants.CENTER);
            fieldPanel.add(fieldLabels[i]);
        }
        newButton = new JButton("New");
        deselectButton = new JButton("Deselect");
        editButton = new JButton("Overwrite");
        deleteButton = new JButton("Delete");

        fieldLabels[0].setText("Name:");
        fieldLabels[1].setText("ID:");
        fieldLabels[2].setText("Category:");
        fieldLabels[3].setText("Quantity:");
        fieldLabels[4].setText("Price:");

        fieldPanel.add(nameField);
        fieldPanel.add(IDField);
        fieldPanel.add(categoryField);
        fieldPanel.add(quantityField);
        fieldPanel.add(priceField);

        buttonPanel.add(newButton);
        buttonPanel.add(deselectButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        panePanel.add(list);

        newButton.addActionListener(this);
        deselectButton.addActionListener(this);
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);

        // I'll be honest, I had no clue how to implement the whole 'show the JList object in the fields' method. I needed to look this stuff up online.
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedKey = list.getSelectedValue().getIndex();
                Inventory selectedItem = inventoryHashMap.get(selectedKey);
                
                if (selectedItem != null) {
                    nameField.setText(selectedItem.getName());
                    IDField.setText(selectedItem.getID());
                    categoryField.setText(selectedItem.getCategory());
                    quantityField.setText(String.valueOf(selectedItem.getQuantity()));
                    priceField.setText(String.valueOf(selectedItem.getPrice()));
                }
            }
        });
        System.out.println();
        System.out.println(inventoryHashMap);
        frame.add(backgroundPanel);
        backgroundPanel.add(fieldPanel);
        backgroundPanel.add(buttonPanel);
        backgroundPanel.add(panePanel);
        backgroundPanel.setBorder(new EmptyBorder(0, 20, 20, 20));

        frame.setSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == newButton) {
            try {
                Inventory newInventory = new Inventory(nameField.getText(), IDField.getText(), categoryField.getText(), Integer.parseInt(quantityField.getText()), Double.parseDouble(priceField.getText()));
                addInventory(newInventory);
                InventoryIO.writeInventoryToFile(newInventory);
            } catch (Exception exception) {
                System.err.println("OOPS!");
            }
        } else if (event.getSource() == deselectButton) {
            list.clearSelection();
        } else if (event.getSource() == editButton) {
            list.getSelectedIndex();
            // listModel.
        } else if (event.getSource() == deleteButton) {
            try {
                removeInventory();
            } catch (Exception e) {}
        }
    }
    /**
     * Adds an inventory object to all the necessary areas to shorten the code required in other methods.
     * @param inventory The inventory object that will be added to the different lists.
     */
    public void addInventory(Inventory inventory) {
        inventoryArrayList.addInventory(inventory);
        inventory.setIndex(inventoryArrayList.indexOf(inventory));
        inventoryHashMap.addInventory(inventory);
        listModel.addElement(inventory);
    }
    public void removeInventory() {
        inventoryArrayList.removeInventory(list.getSelectedIndex());
        inventoryHashMap.removeInventory(list.getSelectedValue().getIndex());
        listModel.removeElement(list.getSelectedValue());
        InventoryIO.rewrite(inventoryArrayList);
    }
    /**
     * Fills the necessary lists from Inventory.txt to make the inventory interactable.
     */
    public void fillList() {
        try (Scanner scan = new Scanner(new File("Inventory.txt"))) {
            scan.useDelimiter("\n");
            while (scan.hasNext()) {
                Scanner scan2 = new Scanner(scan.next());
                scan2.useDelimiter("#PROPERTYBREAK#");
                Inventory temporary = new Inventory(scan2.next(), scan2.next(), scan2.next(), Integer.parseInt(scan2.next()), Double.parseDouble(scan2.next()));
                addInventory(temporary);
                scan2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}