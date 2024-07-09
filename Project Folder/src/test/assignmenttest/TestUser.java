package assignmenttest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TestUser {
	User user = new User();
	@Test
	public void testGetSetName() {
		String expectedResult = "John";
		user.setName(expectedResult);
		assertEquals(expectedResult, user.getName());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetNameInvalid1() {
		user.setName(null);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetNameInvalid2() {
		user.setName("");
	}
	
	@Test
	@Parameters({"vip, vip","member,member","normal,normal","VIP,vip","Vip,vip","MEMBER,member","MemBer,member","NORMAL,normal","NormaL,normal"})
	public void testGetSetMember_type(String member_type, String expected) {
		user.setMember_type(member_type);
		assertEquals(expected, user.getMember_type());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetMember_typeInvalid1() {
		user.setMember_type(null);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetMember_typeInvalid2() {
		user.setMember_type("");
	}
	
	@Test (expected=IllegalArgumentException.class)
	@Parameters({"John","Beta","alpha","dEta","Ana","KyS"})
	public void testSetMember_typeInvalid3(String member_type) {
		user.setMember_type(member_type);
	}
	
	@Test (expected=IllegalArgumentException.class)
	@Parameters({"0","5","14","50","583","6589"})
	public void testSetMember_typeInvalid4(String member_type) {
		user.setMember_type(member_type);
	}
	
	@Test (expected=IllegalArgumentException.class)
	@Parameters({"@","#","`","&","+","$","?",","})
	public void testSetMember_typeInvalid5(String member_type) {
		user.setMember_type(member_type);
	}

	@Test
	@Parameters({"true","false"})
	public void testGetSetExcl_reward(boolean result) {
		boolean expectedResult = result;
		user.setExcl_reward(result);
		assertEquals(expectedResult, user.getExcl_reward());
	}
	
	@Test
	@Parameters({"1","2","3"})
	public void testGetSetNumRoom(int numRoom) {
		int expectedResult = numRoom;
		user.setNumRoom(numRoom);
		assertEquals(expectedResult, user.getNumRoom());
	}
	
	@Test(expected=IllegalArgumentException.class)
	@Parameters({"-12","50","100","1000"})
	public void testSetNumRoomInvalid1(int numRoom) {
		user.setNumRoom(numRoom);
	}
	
	@Test(expected=IllegalArgumentException.class)
	@Parameters({"0","4"})
	public void testSetNumRoomInvalid2(int numRoom) {
		user.setNumRoom(numRoom);
	}
	
}
