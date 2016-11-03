package com.example.android.fortunecookie;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {

    myResult apiResponse;

    public QuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_quote, container, false);
        RequestQueue myRQueue = Volley.newRequestQueue(getContext());

        Intent intent = getActivity().getIntent();

        Bundle extras = intent.getExtras();
        final Icon myIcon = (Icon) extras.getSerializable("Quote");

        String name = myIcon.name;
        String url = "http://quotes.rest/qod.json?category=";

        switch (name) {
            case "Life Quote":
                url = url + "life";
                break;
            case "Inspiring Quote":
                url = url + "inspire";
                break;
            case "Student Quote":
                url = url + "students";
                break;
            case "Funny Quote":
                url = url + "funny";
                break;
            case "Sports Quote":
                url = url + "sports";
                break;
            case "Art Quote":
                url = url + "art";
                break;
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        apiResponse = gson.fromJson(response.toString(), myResult.class);

                        TextView title = (TextView) rootView.findViewById(R.id.title);
                        title.setText(apiResponse.getContents().getQuotes().get(0).getTitle());

                        ImageView myImage = (ImageView) rootView.findViewById(R.id.image);
                        myImage.setImageResource(myIcon.image);

                        TextView quote = (TextView) rootView.findViewById(R.id.quote);
                        quote.setText("'' " + apiResponse.getContents().getQuotes().get(0).getQuote() + " ''");

                        TextView author = (TextView) rootView.findViewById(R.id.author);
                        author.setText("- " + apiResponse.getContents().getQuotes().get(0).getAuthor() + " -");

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: ", "Error: " + error.getMessage());
                Toast.makeText(getActivity(), "Sorry, unable to retrieve the data. Probably due to Internet Connection",
                        Toast.LENGTH_LONG).show();

            }
        });

        myRQueue.add(jsonObjReq);

        return rootView;
    }

}
