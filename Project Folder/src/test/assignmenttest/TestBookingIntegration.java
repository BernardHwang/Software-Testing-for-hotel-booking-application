package assignmenttest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TestBookingIntegration {
	public Object[] getParamsForTestBooking() {
	    ArrayList<Booking> expectedArrayListAfterCancel = new ArrayList<>();
	    ArrayList<User> expectedWaitingListAfterCancel = new ArrayList<>(); 

		User vipUser1 = new User("kok4","vip", false, 1);
    	User vipUser2 = new User("kok5","vip", false, 2);
    	User vipUser3 = new User("kok6","vip", false, 3);
    	
    	User memberUser1 = new User("ys1","member", true, 1);
		User memberUser2 = new User("ys2","member", true, 2);
    	User memberUser2_2 = new User("ys2","member", true, 2);
    	User memberUser2_3 = new User("ys2","member", true, 2);
    	User memberUser3 = new User("ys3","member", false, 1);
    	User memberUser4 = new User("ys4","member", false, 2);
    	
    	User normalUser = new User("bernard","normal", false, 1);

		//num of room booked by user
		int[] nums100 = {1, 0, 0};
		int[] nums010 = {0, 1, 0};
		int[] nums001 = {0, 0, 1};
		int[] nums000 = {0, 0, 0};
		int[] nums200 = {2, 0, 0};
		int[] nums110 = {1, 1, 0};
		int[] nums101 = {1, 0, 1};
		int[] nums020 = {0, 2, 0};
		int[] nums011 = {0, 1, 1};
		int[] nums002 = {0, 0, 2};
		int[] nums300 = {3, 0, 0};
		int[] nums210 = {2, 1, 0};
		int[] nums201 = {2, 0, 1};
		int[] nums120 = {1, 2, 0};
		int[] nums111 = {1, 1, 1};
		int[] nums102 = {1, 0, 2};
		int[] nums030 = {0, 3, 0};
		int[] nums021 = {0, 2, 1};
		int[] nums012 = {0, 1, 2};
		int[] nums003 = {0, 0, 3};
		
		return new Object[] {
			//VIP 
			new Object[] {vipUser1, 1, 0, 0, nums100, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser1, 0, 1, 0, nums010, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser1, 0, 0, 1, nums001, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser1, 0, 0, 0, nums000, 0, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser2, 2, 0, 0, nums200, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser2, 1, 1, 0, nums110, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser2, 1, 0, 1, nums101, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser2, 1, 0, 0, nums000, 0, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser2, 0, 2, 0, nums020, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser2, 0, 1, 1, nums011, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser2, 0, 1, 0, nums000, 0, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser2, 0, 0, 2, nums002, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser2, 0, 0, 1, nums000, 0, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser3, 3, 0, 0, nums300, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser3, 2, 1, 0, nums210, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser3, 2, 0, 1, nums201, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser3, 2, 0, 0, nums000, 0, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser3, 1, 2, 0, nums120, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser3, 1, 1, 1, nums111, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser3, 1, 1, 0, nums000, 0, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser3, 1, 0, 2, nums102, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser3, 1, 0, 1, nums000, 0, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser3, 0, 3, 0, nums030, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser3, 0, 2, 1, nums021, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser3, 0, 2, 0, nums000, 0, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser3, 0, 1, 2, nums012, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser3, 0, 1, 1, nums000, 0, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser3, 0, 0, 3, nums003, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {vipUser3, 0, 0, 2, nums000, 0, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			//Member
			new Object[] {memberUser3, 0, 1, 0, nums010, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {memberUser3, 0, 0, 1, nums001, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {memberUser1, 1, 0, 0, nums100, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {memberUser3, 1, 0, 0, nums000, 0, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {memberUser3, 0, 0, 0, nums000, 0, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {memberUser4, 0, 2, 0, nums020, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {memberUser4, 0, 1, 1, nums011, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {memberUser2, 1, 1, 0, nums110, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel}, 
			new Object[] {memberUser4, 1, 1, 0, nums000, 0, expectedArrayListAfterCancel, expectedWaitingListAfterCancel}, 
			new Object[] {memberUser4, 0, 1, 0, nums000, 0, expectedArrayListAfterCancel, expectedWaitingListAfterCancel}, 
			new Object[] {memberUser4, 0, 0, 2, nums002, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel}, 
			new Object[] {memberUser2_2, 1, 0, 1, nums101, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel}, 
			new Object[] {memberUser4, 1, 0, 1, nums000, 0, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {memberUser4, 0, 0, 0, nums000, 0, expectedArrayListAfterCancel, expectedWaitingListAfterCancel}, 		
			//Normal
			new Object[] {normalUser, 0, 0, 1, nums001, 1, expectedArrayListAfterCancel, expectedWaitingListAfterCancel},
			new Object[] {normalUser, 0, 0, 0, nums000, 0, expectedArrayListAfterCancel, expectedWaitingListAfterCancel}
		};
	}

    @Test
    @Parameters(method="getParamsForTestBooking")
    public void testBookingIntegration(User user, int vipRoom, int deluxeRoom, int standardRoom, int[] numOfRoomBooked, int size, ArrayList<Booking> expectedArrayListAfterCancel,ArrayList<User> expectedWaitingListAfterCancel) {
    	Room rMock = mock(Room.class);
		when(rMock.getVip()).thenReturn(vipRoom);
		when(rMock.getDeluxe()).thenReturn(deluxeRoom);
		when(rMock.getStandard()).thenReturn(standardRoom);
		
		WaitingList waitingList = new WaitingList();
		
		Printer printer = mock(Printer.class);
		
		Booking booking = new Booking(user, rMock, waitingList, printer);
		
		boolean initiateExcl_reward = user.getExcl_reward();

		booking.setBooking();
		
		if (user.getMember_type().toLowerCase().equals("member") && initiateExcl_reward == true && booking.getUserRoom()[0] == 1) 
			assertFalse(user.getExcl_reward());

		assertArrayEquals(numOfRoomBooked, booking.getUserRoom());
		assertEquals(size, booking.getBookedList().size());
        // Perform the action to be tested
        booking.cancelBooking(user);

        // Assert the expected results
        assertEquals(expectedArrayListAfterCancel, booking.getBookedList());
        assertEquals(expectedWaitingListAfterCancel, waitingList.getWaiting(user.getMember_type()));
    }

	public Object[] getParamsForTestBookingInvalid() {
		User vipUser1 = new User("kok4","vip", false, 0);
    	User vipUser2 = new User("kok6","vip", false, 4);
    	
    	User memberUser1 = new User("ys3","member", false, 0);
    	User memberUser2 = new User("ys4","member", false, 3);
    	
    	User normalUser1 = new User("bernard","normal", false, 0);
		User normalUser2 = new User("bernard","normal", false, 2);

		User invalidUser = new User("bernard", "hi", false, 3);

		return new Object[] {
			//VIP 
			new Object[] {vipUser1, vipUser2, 0, 0, 3},
			new Object[] {vipUser2, vipUser1, 0, 0, 3},

			//Member
			new Object[] {memberUser1, memberUser2, 0, 0, 3},
			new Object[] {memberUser2, memberUser1, 0, 0, 3},
			
			//Normal
			new Object[] {normalUser1, normalUser2, 0, 0, 0},
			new Object[] {normalUser2, normalUser1, 0, 0, 0},

			new Object[] {invalidUser, invalidUser, 0, 0, 0,}
			
		};
	}
    
	@Test (expected = IllegalArgumentException.class)
	@Parameters(method="getParamsForTestBookingInvalid")
    public void testBookingIntegrationInvalid(User user1, User user2 ,int vipRoom, int deluxeRoom, int standardRoom) {
		Room rMock = mock(Room.class);
		when(rMock.getVip()).thenReturn(vipRoom);
		when(rMock.getDeluxe()).thenReturn(deluxeRoom);
		when(rMock.getStandard()).thenReturn(standardRoom);
		
		WaitingList waitingList = new WaitingList();
		
		Printer printer = mock(Printer.class);
		
		Booking booking = new Booking(user1, rMock, waitingList, printer);
		
		booking.setBooking();
        // Perform the action to be tested
        booking.cancelBooking(user2);
    }
}
