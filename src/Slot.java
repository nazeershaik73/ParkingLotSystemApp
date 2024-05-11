import java.util.List;

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

    public Slot findAlternateSlot(List<List<Slot>> slots){
        for(int i=0;i<slots.size();i++){
            for(int j=0;j<slots.get(i).size();j++){
                Slot currenSlot =slots.get(i).get(j);
                if(currenSlot.vehicle == null){
                    return currenSlot;
                }
            }
        }
        return null;
    }
     
}
