package com.example.muki.helloworld;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Button startButton;
    private PassengerReaderDbHelper database = new PassengerReaderDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.info);
        startButton = findViewById(R.id.identify);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskRunner task = new AsyncTaskRunner();
                task.execute();
            }
        });
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, NodeList> {

        private String resp;


        @Override
        protected NodeList doInBackground(String... params) {
            // or if you prefer DOM:
            String id = null;

            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = null;
                db = dbf.newDocumentBuilder();
                Document doc = db.parse(new URL("http://10.0.2.2:3161/devices").openStream());
                id = doc.getElementsByTagName("id").item(0).getTextContent();
                Log.i(id, "id");
                URL commandURL = new URL("http://10.0.2.2:3161/devices/" + id);
                URL startURL = new URL("http://10.0.2.2:3161/devices/" + id + "/start");
                doc = db.parse(startURL.openStream());
                URL inventoryURL = new URL("http://10.0.2.2:3161/devices/" + id + "/inventory");
                doc = db.parse(inventoryURL.openStream());

                NodeList nodeList = doc.getElementsByTagName("epc");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Log.i("epc", nodeList.item(i).getTextContent());
                }

                Log.i(String.valueOf(commandURL), "devices");
                Log.i(String.valueOf(startURL), "start");
                Log.i(String.valueOf(inventoryURL), "inventory");

                return nodeList;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(NodeList nodeList) {
            // execution of result of Long time consuming operation
            Log.i("execute", "executing long async task now");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Log.i("epc", nodeList.item(i).getTextContent());
                String raw = nodeList.item(i).getTextContent();
                String epc = raw.substring(raw.length() - 1);

                if (database != null) {
                    Cursor c = database.getPassenger(epc);//epcSelected
                    if (c.moveToFirst()) {
                        do {
                            System.out.println(c.getString(c.getColumnIndex("epc")));
                            System.out.println(c.getString(c.getColumnIndex("firstname")));
                            System.out.println(c.getString(c.getColumnIndex("lastname")));
                            System.out.println(c.getString(c.getColumnIndex("skymilesstatus")));
                        } while (c.moveToNext());
                    }
                    c.close();
                    mTextMessage.setText("Passenger number: " + nodeList.item(0).getTextContent());
                }

            }
        }
    }
}
