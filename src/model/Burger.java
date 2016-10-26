package model;

/**
 * This class represents the burger order. A client can add or change a patty,
 * add or remove categories, and add or remove ingredients. 
 * 
 * @author Matthew Wu
 * @version 1.5
 *
 */
public class Burger {
	
	private MyStack<String> myBurger; // The burger stack
	private int myNumberOfPatties; // A reference to the number of patties on burger
	private String myPattyType; // A reference to the type of patty 
	private boolean myIsCheese; // A boolean value representing whether or not the burger has cheese
	private MyStack<String>myRecipe; // The recipe stack (Baron Burger recipe)
	
	/**
	 * Constructs a burger - default or baron.
	 * @param theWorks is a boolean determining whether or not the burger is default or baron
	 */
	public Burger(boolean theWorks) {
		myBurger = new MyStack<String>();
		myRecipe = new MyStack<String>();
		myNumberOfPatties = 1;
		myPattyType = "Beef";
		myBurger.push("Bun");
		myIsCheese = false;
		if (!theWorks) {
			myBurger.push(myPattyType);
			myBurger.push("Bun");
		} else {
			myBurger.push("Ketchup");
			myBurger.push("Mustard");
			myBurger.push("Mushrooms");
			myBurger.push(myPattyType);
			myBurger.push("Cheddar");
			myBurger.push("Mozzarella");
			myBurger.push("Pepperjack");
			myBurger.push("Onions");
			myBurger.push("Tomato");
			myBurger.push("Lettuce");
			myBurger.push("Baron-Sauce");
			myBurger.push("Mayonnaise");
			myBurger.push("Bun");
			myBurger.push("Pickle");
		}
	}
	
	/**
	 * This method changes the patty to a specific patty type.
	 * @param pattyType is the target patty type 
	 */
	public void changePatties(String pattyType) {
		MyStack<String> temp = new MyStack<String>();

		while (!myBurger.isEmpty()) { 
			if (myBurger.peek().equals(myPattyType)) {
				myBurger.pop();
				myBurger.push(pattyType);
			} else { 
				temp.push(myBurger.pop());
			}
		}
		reloadStack(temp, myBurger);
		myPattyType = pattyType; // update patty type 

	}
	
	/**
	 * This method adds a patty to the burger in the right place.
	 */
	public void addPatty() {
		if (myNumberOfPatties == 3) {
			return;
		}
		MyStack<String> temp = new MyStack<String>();
		if (myNumberOfPatties == 1 && !myIsCheese) { // case: 1 patty no cheese 
			while (!myBurger.peek().equals(myPattyType)) {
				temp.push(myBurger.pop());
			}
		}
		if (myNumberOfPatties == 1 && myIsCheese) { // case: 1 patty with cheese 
			while (!myBurger.peek().equals("Cheddar") && !myBurger.peek().equals("Mozzarella")
				&& !myBurger.peek().equals("Pepperjack")) {
				temp.push(myBurger.pop());
			}
		}
		if (myNumberOfPatties >= 2) { // case: more than 1 patty
			while (!myBurger.peek().equals(myPattyType)) {
					temp.push(myBurger.pop());
				}
		}
		myBurger.push(myPattyType);
		myNumberOfPatties++;
		reloadStack(temp, myBurger);
	}
	
	/**
	 * This method adds a category and its entirety to the burger in the right place. 
	 * @param type is the category type
	 */
	public void addCategory(String type) {
		if (type == "Cheese") {
			addIngredient("Cheddar");
			addIngredient("Mozzarella");
			addIngredient("Pepperjack");
		} else if (type == "Patties") {
			addIngredient("Beef");
			addIngredient("Chicken");
			addIngredient("Veggie");
		} else if (type == "Veggies") { 
			addIngredient("Lettuce");
			addIngredient("Tomato");
			addIngredient("Onions");
			addIngredient("Pickle");
			addIngredient("Mushrooms");
		} else {
			addIngredient("Ketchup");
			addIngredient("Mustard");
			addIngredient("Mayonnaise");
			addIngredient("Baron-Sauce");
		}
	}
	
