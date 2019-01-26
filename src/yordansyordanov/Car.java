package yordansyordanov;

import java.util.ArrayList;
import java.util.List;

public class Car {

    public enum CarType {SMALL, MEDIUM, LARGE}

    private List<TimePeriod> reservations; // reservations for this car
    private CarType carType;

    Car(CarType carType) {
        this.carType = carType;
        this.reservations = new ArrayList<TimePeriod>();
    }

    public CarType getCarType() {
        return carType;
    }

    /**
     * @param prd
     * @return true if the car is free (not reserved) during the entire given period
     */
    public synchronized boolean isFree(TimePeriod prd) {

        for (TimePeriod period : reservations) {
            if (period.isOverlapping(prd)) {
                return false;
            }
        }

        return true;
    }

    /**
     *  Attempts to reserve this car if it has not already been reserved for any time
     *  within the given period.
     *
     * @param reservation
     * @return true if this car is successfully reserved for the given time period, false otherwise.
     */
    public synchronized boolean reserve(TimePeriod reservation) {

        for (TimePeriod period : reservations) {
            if (period.isOverlapping(reservation)) {
                return false;
            }
        }

        reservations.add(reservation);

        return true;
    }
}
