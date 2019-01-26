package yordansyordanov;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class CarTest {

    @Test
    public void reserve() {

        Car car = new Car(Car.CarType.SMALL);
        TimePeriod TimePeriod = new TimePeriod(new Date(), 3);
        assert car.reserve(TimePeriod);
    }

    @Test
    public void reserveOverlapping(){
        Car car = new Car(Car.CarType.SMALL);
        TimePeriod TimePeriod = new TimePeriod(new Date(), 3);
        TimePeriod overlappingTimePeriod = new TimePeriod(DateUtils.addDays(TimePeriod.getStart(), 1), 5);

        assert car.reserve(TimePeriod);
        assert car.reserve(overlappingTimePeriod) == false;
    }

    @Test
    public void reserveNonOverlapping(){
        Car car = new Car(Car.CarType.SMALL);
        TimePeriod TimePeriod = new TimePeriod(new Date(), 3);
        TimePeriod TimePeriod2 = new TimePeriod(DateUtils.addDays(new Date(), 10), 5);

        assert car.reserve(TimePeriod);
        assert car.reserve(TimePeriod2);
    }

    @Test
    public void reserveBackToBack(){
        Car car = new Car(Car.CarType.SMALL);
        TimePeriod TimePeriod = new TimePeriod(new Date(), 3);
        TimePeriod TimePeriod2 = new TimePeriod(TimePeriod.getEnd(), DateUtils.addDays(TimePeriod.getEnd(), 5));

        assert car.reserve(TimePeriod);
        assert car.reserve(TimePeriod2) == false; // end1 = start2
        TimePeriod TimePeriod3 = new TimePeriod(new Date(TimePeriod2.getStart().getTime() + 1L), TimePeriod2.getEnd());  // add 1 mls
        assert car.reserve(TimePeriod3);
    }
}