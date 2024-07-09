package assignmenttest;

import java.util.ArrayList;

public class Booking {
    private WaitingList waitingList;
    ArrayList<Booking> bookedList = new ArrayList<Booking>();
    private int tempVipRoom, tempDeluxeRoom, tempStandardRoom;
    int[] userRoom = {0,0,0};
    private User user;
    private Room room;
    private Printer printer;

    public Booking(User user, Room room, WaitingList waitingList, Printer printer) {
        this.user = user;
        this.room = room;
        this.waitingList = waitingList;
        this.printer = printer;
    }

    //Create a booking list for the user
    public Booking(User user, int[] room) {
        this.user = user;
        this.userRoom = room;
    }
    
    public void addBookedList(User user, int[] userRoom) {
    	bookedList.add(new Booking(user, userRoom));
    }

    public ArrayList<Booking> getBookedList() {
        return bookedList;
    }
    
    public void setBookedList(ArrayList<Booking> bookedList) {
        this.bookedList =bookedList;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setBooking() {
    	tempVipRoom = room.getVip();
        tempDeluxeRoom = room.getDeluxe();
        tempStandardRoom = room.getStandard();
        int vipRoom = tempVipRoom;
        int deluxeRoom = tempDeluxeRoom;
        int standardRoom = tempStandardRoom;
        
        if (user.getMember_type().toLowerCase().equals("vip")) {
            if (user.getNumRoom() <= 3 && user.getNumRoom() > 0) {
                for (int i = 0; i < user.getNumRoom(); i++){
                    if (tempVipRoom > 0){
                        tempVipRoom -= 1;
	                    room.bookRoom("vip");
                        userRoom[0] +=1;
                    }
                    else if (tempDeluxeRoom > 0) {
                        tempDeluxeRoom -= 1;
	                    room.bookRoom("deluxe");
	                    userRoom[1] +=1;
                    }
                    else if (tempStandardRoom > 0) {
                        tempStandardRoom -= 1;
	                    room.bookRoom("standard");
	                    userRoom[2] +=1;
                    }
                    else {
                        waitingList.addWaiting(user);
                        userRoom[0] = 0;
                        userRoom[1] = 0;
                        userRoom[2] = 0;
                        room.setVip(vipRoom);
                        room.setDeluxe(deluxeRoom);
                        room.setStandard(standardRoom);
                        printer.printInfo("Added to Waiting List");
                        break;
                    }
                }
            }
            else {
                throw new IllegalArgumentException();
            }
        }
        else if (user.getMember_type().toLowerCase().equals("member")) {
            if (user.getNumRoom() <= 2 && user.getNumRoom() > 0) {
                for (int i = 0; i < user.getNumRoom(); i++){
                   if (tempDeluxeRoom > 0) {
                        tempDeluxeRoom -= 1;
	                    room.bookRoom("deluxe");
	                    userRoom[1] +=1;
                    }
                    else if (tempStandardRoom > 0) {
                        tempStandardRoom -= 1; 
	                    room.bookRoom("standard");
	                    userRoom[2] +=1;
                    } else if (user.getExcl_reward() && tempVipRoom > 0 && (user.getNumRoom() == 1 || i== 1)) {
                        tempVipRoom -= 1;
                        user.setExcl_reward(false);
	                    room.bookRoom("vip");
                        userRoom[0] +=1;
                    }
                    else{
                        waitingList.addWaiting(user);
                        userRoom[0] = 0;
                        userRoom[1] = 0;
                        userRoom[2] = 0;
                        room.setVip(vipRoom);
                        room.setDeluxe(deluxeRoom);
                        room.setStandard(standardRoom);
                        printer.printInfo("Added to Waiting List");
                        break;
                    }
                }
            }
            else {
                throw new IllegalArgumentException();
            }
        }
        else if (user.getMember_type().toLowerCase().equals("normal")) {
            if (user.getNumRoom() == 1) {
                if (tempStandardRoom > 0) {
                    tempStandardRoom -= 1;
                    room.bookRoom("standard");
                    userRoom[2] +=1;
                 }
                else {
                    waitingList.addWaiting(user);
                    printer.printInfo("Added to Waiting List");
                }
            }
            else {
                throw new IllegalArgumentException();
            }
        }
        else {
            throw new IllegalArgumentException();
        }
        if (userRoom[0] != 0 || userRoom[1] != 0 || userRoom[2] !=0) {
            bookedList.add(new Booking(user,userRoom));
            printer.printInfo("Booked Succesfully");
        }
        
    }
    
    public int[] getUserRoom() {
    	return userRoom;
    }
    
    public void cancelBooking(User user) {
        boolean userFound = false;
        if (waitingList.getWaiting(user.getMember_type()).contains(user)) {
            waitingList.removeWaiting(user);
            userFound = true;
        }
        for (int i = 0; i < bookedList.size(); i++) {
            if (bookedList.get(i).getUser() == user) {
                bookedList.remove(i);
                room.setVip(room.getVip()+userRoom[0]);
                room.setDeluxe(room.getDeluxe()+userRoom[1]);
                room.setStandard(room.getStandard()+userRoom[2]);
                userFound = true;
            }
        }
        
        if (userFound == false)
            throw new IllegalArgumentException();
            
    }
}
