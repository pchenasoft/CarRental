package yordansyordanov;

public class Reservation {

    Car car;
    Customer customer;
    TimePeriod period;

    public Reservation(Car car, Customer customer, TimePeriod period) {
        this.car = car;
        this.customer = customer;
        this.period = period;
    }

    @Override
    public String toString(){

        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append("**************** CAR RESERVATION ********************" + newLine);
        result.append("Customer name: " + customer.getFirstName() + " " + customer.getLastName() + newLine);
        result.append("1 " + car.getCarType().toString() + " car." + newLine );
        result.append("From " + DateUtils.format(period.getStart()) + " to " + DateUtils.format(period.getEnd()) + newLine);
        result.append("Thank you for your business. Please come again!" + newLine);
        result.append("*****************************************************" + newLine);

        return result.toString();
    }
}
