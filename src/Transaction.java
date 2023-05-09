public class Transaction<T1, T2> {
    T1 car;
    T2 customer;
    public Transaction(T1 car, T2 customer) {
        this.car = car;
        this.customer = customer;
    }
    public T1 getCar() {
        return car;
    }
    public T2 getCustomer() {
        return customer;
    }
}
