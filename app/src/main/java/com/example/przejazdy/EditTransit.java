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

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class EditTransit extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTabNumEdit, edtLineNumEdit, edtDirectionEdit,
            edtFirstEdit, edtLastEdit;
    private Button btnEditTransit;
    public String userName;

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

        edtTabNumEdit.setText(AddTransit.taborowyTransit);
        edtLineNumEdit.setText(AddTransit.liniaTransit);
        edtDirectionEdit.setText(AddTransit.kierunekTransit);
        edtFirstEdit.setText(AddTransit.poczatkowyTransit);
        edtLastEdit.setText(AddTransit.koncowyTransit);

        btnEditTransit.setOnClickListener(this);

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

            editTransit();

        }

    }

    private void editTransit() {

        ParseUser parseUser = ParseUser.getCurrentUser();
        userName = parseUser.getUsername().toLowerCase();
        ParseQuery<ParseObject> przejazd = ParseQuery.getQuery("przejazdy_" + userName);
        przejazd.getInBackground(AddTransit.objectIdTransit, new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {

                if (e == null) {

                    object.put("taborowy", edtTabNumEdit.getText().toString());
                    object.put("linia", edtLineNumEdit.getText().toString());
                    object.put("kierunek", edtDirectionEdit.getText().toString());
                    object.put("poczatkowy", edtFirstEdit.getText().toString());
                    object.put("koncowy", edtLastEdit.getText().toString());
                    object.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {

                            if (e == null) {

                                FancyToast.makeText(EditTransit.this,
                                        getString(R.string.fancy_edit_transit),
                                        Toast.LENGTH_SHORT, FancyToast.SUCCESS,
                                        false).show();

                                transitionHomePageActivity();

                            } else {

                                FancyToast.makeText(EditTransit.this,
                                        e.getMessage(),
                                        Toast.LENGTH_LONG, FancyToast.ERROR,
                                        false).show();

                            }

                        }
                    });

                }

            }

        });

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

    public void rootLayoutTapped(View view) {

        try {

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}