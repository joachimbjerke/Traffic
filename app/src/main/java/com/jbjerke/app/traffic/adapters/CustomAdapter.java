package com.jbjerke.app.traffic.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jbjerke.app.traffic.R;
import com.jbjerke.app.traffic.models.ItemModel;

/**
 * Created by jbjerke on 03.07.2015.
 */
public class CustomAdapter extends ArrayAdapter<ItemModel>  {

    private Activity myContext;
    private ItemModel[] datas;

    public CustomAdapter(Context context, int resource, ItemModel[] objects) {
        super(context, resource,objects);
        myContext = (Activity)context;
        datas = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = myContext.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listitem, null);
        ImageView thumbImageView = (ImageView) rowView.findViewById(R.id.iconImage);
        thumbImageView.setImageResource(R.drawable.warning_sign);
        /*
        if (datas[position].getHeader() == null) {
            thumbImageView.setImageResource(R.drawable.warning_sign);
        }
        */
        TextView headingTextView = (TextView) rowView.findViewById(R.id.headingText);
        headingTextView.setText(datas[position].getHeader());

        ImageView arrowImageView = (ImageView) rowView.findViewById(R.id.arrowicon);
        arrowImageView.setImageResource(R.drawable.ic_navigate_next_black_24dp);


        return rowView;

    }
}
