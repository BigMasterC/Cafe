package foodManagement;

/**
 *  The Restaurant has a name,which is of type String, a menu,which is a list of
 *  Entrees, an inventory which is a list of Food, and an amount of cash on hand
 *  measured in pennies (int).
 * 
 *  This class facilitates orders being placed, deliveries being made to the
 *  inventory, and entrees being added to the menu.
 */
public class Restaurant {

	private String name;
	private SortedListOfImmutables menu;       // A list of Entree objects	
	private SortedListOfImmutables inventory;  // A list of Food objects
	private int cash;

	/**
	 * Standard constructor in which the menu and the 
	 * inventory are both initialized as empty lists. The name and cash amount
	 * are set to match the parameters.
	 * 
	 * @param "nameIn" name of the restaurant
	 * @param "startingCash" cash amount that the restaurant will have, measured
	 * in pennies
	 */
	public Restaurant(String nameIn, int startingCash) {
		/*"SortedListOfImmutables is an object therefore, 
		 * you call it the same way you would for a constructor*/
		menu= new SortedListOfImmutables();
		inventory= new SortedListOfImmutables();
		name = nameIn;
		cash = startingCash;
	}

	/**
	 * Simple getter for the name of the restaurant.
	 * 
	 * @return reference to the name of the restaurant
	 */
	public String getName() {
		return name;
	}

	/**
	 * Simple getter for the menu.
	 * 
	 * @return a reference to a copy of the menu
	 */
	public SortedListOfImmutables getMenu() {
		return new SortedListOfImmutables(menu);
	}

	/**
	 * addEntree method does not return anything and adds an entree to the menu.
	 * 
	 * @param "entreeToAdd" reference to the entree to be added to the menu
	 */
	public void addEntree(Entree entreeToAdd) {
		menu.add(entreeToAdd);
	}

	/**
	 * Simple setter for the inventory.
	 * 
	 * @return a reference to a copy of the inventory
	 */
	public SortedListOfImmutables getInventory() {
		return new SortedListOfImmutables(inventory);
	}

	/**
	 * Simple getter for the current amount of cash on hand
	 * 
	 * @return the current amount of cash, measured in pennies
	 */
	public int getCash() {
		return cash;
	}

	/**
	 * Method (below)Checks if the Food items contained in the specified
	 * Entree are actually contained in the restaurant's inventory.
	 * 
	 * @param "entree" Entree that we are checking against the inventory
	 * @return "true" if the list of Food items contained in the Entree are
	 * all present in the inventory, "false" otherwise.
	 */
	public boolean checkIfInInventory(Entree entree) {
		//getting the items of the Foodlist
		if (inventory.checkAvailability(entree.getFoodList())) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Method (below) Adds the specified list of food items to the inventory.
	 * If the total wholesale cost of all of the food items combined exceeds 
	 * the amount of cash on hand, then NONE of the food items are added 
	 * to the inventory (returning "false"). 
	 * 
	 * If the amount of cash on hand is sufficient to
	 * pay for the shipment, then the amount of cash on hand is reduced by 
	 * the wholesale cost of the shipment (and returns "true").
	 * 
	 * @param "list" food items to be added to the inventory
	 * @return "true" if the food items are added; "false" if the food items are
	 * not added because their wholesale cost exceeds the current cash
	 * on hand
	 */
	public boolean addShipmentToInventory(SortedListOfImmutables list) {
		if(list.getWholesaleCost()> cash) {
			return false;
		}else {
			inventory.add(list);
			cash -= list.getWholesaleCost();
		}
		return true;
	}

	/**
	 * Method removes the food items contained in the specified Entree from the 
	 * inventory. If the inventory does not contain all of the items 
	 * required for this Entree, then NOTHING is removed from the inventory.
	 * 
	 * If the inventory contains all of the required items, then the amount of 
	 * cash on hand is INCREASED by the retail value of the Entree.
	 *  
	 * @param "entree" Entree that has been ordered
	 * @return "true" if the food items are removed from the inventory; "false"
	 * if the food items were not removed because one or more required items
	 * were not contained in the inventory
	 */
	public boolean placeOrder(Entree entree) {
		if (inventory.checkAvailability(entree.getFoodList())) {
			//checks if the items needed for the Entree are in the inventory
			inventory.remove(entree.getFoodList()); 
			//removes all the items needed to make the Entree from the inventory
			cash+= entree.getRetailValue();
			return true;
		}else {
			return false;
		}
	}

}
