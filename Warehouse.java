/*
 * Warehouse.java
 * 
 * Victoria Da Rosa
 * ICS4U
 * Culminating Project
 * 
 * This program is a template for the 
 * properties and behaviours of IKEA warehouses.
 */

package ikea;

import java.util.ArrayList;
import java.io.*;
import javax.swing.*;

/**
 * Models an IKEA warehouse.
 */
public class Warehouse {
	// Warehouse location properties.
	private String city;
	private String province;
	// Warehouse inventory.
	private ArrayList<ArrayList<Product>> inventory;
	// Warehouse product categories.
	final private String[] categories = {"Outdoor", "Bedroom", 
			"Living Room", "Bathroom", "Kitchen", "Office", "Kids"};
	// Category objects for warehouse categories.
	private Category outdoor;
	private Category bedroom;
	private Category livingRoom;
	private Category bathroom;
	private Category kitchen;
	private Category office;
	private Category kids;
	
	/**
	 * Default constructor.
	 */
	public Warehouse() {
		city = null;
		province = null;
		// Instantiate warehouse inventory.
		inventory = new ArrayList<ArrayList<Product>>();
		// Instantiate warehouse product categories.
		outdoor = new Category("Outdoor");
		bedroom = new Category("Bedroom");
		livingRoom = new Category("Living Room");
		bathroom = new Category("Bathroom");
		kitchen = new Category("Kitchen");
		office = new Category("Office");
		kids = new Category("Kids");
		// Add each product category to the warehouse inventory.
		inventory.add(outdoor.getProducts());
		inventory.add(bedroom.getProducts());
		inventory.add(livingRoom.getProducts());
		inventory.add(bathroom.getProducts());
		inventory.add(kitchen.getProducts());
		inventory.add(office.getProducts());
		inventory.add(kids.getProducts());
	}
	
	/**
	 * Creates a Warehouse object with inputs for warehouse properties.
	 * @param c Warehouse city.
	 * @param p Warehouse province.
	 */
	public Warehouse(String c, String p) {
		city = c;
		province = p;
		// Instantiate warehouse inventory.
		inventory = new ArrayList<ArrayList<Product>>();
		// Instantiate warehouse product categories.
		outdoor = new Category("Outdoor");
		bedroom = new Category("Bedroom");
		livingRoom = new Category("Living Room");
		bathroom = new Category("Bathroom");
		kitchen = new Category("Kitchen");
		office = new Category("Office");
		kids = new Category("Kids");
		// Add each product category to the warehouse inventory.
		inventory.add(outdoor.getProducts());
		inventory.add(bedroom.getProducts());
		inventory.add(livingRoom.getProducts());
		inventory.add(bathroom.getProducts());
		inventory.add(kitchen.getProducts());
		inventory.add(office.getProducts());
		inventory.add(kids.getProducts());
	}
	
	/**
	 * Getter method for warehouse city.
	 * @return Warehouse city.
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Getter method for warehouse province.
	 * @return Warehouse province.
	 */
	public String getProvince() {
		return province;
	}
	
	/**
	 * Getter method for warehouse inventory.
	 * @return Warehouse inventory.
	 */
	public ArrayList<ArrayList<Product>> getInventory() {
		return inventory;
	}
	
	/**
	 * Getter method for warehouse product categories.
	 * @return Warehouse product categories.
	 */
	public String[] getCategories() {
		return categories;
	}
	
	/**
	 * Getter method for the outdoor product category.
	 * @return Outdoor product category.
	 */
	public Category getOutdoor(){
		return outdoor;
	}
	
	/**
	 * Getter method for the bedroom product category.
	 * @return Bedroom product category.
	 */
	public Category getBedroom(){
		return bedroom;
	}
	
	/**
	 * Getter method for the living room product category.
	 * @return Living room product category.
	 */
	public Category getLivingRoom(){
		return livingRoom;
	}
	
	/**
	 * Getter method for the bathroom product category.
	 * @return Bathroom product category.
	 */
	public Category getBathroom(){
		return bathroom;
	}
	
	/**
	 * Getter method for the kitchen product category.
	 * @return Kitchen product category.
	 */
	public Category getKitchen(){
		return kitchen;
	}
	
	/**
	 * Getter method for the office product category.
	 * @return Office product category.
	 */
	public Category getOffice(){
		return office;
	}
	
	/**
	 * Getter method for the kids product category.
	 * @return Kids product category.
	 */
	public Category getKids(){
		return kids;
	}
	
	/**
	 * Setter method for warehouse city.
	 * @param newCity New warehouse city.
	 */
	public void setCity(String newCity) {
		city = newCity;
	}
	
	/**
	 * Setter method for warehouse province.
	 * @param newProvince New warehouse province.
	 */
	public void setProvince(String newProvince) {
		province = newProvince;
	}
	
