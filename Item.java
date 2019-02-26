
public class Item {

	private String name;
	private int value;
	private int type; 
	private double time;
	
	public Item(String a, int b, int c, double d) {
		this.name = a;
		this.value = b;
		this.type = c;
		this.time = d;
	}
	
	public Item getItem() {
		Item box = new Item(this.name, this.value, this.type, this.time);
		return box;
	}
	
	
}
