/*
 * PointOfSale.java
 * 
 * Victoria Da Rosa
 * ICS4U
 * Culminating Project
 * 
 * This program is a point-of-sale system 
 * for that allows IKEA customers to 
 * see and purchase products.
 */

package ikea;

import java.util.ArrayList;
import javax.swing.*;
import java.io.*;

public class PointOfSale {
	// Warehouse object.
	private static Warehouse w = WarehouseClient.wOne;
	// User's shopping cart.
	private static ArrayList<Product> cart = new ArrayList<Product>();
	
	/**
	 * Displays the user's shopping cart.
	 * @return Cart contents.
	 */
	public static String displayCart() {
		String output = "";
		for (int i = 0; i < cart.size(); i++) {
			output += "\nItem #" + (i + 1) + "\n";
			output += (cart.get(i)).toString();
		}
		return output;
	}
	
	/**
	 * Adds an item to the user's shopping cart.
	 */
	public static void addToCart() {
		// Product category.
		ArrayList<Product> category = w.findCategory();
		// Product stock code.
		String productStockCode = w.retrieveStockCode();
				
		// Find and add the product.
		for (int i = 0; i < category.size(); i++) {
			if (((category.get(i)).getStockCode()).equals(productStockCode)) {
				cart.add(category.get(i));
				break;
			// Display a message if no such product exists.
			} else if (i == category.size() - 1) {
				JOptionPane.showMessageDialog(null, 
						"No matching product found. Try again.");
			}
		}
	}
	
	/**
	 * Removes an item from the user's shopping cart.
	 */
	public static void removeFromCart() {
		// Product category.
		ArrayList<Product> category = w.findCategory();
		// Product stock code.
		String productStockCode = w.retrieveStockCode();
		
		// Find and remove the product.
		for (int i = 0; i < category.size(); i++) {
			if (((category.get(i)).getStockCode()).equals(productStockCode)) {
				cart.remove(category.get(i));
				break;
			// Display a message if no such product exists.
			} else if (i == category.size() - 1) {
				JOptionPane.showMessageDialog(null, 
						"No matching product found. Try again.");
			}
		}
	}
	
	/**
	 * Uses the Luhn algorithm to prompt the user for a valid credit card.
	 * @return Credit card number.
	 */
	public static long chooseCreditCard() {
		// Credit card number.
		long creditCardNum = 0;
		// String object.
		String numCheck;
		// Whether or not the input is valid.
		boolean isValidNum;
		
		// Prompt the user for a valid credit card number.
		do {
			try {
				isValidNum = true;
				creditCardNum = Long.parseLong(JOptionPane.showInputDialog(
								"Enter credit card number."));
				// The number must be 16 digits long.
				if (Long.toString(creditCardNum).length() != 16) {
					isValidNum = false;
				}
				
				// Luhn's algorithm implementation.
				numCheck = Long.toString(creditCardNum);
				// Each digit of the card number.
				int digit;
				// Sum of the digits.
				int total = 0;
				
				for (int i = 1; i < numCheck.length(); i += 2) {
					digit = Integer.parseInt((String) numCheck.subSequence(i, i + 1));
					digit *= 2;
					if (digit > 9) {
						digit -= 9;
					}
					total += digit;
				}
				
				if (total % 10 != 0) {
					isValidNum = false;
				}
			// Ensure the user entered a number.
			} catch (NumberFormatException nfe) {
				isValidNum = false;
			}
		} while (!isValidNum);
		
		return creditCardNum;
	}
	
	/**
	 * Prompts the user for their delivery choice.
	 * @return Delivery choice.
	 */
	public static double chooseDelivery() {
		// No delivery price.
		final double NO_DELIVERY = 0.00;
		// Regular delivery price.
		final double REGULAR_DELIVERY = 59.99;
		// User's choice.
		String choice;
		
		// Prompt the user for a valid choice.
		do {
			choice = JOptionPane.showInputDialog("Choose a delivery method.\n"
					+ "1 - No delivery.\n"
					+ "2 - Regular delivery.");
		} while (!choice.equals("1") && !choice.equals("2"));
		
		if (choice.equals("1")) {
			return NO_DELIVERY;
		} else {
			return REGULAR_DELIVERY;
		}
	}
	
