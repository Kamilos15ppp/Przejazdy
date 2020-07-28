package com.example.przejazdy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtChangePass;
    private Button btnChangePass, btnGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        setTitle(R.string.title_change_pass);

        edtChangePass = findViewById(R.id.edtChangePass);
        btnChangePass = findViewById(R.id.btnChangePass);
        btnGoBack = findViewById(R.id.btnGoBack);

        btnChangePass.setOnClickListener(this);
        btnGoBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnChangePass:

                if (edtChangePass.getText().toString().equals("")) {

                    FancyToast.makeText(ChangePasswordActivity.this,
                            getString(R.string.fancy_change_pass_required),
                            Toast.LENGTH_SHORT, FancyToast.INFO,
                            false).show();

                } else {

                    changePass();
                    transitionHomePageActivity();

                }
                break;
            case R.id.btnGoBack:

                transitionHomePageActivity();
                FancyToast.makeText(ChangePasswordActivity.this,
                        getString(R.string.fancy_go_back),
                        Toast.LENGTH_SHORT, FancyToast.INFO,
                        false).show();
                break;

        }

    }

    private void changePass() {

        String pass = edtChangePass.getText().toString();
        ParseUser user = ParseUser.getCurrentUser();
        user.setPassword(pass);
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null) {

                    FancyToast.makeText(ChangePasswordActivity.this,
                            getString(R.string.fancy_change_pass),
                            Toast.LENGTH_SHORT, FancyToast.SUCCESS,
                            false).show();

                }

            }
        });

    }

    private void transitionHomePageActivity() {

        Intent intent = new Intent(ChangePasswordActivity.this, HomePageActivity.class);
        startActivity(intent);
        finish();

    }

}