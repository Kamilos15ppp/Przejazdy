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
import com.shashank.sony.fancytoastlib.FancyToast;

public class EditBus extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTabNumBus, edtMakerBus, edtModelBus, edtInfoBus;
    private Button btnEditBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bus);

        setTitle(R.string.title_edit_bus);

        edtTabNumBus = findViewById(R.id.edtTabNumBus);
        edtMakerBus = findViewById(R.id.edtMakerBus);
        edtModelBus = findViewById(R.id.edtModelBus);
        edtInfoBus = findViewById(R.id.edtInfoBus);
        btnEditBus = findViewById(R.id.btnEditBus);

        edtTabNumBus.setText(Bus.taborowyBus);
        edtMakerBus.setText(Bus.makerBus);
        edtModelBus.setText(Bus.modelBus);
        edtInfoBus.setText(Bus.infoBus);

        btnEditBus.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (edtTabNumBus.getText().toString().equals("") ||
                edtMakerBus.getText().toString().equals("") ||
                edtModelBus.getText().toString().equals("")) {

            FancyToast.makeText(EditBus.this,
                    getString(R.string.fancy_adding_new_transit_required),
                    Toast.LENGTH_SHORT, FancyToast.INFO,
                    false).show();

        } else {

            ParseQuery<ParseObject> autobus = ParseQuery.getQuery("pojazdy");
            autobus.whereEqualTo("typ", "A");
            autobus.orderByAscending("taborowy");
            autobus.getInBackground(Bus.objectIdBus, new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {

                    if (e == null) {

                        object.put("taborowy", edtTabNumBus.getText().toString());
                        object.put("producent", edtMakerBus.getText().toString());
                        object.put("model", edtModelBus.getText().toString());
                        object.put("info", edtInfoBus.getText().toString());
                        object.saveInBackground();

                    }

                }

            });

            FancyToast.makeText(EditBus.this,
                    getString(R.string.fancy_edit_bus),
                    Toast.LENGTH_SHORT, FancyToast.SUCCESS,
                    false).show();

            transitionHomePageActivity();

        }

    }

    private void transitionHomePageActivity() {

//        Intent intent = new Intent(AddingNewTransit.this, Transits.class);
//        startActivity(intent);

        @SuppressLint("HandlerLeak") Handler h = new Handler(){
            @Override
            public void handleMessage(Message msg) {

                Intent i = new Intent().setClass(EditBus.this, HomePageActivity.class);
                startActivity(i);
            }
        };

        h.sendEmptyMessageDelayed(0, 800);
    }
}