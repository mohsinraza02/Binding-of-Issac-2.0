
public class Item {

	private String name; // display name on map
	private int value; // value being appended in player array
	private int type; // location in player array
	private double time; // time taken from total map time
	private final String[][] ITEM_NAME_AND_DESC = {
			{ "Book", "Games", "TA", "Sleep", "Sharpener", "GUIDE: Sacrifice to the Gods!", "Cheat Sheet",
					"Lucky Charm" },
			{ "+10 Attack", "+10 Defense", "+10 Attack", "+10 Defense", "+10 Attack", "+10 Defense", "+10 Attack",
					"+10 Defense" } };

	/**
	 * This constructor takes the 4 parameter values and sets the afirst parameter
	 * to the instance variable name, the second parameter to the instance variable
	 * value,the third parameter to the instance variable type and the fourth
	 * parameter to the instance variable time.
	 * 
	 * @param name
	 *            this parameter is set to the instance variable name
	 * @param value
	 *            this parameter is set to the instance variable value
	 * @param type
	 *            this parameter is set to the instance variable type
	 * @param time
	 *            this parameter is set to the instance variable time
	 */
	public Item(int nameIndex, int value, int type, double time) {
		this.name = getItemNameFromList(nameIndex);
		this.value = value;
		this.type = type;
		this.time = time;
	}
	
	/**
	 * The method getName returns the instance variable name of type String from the
	 * class Item.
	 * 
	 * @return instance variable name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * This method takes a name from the "Item and Description" list
	 * @param index - index of the name in the array
	 * @return the name pulled from the array
	 */
	public String getItemNameFromList(int index) {
		return ITEM_NAME_AND_DESC[0][index];
	}
	
	/**
	 * This method takes a name from the "Item and Description" list
	 * @param index - index of the description in the array
	 * @return - the description from the array
	 */
	public String getItemDescFromList(int index) {
		return ITEM_NAME_AND_DESC[1][index];
	}
	/**
	 * The method getValue returns the instance variable value of type int from the
	 * class Item.
	 * 
	 * @return instance variable value
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * The method getType returns the instance variable type of type int from the
	 * class Item.
	 * 
	 * @return instance variable type
	 */
	public int getType() {
		return this.type;
	}

	/**
	 * The method getTime returns the instance variable time of type double from the
	 * class Item.
	 * 
	 * @return instance variable time
	 */
	public double getTime() {
		return this.time;
	}

}
