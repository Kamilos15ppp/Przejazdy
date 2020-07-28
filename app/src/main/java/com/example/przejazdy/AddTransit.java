package com.example.przejazdy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddTransit extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener  {

    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter arrayAdapter;
    private FloatingActionButton floatingActionButton;
    final String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    public static String objectIdTransit, objectIdTransit2, taborowyTransit, liniaTransit, kierunekTransit, poczatkowyTransit, koncowyTransit;
    public String userName;

    public AddTransit() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_transit, container, false);

        floatingActionButton = view.findViewById(R.id.floatingActionButton);

        listView = view.findViewById(R.id.listView);
        arrayList = new ArrayList();
        Context context = getContext();
        arrayAdapter = new ArrayAdapter(context, R.layout.text_size_transit, arrayList);

        listView.setOnItemClickListener(AddTransit.this);
        listView.setOnItemLongClickListener(AddTransit.this);

        displayingObject();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                transitionAddingNewTransitActivity();

            }
        });

        return view;
    }

    private void transitionAddingNewTransitActivity() {

        Intent intent = new Intent(getActivity(), AddingNewTransit.class);
        startActivity(intent);

    }

    private void transitionEditTransitActivity() {

        Intent intent = new Intent(getActivity(), EditTransit.class);
        startActivity(intent);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ParseUser parseUser = ParseUser.getCurrentUser();
        userName = parseUser.getUsername().toLowerCase();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("przejazdy_" + userName);
        query.orderByDescending("createdAt");
        List<ParseObject> results = null;
        try {
            results = query.find();
            if(!results.isEmpty()) {
                String objectId, taborowy, linia, kierunek, poczatkowy, koncowy;
                objectId = results.get(position).getObjectId();
                taborowy = results.get(position).getString("taborowy");
                linia = results.get(position).getString("linia");
                kierunek = results.get(position).getString("kierunek");
                poczatkowy = results.get(position).getString("poczatkowy");
                koncowy = results.get(position).getString("koncowy");

                objectIdTransit = objectId;
                taborowyTransit = taborowy;
                liniaTransit = linia;
                kierunekTransit = kierunek;
                poczatkowyTransit = poczatkowy;
                koncowyTransit = koncowy;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        transitionEditTransitActivity();

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

        arrayList.clear();
        listView.setAdapter(arrayAdapter);

        ParseUser parseUser = ParseUser.getCurrentUser();
        userName = parseUser.getUsername().toLowerCase();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("przejazdy_" + userName);
        query.orderByDescending("createdAt");
        List<ParseObject> results = null;
        try {
            results = query.find();
            if(!results.isEmpty()) {
                String objectId;
                objectId = results.get(position).getObjectId();
                objectIdTransit2 = objectId;

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        ParseObject po = ParseObject.createWithoutData("przejazdy_" + userName, objectIdTransit2);
        po.deleteEventually();

        FancyToast.makeText(getContext(),
               getString(R.string.fancy_delete_transit),
                Toast.LENGTH_SHORT, FancyToast.WARNING,
                false).show();

        return true;
    }

    private void displayingObject() {

        ParseUser parseUser = ParseUser.getCurrentUser();
        userName = parseUser.getUsername().toLowerCase();
        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("przejazdy_" + userName);
        parseQuery.whereEqualTo("data", currentDate);
        parseQuery.orderByDescending("createdAt");
        parseQuery.setLimit(50);
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                int i = 0;
                if (e == null) {

                    if (objects.size() > 0) {

                        for (ParseObject object : objects) {

                            arrayList.add(object.getString("taborowy")
                                    + " | " + object.getString("linia")
                                    + " | " + object.getString("kierunek")
                                    + " | " + object.getString("poczatkowy")
                                    + " | " + object.getString("koncowy"));

                            i++;
                        }
                        arrayList.add("Ilość rekordów: " + i + ", Data: " + currentDate);
                        listView.setAdapter(arrayAdapter);
                        listView.setVisibility(View.VISIBLE);

                    }

                } else {

                    FancyToast.makeText(getContext(),
                            e.getMessage(),
                            Toast.LENGTH_LONG, FancyToast.ERROR,
                            false).show();

                }

            }

        });

    }

}