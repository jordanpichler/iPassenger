package com.example.muki.helloworld;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PassengerReaderDbHelper extends SQLiteOpenHelper {

    // --------- SQL Strings to CREATE and DELETE tables

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
        insertData(db);
    }

    public Cursor getPassenger(String epc) {
        Cursor c = getReadableDatabase().query(
                PassengersDbContract.PassengerTable.TABLE_NAME,
                null,
                PassengersDbContract.PassengerTable.EPC + " LIKE ?",
                new String[]{epc},
                null,
                null,
                null);
        return c;
    }

    public void insertData(SQLiteDatabase database) {
        createPassenger(database,"1","Albert","Manzanares","12-03-1990","GOLD ELITE","99900","vegan","window","Business","F","ES");
        createPassenger(database,"2","Jordan Amadeus","Pichler","01-05-1995","PLATINUM ELITE","999900","vegan","window","Leisure","F","DE");
        createPassenger(database,"3","Miquel","Bantulà","04-07-1992","BRONZE ELITE","100","pasta","corridor","Business","Y","NO");
        createPassenger(database,"4","Joan","Moreno","12-03-1990","GOLD ELITE","99900","vegan","window","Business","F","ES");
        createPassenger(database,"5","Narcis","Whoever","12-03-1990","GOLD ELITE","99900","vegan","window","Business","F","ES");
        createPassenger(database,"6","Oye","Tio","12-03-1990","GOLD ELITE","99900","vegan","window","Business","F","ES");
        createPassenger(database,"7","Kevin","Something","12-03-1990","GOLD ELITE","99900","vegan","window","Business","F","ES");
        createPassenger(database,"8","Elon","Musk","12-03-1990","GOLD ELITE","99900","vegan","window","Business","F","ES");
        createPassenger(database,"9","Steve","Jobs","12-03-1990","GOLD ELITE","99900","vegan","window","Business","F","ES");
        createPassenger(database,"10","Youre not","Fucked","12-03-1990","GOLD ELITE","99900","vegan","window","Business","F","ES");
    }

    public void createPassenger(SQLiteDatabase db,
                                String epc, String firstname, String lastname, String birthday,
                                String skymilesstatus, String mileage, String mealpreference,
                                String seatpreference, String travelpurpose, String bookingclass, String language) {
        ContentValues values = new ContentValues();
        values.put(PassengersDbContract.PassengerTable.EPC, epc);
        values.put(PassengersDbContract.PassengerTable.FIRSTNAME , firstname);
        values.put(PassengersDbContract.PassengerTable.LASTNAME , lastname);
        values.put(PassengersDbContract.PassengerTable.BIRTHDAY, birthday);
        values.put(PassengersDbContract.PassengerTable.SKYMILESSTATUS, skymilesstatus);
        values.put(PassengersDbContract.PassengerTable.MILEAGE, mileage);
        values.put(PassengersDbContract.PassengerTable.MEALPREFERENCE,mealpreference);
        values.put(PassengersDbContract.PassengerTable.SEATPREFERENCE, seatpreference);
        values.put(PassengersDbContract.PassengerTable.TRAVELPURPOSE, travelpurpose);
        values.put(PassengersDbContract.PassengerTable.BOOKINGCLASS, bookingclass);
        values.put(PassengersDbContract.PassengerTable.LANGUAGE, language);
        db.insert(PassengersDbContract.PassengerTable.TABLE_NAME, null, values);
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
