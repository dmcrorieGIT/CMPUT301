package com.example.dustinmcrorie.cardiobook;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Measurement> measurements = new ArrayList<Measurement>();
    private ArrayAdapter<Measurement> adapter;
    private ListView listView;
    private View item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.measurementList);
        listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //TEST
        createMeasurements();

        AdapterMeasurement adapter = new AdapterMeasurement(measurements, this);

        listView.setAdapter(adapter.sa);
        item = getViewByPosition(1, listView);
        item.setVisibility(View.GONE);
        item = getViewByPosition(0, listView);
        item.setBackgroundColor(Color.GREEN);

        return;
    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }


    //FOR TEST
    protected void createMeasurements(){
        Measurement mes1 = new Measurement();
        mes1.setHeart_rate(60);
        mes1.setDystolic_pressure(40);
        mes1.setSystolic_pressure(60);
        mes1.setDate("13/08/1994");

        Measurement mes2 = new Measurement();
        mes2.setHeart_rate(65);
        mes2.setDystolic_pressure(57);
        mes2.setSystolic_pressure(45);
        mes2.setDate("17/06/2000");

        measurements.add(mes1);
        measurements.add(mes2);
        return;
    }
}
