
public class Instant {

	private String name; // display name on map
	private String desc;
	private double value; // value being appended in player array
	private int type; // location in player array
	private double time; // time taken from total map time
	
	/**
	 * This is a 2D array called the "Instant Item and Description" list which has 8 items and 8 descriptions in it.
	 * These items are meant to be used instantly by the player when found in the map.
	 */
	private final String[][] INSTANT_ITEM_NAME_AND_DESC = {
			{ "Game", "TA", "Bed", "GUIDE: Sacrifice to the Gods!", "Lucky Charm", "Study Guide", "Pizza","Super Smoothie" },
			{ "+10 Attack", "+10 Defence", "+10 Speed", "+10 Luck","+10 Attack", "+10 Defence", "+10 Speed", "+10 Luck"}};

	
	/**
	 * This constructor takes the 4 parameter values and sets the first parameter
	 * to the instance variables name and desc, the second parameter to the instance variable
	 * value,the third parameter to the instance variable type and the fourth
	 * parameter to the instance variable time.
	 * 
	 * @param name
	 *            this parameter is set to the instance variables name and desc
	 * @param value
	 *            this parameter is set to the instance variable value
	 * @param type
	 *            this parameter is set to the instance variable type
	 * @param time
	 *            this parameter is set to the instance variable time
	 */
	public Instant(int nameIndex, double value, int type, double time) {
		this.name = getItemNameFromListInstant(nameIndex);
		this.desc = getItemDescFromListInstant(nameIndex);
		this.value = value;
		this.type = type;
		this.time = time;
	}
	
	/**
	 * This method takes a name from the "Instant Item and Description" list
	 * @param index 
	 *		index of the name in the array
	 * @return the name pulled from the array
	 */
	public String getItemNameFromListInstant(int index) {
		return INSTANT_ITEM_NAME_AND_DESC[0][index];
	}
	
	/**
	 * This method takes a name from the "Instant Item and Description" list
	 * @param index 
	 * 		index of the description in the array
	 * @return - the description pulled from the array
	 */
	public String getItemDescFromListInstant(int index) {
		return INSTANT_ITEM_NAME_AND_DESC[1][index];
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

	/**
	 * The method getTime returns the instance variable time of type double.
	 * 
	 * @return instance variable time
	 */
	public double getTime() {
		return this.time;
	}
	
}
