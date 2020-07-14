package com.example.przejazdy;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

public class AddTransit extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter arrayAdapter;

    public AddTransit() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_transit, container, false);

        listView = view.findViewById(R.id.listView);
        arrayList = new ArrayList();
        Context context = getContext();
        arrayAdapter =new ArrayAdapter(context, android.R.layout.simple_list_item_1, arrayList);

        listView.setOnItemClickListener(AddTransit.this);
        listView.setOnItemLongClickListener(AddTransit.this);

        ParseUser parseUser = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("przejazdy_" + parseUser.getUsername());
        parseQuery.orderByAscending("data");
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
                        arrayList.add("Ilość rekordów: " + i);
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



        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
}