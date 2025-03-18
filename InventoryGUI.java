import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Scanner;
import java.io.File;

public class InventoryGUI extends JFrame implements ActionListener {
    private DefaultListModel<String> listModel;
    private InventoryArrayList<Inventory> inventoryList;
    private InventoryHashMap inventoryHashMap;
    private JFrame frame;
    private JPanel backgroundPanel, fieldPanel, buttonPanel, panePanel;
    private JTextField nameField, IDField, categoryField, quantityField, priceField;
    private JList<String> list;
    private JLabel[] fieldLabels;
    private JButton newButton;

    public InventoryGUI() {
        listModel = new DefaultListModel<String>();
        inventoryHashMap = new InventoryHashMap();
        inventoryList = new InventoryArrayList<Inventory>();
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
        list = new JList<String>(listModel);
        fieldLabels = new JLabel[5];
        for (int i = 0; i < fieldLabels.length; i++) {
            fieldLabels[i] = new JLabel("", SwingConstants.CENTER);
            fieldPanel.add(fieldLabels[i]);
        }
        newButton = new JButton("New");

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

        panePanel.add(list);

        newButton.addActionListener(this);

        // I'll be honest, I had no clue how to implement the whole 'show the JList object in the fields' method. I needed to look this stuff up online.
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedKey = list.getSelectedValue();
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
                String name = nameField.getText();
                String ID = IDField.getText();
                String category = categoryField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());
                Inventory list = new Inventory(name, ID, category, quantity, price);
                inventoryList.addInventory(list);
                InventoryIO.enterInventory(list);
                inventoryHashMap.addHash(list);
                listModel.addElement(list.toString());
            } catch (Exception exception) {
                System.err.println("OOPS!");
            }
        }
    }
    // Fills the list with the inventory objects from the txt file.
    public void fillList() {
        try (Scanner scan = new Scanner(new File("Inventory.txt"))) {
            scan.useDelimiter("\n");
            String line;
            while (scan.hasNext()) {
                line = scan.next();
                Scanner scan2 = new Scanner(line);
                scan2.useDelimiter(" ");
                Inventory temporary = new Inventory(scan2.next(), scan2.next(), scan2.next(), Integer.parseInt(scan2.next()), Double.parseDouble(scan2.next()));
                inventoryList.addInventory(temporary);
                inventoryHashMap.addHash(temporary);
                listModel.addElement(temporary.toString());
                scan2.close();
            }
        } catch (Exception e) {
            System.err.println("IO Error");
            e.printStackTrace();
        }
    }
}