	/**
	 * This method removes a category and its entirety from the burger.
	 * @param type is the category type
	 */
	public void removeCategory(String type) {
		if (type == "Cheese") {
			removeIngredient("Cheddar");
			removeIngredient("Mozzarella");
			removeIngredient("Pepperjack");
		} else if (type == "Patties") {
			removeIngredient("Beef");
			removeIngredient("Chicken");
			removeIngredient("Veggie");
		} else if (type == "Veggies") { 
			removeIngredient("Lettuce");
			removeIngredient("Tomato");
			removeIngredient("Onions");
			removeIngredient("Pickle");
			removeIngredient("Mushrooms");
		} else {
			removeIngredient("Ketchup");
			removeIngredient("Mustard");
			removeIngredient("Mayonnaise");
			removeIngredient("Baron-Sauce");
		}
	}
	
	/**
	 * This method adds an ingredient to the burger in the right place. 
	 * @param type is the ingredient type to be added 
	 */
	public void addIngredient(String type) {
		setRecipeStack();
		MyStack<String> recipeTemp = new MyStack<String>();
		MyStack<String> burgerTemp = new MyStack<String>();
		while (myRecipe.peek() != type) { 
			if(myRecipe.peek() != myBurger.peek()) { // if recipe stack is not same as burger stack, pop from recipe stack
				recipeTemp.push(myRecipe.pop());
			}
			if(myRecipe.peek() == myBurger.peek()) { // if both stacks are equal, pop from both 
				recipeTemp.push(myRecipe.pop());
				burgerTemp.push(myBurger.pop());
			} 
			if (myBurger.peek() == type) { // burger already has target ingredient, do nothing 
				reloadStack(recipeTemp, myRecipe);
				reloadStack(burgerTemp, myBurger);
				return;
			}
		}
		myBurger.push(type);
		reloadStack(recipeTemp, myRecipe);
		reloadStack(burgerTemp, myBurger);
	}
	
	/**
	 * This method removes an ingredient from the burger.
	 * @param type is the ingredient type to be removed 
	 */
	public void removeIngredient(String type) {
		MyStack<String> temp = new MyStack<String>();
		while (myBurger.peek() != type) {
			temp.push(myBurger.pop());
			if (myBurger.size() == 0) { // burger doesn't have target ingredient, do nothing
				reloadStack(temp, myBurger);
				return;
			}
		}
		myBurger.pop();
		reloadStack(temp, myBurger);
	}
	
	/**
	 * This method returns a string representation of the stack.
	 * @return A string representation of the stack 
	 */
	public String toString() {
		return myBurger.toString();
	}
	
	/**
	 * This method sets the boolean flag to true if there is cheese on the burger and false otherwise.
	 * @param value is the boolean value of true or false
	 */
	public void cheeseCheck(boolean value) {
		myIsCheese = value;
	}
	
	/**
	 * This method reloads the stack.
	 * @param initial is the initial stack 
	 * @param target is the target stack to load the contents of initial stack back into
	 * @return a MyStack 
	 */
	private MyStack<String> reloadStack(MyStack<String>initial, MyStack<String>target) {
		while(!initial.isEmpty()) {
			target.push(initial.pop());
		}
		return target;
	}
	
	/**
	 * This method sets the burger baron recipe stack.
	 */
	private void setRecipeStack() {
		myRecipe.push("Bun");
		myRecipe.push("Ketchup");
		myRecipe.push("Mustard");
		myRecipe.push("Mushrooms");
		myRecipe.push(myPattyType);
		myRecipe.push("Cheddar");
		myRecipe.push("Mozzarella");
		myRecipe.push("Pepperjack");
		myRecipe.push("Onions");
		myRecipe.push("Tomato");
		myRecipe.push("Lettuce");
		myRecipe.push("Baron-Sauce");
		myRecipe.push("Mayonnaise");
		myRecipe.push("Bun");
		myRecipe.push("Pickle");
	}
}
