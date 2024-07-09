package assignmenttest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TestWaitingList {
	WaitingList wl = new WaitingList();

    private ArrayList<User> userList = new ArrayList<User>();
	private File userFile = new File("User.txt");
	public ArrayList<User> getUserList() {
		return userList;
	}
	public void checkFileExist() throws Exception {
        if (!userFile.exists()) {
            userFile.createNewFile();
        }
    }
	public void loadFile() throws Exception {
        try (BufferedReader userReader = new BufferedReader(new FileReader(userFile))) {
            String line;
            userList.clear();
            while ((line = userReader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 4) {
                    String name = fields[0].trim();
                    String memberType = fields[1].trim();
                    boolean exclRewards = Boolean.parseBoolean(fields[2].trim());
                    int numRoom = Integer.parseInt(fields[3].trim());
                    User user = new User(name, memberType, exclRewards, numRoom);
                    userList.add(user);
                } else {
                    // Handle invalid lines or missing fields
                    System.out.println("Invalid line: " + line);
                }
            }
        }
    }

private Object[] getParamsForTestAddWaiting() {
		try {
			checkFileExist();
			loadFile();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		ArrayList<User> users = getUserList();

    	
        return new Object[] {
                new Object[]{users.get(0),0,0,0},
                new Object[]{users.get(1),0,0,0},
                new Object[]{users.get(2),0,0,0},
                new Object[]{users.get(3),0,0,0},
                new Object[]{users.get(4),0,0,0},
                new Object[]{users.get(5),0,0,0},
                new Object[]{users.get(6),0,0,0},
                new Object[]{users.get(7),0,0,0},
        };
    }

	@Test
	@Parameters(method="getParamsForTestAddWaiting")
	public void testAddWaiting(User user, int vipRoom, int deluxeRoom, int standardRoom) {
	    Room rMock = mock(Room.class);
		when(rMock.getVip()).thenReturn(vipRoom);
		when(rMock.getDeluxe()).thenReturn(deluxeRoom);
		when(rMock.getStandard()).thenReturn(standardRoom);
		
		User userMock = mock(User.class);
		when(userMock.getMember_type()).thenReturn(user.getMember_type());
		when(userMock.getNumRoom()).thenReturn(user.getNumRoom());
		when(userMock.getExcl_reward()).thenReturn(user.getExcl_reward());
		
		Printer printer = mock(Printer.class);
		
		Booking booking = new Booking(userMock, rMock, wl, printer);
		
		booking.setBooking();
        assertTrue(wl.getWaiting(userMock.getMember_type()).contains(userMock));
	}

	@Test
	@Parameters(method="getParamsForTestAddWaiting")
	public void testWaitingListIntegration(User user, int vipRoom, int deluxeRoom, int standardRoom) {
	    Room rMock = mock(Room.class);
		when(rMock.getVip()).thenReturn(vipRoom);
		when(rMock.getDeluxe()).thenReturn(deluxeRoom);
		when(rMock.getStandard()).thenReturn(standardRoom);

        Printer printer = mock(Printer.class);
		
		Booking booking = new Booking(user, rMock, wl, printer);

        booking.setBooking();
        assertTrue(wl.getWaiting(user.getMember_type()).contains(user));

        booking.cancelBooking(user);
        assertFalse(wl.getWaiting(user.getMember_type()).contains(user));
	}

    private Object[] getParamsForTestAddWaitingInvalid() {
        return new Object[] {
            new Object[] { new User("kok1","hi", true, 1) },
        };
    }

    @Test (expected=IllegalArgumentException.class)
    @Parameters(method="getParamsForTestAddWaitingInvalid")
    public void testAddWaitingInvalid(User user) {
        wl.addWaiting(user);
    }
    
    private Object[] getParamsForTestRemoveWating() {
		try {
			checkFileExist();
			loadFile();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		ArrayList<User> users = getUserList();
    	
        return new Object[] {
                new Object[]{users.get(0), new ArrayList<User>(){{ add(users.get(0)); }}},
                new Object[]{users.get(1), new ArrayList<User>(){{ add(users.get(1)); }}},
                new Object[]{users.get(2), new ArrayList<User>(){{ add(users.get(2)); }}},
                new Object[]{users.get(3), new ArrayList<User>(){{ add(users.get(3)); }}},
                new Object[]{users.get(4), new ArrayList<User>(){{ add(users.get(4)); }}},
                new Object[]{users.get(5), new ArrayList<User>(){{ add(users.get(5)); }}},
                new Object[]{users.get(6), new ArrayList<User>(){{ add(users.get(6)); }}},
                new Object[]{users.get(7), new ArrayList<User>(){{ add(users.get(7)); }}},
        };
    }

    @Test
    @Parameters(method="getParamsForTestRemoveWating")
    public void testRemoveWaiting(User user, ArrayList<User> arrList) {
        if (user.getMember_type().toLowerCase().equals("vip"))
            wl.VIP = arrList;
        else if (user.getMember_type().toLowerCase().equals("member"))
            wl.member = arrList;
        else if (user.getMember_type().toLowerCase().equals("normal"))
            wl.normal = arrList;
    	wl.removeWaiting(user);
        assertFalse(wl.getWaiting(user.getMember_type()).contains(user));
    }

    private Object[] getParamsForTestRemoveWatingInvalid() {
    	User user1 = new User("kok1","vip", true, 1);
    	User user2 = new User("kok2","hi", true, 2);

        return new Object[] {
        		new Object[]{user1, new ArrayList<User>()},
                new Object[]{user2, new ArrayList<User>()},

        };
    }

    @Test (expected=IllegalArgumentException.class)
    @Parameters(method="getParamsForTestRemoveWatingInvalid")
    public void testRemoveWaitingInvalid(User user, ArrayList<User> arrList) {
        if (user.getMember_type().toLowerCase().equals("vip"))
            wl.VIP = arrList;
        else if (user.getMember_type().toLowerCase().equals("member"))
            wl.member = arrList;
        else if (user.getMember_type().toLowerCase().equals("normal"))
            wl.normal = arrList;
    	wl.removeWaiting(user);
    }
}