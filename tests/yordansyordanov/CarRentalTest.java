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
        carRental = new CarRental(3, 2, 1);
        timePeriod = new TimePeriod(new Date(), 3);
    }

    @After
    public  void TearDown(){
        carRental = null;
        timePeriod = null;
    }


    @Test
    public void getAvailableCars() {

        assert carRental.getAvailableCars(CarType.LARGE, timePeriod).size() == 1;
    }

    @Test
    public void reserve() {

        Reservation reservation = carRental.reserve(CarType.LARGE, timePeriod, new Customer("Charles","River Development"));

        assert reservation != null;

        System.out.println(reservation);
    }

    @Test
    public void carRentalTest() {

        assert carRental.getAvailableCars(CarType.LARGE, timePeriod).size() == 1;   // before reserve

        carRental.reserve(CarType.LARGE, timePeriod, new Customer("Charles","River"));

        assert carRental.getAvailableCars(CarType.LARGE, timePeriod).size() == 0;   // after reserve

        assert carRental.getAvailableCars(CarType.SMALL, timePeriod).size() == 3;   // different car type

        TimePeriod period2 = new TimePeriod(DateUtils.addDays(timePeriod.getEnd(), 1), 5);

        assert carRental.getAvailableCars(CarType.LARGE, period2).size() == 1;  // different period
    }
}