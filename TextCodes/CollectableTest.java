package TextCodes;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class CollectableTest {

	@Test
	void testConstructor() {
		Collectable test = new Collectable(0, 1.0);
		
		assertEquals("Correct Name", "Book", test.getName());
		assertEquals("Correct output", "+10 Health", test.getDesc());
		assertEquals("Correct Value", 1.0, test.getValue(), 0.0001);	
	}

}
