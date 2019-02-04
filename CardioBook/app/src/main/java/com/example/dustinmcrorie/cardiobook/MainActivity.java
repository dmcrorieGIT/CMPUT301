package com.example.dustinmcrorie.cardiobook;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * The main screen activity for this application. From here, the user can add
 * a new measurement, view an existing measurement, highlight unusual blood pressures,
 * or exit the application.
 * @author Dustin McRorie
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "file1.sav";
    public static ArrayList<Measurement> measurements = new ArrayList<Measurement>();
    private MeasurementAdapter adapter;
    private ListView listView;
    private Button unusual_button;
    private Button add_button;
    private Button close_button;
    private boolean called;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get intent if other activity called
        Intent intent = getIntent();
        called = intent.getBooleanExtra("CALLED", false);

        unusual_button = (Button) findViewById(R.id.show_unusual);
        add_button = (Button) findViewById(R.id.add_mainscreen);
        listView = (ListView) findViewById(R.id.measurementList);
        close_button = (Button) findViewById(R.id.close_button);
        listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE);

        /**
         * Click listener for the measurement list
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Measurement item = adapter.getItem(position);
                int index = measurements.indexOf(item);
                startSeeActivity(index);
            }
        });

        /**
         * Click listener for the "show unusual" button
         */
        unusual_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adapter.Highlight();
                adapter.notifyDataSetChanged();
                if (adapter.isHighlighted()){
                    unusual_button.setText("Hide Unusual");
                } else {
                    unusual_button.setText("Show Unusual");
                }
            }
        });

        /**
         * Click listener for the "Add" button
         */
        add_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startAddActivity();
            }
        });

        /**
         * Click listener for the "Close" button
         */
        close_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                closeApplication();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        //TEST - remove later
        //createMeasurements();

        //If starting (not called), then load Gson
        if (!called) {
            loadFromFile();
        } else if (called) {
            saveInFile();
        }


        adapter = new MeasurementAdapter(this, R.layout.list_item, measurements);
        //adapter.setHighlight(true);
        listView.setAdapter(adapter);


        return;
    }

    public void startAddActivity() {
        //Do something in response to the button
        Intent intent = new Intent(this, NewMeasurementActivity.class);
        intent.putExtra("EDIT", false);
        intent.putExtra("INDEX", 0);
        startActivity(intent);
    }

    /**
     * User has clicked on a measurement; takes user to the "see measurement" activity
     */
    public void startSeeActivity(int index) {
        Intent intentSee = new Intent(this, SeeMeasurementActivity.class);
        intentSee.putExtra("INDEX", index);
        startActivity(intentSee);
    }

    /**
     * This method closes the app
     */
    public void closeApplication() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }


    //FOR TEST - delete later
    protected void createMeasurements() {
        Measurement mes1 = new Measurement();
        mes1.setHeart_rate(60);
        mes1.setDystolic_pressure(70);
        mes1.setSystolic_pressure(100);
        mes1.setDate("13/08/1994");

        Measurement mes2 = new Measurement();
        mes2.setHeart_rate(65);
        mes2.setDystolic_pressure(30);
        mes2.setSystolic_pressure(150);
        mes2.setDate("17/06/2000");

        measurements.add(mes1);
        measurements.add(mes2);
        return;
    }

    /**
     * Load measurement array from file
     */
    private void loadFromFile() {
        //ArrayList<String> tweets = new ArrayList<String>();
        try {

            FileReader in = new FileReader(new File(getFilesDir(), FILENAME));
            Gson gson = new Gson();

            Type lType = new TypeToken<ArrayList<Measurement>>() {
            }.getType();

            ArrayList<Measurement> temp = new ArrayList<Measurement>();
            temp = gson.fromJson(in, lType);
            if (temp != null)
                measurements = temp;
            else
                Log.d("My_Log", "The gson object was a null reference");
            return;


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * This method saves the measurement list to the savefile
     */
    private void saveInFile() {
        try {

            //taking the tweet list, converting it using Gson
            FileWriter out = new FileWriter(new File(getFilesDir(), FILENAME));
            Gson gson = new Gson();

            gson.toJson(measurements,out);

            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}