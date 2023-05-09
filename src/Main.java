import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        ArrayDequeue<Car> arrayDequeue = new ArrayDequeue();
        ArrayQueue<Customer> arrayQueue = new ArrayQueue();
        AList<Transaction> aList = new AList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of Car: ");
        int numberOfCar = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the number of Customer: ");
        int numberOfCustomer = Integer.parseInt(scanner.nextLine());
        for (int index = 0; index < numberOfCar; index++) {
            Car car = new Car(index);
            arrayDequeue.addToBack(car);
        }

        for (int index = 0; index < numberOfCustomer; index++) {
            Customer customer = new Customer(index);
            arrayQueue.enqueue(customer);
        }

        int numberOfCar2 = numberOfCar;
        for (int i = 0; i < 6; i++) {
            System.out.println("***********Day" + (i + 1) + "**************");
            boolean flagged = false;
            for (int index = 0; index < numberOfCar2; index++) {
                Car car = arrayDequeue.removeFront();
                if (car == null) {
                    break;
                }
                System.out.println("Current " + car.carName() + " quality " + car.getThreshold() + " is offering to");
                double thresholdOfCustomer = 4;
                int recursion = 0;
                int numberofcustomer = numberOfCustomer;
                while (car.getThreshold() < thresholdOfCustomer) {
                    Customer customer = arrayQueue.dequeue();
                    if (customer == null) {
                        System.out.println("All customer rent a car.");
                        arrayDequeue.addToFront(car);
                        flagged = true;
                        break;
                    }
                    thresholdOfCustomer = customer.getThreshold();
                    System.out.print("\t" + "Current " + customer.customerName() + " threshold " + customer.getThreshold());
                    if (car.getThreshold() >= thresholdOfCustomer) {
                        numberofcustomer = numberofcustomer - 1;
                        System.out.print("\t" + "---accepted" + "\n");
                        int aNumber = numberOfCustomer - (recursion + 1);
                        numberOfCustomer--;
                        while (aNumber > 0) {
                            Customer customer1 = arrayQueue.dequeue();
                            arrayQueue.enqueue(customer1);
                            aNumber = aNumber - 1;
                        }
                        Random rand = new Random();
                        int randomNumber = rand.nextInt(5) + 1;
                        car.setOccupancy(randomNumber);
                        Transaction<Car, Customer> transaction = new Transaction(car, customer);
                        aList.add(transaction);
                        break;
                    } else {
                        System.out.println("\t" + "---not accepted");
                        customer.setThreshold();
                        arrayQueue.enqueue(customer);
                    }
                    recursion++;
                    if (recursion == numberofcustomer) {
                        arrayDequeue.addToBack(car);
                        break;
                    }
                }
                if (flagged) {
                    break;
                }
            }



            System.out.println("Rented cars:");
            AList aList1 = new AList(500);
            for (int index = 0; index < numberOfCar; index++) {
                for (int iindex = 0; iindex < aList.getLength(); iindex++) {
                    Transaction displayedTransaction = aList.getEntry(iindex + 1);
                    Car addedCar = (Car) displayedTransaction.getCar();
                    aList1.add(addedCar.carName());
                }
            }

            for (int index = 0; index < aList.getLength(); index++) {
                Transaction displayedTransaction = aList.getEntry(index + 1);
                Car addedCar1 = (Car) displayedTransaction.getCar();
                Customer addedCustomer = (Customer) displayedTransaction.getCustomer();
                System.out.println("\t" + addedCar1.carName() + " by " + addedCustomer.customerName() + " occupancy = " + addedCar1.getOccupancy());
            }

            System.out.println("Available cars:");
            numberOfCar2 = 0;
            for (int index = 0; index < numberOfCar; index++) {
                if (!aList1.contains("Car " + Integer.toString(index))) {
                    numberOfCar2++;
                    System.out.println("\t" + "Car " + Integer.toString(index));
                }
            }

            for (int index = 0; index < aList.getLength(); index++) {
                Transaction displayedTransaction = aList.getEntry(aList.getLength() - index);
                Car addedCar1 = (Car) displayedTransaction.getCar();
                addedCar1.setOccupancy(addedCar1.occupancy - 1);
                if (addedCar1.getOccupancy() == 0) {
                    numberOfCar2++;
                    arrayDequeue.addToFront(addedCar1);
                }
            }

            for (int index = 0; index < aList.getLength(); index++) {
                Transaction displayedTransaction = aList.getEntry(index + 1);
                Car addedCar1 = (Car) displayedTransaction.getCar();
                if (addedCar1.getOccupancy() == 0) {
                    aList.remove(index + 1);
                }
            }

            System.out.println("**************End of Day**************");
            System.out.println();
        }
    }
}
