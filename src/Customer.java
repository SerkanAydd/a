public class Customer {
    int id;
    double threshold;
    public Customer(int id) {
        this.id = id;
        double randomDouble = Math.random() * 2 + 1;
        this.threshold = randomDouble;
    }
    public String customerName() {
        return "customer " + Integer.toString(id);
    }
    public double getThreshold() {
        return threshold;
    }
    public void setThreshold() {
        threshold = threshold * (0.9);
    }
}
