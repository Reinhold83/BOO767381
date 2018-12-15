public class Bookings1 {
    private String destination;
    private int capacity;
    private String feature;
    private boolean accesibility;
  
    Bookings1(String d, int c, String f, boolean a) {
        this.destination = d;
        this.capacity = c;
        this.feature = f;
        this.accesibility = a;
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
        return accesibility;
    }
    
}