package yordansyordanov;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class SchedulerTest {

    Car car;
    TimePeriod timePeriod;
    Scheduler scheduler;

    @Before
    public void setUp() throws Exception {
        car = new Car(CarType.SMALL);
        timePeriod = new TimePeriod(new Date(), 3);
        scheduler = new Scheduler(Arrays.asList(new Car[]{car}));
    }

    @After
    public void tearDown() throws Exception {
        car = null;
        timePeriod = null;
        scheduler = null;
    }

    @Test
    public void isFree() {
        assert scheduler.isFree(timePeriod, car);
    }



    @Test
    public void reserve() {

        assert scheduler.reserve(timePeriod, car);
    }

    @Test
    public void reserveOverlapping(){


        TimePeriod overlappingTimePeriod = new TimePeriod(DateUtils.addDays(timePeriod.getStart(), 1), 5);

        assert scheduler.reserve(timePeriod, car);
        assert scheduler.reserve(overlappingTimePeriod, car) == false;
    }

    @Test
    public void reserveNonOverlapping(){

        TimePeriod TimePeriod2 = new TimePeriod(DateUtils.addDays(new Date(), 10), 5);
        assert scheduler.reserve(timePeriod, car);
        assert scheduler.reserve(TimePeriod2, car);
    }

    @Test
    public void reserveBackToBack(){

        TimePeriod TimePeriod2 = new TimePeriod(timePeriod.getEnd(), DateUtils.addDays(timePeriod.getEnd(), 5));

        assert scheduler.reserve(timePeriod, car);
        assert scheduler.reserve(TimePeriod2, car) == false; // end1 = start2
        TimePeriod TimePeriod3 = new TimePeriod(new Date(TimePeriod2.getStart().getTime() + 1L), TimePeriod2.getEnd());  // add 1 mls
        assert scheduler.reserve(TimePeriod3, car);
    }
}