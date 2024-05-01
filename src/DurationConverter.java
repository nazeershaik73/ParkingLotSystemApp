public class DurationConverter {
    public static String convertDuration(long durationInMillis)
    {
        //calculate hours
        long hours=durationInMillis / (60*60*1000); //minutes * seconds* milliseconds
        //calculate remaining millis after subtraction hours
        long remainingMillis=durationInMillis - ( hours *60*60*1000);
        //calculate minutes
        long minutes = remainingMillis / (60*1000);
        //calculate remaining millis after subtracting minutes
        remainingMillis -= (minutes*60*1000);
        //calculate seconds
        long seconds = remainingMillis / 1000;
        return hours+":"+minutes+":"+seconds;
        // System.out.println("Duration of stay: "+hours +" hours, "+minutes+" minutes, "+seconds+" seconds");
    }
}
