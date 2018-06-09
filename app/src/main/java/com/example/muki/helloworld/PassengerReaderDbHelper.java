package com.example.muki.helloworld;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PassengerReaderDbHelper extends SQLiteOpenHelper {

    // --------- SQL Strings to CREATE and DELTE tables

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PassengersDbContract.PassengerTable.TABLE_NAME + " (" +
                    PassengersDbContract.PassengerTable._ID + " INTEGER PRIMARY KEY,"
                    + PassengersDbContract.PassengerTable.EPC + " TEXT NOT NULL,"
                    + PassengersDbContract.PassengerTable.FIRSTNAME + " TEXT NOT NULL,"
                    + PassengersDbContract.PassengerTable.LASTNAME + " TEXT NOT NULL,"
                    + PassengersDbContract.PassengerTable.BIRTHDAY + " TEXT NOT NULL,"
                    + PassengersDbContract.PassengerTable.SKYMILESSTATUS + " TEXT NOT NULL,"
                    + PassengersDbContract.PassengerTable.MILEAGE + " TEXT NOT NULL,"
                    + PassengersDbContract.PassengerTable.MEALPREFERENCE + " TEXT NOT NULL,"
                    + PassengersDbContract.PassengerTable.SEATPREFERENCE + " TEXT NOT NULL,"
                    + PassengersDbContract.PassengerTable.TRAVELPURPOSE + " TEXT NOT NULL,"
                    + PassengersDbContract.PassengerTable.BOOKINGCLASS + " TEXT NOT NULL,"
                    + PassengersDbContract.PassengerTable.LANGUAGE + " TEXT NOT NULL,"
                    + "UNIQUE (" + PassengersDbContract.PassengerTable.EPC + "))";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PassengersDbContract.PassengerTable.TABLE_NAME;


    // --------- DB Properties

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DeltaPassengers.db";


    // --------- DB Methods

    public PassengerReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
