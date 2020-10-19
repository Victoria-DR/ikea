/*
 * Product.java
 * 
 * Victoria Da Rosa
 * ICS4U
 * Culminating Project
 * 
 * This program is a template for the 
 * properties and behaviours of IKEA products.
 */

package ikea;

/**
 * Models an IKEA product.
 */
public class Product {
	// Product object properties.
	private String category;
	private String stockCode;
	private String description;
	private double price;
	private int inStock;
	
	/**
	 * Default constructor.
	 */
	public Product() {
		category = null;
		stockCode = null;
		description = null;
		price = 0.00;
		inStock = 0;
	}
	
	/**
	 * Creates a Product object with inputs for product properties.
	 * @param c Product category.
	 * @param s Product stock code.
	 * @param d Product description.
	 * @param p Product price.
	 * @param i Number of products in stock.
	 */
	public Product(String c, String s, String d, double p, int i) {
		category = c;
		stockCode = s;
		description = d;
		price = p;
		inStock = i;
	}
	
	/**
	 * Getter method for product category.
	 * @return Product category.
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Getter method for product stock code.
	 * @return Product stock code.
	 */
	public String getStockCode() {
		return stockCode;
	}
	
	/**
	 * Getter method for product description.
	 * @return Product description.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Getter method for product price.
	 * @return Product price.
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Getter method for number of products in stock.
	 * @return Number of products in stock.
	 */
	public int getInStock() {
		return inStock;
	}
	
	/**
	 * Setter method for product category.
	 * @param newCategory New product category.
	 */
	public void setCategory(String newCategory) {
		category = newCategory;
	}
	
	/**
	 * Setter method for product stock code.
	 * @param newStockCode New product stock code.
	 */
	public void setStockCode(String newStockCode) {
		stockCode = newStockCode;
	}
	
	/**
	 * Setter method for product description.
	 * @param newDescription New product description.
	 */
	public void setDescription(String newDescription) {
		description = newDescription;
	}
	
	/**
	 * Setter method for product price.
	 * @param newPrice New product price.
	 */
	public void setPrice(double newPrice) {
		price = newPrice;
	}
	
	/**
	 * Setter method for number of products in stock.
	 * @param newInStock New number of products in stock.
	 */
	public void setInStock(int newInStock) {
		inStock = newInStock;
	}
	
	/**
	 * Changes the number of products in stock.
	 * @param stockChange Amount of stock to change.
	 */
	public void changeStock(int stockChange) {
		inStock += stockChange;
	}
	
	/**
	 * Returns a Product object's properties.
	 * @return Product object properties.
	 */
	public String toString() {
		String output = "Category: " + category + "\n";
		output += "Stock Code: " + stockCode + "\n";
		output += "Description: " + description + "\n";
		output += "Price: $" + price + "\n";
		output += "In Stock: " + inStock + "\n";
		return output;
	}

}
