package yordansyordanov;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scheduler {

    private Map<Car, List<TimePeriod>> schedule;

    public Scheduler(List<Car> cars) {

        this.schedule = new HashMap<Car, List<TimePeriod>>();

        for(Car car : cars){
            schedule.put(car, new ArrayList<TimePeriod>());
        }
    }

    /**
     * @param prd
     * @return true if the given car is free (not reserved) during the entire given period
     */
    public synchronized boolean isFree(TimePeriod prd, Car car) {

        for (TimePeriod period : schedule.get(car)) {
            if (period.isOverlapping(prd)) {
                return false;
            }
        }

        return true;
    }

    /**
     *  Attempts to reserve the given car if it has not already been reserved for any time
     *  within the given period.
     *
     * @param timePeriod
     * @return true if the car is successfully reserved for the given time period, false otherwise.
     */
    public synchronized boolean reserve(TimePeriod timePeriod, Car car) {

        if(isFree(timePeriod, car)){
            schedule.get(car).add(timePeriod);
            return true;
        } else {
            return false;
        }
    }
}
