package yordansyordanov;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarRental {

    List<Car> cars;
    List<Reservation> reservations;
    Scheduler scheduler;

    public CarRental(int small, int mid, int large) {

        cars = new ArrayList<Car>();
        reservations = new ArrayList<Reservation>();

        init(cars, small, CarType.SMALL);
        init(cars, mid, CarType.MEDIUM);
        init(cars, large, CarType.LARGE);

        scheduler = new Scheduler(cars);
    }

    private void init(List<Car> cars, int size, CarType type) {

        for (int i = 0; i < size; i++) {
            cars.add(new Car(type));
        }
    }


    /**
     * @param type
     * @param period
     * @return The cars of the given type available during the given period.
     */
    public List<Car> getAvailableCars(CarType type, TimePeriod period) {

        List<Car> result =  new ArrayList<Car>();

        for (Car car : cars) {
            if (car.getCarType() == type && scheduler.isFree(period, car)) {
                result.add(car);
            }
        }

        return result;
    }


    /**
     * @param type
     * @param period
     * @param customer
     * @return Attempts to reserve a car of the given type for the given period if
     * a car is available.
     */
    public Reservation reserve(CarType type, TimePeriod period, Customer customer) {

        Reservation result = null;

        for (Car car : getAvailableCars(type, period)) {
            if (scheduler.reserve(period, car)) {
                result =  new Reservation(car, customer, period);
                reservations.add(result);
                break;
            }
        }

        return result;
    }

    public void DisplayReservations(){

        for(Reservation reservation : reservations){
            System.out.println(reservation);
        }
    }


    public static void main(String... args){

        CarRental carRental = new CarRental(3,2,1);
        TimePeriod timePeriod = new TimePeriod(new Date(), 3);
        Customer customer = new Customer("Charles","River");

        carRental.reserve(CarType.SMALL, timePeriod, customer);
        carRental.reserve(CarType.MEDIUM, timePeriod, customer);
        carRental.reserve(CarType.LARGE, timePeriod, customer);

        carRental.DisplayReservations();
    }


}
