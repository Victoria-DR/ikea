/*
 * Category.java
 * 
 * Victoria Da Rosa
 * ICS4U
 * Culminating Project
 * 
 * This program is a template for the 
 * properties and behaviours of IKEA product categories.
 */

package ikea;

import java.util.ArrayList;

/**
 * Models an IKEA product category.
 */
public class Category {
	// Category object properties.
	final private String NAME;
	private ArrayList<Product> products;
	private int size;
	
	/**
	 * Default constructor.
	 */
	public Category() {
		NAME = null;
		products = null;
		size = 0;
	}
	
	/**
	 * Creates a Category object with inputs for category properties.
	 * @param n Category name.
	 */
	public Category(String n) {
		NAME = n;
		products = new ArrayList<Product>();
		size = 0;
	}
	
	/**
	 * Getter method for category name.
	 * @return Category name.
	 */
	public String getName() {
		return NAME;
	}
	
	/**
	 * Getter method for category products.
	 * @return Category products.
	 */
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	/**
	 * Getter method for number of products in the category.
	 * @return Number of products in the category.
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Returns a Category object's properties.
	 * @return Category object properties.
	 */
	public String toString() {
		String output = NAME + "\n";
		output += "Number Of Products: " + size + "\n\n";
		for (int i = 0; i < products.size(); i++) {
			output += (products.get(i)).toString() + "\n";
		}
		output += "\n\n";
		return output;
	}

}
