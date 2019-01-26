package yordansyordanov;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class CarRentalTest {

    CarRental carRental;
    TimePeriod timePeriod;

    @Before
    public void setUp() throws Exception {
        carRental = new CarRental(3,2,1);
        timePeriod = new TimePeriod(new Date(), 3);
    }

    @After
    public  void TearDown(){
        carRental = null;
        timePeriod = null;
    }


    @Test
    public void getAvailableCars() {

        assert carRental.getAvailableCars(Car.CarType.LARGE, timePeriod) == 1;
    }

    @Test
    public void reserve() {

        Reservation reservation = carRental.reserve(Car.CarType.LARGE, timePeriod, new Customer("Charles","River Development"));

        System.out.print(reservation);

        assert reservation != null;
    }

    @Test
    public void carRentalTest() {

        assert carRental.getAvailableCars(Car.CarType.LARGE, timePeriod) == 1;

        carRental.reserve(Car.CarType.LARGE, timePeriod, new Customer("Charles","River"));

        assert carRental.getAvailableCars(Car.CarType.LARGE, timePeriod) == 0;

        assert carRental.getAvailableCars(Car.CarType.SMALL, timePeriod) == 3;

        TimePeriod period2 = new TimePeriod(DateUtils.addDays(timePeriod.getEnd(), 1), 5);

        assert carRental.getAvailableCars(Car.CarType.LARGE, period2) == 1;
    }
}