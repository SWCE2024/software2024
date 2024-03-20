package org.example;
import javafx.beans.property.SimpleStringProperty;
public class EventFinance {
    private final SimpleStringProperty eventName;
    private final SimpleStringProperty vendorPayments;
    private final SimpleStringProperty venueRentalFees;
    private final SimpleStringProperty bookingStatus;

    public EventFinance(String eventName, String vendorPayments, String venueRentalFees, String bookingStatus ) {
        this.eventName = new SimpleStringProperty(eventName);
        this.vendorPayments = new SimpleStringProperty(vendorPayments);
        this.venueRentalFees = new SimpleStringProperty(venueRentalFees);
        this.bookingStatus = new SimpleStringProperty(bookingStatus);
    }
    public String getEventName() { return eventName.get(); }
    public void setEventName(String eventName) { this.eventName.set(eventName); }
    public String getVendorPayments() { return vendorPayments.get(); }
    public void setVendorPayments(String vendorPayments) { this.vendorPayments.set(vendorPayments); }
    public String getVenueRentalFees() { return venueRentalFees.get(); }
    public void setVenueRentalFees(String venueRentalFees) { this.venueRentalFees.set(venueRentalFees); }

    public String getBookingStatus() {
        return bookingStatus.get();
    }
    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus.set(bookingStatus);
    }

    public SimpleStringProperty eventNameProperty() { return eventName; }
    public SimpleStringProperty vendorPaymentsProperty() { return vendorPayments; }
    public SimpleStringProperty venueRentalFeesProperty() { return venueRentalFees; }
    public SimpleStringProperty bookingStatusProperty() {
        return bookingStatus;
    }
}
