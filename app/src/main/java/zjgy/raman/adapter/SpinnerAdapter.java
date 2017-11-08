package zjgy.raman.adapter;


import com.example.raman.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<String> {

    Context context;
    List<String> items = new ArrayList<String>();

    public SpinnerAdapter(final Context context,final int textViewResourceId, final List<String> objects) {
        super(context, textViewResourceId, objects);
        this.items = objects;
        this.context = context;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(
                    R.layout.simple_spinner_item, parent, false);
        }

        TextView tv = (TextView) convertView
                .findViewById(android.R.id.text1);
        tv.setText(items.get(position));
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(36);
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(
                    R.layout.simple_spinner_item, parent, false);
        }


        TextView tv = (TextView) convertView
                .findViewById(android.R.id.text1);
        tv.setText(items.get(position));
        tv.setTextColor(Color.BLACK);
        //tv.setTextSize(30);
        return convertView;
    }
}