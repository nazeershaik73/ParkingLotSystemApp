
public class Slot {
    String type;
    Vehicle vehicle;
    String ticketId;
    public Slot(String type) {
        this.type = type;
        this.vehicle = null;
        this.ticketId = null;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public String getTicketId() {
        return ticketId;
    }
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
     
}
