package com.example.dustinmcrorie.cardiobook;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * This is a custom adapter which allows multiple lines to be displayed to the view,
 * and also for certain lines to be highlighted.
 * @author Dustin McRorie
 * @version 1.0
 */
public class MeasurementAdapter extends ArrayAdapter<Measurement> {

    private int resourceLayout;
    private Context mContext;
    private boolean highlight;

    /**
     * Extension of the ArrayAdapter constructor
     * @param context context to be used in
     * @param resource xml resource
     * @param items items to be included
     */
    public MeasurementAdapter(Context context, int resource, List<Measurement> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
        this.highlight = false;
    }

    /**
     * toggles the highlighting of the systolic and dystolic pressure values
     */
    public void Highlight () {
        if (this.highlight == false)
            this.highlight = true;
        else if (this.highlight = true)
            this.highlight = false;
    }

    /**
     * returns true if currently highlighted, false otherwise
     * @return boolean true/false value to be returned
     */
    public boolean isHighlighted(){
        return this.highlight;
    }


    /**
     * The view that the adapter transfers the list data to.
     * @param position position of the view
     * @param convertView current view
     * @param parent viewgroup
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            //v = vi.inflate(resourceLayout, null);
            v = vi.inflate(R.layout.list_item, parent, false);
        }

        Measurement p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.line_a);
            TextView tt2 = (TextView) v.findViewById(R.id.line_b);
            TextView tt3 = (TextView) v.findViewById(R.id.line_c);
            TextView tt4 = (TextView) v.findViewById(R.id.line_d);

            if (tt1 != null) {
                String date = "Date: " + p.getDate();
                tt1.setText(date);
            }

            if (tt2 != null){
                int sys = p.getSystolic_pressure();
                String sysS = "Systolic Pressure: " + Integer.toString(sys);
                tt2.setText(sysS);
                if ((sys < 90 || sys > 140) &&  highlight)
                    tt2.setTextColor(Color.RED);
                else
                    tt2.setTextColor(Color.BLACK);
            }

            if (tt3 != null) {
                int dys = p.getDystolic_pressure();
                String dysS = "Dystolic Pressure: " + Integer.toString(dys);
                tt3.setText(dysS);
                if ((dys < 60 || dys > 90) &&  highlight)
                    tt3.setTextColor(Color.RED);
                else
                    tt3.setTextColor(Color.BLACK);
            }

            if (tt4 != null) {
                String heart = "Heart Rate: " + Integer.toString(p.getHeart_rate());
                tt4.setText(heart);
            }
        }

        return v;
    }

}
