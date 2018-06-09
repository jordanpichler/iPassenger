package com.example.muki.helloworld;

import android.provider.BaseColumns;

public final class PassengersDbContract {

    private PassengersDbContract() {}

    /* Inner class that defines the table contents */
    public static class PassengerTable implements BaseColumns {
        public static final String TABLE_NAME ="passengers";
        public static final String EPC = "epc";                         // RFID Tag
        public static final String FIRSTNAME = "firstname";             // First Name
        public static final String LASTNAME = "lastname";               // Last Name
        public static final String BIRTHDAY = "birthday";               // Birthday
        public static final String SKYMILESSTATUS = "skymilesstatus";   // Status in frequent flyer program
        public static final String MILEAGE = "mileage";                 // No. of miles in frequent flyer program
        public static final String MEALPREFERENCE = "mealpreference";   // Preferred meal
        public static final String SEATPREFERENCE = "seatpreference";   // Preferred seat (windows, middle, aisle)
        public static final String TRAVELPURPOSE = "travelpurpose";     // Purpose of travel
        public static final String BOOKINGCLASS = "bookingclass";       // Class of Booking (F, B, Y, ...)
        public static final String LANGUAGE = "language";               // Preferred language of passenger
    }
}