	/**
	 * Finds a category ArrayList specified by the user.
	 * @return Product category.
	 */
	public ArrayList<Product> findCategory() {
		// Whether or not the category is valid.
		boolean isValid = false;
		// Product category.
		ArrayList<Product> category = null;
		
		// Find the specified category.
		do {
			String productCategory = retrieveCategory();
			for (int i = 0; i < categories.length; i++) {
				if (productCategory.equalsIgnoreCase(categories[i])) {
					category = inventory.get(i);
					isValid = true;
					break;
				}
			}
		} while (!isValid);
		
		return category;
	}
	
	/**
	 * Finds a category ArrayList specified by the parameter.
	 * @param productCategory String of product category name.
	 * @return Product category.
	 */
	public ArrayList<Product> findCategory(String productCategory) {
		// Whether or not the category is valid.
		boolean isValid = false;
		// Product category.
		ArrayList<Product> category = null;
		
		// Find the specified category.
		do {
			for (int i = 0; i < categories.length; i++) {
				if (productCategory.equalsIgnoreCase(categories[i])) {
					category = inventory.get(i);
					isValid = true;
					break;
				}
			}
		} while (!isValid);
		
		return category;
	}
	
	/**
	 * Imports an existing warehouse inventory from a text file.
	 * @throws IOException
	 */
	public void importInventory() throws IOException {
		// Text file file path.
		String filePath;
		// Whether or not the file path is valid.
		boolean isValidPath;
		// BufferedReader object.
		BufferedReader myFile = null;
		
		// Prompt the user for a valid file path.
		do {
			filePath = JOptionPane.showInputDialog("Enter a valid file path.");
			
			isValidPath = true;
			try {
				myFile = new BufferedReader(new FileReader(filePath));
			} catch (FileNotFoundException fnfe) {
				isValidPath = false;
			}
		} while (!isValidPath);
		
		// A line in the text file.
		String line;
		// Information for a single Product.
		String[] productInfo = new String[5];
		// Product object.
		Product item;
		//Product price.
		double productPrice;
		// Number of products in stock.
		int productInStock;
		// Double object.
		Double priceDouble;
		
		line = myFile.readLine();
		
		// Read each line until the EOF.
		while (line != null) {
			/* Split each line at the semicolons
			 * and save the information to productInfo. */
			productInfo = line.split(";");
			
			priceDouble = Double.parseDouble(productInfo[3]);
			productPrice = priceDouble.doubleValue();
			productInStock = Integer.parseInt(productInfo[4]);
			
			// Create a new Product from the information on a line.
			item = new Product(productInfo[0], productInfo[1], 
					productInfo[2], productPrice, productInStock);
			// Add the product to its corresponding category.
			findCategory(productInfo[0]).add(item);
			
			line = myFile.readLine();
		}
		myFile.close();
	}
		
	/**
	 * Prompts the user for a product category.
	 * @return Product category name.
	 */
	public String retrieveCategory() {
		// Whether or not the input is valid.
		boolean isValid;
		// Product category name.
		String productCategory;
		
		// Prompt the user for a valid category name.
		do {
			isValid = true;
			productCategory = JOptionPane.showInputDialog(
					"Enter a valid product category.");
			for (int i = 0; i < categories.length; i++) {
				if (productCategory.equalsIgnoreCase(categories[i])) {
					break;
				} else if (i == categories.length - 1) {
					isValid = false;
				}
			}
		} while (!isValid);
		
		return productCategory;
	}
		
	/**
	 * Prompts the user for a product's stock code.
	 * @return Product stock code.
	 */
	public String retrieveStockCode() {
		// Whether or not the input is valid.
		boolean isValid;
		// Product stock code.
		String productStockCode;
		
		// Prompt the user for a valid stock code in proper format.
		do {
			isValid = true;
			productStockCode = JOptionPane.showInputDialog(
					"Enter a valid product stock code (format: 000.000.00).");
			// Check for periods.
			if (productStockCode.charAt(3) != '.' || 
					productStockCode.charAt(7) != '.') {
				isValid = false;
				continue;
			}
			// Check if numbers were inputted.
			try {
				Integer.parseInt(productStockCode.substring(0, 3));
			} catch (NumberFormatException nfe) {
				isValid = false;
				continue;
			}
			// Check if numbers were inputted.
			try {
				Integer.parseInt(productStockCode.substring(4, 7));
			} catch (NumberFormatException nfe) {
				isValid = false;
				continue;
			}
			// Check if numbers were inputted.
			try {
				Integer.parseInt(productStockCode.substring(8));
			} catch (NumberFormatException nfe) {
				isValid = false;
			}
		} while (!isValid);
		
		return productStockCode;
	}
		
	/**
	 * Prompts the user for a product description.
	 * @return Product description.
	 */
	public String retrieveDescription() {
		// Product description.
		String productDescription = JOptionPane.showInputDialog(
				"Enter a product description.");
		return productDescription;
	}
		
