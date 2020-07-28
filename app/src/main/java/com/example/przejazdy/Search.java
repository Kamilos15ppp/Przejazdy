package com.example.przejazdy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class Search extends Fragment implements View.OnClickListener{

    private EditText edtSearchData;
    private Button btnSearch, btnClear;
    private RadioButton rbtnButton;
    private RadioGroup rbtnGroup;
    private String columnName;

    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter arrayAdapter;
    private String userName;


    public Search() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        listView = view.findViewById(R.id.listView);
        arrayList = new ArrayList();
        Context context = getContext();
        arrayAdapter =new ArrayAdapter(context, android.R.layout.simple_list_item_1, arrayList);

        rbtnGroup = view.findViewById(R.id.radioGroup);
        btnSearch = view.findViewById(R.id.btnSearch);
        btnClear = view.findViewById(R.id.btnClear);
        edtSearchData = view.findViewById(R.id.edtSearchData);

        rbtnGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                 rbtnButton = (RadioButton) group.findViewById(checkedId);

//                if (null != rb && checkedId > -1) {
//                    Toast.makeText(getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
//                }

            }
        });

        btnSearch.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        return view;
    }

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnSearch:

                int index = rbtnGroup.indexOfChild(rbtnButton.findViewById(rbtnGroup.getCheckedRadioButtonId()));
                //rbtnButton = (RadioButton) rbtnGroup.findViewById(rbtnGroup.getCheckedRadioButtonId());
                //Toast.makeText(getContext(), String.valueOf(index), Toast.LENGTH_SHORT).show();

                switch (index) {

                    case 0:
                        columnName = "taborowy";
                        break;
                    case 1:
                        columnName = "linia";
                        break;
                    case 2:
                        columnName = "poczatkowy";
                        break;
                    case 3:
                        columnName = "koncowy";
                        break;
                    case 4:
                        columnName = "data";
                        break;

                }
                //Toast.makeText(getContext(), columnName, Toast.LENGTH_SHORT).show();
                displayingObject();

                break;
            case R.id.btnClear:

                clearAll();
                break;

        }

    }

    private void displayingObject() {

        arrayList.clear();

        ParseUser parseUser = ParseUser.getCurrentUser();
        userName = parseUser.getUsername().toLowerCase();
        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("przejazdy_" + userName);
        parseQuery.whereEqualTo(columnName, edtSearchData.getText().toString());
        parseQuery.orderByDescending("createdAt");
        parseQuery.setLimit(250);
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
                                    + " | " + object.getString("koncowy")
                                    + " | " + object.getString("data"));

                            i++;
                        }

                        arrayList.add("Ilość przejazdów: " + i);
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

    private void clearAll() {

        edtSearchData.setText("");
        rbtnGroup.clearCheck();
        arrayList.clear();
        listView.setAdapter(arrayAdapter);
    }
}