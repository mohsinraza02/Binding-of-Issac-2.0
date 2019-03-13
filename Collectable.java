
public class Collectable {
	
	private String name; // display name on map
	private String desc;
	private double value; // value being appended in player array
	private int type; // location in player array

	private final String[][] COLLECTABLE_ITEM_NAME_AND_DESC = {
			{"Book", "Cheat Sheet","Sharpener", "Pencil", "Eraser","Pen","Water Bottle","Calculator" },
			{"+10 Attack", "+10 Defense", "+10 Attack", "+10 Defense", "+10 Attack", "+10 Attack", "+10 Defence", "+10 Defence"}};
	
	public Collectable(int nameIndex, double value, int type){
		this.name = getItemNameFromListCollectable(nameIndex);
		this.desc = getItemDescFromListCollectable(nameIndex);
		this.value = value;
		this.type = type;
	}
	
	/**
	 * This method takes a name from the "Collectable Item and Description" list
	 * @param index - index of the name in the array
	 * @return the name pulled from the array
	 */
	public String getItemNameFromListCollectable(int index) {
		return COLLECTABLE_ITEM_NAME_AND_DESC[0][index];
	}
	
	/**
	 * This method takes a name from the "Collectables Item and Description" list
	 * @param index - index of the description in the array
	 * @return - the description from the array
	 */
	public String getItemDescFromListCollectable(int index) {
		return COLLECTABLE_ITEM_NAME_AND_DESC[1][index];
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
	 * Getter for the item's description
	 * @return the item's description
	 */
	public String getDesc() {
		return this.desc;
	}
	
	/**
	 * The method getValue returns the instance variable value of type int from the
	 * class Item.
	 * 
	 * @return instance variable value
	 */
	public double getValue() {
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
	
}