import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String parkingLotId1="AMB01";
        int nFloors=4;
        int noOfSlotsPerFlr=4;
        ParkingLot parkingLot=new ParkingLot(parkingLotId1, nFloors, noOfSlotsPerFlr);
        System.out.println("Enter 0 - Park Vehicle");
        System.out.println("Enter 1 - Unpark Vehicle");
        System.out.println("Enter 2 - Get no of open slots of type");
        System.out.println("Enter 3 - Display open slots of type");
        System.out.println("Enter 4 - Display occupied slots of type");
        switch(sc.nextInt())
        {
            case 0:
                System.out.println("Type: "); 
                String type=sc.next();
                System.out.println("Registration Number: "); 
                String regNo=sc.next();
                System.out.println("Color: "); 
                String color=sc.next();
                String ticket=parkingLot.parkVehicle(type, regNo, color);
                System.out.println("Ticket Id :"+ticket);
                break;
            case 1:
                System.out.println("Enter ticket id");
                parkingLot.unPark(sc.next());
                break;
            case 2:
                System.out.println("Enter type of vehicle");
                System.out.println(parkingLot.getNoOfOpenSlots(sc.next()));
                break;
            case 3:
                System.out.println("Enter type of vehicle");
                parkingLot.displayOpenSlots(sc.next());
                break;
            case 4:
                System.out.println("Enter type of vehicle");
                parkingLot.displayOccupiedSlots(sc.next());
                break;
             default :
            break;
        }
        sc.close();
    }
}
