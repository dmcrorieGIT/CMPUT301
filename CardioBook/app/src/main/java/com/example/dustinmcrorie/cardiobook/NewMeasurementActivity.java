package com.example.dustinmcrorie.cardiobook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.dustinmcrorie.cardiobook.MainActivity.measurements;


/**
 * This activity is where users enter data for a new measurement, or edit existing
 * data for an existing measurement.
 * @author Dustin McRorie
 * @version 1.0
 */
public class NewMeasurementActivity extends AppCompatActivity {

    // init xml variables to be used
    private TextView title_text;
    private EditText date_text;
    private EditText time_text;
    private EditText sys_text;
    private EditText dys_text;
    private EditText heart_text;
    private EditText comment_text;
    private Button add_button;
    private Measurement measure;
    private boolean isEdit;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newmeasure_activity);

        // Load in xml variables
        title_text = (TextView) findViewById(R.id.add_measurement_title);
        date_text = (EditText) findViewById(R.id.editDate);
        time_text = (EditText) findViewById(R.id.editTime);
        sys_text = (EditText) findViewById(R.id.editSys);
        dys_text = (EditText) findViewById(R.id.editDys);
        heart_text = (EditText) findViewById(R.id.editHeart);
        comment_text = (EditText) findViewById(R.id.editComment);
        add_button = (Button) findViewById(R.id.addMeasure);

        // Get the intent that started this activity and extract the string
        Intent intent = getIntent();
        isEdit = intent.getBooleanExtra("EDIT", false);
        index = intent.getIntExtra("INDEX", 0);

        // If it was called by edit, then set up the edit environment
        if (isEdit) {
            String string = "Edit Measurement";
            title_text.setText(string);
            string = "Save";
            add_button.setText(string);
            fillMeasurement();
        }

        /**
         * Click listener for "Add" button, which adds the measurement to the array
         * of measurements
         */
        add_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (checkValues()) {
                    if (isEdit) {
                        editMeasurementFromClick();
                        goBack();
                    } else {
                        addMeasurementFromClick();
                        goBack();
                    }
                }
            }
        });


    }



    public void addMeasurementFromClick(){
        measure = new Measurement();
        measure.setDate(date_text.getText().toString());
        measure.setTime(time_text.getText().toString());
        int sysint = Integer.parseInt(sys_text.getText().toString());
        measure.setSystolic_pressure(sysint);
        int dysint = Integer.parseInt(dys_text.getText().toString());
        measure.setDystolic_pressure(dysint);
        int heartint = Integer.parseInt(heart_text.getText().toString());
        measure.setHeart_rate(heartint);
        measure.setComment(comment_text.getText().toString());
        measurements.add(measure);
    }

    public void editMeasurementFromClick(){
        measurements.get(index).setDate(date_text.getText().toString());
        measurements.get(index).setTime(time_text.getText().toString());
        int sysint = Integer.parseInt(sys_text.getText().toString());
        measurements.get(index).setSystolic_pressure(sysint);
        int dysint = Integer.parseInt(dys_text.getText().toString());
        measurements.get(index).setDystolic_pressure(dysint);
        int heartint = Integer.parseInt(heart_text.getText().toString());
        measurements.get(index).setHeart_rate(heartint);
        measurements.get(index).setComment(comment_text.getText().toString());
    }

    public void fillMeasurement(){
        String temp;
        temp = measurements.get(index).getDate();
        date_text.setText(temp);
        temp = measurements.get(index).getTime();
        time_text.setText(temp);
        temp = Integer.toString(measurements.get(index).getSystolic_pressure());
        sys_text.setText(temp);
        temp = Integer.toString(measurements.get(index).getDystolic_pressure());
        dys_text.setText(temp);
        temp = Integer.toString(measurements.get(index).getHeart_rate());
        heart_text.setText(temp);
        temp = measurements.get(index).getComment();
        comment_text.setText(temp);
    }

    /**
     * Returns the user to the main activity screen
     *
     */
    public void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("CALLED", true);
        finish();
        startActivity(intent);
    }

    /**
     * Checks to see if all of the values entered are legal
     * @return boolean true if values are valid, false otherwise
     */
    public boolean checkValues(){
        String temp;
        String temp2;
        String temp3;

        //first check the date
        temp = date_text.getText().toString();
        if (temp.length() != 10){
            Toast.makeText(getBaseContext(), "date must be in yyyy-mm-dd format",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (temp.charAt(4) != '-' && temp.charAt(7) != '-'){
            Toast.makeText(getBaseContext(), "date must be in yyyy-mm-dd format",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        for (int i = 0; i < temp.length(); i++){
            if (i == 4 || i == 7)
                continue;
            if (!Character.isDigit(temp.charAt(i))){
                Toast.makeText(getBaseContext(), "date must be in yyyy-mm-dd format",
                        Toast.LENGTH_LONG).show();
                return false;
            }
        }

        //check time
        temp = time_text.getText().toString();
        if (temp.length() != 5){
            Toast.makeText(getBaseContext(), "time must be in hh:mm format",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (temp.charAt(2) != ':'){
            Toast.makeText(getBaseContext(), "time must be in hh:mm format",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        for (int i = 0; i < temp.length(); i++){
            if (i == 2)
                continue;
            if (!Character.isDigit(temp.charAt(i))){
                Toast.makeText(getBaseContext(), "time must be in hh:mm format",
                        Toast.LENGTH_LONG).show();
                return false;
            }
        }

        //Check systolic, dystolic, and heart rate
        temp = sys_text.getText().toString();
        temp2 = dys_text.getText().toString();
        temp3 = sys_text.getText().toString();
        if (temp.length() == 0){
            Toast.makeText(getBaseContext(), "systolic pressure can't be empty",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (temp2.length() == 0){
            Toast.makeText(getBaseContext(), "dystolic pressure can't be empty",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (temp3.length() == 0){
            Toast.makeText(getBaseContext(), "heart rate can't be empty",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        for (int i = 0; i < temp.length(); i++){
            if (!Character.isDigit(temp.charAt(i))){
                Toast.makeText(getBaseContext(), "systolic pressure must be a number!",
                        Toast.LENGTH_LONG).show();
                return false;
            }
        }
        for (int i = 0; i < temp2.length(); i++){
            if (!Character.isDigit(temp.charAt(i))){
                Toast.makeText(getBaseContext(), "dystolic pressure must be a number!",
                        Toast.LENGTH_LONG).show();
                return false;
            }
        }
        for (int i = 0; i < temp3.length(); i++){
            if (!Character.isDigit(temp.charAt(i))){
                Toast.makeText(getBaseContext(), "heart rate must be a number!",
                        Toast.LENGTH_LONG).show();
                return false;
            }
        }
        if (Integer.parseInt(temp) < 0){
            Toast.makeText(getBaseContext(), "Systolic pressure must be greater than zero",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (Integer.parseInt(temp2) < 0){
            Toast.makeText(getBaseContext(), "Dystolic pressure must be greater than zero",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (Integer.parseInt(temp3) < 0){
            Toast.makeText(getBaseContext(), "Heart rate must be greater than zero",
                    Toast.LENGTH_LONG).show();
            return false;
        }

        //Check Comment
        temp = comment_text.getText().toString();
        if (temp.length() == 0) {
            String comment = "no comment";
            comment_text.setText(comment);
        }
        if (temp.length() > 20){
            Toast.makeText(getBaseContext(), "Comment must be under 200 characters",
                    Toast.LENGTH_LONG).show();
            return false;
        }


        return true;
    }
}