	/**
	 * Prompts the user for a product price.
	 * @return Product price.
	 */
	public double retrievePrice() {
		// Whether or not the input is valid.
		boolean isValid;
		// Product price.
		double productPrice;
		// Double object.
		Double priceDouble = 0.0;
		
		// Prompt the user for a valid product price.
		do {
			isValid = true;
			try {
				priceDouble = Double.parseDouble(JOptionPane.showInputDialog(
						"Enter a valid product price."));
			} catch (NumberFormatException nfe) {
				isValid = false;
			}
		} while (!isValid);
		productPrice = priceDouble.doubleValue();
		
		return productPrice;
	}
		
	/**
	 * Prompts the user for the number of products in stock.
	 * @return Number of products in stock.
	 */
	public int retrieveInStock() {
		// Whether or not the input is valid.
		boolean isValid;
		// Number of products in stock.
		int productInStock = 0;
		
		// Prompt the user for a valid number of products in stock.
		do {
			isValid = true;
			try {
				productInStock = Integer.parseInt(JOptionPane.showInputDialog(
						"Enter a valid number of products in stock."));
			} catch (NumberFormatException nfe) {
				isValid = false;
			}
		} while (!isValid);
		
		return productInStock;
	}
	
	/**
	 * Creates a Product object using the retrieve methods.
	 * @return Product object.
	 */
	public Product makeProduct() {
		Product product = new Product(retrieveCategory(), retrieveStockCode(), 
				retrieveDescription(), retrievePrice(), retrieveInStock());
		return product;
	}
	
	/**
	 * Adds a Product object to its corresponding category.
	 * @param productToAdd Product object to be added.
	 */
	public void addProduct(Product productToAdd) {
		findCategory().add(productToAdd);
	}
	
	/**
	 * Removes a Product object from its corresponding category.
	 */
	public void removeProduct() {
		// Product category.
		ArrayList<Product> category = findCategory();
		// Product stock code.
		String productStockCode = retrieveStockCode();
				
		// Find and remove the product.
		for (int i = 0; i < category.size(); i++) {
			if (((category.get(i)).getStockCode()).equals(productStockCode)) {
				category.remove(i);
				break;
			// Display a message if no such product exists.
			} else if (i == category.size() - 1) {
				JOptionPane.showMessageDialog(null, 
						"No matching product found. Try again.");
			}
		}
	}
	
	/**
	 * Changes the number of products in stock.
	 */
	public void changeStock() {
		// Whether or not the input is valid.
		boolean isValid;
		// Stock to change.
		int stockChange = 0;
		
		// Prompt the user for a valid number of stock to change.
		do {
			try {
				isValid = true;
				stockChange = Integer.parseInt(JOptionPane.showInputDialog(
						"Enter a valid number of stock "
						+ "(positive to add, negative to remove)."));
			} catch (NumberFormatException nfe) {
				isValid = false;
			}
		} while (!isValid);
		
		// Product category.
		ArrayList<Product> category = findCategory();
		// Product stock code.
		String productStockCode = retrieveStockCode();
		
		// Find and change the stock of the specified product.
		for (int j = 0; j < category.size(); j++) {
			if (((category.get(j)).getStockCode()).equals(productStockCode)) {
				(category.get(j)).changeStock(stockChange);
				break;
			// Display a message if no such product exists.
			} else if (j == category.size() - 1) {
				JOptionPane.showMessageDialog(null, 
						"No matching product found. Try again.");
			}
		}
	}
	
	/**
	 * Displays the products in the warehouse inventory by category.
	 */
	public void displayProducts() {
		// User's category choice.
		String choice;
		
		// Prompt the user to select a category to display.
		choice = JOptionPane.showInputDialog(
				"1 - Outdoor category.\n"
				+ "2 - Bedroom category.\n"
				+ "3 - Living Room category.\n"
				+ "4 - Bathroom category.\n"
				+ "5 - Kitchen category.\n"
				+ "6 - Office category.\n"
				+ "7 - Kids category.");
		// Display the specified category.
		if (choice.equals("1")) {
			JOptionPane.showMessageDialog(null, (getOutdoor()).toString());
		} else if (choice.equals("2")) {
			JOptionPane.showMessageDialog(null, (getBedroom()).toString());
		} else if (choice.equals("3")) {
			JOptionPane.showMessageDialog(null, (getLivingRoom()).toString());
		} else if (choice.equals("4")) {
			JOptionPane.showMessageDialog(null, (getBathroom()).toString());
		} else if (choice.equals("5")) {
			JOptionPane.showMessageDialog(null, (getKitchen()).toString());
		} else if (choice.equals("6")) {
			JOptionPane.showMessageDialog(null, (getOffice()).toString());
		} else if (choice.equals("7")) {
			JOptionPane.showMessageDialog(null, (getKids()).toString());
		} else {
			JOptionPane.showMessageDialog(null, "Invalid choice. Try again.");
		}
	}
	
	/**
	 * Returns a Warehouse object's properties and inventory.
	 * @return Warehouse object properties and inventory.
	 */
	public String toString() {
		String output = "Location: " + city + ", " + province + "\n\n";
		output += "Inventory\n";
		for (int i = 0; i < inventory.size(); i++) {
			output += (inventory.get(i)).toString();
		}
		return output;
	}

}
