package lavraken.yourplace;

/**
 * Created by wishe on 24-Nov-17.
 */

public class Procedures {

    private String name, duration, price;

    public Procedures(){

    }
    public Procedures(String name, String duration, String price){
        this.price = price;
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
