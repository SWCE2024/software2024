package org.example;

public class Vendor {
        private String phoneNumber;
        private int price;

        // Constructor
        public Vendor(String phoneNumber, int price) {
            this.phoneNumber = phoneNumber;
            this.price = price;
        }

        // Getters and setters
        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }


}
