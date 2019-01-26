package yordansyordanov;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarRental {

    List<Car> cars;

    public CarRental(int small, int mid, int large) {

        cars = new ArrayList<Car>();

        init(cars, small, Car.CarType.SMALL);
        init(cars, mid, Car.CarType.MEDIUM);
        init(cars, large, Car.CarType.LARGE);
    }

    private void init(List<Car> cars, int size, Car.CarType type) {

        for (int i = 0; i < size; i++) {
            cars.add(new Car(type));
        }
    }


    public static void main(String... args){

        CarRental carRental = new CarRental(3,2,1);
        TimePeriod timePeriod = new TimePeriod(new Date(), 3);

        Reservation res = carRental.reserve(Car.CarType.LARGE, timePeriod, new Customer("Yordan","Y"));

        System.out.println(res);


    }


    /**
     * @param type
     * @param period
     * @return The cars of the given type available during the given period.
     */
    public int getAvailableCars(Car.CarType type, TimePeriod period) {

        int result = 0;

        for (Car car : cars) {
            if (car.getCarType() == type && car.isFree(period)) {
                result++;
            }
        }

        return result;
    }

    public Reservation reserve(Car.CarType type, TimePeriod period, Customer customer) {

        Reservation result = null;

        for (Car car : cars) {
            if (car.getCarType() == type && car.isFree(period) && car.reserve(period)) {
                return new Reservation(car, customer, period);
            }
        }

        return result;
    }


}
