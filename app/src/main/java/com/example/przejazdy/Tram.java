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
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

public class Tram extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter arrayAdapter;
    private FloatingActionButton floatingActionButton;
    public static String objectIdTram, taborowyTram, makerTram, modelTram, infoTram;

    public Tram() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tram, container, false);

        floatingActionButton = view.findViewById(R.id.floatingActionButton3);

        listView = view.findViewById(R.id.listView);
        arrayList = new ArrayList();
        Context context = getContext();
        arrayAdapter =new ArrayAdapter(context, android.R.layout.simple_list_item_1, arrayList);

        listView.setOnItemClickListener(Tram.this);
        listView.setOnItemLongClickListener(Tram.this);

        displayingObjects();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                transitionAddingNewTramActivity();

            }
        });

        return view;
    }

    private void transitionAddingNewTramActivity() {

        Intent intent = new Intent(getActivity(), AddingNewTram.class);
        startActivity(intent);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

//        WORKING FOR 100 RECORDS
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("pojazdy");
//        query.whereEqualTo("typ", "T");
//        query.orderByAscending("taborowy");
//        List<ParseObject> results = null;
//        try {
//            results = query.find();
//            if(!results.isEmpty()) {
//                String objectId, taborowy, producent, model, info;
//                objectId = results.get(position).getObjectId();
//                taborowy = results.get(position).getString("taborowy");
//                producent = results.get(position).getString("producent");
//                model = results.get(position).getString("model");
//                info = results.get(position).getString("info");
//
//
//                //Toast.makeText(getContext(), objectId, Toast.LENGTH_SHORT).show();
//                objectIdTram = objectId;
//                taborowyTram = taborowy;
//                makerTram = producent;
//                modelTram = model;
//                infoTram = info;
//
//            }
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        transitionEditTramActivity();
//--------------------------------------------------------------------------------------------------
//        NOT WORKING
//        arrayList.clear();
//        listView.setAdapter(arrayAdapter);
//
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("pojazdy");
//        query.whereEqualTo("typ", "T");
//        query.orderByAscending("taborowy");
//        List<ParseObject> results = null;
//        try {
//            results = query.find();
//            if(!results.isEmpty()) {
//                String objectId;
//                objectId = results.get(position).getObjectId();
//                objectIdTram2 = objectId;
//                //Toast.makeText(getContext(), objectId, Toast.LENGTH_SHORT).show();
//
//            }
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        ParseObject po = ParseObject.createWithoutData("pojazdy", objectIdTram2);
//        po.deleteEventually();
//
//        FancyToast.makeText(getContext(),
//                getString(R.string.fancy_delete_tram),
//                Toast.LENGTH_SHORT, FancyToast.WARNING,
//                false).show();

        return false;
    }

    private void displayingObjects() {

        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("pojazdy");
        parseQuery.whereEqualTo("typ", "T");
        parseQuery.orderByAscending("taborowy");
        parseQuery.setLimit(400);
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                int i = 0;
                if (e == null) {

                    if (objects.size() > 0) {

                        for (ParseObject object : objects) {



                            arrayList.add(object.getString("taborowy")
                                    + " | " + object.getString("producent")
                                    + " | " + object.getString("model")
                                    +  " | " + object.getString("info"));

                            i++;
                        }
                        arrayList.add("Ilość pojazdów: " + i);
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