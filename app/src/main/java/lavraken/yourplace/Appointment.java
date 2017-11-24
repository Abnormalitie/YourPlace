package lavraken.yourplace;

/**
 * Created by wishe on 24-Nov-17.
 */

public class Appointment {

    private String date, time, rName, procedure;

    public Appointment(){

    }
    public Appointment(String date, String time, String rName, String procedure){
        this.date = date;
        this.procedure = procedure;
        this.rName = rName;
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

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
}
