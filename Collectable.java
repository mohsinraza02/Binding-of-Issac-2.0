
public class Collectable {
	
	private String name; // display name on map
	private String desc;
	private double value; // value being appended in player array
	private int type; // location in player array
	/**
	 * This is a 2D array called the "Collectable Item and Description" list which has 8 items and 8 descriptions in it.
	 * These items are meant to be collected by the player when found in the map and can be used in the Final Battle.
	 */
	private final String[][] COLLECTABLE_ITEM_NAME_AND_DESC = {
			{"Book", "Cheat Sheet","Sharpener", "Pencil", "Eraser","Pen", "Water Bottle", "Calculator" },
			{"+10 Health", "+10 Health", "+10 Health", "+10 Health", "+10 Health", "+10 Health", "+10 Health", "+10 Health"}};
	
	
	/**
         * This constructor takes 3 parameter values and sets the first parameter
         * to the instance variables name and desc,the second parameter to the instance
         * variable value and the third parameter to the instance variable type.
	 * 
         * @param nameIndex  
	 * 		   this parameter is set to the instance variable name and the instance variable desc
         * @param value
	 *		   this parameter is set to the instance variable value
	 * @param type 
	 *		   this parameter is set to the instance variable type
         */
	public Collectable(int nameIndex, double value, int type){
		this.name = getItemNameFromListCollectable(nameIndex);
		this.desc = getItemDescFromListCollectable(nameIndex);
		this.value = value;
		this.type = type;
	}
	
	/**
	 * This method takes a name from the "Collectable Item and Description" list
	 *
	 * @param index - index of the name in the array
	 *
	 * @return the name pulled from the array
	 */
	public String getItemNameFromListCollectable(int index) {
		return COLLECTABLE_ITEM_NAME_AND_DESC[0][index];
	}
	
	/**
	 * This method takes a description from the "Collectables Item and Description" list
	 *
	 * @param index - index of the description in the array
	 *
	 * @return - the description pulled from the array
	 */
	public String getItemDescFromListCollectable(int index) {
		return COLLECTABLE_ITEM_NAME_AND_DESC[1][index];
	}
	
	/**
	 * The method getName returns the instance variable name of type String.
	 * 
	 * @return instance variable name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * The method getDesc returns the instance variable desc of type String.
	 * 
	 * @return instance variable desc
	 */
	
	public String getDesc() {
		return this.desc;
	}
	
	/**
	 * The method getValue returns the instance variable value of type int.
	 * 
	 * @return instance variable value
	 */
	public double getValue() {
		return this.value;
	}

	/**
	 * The method getType returns the instance variable type which is an int.
	 * 
	 * @return instance variable type
	 */
	public int getType() {
		return this.type;
	}
	
}
