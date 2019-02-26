
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
	
	public static void main(String[] args){
		//Strength
		Item book = new Item("Book", 1, 0, 2.0);
		Item ta = new Item("TA", 3, 0, 5.0);
		//Defence
		Item games = new Item("Games", 1, 1, 3.5);
		Item Sleep = new Item("Sleep", 5, 1, 8.0);
		//Luck
		Item sacrifice = new Item("Sacifice to the Gods!", 5, 2, 5.5);
		Item charm= new Item("Charm", 3, 2, 0.5);
		
	}
}
