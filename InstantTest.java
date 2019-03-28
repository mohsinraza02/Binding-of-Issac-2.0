package codes;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InstantTest {

	@Test
	void testConstructor() {
		Instant test = new Instant(0, 1.0, 1, 1.0);
		
		assertEquals("Correct Name", "Game", test.getName());
		assertEquals("Correct output", "+10 Defense", test.getDesc());
		assertEquals("Correct Value", 1.0, test.getValue(), 0.0001);
		assertEquals("Correct Type", 1, test.getType(), 0.0001);
		assertEquals("Correct time", 1.0, test.getTime(), 0.0001);
		
	}

}
