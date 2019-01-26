package yordansyordanov;


import java.util.Date;

public class TimePeriod {

    private Date start;
    private Date end;

    public TimePeriod(Date start, int days) {
        this.start = start;
        end = DateUtils.addDays(start, days);
    }

    TimePeriod(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return start;
    }


    public Date getEnd() {
        return end;
    }


    /**
     * @param theOther
     * @return Whether or not the the given period overlaps with this period.
     */
    public boolean isOverlapping(TimePeriod theOther) {

        return start.equals(theOther.end)
                || end.equals(theOther.start)
                || (this.start.before(theOther.end) && this.end.after(theOther.start));
    }
}
