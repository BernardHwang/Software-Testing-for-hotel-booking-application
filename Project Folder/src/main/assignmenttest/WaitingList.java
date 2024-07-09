package assignmenttest;

import java.util.ArrayList;

public class WaitingList{
    ArrayList<User> VIP = new ArrayList<User>();
    ArrayList<User> member = new ArrayList<User>();
    ArrayList<User> normal = new ArrayList<User>();
    
    public WaitingList() {
    	VIP = new ArrayList<User>();
    	member = new ArrayList<User>();
    	normal = new ArrayList<User>();
    }
    
    public void addWaiting(User user){
        if (user.getMember_type().toLowerCase().equals("vip")){
            VIP.add(user);
        }
        else if (user.getMember_type().toLowerCase().equals("member")){
            member.add(user);
        }
        else if (user.getMember_type().toLowerCase().equals("normal")){
            normal.add(user);
        }
        else{
        	throw new IllegalArgumentException();
        }
    }

    public ArrayList<User> getWaiting(String member_type){
        if (member_type.toLowerCase().equals("vip")){
            return VIP;
        }
        else if (member_type.toLowerCase().equals("member")){
            return member;
        }
        else if (member_type.toLowerCase().equals("normal")) {
            return normal;
        }
        else
        	throw new IllegalArgumentException();
    }

    public void removeWaiting(User user) {
        if (user.getMember_type().toLowerCase().equals("vip") && VIP.contains(user)){
            VIP.remove(user);
        }
        else if (user.getMember_type().toLowerCase().equals("member") && member.contains(user)){
            member.remove(user);
        }
        else if (user.getMember_type().toLowerCase().equals("normal") && normal.contains(user)){
            normal.remove(user);
        }
        else{
            throw new IllegalArgumentException();
        }
    }   
}