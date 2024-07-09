package assignmenttest;

public class User{
    private String name;
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
    	if (name == null || name.equals(""))
    		throw new IllegalArgumentException();
    	
    	this.name = name;
    }

    private String member_type;
    public String getMember_type() {
        return member_type;
    }
    
    public void setMember_type(String member_type) {
    	if (member_type == null)
    		throw new IllegalArgumentException();
    	
    	if (!member_type.toLowerCase().equals("vip") && !member_type.toLowerCase().equals("member") && !member_type.toLowerCase().equals("normal"))
    		throw new IllegalArgumentException();
    	
    	this.member_type = member_type.toLowerCase();
    }

    private boolean excl_reward;
    public boolean getExcl_reward() {
        return excl_reward;
    }
    public void setExcl_reward(boolean excl_reward) {
        this.excl_reward = excl_reward;
    }

    private int numRoom;
    public int getNumRoom() {
        return numRoom;
    }
    
    public void setNumRoom(int numRoom) {
    	if (numRoom <= 0 || numRoom > 3)
    		throw new IllegalArgumentException();
    	
    	this.numRoom = numRoom;
    }
 
    public User(String name, String member_type, boolean excl_reward, int numRoom) {
    	this.name = name;
    	this.member_type = member_type.toLowerCase();
    	this.excl_reward = excl_reward;
    	this.numRoom = numRoom;
    }
    public User() {
    }
}