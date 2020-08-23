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
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class AddingNewTram extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTabNumTramAdd, edtMakerTramAdd, edtModelTramAdd, edtInfoTramAdd;
    private Button btnAddTram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_new_tram);

        setTitle(R.string.title_adding_new_tram);

        edtTabNumTramAdd = findViewById(R.id.edtTabNumTramAdd);
        edtMakerTramAdd = findViewById(R.id.edtMakerTramAdd);
        edtModelTramAdd = findViewById(R.id.edtModelTramAdd);
        edtInfoTramAdd = findViewById(R.id.edtInfoTramAdd);
        btnAddTram = findViewById(R.id.btnAddTram);

        btnAddTram.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (edtTabNumTramAdd.getText().toString().equals("") ||
                edtMakerTramAdd.getText().toString().equals("") ||
                edtModelTramAdd.getText().toString().equals("")) {

            FancyToast.makeText(AddingNewTram.this,
                    getString(R.string.fancy_adding_new_transit_required),
                    Toast.LENGTH_SHORT, FancyToast.INFO,
                    false).show();

        } else {

            addingNewTram();

        }

    }

    private void addingNewTram() {

        ParseObject pojazd = new ParseObject("pojazdy");

        pojazd.put("taborowy", edtTabNumTramAdd.getText().toString());
        pojazd.put("producent", edtMakerTramAdd.getText().toString());
        pojazd.put("model", edtModelTramAdd.getText().toString());
        pojazd.put("info", edtInfoTramAdd.getText().toString());
        pojazd.put("typ", "T");
        pojazd.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null) {

                    FancyToast.makeText(AddingNewTram.this,
                            getString(R.string.fancy_adding_new_tram),
                            Toast.LENGTH_SHORT, FancyToast.SUCCESS,
                            false).show();

                    transitionHomePageActivity();

                } else {

                    FancyToast.makeText(AddingNewTram.this,
                            e.getMessage(),
                            Toast.LENGTH_LONG, FancyToast.ERROR,
                            false).show();

                }

            }
        });

    }

    private void transitionHomePageActivity() {

//        Intent intent = new Intent(AddingNewTransit.this, Transits.class);
//        startActivity(intent);

        @SuppressLint("HandlerLeak") Handler h = new Handler(){
            @Override
            public void handleMessage(Message msg) {

                Intent i = new Intent().setClass(AddingNewTram.this, HomePageActivity.class);
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