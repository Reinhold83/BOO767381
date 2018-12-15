
package ie.ulster.exam;


public class Bookings1 {
    private String destination;
    private int capacity;
    private String feature;
    private boolean access;
  
    Bookings1(String d, int c, String f, boolean a) {
        this.destination = d;
        this.capacity = c;
        this.feature = f;
        this.access = a;
    }
    public String getDestination() {
        return destination;
    }
    public int getCapacity() {
        return capacity;
    }
    public String getFeature() {
        return feature;
    }
    public boolean getAccesibility() {
        return access;
    }
    
}