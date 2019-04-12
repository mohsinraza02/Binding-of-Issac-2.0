package TextCodes;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void testConstructor() {
		Player test = new Player(1000.0,1000.0,1000,1000);
		
		assertEquals("Constructor Health", 1000.0, test.getHealth(), 0.0001);
		assertEquals("Constructor Attack", 1000.0, test.getAttack(), 0.0001);
		assertEquals("Constructor Health cap", 1000, test.getHealthCap(), 0.0001);
		assertEquals("Constructor Skill P", 1000, test.getSkillP(), 0.0001);
	}

	@Test
	void testSettersAndGetters() {
		Player test = new Player(1000.0,1000.0,1000,1000);
		
		test.setHealth(500.0);
		test.setAttack(500.0);
		test.setHealthCap(500);
		test.setSkillP(500);
		
		assertEquals("SettersAndGetters Health", 500.0, test.getHealth(), 0.0001);
		assertEquals("SettersAndGetters Attack", 500.0, test.getAttack(), 0.0001);
		assertEquals("CSettersAndGetters Health cap", 500, test.getHealthCap(), 0.0001);
		assertEquals("SettersAndGetters Skill P", 500, test.getSkillP(), 0.0001);
	}
	
	@Test
	void testStatsArray() {
		Player test1 = new Player(1000.0,1000.0,1000,1000);
		Instant test2 = new Instant(0, 10.0, 0, 5.0);
		
		test1.interactWithItem(test2);
		
		assertEquals("", 10.0,test1.getStat(0), 0.0001);
	}
}
