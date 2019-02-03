package com.example.dustinmcrorie.cardiobook;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterMeasurement {

    public ArrayList<HashMap<String,String>> list;
    public SimpleAdapter sa;
    /**
     * Citation: https://tekeye.uk/android/examples/lists/android-multi-line-listview-entries
     * Takes in an ArrayList
     *
     */
    AdapterMeasurement(ArrayList<Measurement> arrayList, Context context){


        list = new ArrayList<HashMap<String, String>>();
        HashMap<String,String> item;
        for(int i=0;i<arrayList.size();i++) {
            item = new HashMap<String, String>();
            item.put("line1", "date: " + arrayList.get(i).getDate());
            item.put("line2", "Systolic Pressure: " + Integer.toString(arrayList.get(i).getSystolic_pressure()));
            item.put("line3", "Dystolic Pressure: " + Integer.toString(arrayList.get(i).getDystolic_pressure()));
            item.put("line4", "Heart Rate: " + Integer.toString(arrayList.get(i).getHeart_rate()));
            item.put("bool", "1");
            list.add(item);
        }

        sa = new SimpleAdapter(context, list, R.layout.list_item,
                new String[] {"line1", "line2", "line3", "line4", "bool"},
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.bool});
    }


}
