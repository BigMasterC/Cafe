package foodManagement;
//Note to self: CAN USE "FOR" LOOPS IN THIS CLASS
/**
 * A SortedListOfImmutables represents a sorted collection of immutable objects 
 * that implement the Listable interface. In a SortedListOfImmutables, there 
 * exists an array of references to Listable objects
 * 
 * The items in the list are always kept in alphabetical order based on the 
 * names of the items.  When a new item is added into the list, it is inserted 
 * into the correct position so that the list stays ordered alphabetically
 * by name.
 */
public class SortedListOfImmutables {

	private Listable[] items;

	/**
	 * This constructor creates an empty list by creating an internal array
	 * of size 0.
	 */
	public SortedListOfImmutables() {
		this.items = new Listable[0];

	}

	/**
	 *  The class' Copy constructor. 
	 *  In this Copy Constructor, the current object will become a copy of the
	 *  list that the parameter refers to.
	 *  
	 *  @param "other": the list that is to be copied
	 */
	public SortedListOfImmutables(SortedListOfImmutables other) {//shallow copy
		items= new Listable[other.items.length];
		for (int i = 0; i < other.items.length; i++) {
			items[i] = other.get(i); 
			//getter is referencing the getter in this class
		}
	}

	/**
	 * A simple getter that returns the number of items in the list array.
	 * @return number of items in the list
	 */
	public int getSize() {
		return items.length;
	}

	/**
	 * A simple getter that returns a reference to the item in the ith position
	 * in the list.
	 * 
	 * @param "i": index of item requested
	 * @return reference to the ith item in the list
	 */
	public Listable get(int i) {//is this correct?
		return items[i];
	}

	/**
	 * Method adds an item to the list.  This method assumes that the list 
	 * is already sorted in alphabetical order based on the names of 
	 * the items in the list.
	 * 
	 * The new item will be inserted into the list in the appropriate place so
	 * that the list will remain alphabetized by names.
	 *  
	 * @param itemToAdd refers to a Listable item to be added to this list
	 */

	public void add(Listable itemToAdd) {
		int i;
		/*finds if the there are two matching items 
		 * (current object and itemToRemove) and find its position*/
		for(i =0; i < items.length; i++) {
			if (items[i].getName().compareTo(itemToAdd.getName()) > 0) {
				break;
			}
		}
		//making a new array WITHOUT that duplicate item
		//if position is not equal to the impossible value
		/*copy previous position so that you can put the 
		 * other duplicate in that position/index*/
		Listable[] newItemsList = new Listable[items.length + 1];
		for (int j = 0; j <= i -1; j++) {
			newItemsList[j] = items[j];
		}
		newItemsList[i] = itemToAdd;
		//copy new position
		for (int k = i; k < items.length; k++) {
			newItemsList[k + 1] = items[k];
		}
		/*In order to accommodate the new item, the current object array must 
		 * be re-sized so that it is one unit larger than it was before the 
		 * call to this method.*/
		items = newItemsList;

	}



	/**
	 * Adds an entire list of items to the current list, maintaining the 
	 * alphabetical ordering of the list by the names of the items.
	 * 
	 *@param "listToAdd" a list of items that are to be added to the curr object
	 */

	public void add(SortedListOfImmutables listToAdd) {
		/*loops through the array in the listToadd and 
		 * calls the previous add method*/
		for (int i =0; i < listToAdd.items.length; i++) {
			add(listToAdd.items[i]); 
			/*calls the simple add method and the ".items[]" portion turns the 
			 * parameter from a SortedListOfImmutables to a Listable*/
		}
	}

