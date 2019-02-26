
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
	
	public Item getItem() {
		Item box = new Item(this.name, this.value, this.type, this.time);
		return box;
	}
	
}
