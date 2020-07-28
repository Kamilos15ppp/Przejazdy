package com.example.przejazdy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddingNewTransit extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTabNumAdding, edtLineNumAdding, edtDirectionAdding,
            edtFirstAdding, edtLastAdding;
    private Button btnAddingTransit;
    final String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    public String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_new_transit);

        setTitle(R.string.title_adding_new_transit);

        edtTabNumAdding = findViewById(R.id.edtTabNumAdding);
        edtLineNumAdding = findViewById(R.id.edtLineNumAdding);
        edtDirectionAdding = findViewById(R.id.edtDirectionAdding);
        edtFirstAdding = findViewById(R.id.edtFirstAdding);
        edtLastAdding = findViewById(R.id.edtLastAdding);
        btnAddingTransit = findViewById(R.id.btnAddingTransit);

        btnAddingTransit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (edtTabNumAdding.getText().toString().equals("") ||
                edtLineNumAdding.getText().toString().equals("") ||
                edtDirectionAdding.getText().toString().equals("") ||
                edtFirstAdding.getText().toString().equals("") ||
                edtLastAdding.getText().toString().equals("")) {

            FancyToast.makeText(AddingNewTransit.this,
                    getString(R.string.fancy_adding_new_transit_required),
                    Toast.LENGTH_SHORT, FancyToast.INFO,
                    false).show();

        } else {

            addingNewTransit();

        }
    }

    private void addingNewTransit() {

        ParseUser parseUser = ParseUser.getCurrentUser();
        userName = parseUser.getUsername().toLowerCase();
        ParseObject przejazd = new ParseObject("przejazdy_" + userName);
        przejazd.put("taborowy", edtTabNumAdding.getText().toString());
        przejazd.put("linia", edtLineNumAdding.getText().toString());
        przejazd.put("kierunek", edtDirectionAdding.getText().toString());
        przejazd.put("poczatkowy", edtFirstAdding.getText().toString());
        przejazd.put("koncowy", edtLastAdding.getText().toString());
        przejazd.put("data", currentDate);
        przejazd.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null) {

                    FancyToast.makeText(AddingNewTransit.this,
                            getString(R.string.fancy_adding_new_transit),
                            Toast.LENGTH_SHORT, FancyToast.SUCCESS,
                            false).show();

                    transitionHomePageActivity();

                } else {

                    FancyToast.makeText(AddingNewTransit.this,
                            e.getMessage(),
                            Toast.LENGTH_LONG, FancyToast.ERROR,
                            false).show();

                }

            }
        });

    }

    private void transitionHomePageActivity() {

//        Intent intent = new Intent(AddingNewTransit.this, AddTransit.class);
//        startActivity(intent);

        @SuppressLint("HandlerLeak") Handler h = new Handler(){
            @Override
            public void handleMessage(Message msg) {

                Intent i = new Intent().setClass(AddingNewTransit.this, HomePageActivity.class);
                startActivity(i);
            }
        };

        h.sendEmptyMessageDelayed(0, 800);
    }

    public void rootLayoutTapped(View view) {

        try {

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}