package assignmenttest;

public class Room {
    private int vip;
    public int getVip() {
        return vip;
    }
    public void setVip(int i) {
    	this.vip = i;
    }

    private int deluxe;
    public int getDeluxe() {
        return deluxe;
    }
    public void setDeluxe(int i) {
    	this.deluxe = i;
    }

    private int standard;
    public int getStandard() {
        return standard;
    }
    public void setStandard(int i) {
    	this.standard = i;
    }
    
    public boolean checkRoom(String roomType) {
        switch(roomType) {
        case "vip":
        	return (vip>0);
        case "deluxe":
    		return (deluxe>0);
    	case "standard":
    		return (standard>0);
    	default:
    		throw new IllegalArgumentException();
    	}
    }
    
    
    public void bookRoom(String roomType) {
    	switch(roomType) {
    	case "vip":
    		vip -= 1;
    		break;
    	case "deluxe":
    		deluxe -= 1;
    		break;
    	case "standard":
    		standard -= 1;
    		break;
    	}
    }
}

