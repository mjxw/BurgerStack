/*
 * Matthew Wu
 * TCSS 342
 * Baron Burger 
 */
package model;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	private static final String TEXT_FILE = "test.txt";
	private static int orderNumber; // A reference to the order number 
	
	/**
	 * Main method calls on other methods to perform tasks.
	 * @param theArgs is the argument 
	 */
    public static void main(final String[] theArgs) {
    	orderNumber = 0;
//    	testMyStack();
//    	testBurger();
    	readTextFile();
    }
    
    public static void parseLine(String line) {
        StringTokenizer stringTokens = new StringTokenizer(line);
        List<String> list = new ArrayList<String>();
        Burger order = new Burger(false);
        List<String> ingredients = ingredients();
        List<String> categories = categories();
        
        while (stringTokens.hasMoreTokens()) {
            list.add(stringTokens.nextToken());
        }
        
        int withIndex = list.indexOf("with");
        int butIndex = list.indexOf("but");
        int noIndex = list.indexOf("no");
            	
    	if (list.contains("Baron")) { // If list contains 'baron', then make a baron burger
    		order = new Burger(true);
    	} else if (!list.contains("Baron")) { // If list doesn't contain 'baron', then make default burger
    		order = new Burger(false);
    	} 
    	    	
    	if (list.contains("Chicken")) { // Check for chicken patty, change patty
    		order.changePatties("Chicken");
    	} else if (list.contains("Veggie")) { // Check for veggie patty, change patty
    		order.changePatties("Veggie");
    	}
    	    	
    	if (noIndex - withIndex == 1 && !list.contains("but")) { // Case: "with no..."
    		for (String ingredient : ingredients) { 
    			if (list.contains(ingredient)) {
    				order.removeIngredient(ingredient); 
    			} 
    		}
    		for (String category : categories) {
    			if (list.contains(category)) {
    				order.removeCategory(category);
    			}
    		}
    	}
    	
    	if (list.contains("with") && !list.contains("no") && !list.contains("but")) { // Case: "with..."
    		for (String ingredient : ingredients) { 
    			if (list.contains(ingredient)) {
    				order.addIngredient(ingredient); 
    			} 
    		}
    	}
    	
    	if (list.contains("with") && (noIndex - butIndex == 1)) { // Case: "with...but no..."
    		for (int i = withIndex; i < butIndex; i++) {
    			for (String ingredient : ingredients) {
    				if (list.get(i).equals(ingredient)) {
    					order.addIngredient(ingredient);
    				}
    			}
        		for (String category : categories) {
        			if (list.get(i).equals(category)) {
        				order.addCategory(category);
        			}
        		}
    		}
    		for (int i = noIndex; i < list.size(); i++) {
    			for (String ingredient : ingredients) {
    				if (list.get(i).equals(ingredient)) {
    					order.removeIngredient(ingredient);
    				}
    			}
        		for (String category : categories) {
        			if (list.get(i).equals(category)) {
        				order.removeCategory(category);
        			}
        		}
    		}
    	}
    	
		if ((noIndex - withIndex == 1) && list.contains("but")) { // Case: "with no...but"
			for (int i = noIndex; i < butIndex; i++) {
    			for (String ingredient : ingredients) {
    				if (list.get(i).equals(ingredient)) {
    					order.removeIngredient(ingredient);
    				}
    			}
        		for (String category : categories) {
        			if (list.get(i).equals(category)) {
        				order.removeCategory(category);
        			}
        		}
			}
			for (int i = butIndex; i < list.size(); i++) {
    			for (String ingredient : ingredients) {
    				if (list.get(i).equals(ingredient)) {
    					order.addIngredient(ingredient);
    				}
    			}
        		for (String category : categories) {
        			if (list.get(i).equals(category)) {
        				order.addCategory(category);
        			}
        		}
			}
		}
    
    	if (list.contains("Double")) { // Add 1 patty of correct type
            if (list.contains("Cheddar") || list.contains("Mozzarella") || list.contains("Pepperjack") || list.contains("Baron")) {
            	order.cheeseCheck(true);
            } else {
            	order.cheeseCheck(false);
            }
    		order.addPatty();
    	} else if (list.contains("Triple")) { // Add 2 patty of correct type
            if (list.contains("Cheddar") || list.contains("Mozzarella") || list.contains("Pepperjack") || list.contains("Baron")) {
            	order.cheeseCheck(true);
            } else {
            	order.cheeseCheck(false);
            }
    		order.addPatty();
    		order.addPatty();
    	}
    	System.out.println(order);
    }
    
    /**
     * This method tests the stack to see if it functions properly.
     */
    public static void testMyStack() {
        MyStack<String>test = new MyStack<String>();
        test.push("1");
        test.push("2");
        test.push("3");
        test.pop();
        test.pop();
        test.pop();
        test.pop();
        test.pop();
        System.out.println("Stack size: " + test.size());
        System.out.println("Is Stack empty: " + test.isEmpty());
        System.out.println("Current Stack: " + test);
    }
    
    /**
     * This method tests the burger class to see if it functions correctly.
     */
    public static void testBurger() {
		System.out.println("");
    	System.out.println("Testing changePatty() and addPatty() for regular burger");
		Burger defaultBurger = new Burger(false);
		defaultBurger.cheeseCheck(false);
		System.out.println("Default: " + defaultBurger);
		defaultBurger.changePatties("Chicken");
		System.out.println("Patty changed to chicken: " + defaultBurger);
		defaultBurger.addPatty();
		System.out.println("Added a patty(chicken): " + defaultBurger);
		defaultBurger.addPatty();
		System.out.println("Added a patty(Chicken): " + defaultBurger);
		System.out.println();
		
		System.out.println("Test addPatty() and changePatty() for baron burger");
		Burger baronBurger = new Burger(true);
		baronBurger.cheeseCheck(true);
		System.out.println("Default: " + baronBurger);
		baronBurger.addPatty();
		System.out.println("Added a patty(beef): " + baronBurger);
		baronBurger.addPatty();
		System.out.println("Added a patty(beef): " + baronBurger);
		baronBurger.addPatty();
		System.out.println("Added extra patty: " + baronBurger);
		baronBurger.changePatties("Chicken");
		System.out.println("Changed patties to chicken: " + baronBurger);
		System.out.println();
    	
    	System.out.println("Test addIngredient() for regular burger"); 
    	defaultBurger = new Burger(false);
    	System.out.println("Default: " + defaultBurger);
    	defaultBurger.addIngredient("Lettuce");
    	System.out.println("Added lettuce: " + defaultBurger);
    	defaultBurger.addIngredient("Pickle");
    	System.out.println("Added pickle: " + defaultBurger);
    	defaultBurger.addIngredient("Cheddar");
    	defaultBurger.cheeseCheck(true);
    	System.out.println("Added cheddar: " + defaultBurger);
    	defaultBurger.addIngredient("Ketchup");
    	System.out.println("Added ketchup: " + defaultBurger);
    	defaultBurger.addPatty();
    	System.out.println("Added a patty: " + defaultBurger);
    	System.out.println();
    	
    	System.out.println("Test addIngredient() for baron burger"); 
    	baronBurger = new Burger(true);
    	baronBurger.cheeseCheck(true);
    	System.out.println("Default baron: " + baronBurger);
    	baronBurger.addIngredient("Lettuce");
    	System.out.println("Added lettuce: " + baronBurger);
    	baronBurger.addIngredient("Cheddar");
    	System.out.println("Added cheddar: " + baronBurger);
    	baronBurger.addPatty();
    	System.out.println("Added beef: " + baronBurger);
    	System.out.println();
    	
    	System.out.println("Test removeIngredient() for regular burger");
    	defaultBurger = new Burger(false);
    	defaultBurger.cheeseCheck(false);
    	System.out.println("Default burger: " + defaultBurger);
    	defaultBurger.removeIngredient("Bun");
    	System.out.println("Removed Bun: " + defaultBurger);
    	defaultBurger.removeIngredient("Beef");
    	System.out.println("Removed beef: " + defaultBurger);
    	System.out.println();

    	
    	System.out.println("Test removeIngredient() for baron burger"); 
    	baronBurger = new Burger(true); 
    	baronBurger.cheeseCheck(true);
    	System.out.println("Default Baron: " + baronBurger);
    	baronBurger.removeIngredient("Baron Sauce");
    	System.out.println("Removed baron sauce: " + baronBurger);
    	baronBurger.removeIngredient("Cheddar");
    	System.out.println("Removed cheddar: " + baronBurger);
    	baronBurger.removeIngredient("Cheddar");
    	System.out.println("Removed cheddar: " + baronBurger);
    	baronBurger.cheeseCheck(false);
    	System.out.println();
    	
    	System.out.println("Test addCategory() and removeCategory() for regular burger");
    	defaultBurger = new Burger(false);
    	System.out.println("Default burger: " + defaultBurger);
    	defaultBurger.addCategory("Cheese");
    	defaultBurger.cheeseCheck(true);
    	System.out.println("Added cheese: " + defaultBurger);
    	defaultBurger.addCategory("Veggies"); 
    	System.out.println("Added veggies: " + defaultBurger);
    	defaultBurger.removeCategory("Cheese");
    	System.out.println("Removed cheese: " + defaultBurger);
    	defaultBurger.removeCategory("Veggies"); 
    	System.out.println("Removed veggies: " + defaultBurger);
    	defaultBurger.removeCategory("Patties");
    	System.out.println("Removed Patties: " + defaultBurger);
    }
    
    /**
     * This method reads a text file and prints out the result.
     */
	private static void readTextFile() {
		Scanner inputFile;		
		try {
			inputFile = new Scanner(new File(TEXT_FILE));
			while (inputFile.hasNextLine()) {
				String line = inputFile.nextLine();
				System.out.println("Processing Order " + orderNumber + ": " + line);
				parseLine(line);
				System.out.println();
				orderNumber++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
    
    /**
     * This method returns a list of the possible categories.
     * @return a List<String> of categories
     */
    private static List<String> categories() {
      List<String> list = new ArrayList<String>();
      list.add("Cheese");
      list.add("Sauce");
      list.add("Veggies");
      return list;
    }
    
    /**
     * This method returns a list of possible ingredients. 
     * @return a List<String> of ingredients
     */
    private static List<String> ingredients() {
        List<String> list = new ArrayList<String>();
        list.add("Cheddar");
        list.add("Mozzarella");
        list.add("Pepperjack");
        list.add("Lettuce");
        list.add("Tomato");
        list.add("Onions");
        list.add("Pickle");
        list.add("Mushrooms");
        list.add("Ketchup");
        list.add("Mustard");
        list.add("Mayonnaise");
        list.add("Baron-Sauce");
        return list;
    }
}
