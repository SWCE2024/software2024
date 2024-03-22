package org.example;

import javafx.beans.property.SimpleStringProperty;

public class EventCalender {

    private final SimpleStringProperty eventName;
    private final SimpleStringProperty eventDate;
    private final SimpleStringProperty eventTime;
    private final SimpleStringProperty eventType;
    public EventCalender(String eventName, String eventDate, String eventTime, String eventType ) {
        this.eventName = new SimpleStringProperty(eventName);
        this.eventDate = new SimpleStringProperty(eventDate);
        this.eventTime = new SimpleStringProperty(eventTime);
        this.eventType = new SimpleStringProperty(eventType);
    }


    public String getEventName() { return eventName.get(); }
    public void setEventName(String eventName) { this.eventName.set(eventName); }
    public String geteventDate() { return eventDate.get(); }
    public void seteventDate(String eventDate) { this.eventDate.set(eventDate); }
    public String geteventTime() { return eventTime.get(); }
    public void seteventTime(String eventTime) { this.eventTime.set(eventTime); }

    public String geteventType() { return eventType.get(); }
    public void seteventType(String eventType) { this.eventType.set(eventType); }

    public SimpleStringProperty EventNameProperty() { return eventName; }
    public SimpleStringProperty eventDateProperty() { return eventDate; }
    public SimpleStringProperty eventTimeProperty() { return eventTime; }
    public SimpleStringProperty eventTypeProperty() { return eventType; }


}