	/**
	 * Creates an invoice for the user's order.
	 * 
	 * @param fName First name.
	 * @param lName Last name.
	 * @param creditCard Credit card number.
	 * @param subtotal Subtotal.
	 * @param address Address.
	 * @param delivery Delivery choice.
	 * @throws IOException
	 */
	public static void makeInvoice(String fName, String lName, 
			long creditCard, double subtotal, 
			String address, double delivery) throws IOException {
		// Invoice file path.
		String filePath;
		// Whether or not the path is valid.
		boolean isValidPath;
		// PrintWriter object.
		PrintWriter invoice = null;
		
		// Prompt the user for a valid file path.
		do {
			filePath = JOptionPane.showInputDialog("Enter a valid file path.");
			
			isValidPath = true;
			try {
				invoice = new PrintWriter(new FileWriter(filePath));
			} catch (FileNotFoundException fnfe) {
				isValidPath = false;
			}
		} while (!isValidPath);
		
		// Calculate tax.
		double tax = ((int) (subtotal * 0.13)) / 100;
		
		// Write to the invoice file.
		invoice.println("IKEA\n");
		invoice.println(fName + " " + lName);
		invoice.println(address + "\n");
		invoice.print(displayCart());
		invoice.println("\nSubtotal: $" + subtotal);
		invoice.println("Tax: $" + tax);
		invoice.println("Delivery: $" + delivery);
		invoice.println("\nTotal: $" + (subtotal + tax + delivery));
		invoice.println("\nCredit Card: " + creditCard);
		invoice.close();
	}
	
	/**
	 * Checks out the user's shopping cart.
	 * @throws IOException
	 */
	public static void checkout() throws IOException {
		// Customer name.
		String firstName, lastName;
		// Customer address.
		String address;
		
		// Prompt for the customer's information.
		firstName = JOptionPane.showInputDialog("Enter customer first name.");
		lastName = JOptionPane.showInputDialog("Enter customer last name.");
		address = JOptionPane.showInputDialog("Enter customer address.");
				
		// Order subtotal.
		double subtotal = 0.00;
		
		// Add each item's price to the subtotal.
		for (int i = 0; i < cart.size(); i++) {
			Product itemInCart = cart.get(i);
			if (itemInCart.getInStock() == 0) {
				JOptionPane.showMessageDialog(null, 
						"Item "	+ itemInCart.getStockCode() 
						+ " in " + itemInCart.getCategory() 
						+ " is out of stock and "
						+ "will not be added to the invoice.");
				cart.remove(i);
			} else {
				subtotal += itemInCart.getPrice();
			}
		}
		
		// Create customer invoice.
		makeInvoice(firstName, lastName, chooseCreditCard(), 
				subtotal, address, chooseDelivery());
		
		// Decrease amount of stock accordingly.
		for (int i = 0; i < cart.size(); i++) {
			Product itemPurchased = cart.get(i);
			itemPurchased.changeStock(-1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		// Create and organize a new warehouse.
		w.setCity(JOptionPane.showInputDialog("Creating IKEA warehouse.\n"
				+ "Enter warehouse city."));
		w.setProvince(JOptionPane.showInputDialog("Enter warehouse province."));
		w.importInventory();
		// User's choice of action.
		String choice = "";
		
		// Prompt the user to choose an action.
		while (!choice.equalsIgnoreCase("X")) {
			choice = JOptionPane.showInputDialog("Menu\n"
					+ "1 - Display products for sale.\n"
					+ "2 - View cart.\n"
					+ "3 - Add product to cart.\n"
					+ "4 - Remove product from cart.\n"
					+ "5 - Checkout.\n"
					+ "X - Exit.");
			// Display products for sale.
			if (choice.equals("1")) {
				w.displayProducts();
			// Display shopping cart.
			} else if (choice.equals("2")) {
				JOptionPane.showMessageDialog(null, displayCart());
			// Add a product to cart.
			} else if (choice.equals("3")) {
				addToCart();
			// Remove a product from cart.
			} else if (choice.equals("4")) {
				removeFromCart();
			// Checkout.
			} else if (choice.equals("5")) {
				checkout();
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