	/**
	 * "remove" method removes an item from the list (current object).
	 * 
	 * If the list contains the same item that the parameter refers to, 
	 * it will be removed from the list.  
	 * 
	 * If the item appears in the list more than once, just one instance will be
	 * removed.
	 * 
	 * If the item does not appear on the list, then this method does nothing.
	 * 
	 * @param "itemToRemove" refers to the item that is to be removed from 
	 * the list
	 */
	public void remove(Listable itemToRemove) {
		int position = -1;
		/*finds if the there are two matching items (current object and 
		 * itemToRemove) and find its position*/
		for(int i =0; i < items.length; i++) {
			if (items[i].getName().compareTo(itemToRemove.getName())==0) {
				position = i;
				break;
			}
		}
		//making a new array WITHOUT that duplicate item
		if (position != -1) { //if position is not equal to the impossible value
			/*copy previous position so that you can put the other 
			 * duplicate in that position/index*/
			Listable[] newItemsList = new Listable[items.length -1];
			for (int i = 0; i < position; i++) {
				newItemsList[i] = items[i];
			}
			//copy new position
			for (int i = position +1; i < items.length; i++) {
				newItemsList[i - 1] = items[i];
			}
			items = newItemsList;
		}
	}

	/**
	 * more complex "remove" method removes an entire list of items from the 
	 * current list. Any items in the parameter that appear in the current list
	 * are removed; any items in the parameter that do not appear in the current
	 * list are ignored.
	 * 
	 * @param "listToRemove" list of items that are to be removed from this list
	 */
	public void remove(SortedListOfImmutables listToRemove) {
		//loops through parameter whilst calling simple remove method
		for (int i= 0; i < listToRemove.items.length; i++) {
			remove(listToRemove.items[i]);
		}
	}

	/**
	 * "getWholesaleCost" method returns the sum of the wholesale costs 
	 * of all items in the list.
	 * 
	 * @return "sum" of the wholesale costs of all items in the list
	 */
	public int getWholesaleCost() {
		int WholesaleCost = 0;
		for (int i = 0; i < items.length; i++) {
			WholesaleCost += items[i].getWholesaleCost();
		}
		return WholesaleCost;
	}

	/**
	 * "getRetailValue" method returns the sum of the retail values 
	 * of all items in the list.
	 * 
	 * @return "sum" of the retail values of all items in the list
	 */
	public int getRetailValue() {
		int sum = 0;
		for (int i = 0; i < items.length; i++) {
			sum += items[i].getRetailValue();
		}
		return sum;
	}

	/**
	 * Method(below) checks to see if a particular item is in the list.
	 * 
	 * @param "itemToFind" item to look for
	 * @return "true" if the item is found in the list, "false" otherwise
	 */
	public boolean checkAvailability(Listable itemToFind) {
		for (int i = 0; i < items.length; i++) {
			if (items[i].getName().equals(itemToFind.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method (below) checks if a list of items is 
	 * contained in the current list.
	 * 
	 * @param "listToCheck" list of items that may or may not appear in the
	 * current list
	 * @return "true" if all items in the parameter are contained in the current 
	 * list. If more than one copy of a particular element appear in the
	 * parameter, then the current list must contain at least that many as well.
	 */
	public boolean checkAvailability(SortedListOfImmutables listToCheck) {
		Listable[] newArray= new Listable[items.length];
		//making a new array to manipulate

		for (int j = 0; j < items.length; j++) {
			newArray[j]= items[j];
		}
		for (int i = 0; i < listToCheck.items.length; i++) {
			/*"listToCheck.items.length"  allows me to gather the array/list 
			 * of items in the listToCheck*/
			if (!(checkAvailability(listToCheck.items[i]))){
				items = newArray;
				return false;
			}else {
				remove(listToCheck.items[i]);
			}
		}

		items = newArray;		
		return true;
	}

	/*
	 * We'll give you this one for free.  Do not modify this method or you
	 * will fail our tests!
	 */
	public String toString() {
		String retValue = "[ ";
		for (int i = 0; i < items.length; i++) {
			if (i != 0) {
				retValue += ", ";
			}
			retValue += items[i];
		}
		retValue += " ]";
		return retValue;
	}
}
