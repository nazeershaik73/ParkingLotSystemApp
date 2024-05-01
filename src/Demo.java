// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.Statement;
// import java.time.LocalDate;
// import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Demo {
    public static void main(String[] args) {
        // //display current date, time, date & time
        // LocalDate dateObj=LocalDate.now();
        // System.out.println(dateObj);
         LocalTime timeObj=LocalTime.now();
        // System.out.println(timeObj);
        // LocalDateTime dtObj=LocalDateTime.now();
        // System.out.println("before formatting => "+dtObj);
        //  //formatting current date, time, date & time
        //  DateTimeFormatter formattedObj= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        DateTimeFormatter formattedObj= DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDateTime=timeObj.format(formattedObj);
        //  System.out.println("After formatting => "+formattedDateTime);
        System.out.println(formattedDateTime);


        //  String url="jdbc:mysql://localhost:3306/mysql";
        //  try {
        //     String query="SELECT * FROM myrestaurant.users";
        //     // Class.forName("com.mysql.cj.jdbcDriver");
        //     Connection connection=DriverManager.getConnection(url, "root", "12345");
        //     Statement stmt=connection.createStatement();
        //     ResultSet res=stmt.executeQuery(query);
        //     while (res.next()) {
        //         System.out.println("ID: "+res.getInt(1));
        //         System.out.println("Name: "+res.getString(2));
        //         System.out.println("Phone: "+res.getString(3));
        //         System.out.println("Address: "+res.getString(4));
        //         System.out.println("Password: "+res.getString(5));
        //     }
        //    System.out.println("Connection Successful");


        // } catch (Exception e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
    }

}
