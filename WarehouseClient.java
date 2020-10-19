/*
 * WarehouseClient.java
 * 
 * Victoria Da Rosa
 * ICS4U
 * Culminating Project
 * 
 * This program allows IKEA warehouse employees to 
 * monitor and change aspects of an IKEA warehouse.
 */

package ikea;

import java.io.IOException;
import javax.swing.*;

public class WarehouseClient {
	// Warehouse location properties.
	private static String city;
	private static String province;
	// Create a Warehouse object.
	static Warehouse wOne = new Warehouse();
	
	public static void main(String[] args) throws IOException {
		// Prompt the user for the warehouse location.
		city = JOptionPane.showInputDialog("Creating IKEA warehouse.\n"
				+ "Enter warehouse city.");
		province = JOptionPane.showInputDialog("Enter warehouse province.");
		
		wOne.setCity(city);
		wOne.setProvince(province);
		
		// User's choice of action.
		String choice = "";
		
		// Prompt the user to choose an action.
		while (!choice.equals("X")) {
			choice = JOptionPane.showInputDialog("Menu\n"
					+ "1 - Display warehouse contents.\n"
					+ "2 - Fill warehouse from text file.\n"
					+ "3 - Add product.\n"
					+ "4 - Remove product.\n"
					+ "5 - Add/remove stock of existing product.\n"
					+ "X - Exit.");
			// Display warehouse products by category.
			if (choice.equals("1")) {
				wOne.displayProducts();
			// Import inventory from a text file.
			} else if (choice.equals("2")) {
				wOne.importInventory();
			// Add a product to the inventory.
			} else if (choice.equals("3")) {
				wOne.addProduct(wOne.makeProduct());
			// Remove a product from the inventory.
			} else if (choice.equals("4")) {
				wOne.removeProduct();
			// Change the stock of a product.
			} else if (choice.equals("5")) {
				wOne.changeStock();
			// Exit the menu.
			} else if (choice.equalsIgnoreCase("X")) {
				continue;
			} else {
				JOptionPane.showMessageDialog(null, 
						"Invalid choice. Try again.");
			}
		}
	}

}
