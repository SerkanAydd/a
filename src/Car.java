public class Car {
    int id;
    double threshold;
    int occupancy;
    public Car(int id) {
        this.id = id;
        double randomDouble = Math.random() * 2 + 1;
        this.threshold = randomDouble;
    }
    public double getThreshold() {
        return threshold;
    }
    public String carName() {
        return "Car " + Integer.toString(id);
    }
    public int getOccupancy() {
        return occupancy;
    }
    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }
}
