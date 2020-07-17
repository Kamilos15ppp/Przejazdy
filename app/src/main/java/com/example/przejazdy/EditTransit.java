package com.example.przejazdy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class EditTransit extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTabNumEdit, edtLineNumEdit, edtDirectionEdit,
            edtFirstEdit, edtLastEdit;
    private Button btnEditTransit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_transit);

        setTitle(R.string.title_edit_transit);

        edtTabNumEdit = findViewById(R.id.edtTabNumEdit);
        edtLineNumEdit = findViewById(R.id.edtLineNumEdit);
        edtDirectionEdit = findViewById(R.id.edtDirectionEdit);
        edtFirstEdit = findViewById(R.id.edtFirstEdit);
        edtLastEdit = findViewById(R.id.edtLastEdit);
        btnEditTransit = findViewById(R.id.btnEditTransit);

        btnEditTransit.setOnClickListener(this);

        edtTabNumEdit.setText(AddTransit.taborowyPublic);
        edtLineNumEdit.setText(AddTransit.liniaPublic);
        edtDirectionEdit.setText(AddTransit.kierunekPublic);
        edtFirstEdit.setText(AddTransit.poczatkowyPublic);
        edtLastEdit.setText(AddTransit.koncowyPublic);

    }

    @Override
    public void onClick(View view) {

        if (edtTabNumEdit.getText().toString().equals("") ||
                edtLineNumEdit.getText().toString().equals("") ||
                edtDirectionEdit.getText().toString().equals("") ||
                edtFirstEdit.getText().toString().equals("") ||
                edtLastEdit.getText().toString().equals("")) {

            FancyToast.makeText(EditTransit.this,
                    getString(R.string.fancy_adding_new_transit_required),
                    Toast.LENGTH_SHORT, FancyToast.INFO,
                    false).show();

        } else {

            final String userName;
            ParseUser parseUser = ParseUser.getCurrentUser();
            userName = parseUser.getUsername().toLowerCase();
            ParseQuery<ParseObject> przejazd = ParseQuery.getQuery("przejazdy_" + userName);
            przejazd.getInBackground(AddTransit.objectIdPublic, new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {

                    if (e == null) {

                        object.put("taborowy", edtTabNumEdit.getText().toString());
                        object.put("linia", edtLineNumEdit.getText().toString());
                        object.put("kierunek", edtDirectionEdit.getText().toString());
                        object.put("poczatkowy", edtFirstEdit.getText().toString());
                        object.put("koncowy", edtLastEdit.getText().toString());
                        object.saveInBackground();

                    }

                }

            });

            FancyToast.makeText(EditTransit.this,
                    getString(R.string.fancy_edit_transit),
                    Toast.LENGTH_SHORT, FancyToast.SUCCESS,
                    false).show();

            transitionHomePageActivity();

        }

    }

    private void transitionHomePageActivity() {

//        Intent intent = new Intent(AddingNewTransit.this, AddTransit.class);
//        startActivity(intent);
//
        @SuppressLint("HandlerLeak") Handler h = new Handler(){
            @Override
            public void handleMessage(Message msg) {

                Intent i = new Intent().setClass(EditTransit.this, HomePageActivity.class);
                startActivity(i);
            }
        };

        h.sendEmptyMessageDelayed(0, 800);
    }

}