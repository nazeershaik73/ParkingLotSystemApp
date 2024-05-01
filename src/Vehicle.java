import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Vehicle {

    String type;
    String registration;
    String color;
    String entryTime;
    public Vehicle(String type, String registration, String color) {
        this.type = type;
        this.registration = registration;
        this.color = color;
        LocalTime eTime = LocalTime.now();
        DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("HH:mm:ss");
        String entryTime=timeFormatter.format(eTime);
        this.entryTime=entryTime;
    }
    
    public Vehicle() {
        
    }

    //method to get entry time
    public String getEntryTime() {
        return entryTime;
    }

    //method to set entry time if needed
    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    @Override
    public String toString() {
        return "Vehicle [type=" + type + ", registration=" + registration + ", color=" + color + "]";
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getRegistration() {
        return registration;
    }
    public void setRegistration(String registration) {
        this.registration = registration;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    // public long calculateDurationOfStay()
    // {
    //     long currentTime=System.currentTimeMillis();
    //     System.out.println("current time : "+currentTime);
    //     System.out.println("entry time : "+entryTime);
    //     // return currentTime-entryTime;
    // }   
}