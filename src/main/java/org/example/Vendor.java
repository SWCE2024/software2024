package org.example;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vendor {
    private final StringProperty number;
    private final DoubleProperty price;

    public Vendor(String number, double price) {
        this.number = new SimpleStringProperty(number);
        this.price = new SimpleDoubleProperty(price);
    }

    public StringProperty numberProperty() {
        return number;
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public String getNumber() {
        return number.get();
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
}

