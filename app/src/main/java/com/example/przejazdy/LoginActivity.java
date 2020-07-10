package com.example.przejazdy;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private Button btnLogin, btnSignup;
    private ImageView imgLoginBus;
    private ConstraintLayout loginConstraintLayout;
    private boolean click = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Logowanie");
        loginConstraintLayout = findViewById(R.id.loginContraintLayout);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        imgLoginBus = findViewById(R.id.imgLoginBus);

        imgLoginBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (click == false) {

                    imgLoginBus.animate().alpha(0.5f).setDuration(200);
                    imgLoginBus.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    edtUsername.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    edtPassword.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    btnLogin.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    btnSignup.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    click = true;

                } else if (click == true){

                    imgLoginBus.animate().alpha(1f).setDuration(200);
                    imgLoginBus.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    edtUsername.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    edtPassword.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    btnLogin.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    btnSignup.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    click = false;

                }


            }
        });
    }


}