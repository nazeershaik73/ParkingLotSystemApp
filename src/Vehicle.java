import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vehicle {

    String type;
    String registration;
    String color;
    String entryTimeDate;
    public Vehicle(String type, String registration, String color) {
        this.type = type;
        this.registration = registration;
        this.color = color;
        LocalDateTime eDateTime = LocalDateTime.now();
        DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        String entryTimeDate=timeFormatter.format(eDateTime);
        this.entryTimeDate=entryTimeDate;
    }
    
    public Vehicle() {
        
    }

    //method to get entry time
    public String getEntryTimeDate() {
        return entryTimeDate;
    }

    //method to set entry time if needed
    public void setEntryTimeDate(String entryTimeDate) {
        this.entryTimeDate=entryTimeDate;
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