
public class Item {

	private String name; // display name on map
	private int value; // value being appended in player array
	private int type; // location in player array
	private double time; // time taken from total map time
	
	/* Javadoc in process
	*
	
	*/
	public Item(String a, int b, int c, double d) {
		this.name = a;
		this.value = b;
		this.type = c;
		this.time = d;
	}
	
	/* Javadoc in process
	* 
	*
	*/
	// new get methods for each of the variables
	
	public Item getName(){
		return this.name;
	}
	public Item getValue(){
		return this.value;
	}
	public Item getType(){
		return this.type;
	}
	public Item getTime(){
		return this.time;
	}
	
	
}
