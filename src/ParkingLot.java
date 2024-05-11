
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class ParkingLot {
    String parkingLotId;
    String url="jdbc:mysql://localhost:3306/mysql";
    String user="root";
    String pass="12345";
    private Connection connection;
    private String query;
    String createQuery="CREATE TABLE IF NOT EXISTS myrestaurant.parkedVehicles(ticketId VARCHAR(9) PRIMARY KEY, registration VARCHAR(10) NOT NULL, type VARCHAR(10) NOT NULL, color VARCHAR(10) NOT NULL, entryTime VARCHAR(12)NOT NULL)";
    List<List<Slot>> slots;
    ParkingLot(String parkingLotId, int nFloors, int noOfSlotsPerFlr) throws SQLException{
        this.parkingLotId=parkingLotId;
        slots =new ArrayList<>();
        for(int i=0;i<nFloors;i++){
            slots.add(new ArrayList<>());
            List<Slot> floorSlots=slots.get(i);
            floorSlots.add(new Slot("truck"));
            floorSlots.add(new Slot("bike"));
            floorSlots.add(new Slot("bike"));
            for(int j=3;j<noOfSlotsPerFlr;j++)
            {
                slots.get(i).add(new Slot("car"));
            }
        }
        connection =DriverManager.getConnection(url, user, pass);
    }



    //parkVehicle
    public String parkVehicle(String type, String regNo, String color)
    {
        Vehicle vehicle=new Vehicle(type, regNo, color);
        for(int i=0;i<slots.size();i++){
            for(int j=0;j<slots.get(i).size();j++){
                Slot slot=slots.get(i).get(j);
                if(slot.type.equals(type) && slot.vehicle==null){
                    slot.setVehicle(vehicle);
                    slot.setTicketId(generateTicketId(parkingLotId, i+1, j+1));
                    try{
                        String query = "INSERT INTO myrestaurant.parkedVehicles (ticketId, registration, type, color, entryTimeDate) VALUES (?,?,?,?,?)";
                        PreparedStatement pstmt=connection.prepareStatement(query);
                        pstmt.setString(1, slot.getTicketId());
                        pstmt.setString(2, regNo);
                        pstmt.setString(3, type);
                        pstmt.setString(4, color);
                        pstmt.setString(5, vehicle.getEntryTimeDate());
                        pstmt.executeUpdate();
                        return slot.getTicketId();
                    }
                    catch (SQLException e) {
                        if(e.getErrorCode() == 1062){
                        System.err.println("Duplicate tickedId found, generating new ticketId");
                        continue;
                        }
                        else{
                            e.printStackTrace();
                            return null;
                        }
                    }
                }
            }
        }
        System.err.println("No available slots found, parking is full");
        return null;
    }

    private String generateTicketId(String parkingLotId, int flr, int slno) {
       
        return parkingLotId + "_" + flr + "_" + slno;
    }


    //unPark
    public void unPark(String ticketId)
    {
        try {
            String[] extract =ticketId.split("_");
            if(extract[0].equals(parkingLotId))
            {
            int floor_id=Integer.parseInt(extract[1])-1;
            int slot_id=Integer.parseInt(extract[2])-1;
            for(int i=0;i<slots.size();i++){
                for(int j=0;j<slots.get(i).size();j++){
                    if(i==floor_id && j==slot_id)
                    {
                        Slot slot=slots.get(i).get(j);
                        query = "DELETE FROM myrestaurant.parkedvehicles WHERE ticketId=?";                        
                        Connection connection=DriverManager.getConnection(url, user, pass);
                        PreparedStatement pstmt=connection.prepareStatement(query);
                        pstmt.setString(1, ticketId);
                        int res=pstmt.executeUpdate();
                        if(res==1)
                        {
                        slot.setVehicle(null);
                        slot.setTicketId(null);
                        System.out.println("Vehicle Unparked");
                        return;
                        }
                    }
                }
            }
        }
        System.err.println("Invalid Ticket");
        } catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
        }
    }


    //getNoOfOpenSlots
    public int getNoOfOpenSlots(String type)
    {
        int countType=0;
        int count=0;
        try {
            query="SELECT * FROM myrestaurant.parkedvehicles WHERE type=?";
            Connection connection=DriverManager.getConnection(url, user, pass);
            PreparedStatement pstmt=connection.prepareStatement(query);
            pstmt.setString(1, type);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()) 
            {
                countType++;
            }
            for(int i=0;i<slots.size();i++){
                for(int j=0;j<slots.get(i).size();j++){
                    Slot slot=slots.get(i).get(j);
                    if(slot.vehicle==null && slot.type.equals(type)){
                        count++;
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count-countType;
    }


    //displayOpenSlots
    public void displayOpenSlots(String type)
    {
        try {
            query = "SELECT * FROM myrestaurant.parkedvehicles WHERE type=?";                        
            Connection connection=DriverManager.getConnection(url, user, pass);
            PreparedStatement pstmt=connection.prepareStatement(query);
            pstmt.setString(1, type);
            ResultSet rs=pstmt.executeQuery();
            Set<String> occupiedSlots=new HashSet<>();
            while (rs.next()) {
                String[] extract =rs.getString("ticketId").split("_");
                int floor_id=Integer.parseInt(extract[1])-1;
                int slot_id=Integer.parseInt(extract[2])-1;
                occupiedSlots.add(floor_id+"-"+slot_id);
                
            }
            for(int i=0;i<slots.size();i++){
                for(int j=0;j<slots.get(i).size();j++){
                    Slot slot=slots.get(i).get(j);
                    if(slot.vehicle==null && !occupiedSlots.contains(i+"-"+j)){
                        System.out.println("floor "+(i+1)+ " Slot "+(j+1)+" is Open");
                    }
                }
            }
        }  
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    //displatOccupiedSlots
    public void displayOccupiedSlots(String type)
    {
        try {
            query ="SELECT * FROM myrestaurant.parkedvehicles WHERE type=?";
            Connection connection=DriverManager.getConnection(url, user, pass);
            PreparedStatement pstmt=connection.prepareStatement(query);
            pstmt.setString(1, type);
            ResultSet rs=pstmt.executeQuery();
            Set<String> openSlots=new HashSet<>();
            while (rs.next()) {
                String[] extract =rs.getString("ticketid").split("_");
                int floor_id=Integer.parseInt(extract[1])-1;
                int slot_id=Integer.parseInt(extract[2])-1;
                openSlots.add(floor_id+"-"+slot_id);
            }
            for(int i=0;i<slots.size();i++){
                for(int j=0;j<slots.get(i).size();j++){
                    if(openSlots.contains(i+"-"+j)){
                        System.out.println("Floor "+(i+1)+" Slot "+(j+1)+" is Occupied");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
