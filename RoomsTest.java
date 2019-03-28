package codes;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class RoomsTest {

	@Test
	void testConstructor() {
		Rooms test = new Rooms(4,4);
		
		ArrayList<Entities> mainTest = new ArrayList<Entities>();

		assertEquals("Room 4x4 is too big", mainTest, test.getRoomContents());
	}
	
	@Test
	void testGetRoomContents() {
		Rooms test = new Rooms(2,2);

		assertEquals("Made a perfect room", test.getRoomContents().get(0) != null );
		assertEquals("Made a perfect room", test.getRoomContents().get(1) != null );
		assertEquals("Made a perfect room", test.getRoomContents().get(2) != null );
	}

}
