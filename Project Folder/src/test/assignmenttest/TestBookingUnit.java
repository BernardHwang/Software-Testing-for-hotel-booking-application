package assignmenttest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TestBookingUnit{
	private Object[] getParamsForTestSetBooking() {
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
			new Object[] {"Vip",false, 1, 1, 0, 0, nums100, 0, 1, 1, 0, 0},
			new Object[] {"Vip",false, 1, 0, 1, 0, nums010, 0, 1, 0, 1, 0},
			new Object[] {"Vip",false, 1, 0, 0, 1, nums001, 0, 1, 0, 0, 1},
			new Object[] {"Vip",false, 1, 0, 0, 0, nums000, 1, 0, 0, 0, 0},
			new Object[] {"Vip",false, 2, 2, 0, 0, nums200, 0, 1, 2, 0, 0},
			new Object[] {"Vip",false, 2, 1, 1, 0, nums110, 0, 1, 1, 1, 0},
			new Object[] {"Vip",false, 2, 1, 0, 1, nums101, 0, 1, 1, 0, 1},
			new Object[] {"Vip",false, 2, 1, 0, 0, nums000, 1, 0, 1, 0, 0},
			new Object[] {"Vip",false, 2, 0, 2, 0, nums020, 0, 1, 0, 2, 0},
			new Object[] {"Vip",false, 2, 0, 1, 1, nums011, 0, 1, 0, 1, 1},
			new Object[] {"Vip",false, 2, 0, 1, 0, nums000, 1, 0, 0, 1, 0},
			new Object[] {"Vip",false, 2, 0, 0, 2, nums002, 0, 1, 0, 0, 2},
			new Object[] {"Vip",false, 2, 0, 0, 1, nums000, 1, 0, 0, 0, 1},
			new Object[] {"Vip",false, 3, 3, 0, 0, nums300, 0, 1, 3, 0, 0},
			new Object[] {"Vip",false, 3, 2, 1, 0, nums210, 0, 1, 2, 1, 0},
			new Object[] {"Vip",false, 3, 2, 0, 1, nums201, 0, 1, 2, 0, 1},
			new Object[] {"Vip",false, 3, 2, 0, 0, nums000, 1, 0, 2, 0, 0},
			new Object[] {"Vip",false, 3, 1, 2, 0, nums120, 0, 1, 1, 2, 0},
			new Object[] {"Vip",false, 3, 1, 1, 1, nums111, 0, 1, 1, 1, 1},
			new Object[] {"Vip",false, 3, 1, 1, 0, nums000, 1, 0, 1, 1, 0},
			new Object[] {"Vip",false, 3, 1, 0, 2, nums102, 0, 1, 1, 0, 2},
			new Object[] {"Vip",false, 3, 1, 0, 1, nums000, 1, 0, 1, 0, 1},
			new Object[] {"Vip",false, 3, 0, 3, 0, nums030, 0, 1, 0, 3, 0},
			new Object[] {"Vip",false, 3, 0, 2, 1, nums021, 0, 1, 0, 2, 1},
			new Object[] {"Vip",false, 3, 0, 2, 0, nums000, 1, 0, 0, 2, 0},
			new Object[] {"Vip",false, 3, 0, 1, 2, nums012, 0, 1, 0, 1, 2},
			new Object[] {"Vip",false, 3, 0, 1, 1, nums000, 1, 0, 0, 1, 1},
			new Object[] {"Vip",false, 3, 0, 0, 3, nums003, 0, 1, 0, 0, 3},
			new Object[] {"Vip",false, 3, 0, 0, 2, nums000, 1, 0, 0, 0, 2},
			//Member
			new Object[] {"Member",false, 1, 0, 1, 0, nums010, 0, 1, 0, 1, 0},
			new Object[] {"Member",false, 1, 0, 0, 1, nums001, 0, 1, 0, 0, 1},
			new Object[] {"Member",true, 1, 1, 0, 0, nums100, 0, 1, 1, 0, 0},
			new Object[] {"Member",false, 1, 1, 0, 0, nums000, 1, 0, 0, 0, 0},
			new Object[] {"Member",false, 1, 0, 0, 0, nums000, 1, 0, 0, 0, 0},
			new Object[] {"Member",false, 2, 0, 2, 0, nums020, 0, 1, 0, 2, 0},
			new Object[] {"Member",false, 2, 0, 1, 1, nums011, 0, 1, 0, 1, 1},
			new Object[] {"Member",true, 2, 1, 1, 0, nums110, 0, 1, 1, 1, 0}, 
			new Object[] {"Member",false, 2, 1, 1, 0, nums000, 1, 0, 0, 1, 0}, 
			new Object[] {"Member",false, 2, 0, 1, 0, nums000, 1, 0, 0, 1, 0}, 
			new Object[] {"Member",false, 2, 0, 0, 2, nums002, 0, 1, 0, 0, 2}, 
			new Object[] {"Member",true, 2, 1, 0, 1, nums101, 0, 1, 1, 0, 1}, 
			new Object[] {"Member",false, 2, 1, 0, 1, nums000, 1, 0, 0, 0, 1},
			new Object[] {"Member",false, 2, 0, 0, 0, nums000, 1, 0, 0, 0, 0}, 		
			//Normal
			new Object[] {"Normal",false, 1, 0, 0, 1, nums001, 0, 1, 0, 0, 1},
			new Object[] {"Normal",false, 1, 0, 0, 0, nums000,1,0,0,0,0},
		};
	}

	@Test
	@Parameters(method="getParamsForTestSetBooking")
	public void testSetBooking(String member_type, boolean excl_reward, int numOfRoom,int vipRoom, int deluxeRoom, int standRoom, int[] expectedResult, int times, int size, int vipTimes, int deluxeTimes, int standardTimes ) {
		Room rMock = mock(Room.class);
		when(rMock.getVip()).thenReturn(vipRoom);
		when(rMock.getDeluxe()).thenReturn(deluxeRoom);
		when(rMock.getStandard()).thenReturn(standRoom);
		
		User user = mock(User.class);
		when(user.getMember_type()).thenReturn(member_type);
		when(user.getNumRoom()).thenReturn(numOfRoom);
		when(user.getExcl_reward()).thenReturn(excl_reward);
		
		WaitingList waitingList = mock(WaitingList.class);
		
		Printer printer = mock(Printer.class);
		
		Booking booking = new Booking(user, rMock, waitingList, printer);
		
		booking.setBooking();
		
		assertArrayEquals(expectedResult, booking.getUserRoom());
		
		verify(rMock,times(vipTimes)).bookRoom("vip");
		verify(rMock,times(deluxeTimes)).bookRoom("deluxe");
		verify(rMock,times(standardTimes)).bookRoom("standard");
		
		int[] result = booking.getUserRoom();
		if(result[0] != 0 || result[1] != 0 || result[2] != 0 )
			verify(printer).printInfo("Booked Succesfully");
		else
            verify(printer).printInfo("Added to Waiting List");
		
		verify(waitingList, times(times)).addWaiting(user);
		assertEquals(size, booking.getBookedList().size());
	}
	
	private Object[] getParamsForTestSetBookingInvalid() {
		
		return new Object[] {
			//VIP 
			new Object[] {"Vip",false, 0, 0, 0, 3},
			new Object[] {"Vip",false, 4, 0, 0, 3},
			//Member
			new Object[] {"Member",false, 0, 0, 0, 3},
			new Object[] {"Member",false, 3, 0, 0, 3},
			//Normal
			new Object[] {"Normal",false, 0, 0, 0, 0},
			new Object[] {"Normal",false, 2, 0, 0, 0},

			new Object[] {"",false, 2, 0, 0, 0},
		};
	}
	@Test (expected = IllegalArgumentException.class)
	@Parameters(method="getParamsForTestSetBookingInvalid")
	public void testSetBookingInvalid(String member_type, boolean excl_reward, int numOfRoom, int vipRoom, int deluxeRoom, int standRoom ) {
		Room rMock = mock(Room.class);
		when(rMock.getVip()).thenReturn(vipRoom);
		when(rMock.getDeluxe()).thenReturn(deluxeRoom);
		when(rMock.getStandard()).thenReturn(standRoom);

		User user = mock(User.class);
		when(user.getMember_type()).thenReturn(member_type);
		when(user.getNumRoom()).thenReturn(numOfRoom);
		when(user.getExcl_reward()).thenReturn(excl_reward);
		
		WaitingList waitingList = mock(WaitingList.class);
		
		Printer printer = mock(Printer.class);
		
		Booking booking = new Booking(user, rMock, waitingList, printer);
		
		booking.setBooking();
	}

	private Object[] getParamsForTestCancelBooking() {
    	User user1 = new User("kok1","vip", true, 1);
		User user2 = new User("kok2","vip", true, 2);

    	int[] num = {0,0,0};

		Booking booking1 = new Booking(user1, num);
		Booking booking2 = new Booking(user2, num);

        return new Object[] {
			new Object[]{user1, booking1, new ArrayList<Booking>(){{ add(booking1); }}, new ArrayList<User>(){{ add(user1); add(user2); }}},
			new Object[]{user1, booking1, new ArrayList<Booking>(){{ add(booking2); }}, new ArrayList<User>(){{add(user1);  add(user2); }}},
			new Object[]{user1, booking1, new ArrayList<Booking>(){{ add(booking1); add(booking2); }}, new ArrayList<User>()},
			new Object[]{user1, booking1, new ArrayList<Booking>(){{ add(booking1); add(booking2);}}, new ArrayList<User>(){{ add(user1); add(user2); }}},
			new Object[]{user1, booking1, new ArrayList<Booking>(), new ArrayList<User>(){{add(user1); }}},
			new Object[]{user1, booking1, new ArrayList<Booking>(){{ add(booking1); add(booking2); }}, new ArrayList<User>(){{ add(user2); }}}
        };
    }

    @Test
    @Parameters(method="getParamsForTestCancelBooking")
    public void testCancelBooking(User user, Booking book, ArrayList<Booking> bookList, ArrayList<User> waitList) {
    	Room rMock = mock(Room.class);
		WaitingList waitingList = new WaitingList();
		Printer printer = mock(Printer.class);
		Booking booking = new Booking(user, rMock, waitingList, printer);
		
        booking.bookedList = bookList;
		if (user.getMember_type().toLowerCase().equals("vip"))
			waitingList.VIP = waitList;
        else if (user.getMember_type().toLowerCase().equals("member"))
			waitingList.member = waitList;
        else if (user.getMember_type().toLowerCase().equals("normal"))
			waitingList.normal = waitList;
        // Perform the action to be tested
        booking.cancelBooking(user);

        // Assert the expected results
        assertFalse(booking.getBookedList().contains(book));
        assertFalse(waitingList.getWaiting(user.getMember_type()).contains(user));
    }
    
    private Object[] getParamsForTestCancelBookingInvalid() {
    	User user1 = new User("kok1","vip", true, 1);
    	User user2 = new User("kok2","vip", true, 2);
    	

    	int[] num = {0,0,0};

		Booking booking1 = new Booking(user1, num);
		Booking booking2 = new Booking(user2, num);


        return new Object[] {
        		new Object[]{user1, booking1, new ArrayList<Booking>(), new ArrayList<User>()},
				new Object[]{user1, booking1, new ArrayList<Booking>(){{ add(booking2); }}, new ArrayList<User>(){{ add(user2); }}},
				new Object[]{user1, booking1, new ArrayList<Booking>(), new ArrayList<User>(){{ add(user2); }}},
				new Object[]{user1, booking1, new ArrayList<Booking>(){{ add(booking2); }}, new ArrayList<User>()},
        };
    }
	// Both bookedList and waitingList are empty
    @Test (expected = IllegalArgumentException.class)
    @Parameters(method="getParamsForTestCancelBookingInvalid")
    public void testCancelBookingInvalid(User user, Booking book, ArrayList<Booking> bookList, ArrayList<User> waitList) {
        WaitingList waitingList = mock(WaitingList.class);
        Room room = new Room();
        Printer printer = mock(Printer.class);
		
		Booking booking = new Booking(user, room, waitingList, printer);
		booking.bookedList = bookList;
		if (user.getMember_type().toLowerCase().equals("vip"))
			waitingList.VIP = waitList;
        else if (user.getMember_type().toLowerCase().equals("member"))
			waitingList.member = waitList;
        else if (user.getMember_type().toLowerCase().equals("normal"))
			waitingList.normal = waitList;

        // Perform the action to be tested
        booking.cancelBooking(user);
    }
}