package com.example.android.fortunecookie;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fatma on 01/11/16.
 */
public class CustomAdapter extends ArrayAdapter<Icon> {

    public CustomAdapter(Activity context, List<Icon> iconsList) {

        super(context, 0, iconsList);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Icon myIcon = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.icon_item, parent, false);
        }

        ImageView myImage = (ImageView) convertView.findViewById(R.id.icon_image);
        myImage.setImageResource(myIcon.image);

        TextView myText = (TextView) convertView.findViewById(R.id.icon_text);
        myText.setText(myIcon.name);


        return convertView;
    }


}
