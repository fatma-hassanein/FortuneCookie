package com.example.android.fortunecookie;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    Icon[] myIcons = new Icon[6];
    CustomAdapter mAdapter;


    public MainFragment() {

        myIcons[0] = new Icon(R.drawable.life, "Life Quote");
        myIcons[1] = new Icon(R.drawable.inspire, "Inspiring Quote");
        myIcons[2] = new Icon(R.drawable.student, "Student Quote");
        myIcons[3] = new Icon(R.drawable.funny, "Funny Quote");
        myIcons[4] = new Icon(R.drawable.sports, "Sports Quote");
        myIcons[5] = new Icon(R.drawable.art, "Art Quote");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.gridView);

        mAdapter = new CustomAdapter(getActivity(), Arrays.asList(myIcons));

        gridView.setAdapter(mAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Icon icon = mAdapter.getItem(position);

                Intent intent = new Intent(getActivity(), QuoteActivity.class);
                intent.putExtra("Quote", icon);

                startActivity(intent);
            }
        });

        return rootView;
    }

}
