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

public class EditTram extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTabNumTram, edtMakerTram, edtModelTram, edtInfoTram;
    private Button btnEditTram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tram);

        setTitle(R.string.title_edit_tram);

        edtTabNumTram = findViewById(R.id.edtTabNumTram);
        edtMakerTram = findViewById(R.id.edtMakerTram);
        edtModelTram = findViewById(R.id.edtModelTram);
        edtInfoTram = findViewById(R.id.edtInfoTram);
        btnEditTram = findViewById(R.id.btnEditTram);

        edtTabNumTram.setText(Tram.taborowyTram);
        edtMakerTram.setText(Tram.makerTram);
        edtModelTram.setText(Tram.modelTram);
        edtInfoTram.setText(Tram.infoTram);

        btnEditTram.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (edtTabNumTram.getText().toString().equals("") ||
                edtMakerTram.getText().toString().equals("") ||
                edtModelTram.getText().toString().equals("")) {

            FancyToast.makeText(EditTram.this,
                    getString(R.string.fancy_adding_new_transit_required),
                    Toast.LENGTH_SHORT, FancyToast.INFO,
                    false).show();

        } else {

            ParseQuery<ParseObject> tramwaj = ParseQuery.getQuery("pojazdy");
            tramwaj.whereEqualTo("typ", "T");
            tramwaj.orderByAscending("taborowy");
            tramwaj.getInBackground(Tram.objectIdTram, new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {

                    if (e == null) {

                        object.put("taborowy", edtTabNumTram.getText().toString());
                        object.put("producent", edtMakerTram.getText().toString());
                        object.put("model", edtModelTram.getText().toString());
                        object.put("info", edtInfoTram.getText().toString());
                        object.saveInBackground();

                    }

                }

            });

            Tram.objectIdTram = "";
            Tram.taborowyTram = "";
            Tram.makerTram = "";
            Tram.modelTram = "";
            Tram.infoTram = "";

            FancyToast.makeText(EditTram.this,
                    getString(R.string.fancy_edit_tram),
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

                Intent i = new Intent().setClass(EditTram.this, HomePageActivity.class);
                startActivity(i);
            }
        };

        h.sendEmptyMessageDelayed(0, 800);
    }
}