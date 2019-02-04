package com.example.dustinmcrorie.cardiobook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.dustinmcrorie.cardiobook.MainActivity.measurements;


/**
 * This screen is where the user can view the details of an existing measurement. From
 * here, the user can also edit the details of the measurement, or delete the measurement
 * from the list.
 * @author Dustin McRorie
 * @version 1.0
 */
public class SeeMeasurementActivity extends AppCompatActivity {


    private Measurement measurement;

    // Initialize xml variables
    private TextView date_view;
    private TextView time_view;
    private TextView sys_view;
    private TextView dys_view;
    private TextView heart_view;
    private TextView comment_view;
    private TextView title_view;
    private Button edit_button;
    private Button delete_button;
    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seemeasure_activity);

        Intent intent = getIntent();
        index = intent.getIntExtra("INDEX", 0);
        measurement = measurements.get(index);

        // load in xml variables
        date_view = (TextView) findViewById(R.id.displayDate);
        time_view = (TextView) findViewById(R.id.displayTime);
        sys_view = (TextView) findViewById(R.id.displaySys);
        dys_view = (TextView) findViewById(R.id.displayDys);
        heart_view = (TextView) findViewById(R.id.displayHeart);
        comment_view = (TextView) findViewById(R.id.displayComment);
        edit_button = (Button) findViewById(R.id.editMeasurement);
        delete_button = (Button) findViewById(R.id.deleteMeasurement);

        edit_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startEdit();
            }
        });

        /**
         * calls startDelete() when the Delete button is clicked
         */
        delete_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startDelete();
            }
        });

        //DEBUG - remove later
        //Toast.makeText(getBaseContext(), measurement.getDate(), Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        displayMeasurement();
    }

    /**
     * Display the variable information
     */
    public void displayMeasurement () {
        String temp;
        temp = "Date: " + measurement.getDate();
        date_view.setText(temp);
        temp = "Time: " + measurement.getTime();
        time_view.setText(temp);
        temp = "Systolic Pressure: " + Integer.toString(measurement.getSystolic_pressure());
        sys_view.setText(temp);
        temp = "Dystolic Pressure: " + Integer.toString(measurement.getDystolic_pressure());
        dys_view.setText(temp);
        temp = "Heart Rate: " + Integer.toString(measurement.getHeart_rate());
        heart_view.setText(temp);
        temp = "Comment: " + measurement.getComment();
        comment_view.setText(temp);
    }

    /**
     * Brings user to the edit measurement screen, where he/she can edit
     * the measurement he/she clicked on
     */
    public void startEdit(){
        Intent intent = new Intent(this, NewMeasurementActivity.class);
        intent.putExtra("EDIT", true);
        intent.putExtra("INDEX", index);

        finish();
        startActivity(intent);
    }

    /**
     * Deletes the measurement from the array list and returns to the main screen
     */
    public void startDelete(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("CALLED", true);

        //Delete from array list
        measurements.remove(index);

        finish();
        startActivity(intent);
    }
}
