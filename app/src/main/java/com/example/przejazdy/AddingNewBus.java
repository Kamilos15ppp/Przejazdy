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

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class AddingNewBus extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTabNumBusAdd, edtMakerBusAdd, edtModelBusAdd, edtInfoBusAdd;
    private Button btnAddBus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_new_bus);

        setTitle(R.string.title_adding_new_bus);

        edtTabNumBusAdd = findViewById(R.id.edtTabNumBusAdd);
        edtMakerBusAdd = findViewById(R.id.edtMakerBusAdd);
        edtModelBusAdd = findViewById(R.id.edtModelBusAdd);
        edtInfoBusAdd = findViewById(R.id.edtInfoBusAdd);
        btnAddBus = findViewById(R.id.btnAddBus);

        btnAddBus.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (edtTabNumBusAdd.getText().toString().equals("") ||
                edtMakerBusAdd.getText().toString().equals("") ||
                edtModelBusAdd.getText().toString().equals("")) {

            FancyToast.makeText(AddingNewBus.this,
                    getString(R.string.fancy_adding_new_transit_required),
                    Toast.LENGTH_SHORT, FancyToast.INFO,
                    false).show();

        } else {

            ParseObject pojazd = new ParseObject("pojazdy");

            pojazd.put("taborowy", edtTabNumBusAdd.getText().toString());
            pojazd.put("producent", edtMakerBusAdd.getText().toString());
            pojazd.put("model", edtModelBusAdd.getText().toString());
            pojazd.put("info", edtInfoBusAdd.getText().toString());
            pojazd.put("typ", "A");
            pojazd.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null) {

                        FancyToast.makeText(AddingNewBus.this,
                                getString(R.string.fancy_adding_new_bus),
                                Toast.LENGTH_SHORT, FancyToast.SUCCESS,
                                false).show();

                        transitionHomePageActivity();

                    } else {

                        FancyToast.makeText(AddingNewBus.this,
                                e.getMessage(),
                                Toast.LENGTH_LONG, FancyToast.ERROR,
                                false).show();

                    }

                }
            });
        }

    }

    private void transitionHomePageActivity() {

//        Intent intent = new Intent(AddingNewTransit.this, AddTransit.class);
//        startActivity(intent);

        @SuppressLint("HandlerLeak") Handler h = new Handler(){
            @Override
            public void handleMessage(Message msg) {

                Intent i = new Intent().setClass(AddingNewBus.this, HomePageActivity.class);
                startActivity(i);
            }
        };

        h.sendEmptyMessageDelayed(0, 800);
    }
}