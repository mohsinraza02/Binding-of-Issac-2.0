
public class Item {

	private String name; // display name on map
	private int value; // value being appended in player array
	private int type; // location in player array
	private double time; // time taken from total map time
	
	/**
        * This constructor takes the 4 parameter values and sets the afirst parameter
        * to the instance variable name, the second parameter to the instance
        * variable value,the third parameter to the instance variable type and the fourth parameter
	* to the instance variable time.
        * @param a this parameter is set to the instance variable name
        * @param b this parameter is set to the instance variable value
	* @param c this parameter is set to the instance variable type
	* @param d this parameter is set to the instance variable time
        */
	public Item(String a, int b, int c, double d) {
		this.name = a;
		this.value = b;
		this.type = c;
		this.time = d;
	}
	

	/**
        * The method getName returns the instance variable name of type String from the class
        * Item.
        * @return instance variable name
        */
	public Item getName(){
		return this.name;
	}
	/**
        * The method getValue returns the instance variable value of type int from the class
        * Item.
        * @return instance variable value
        */
	public Item getValue(){
		return this.value;
	}
	/**
        * The method getType returns the instance variable type of type int from the class
        * Item.
        * @return instance variable type
        */
	public Item getType(){
		return this.type;
	}
	/**
        * The method getTime returns the instance variable time of type double from the class
        * Item.
        * @return instance variable time
        */
	public Item getTime(){
		return this.time;
	}
	
	
}
