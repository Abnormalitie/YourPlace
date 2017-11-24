package lavraken.yourplace;

/**
 * Created by wishe on 24-Nov-17.
 */

public class Appointment {

    private String date, time, rName, procedure;

    public Appointment(){

    }
    public Appointment(String date, String time, String procedure){
        this.date = date;
        this.procedure = procedure;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
}
