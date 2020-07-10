package com.example.przejazdy;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsernameLogin, edtPasswordLogin;
    private Button btnLogin1, btnSignup1;
    private ImageView imgLoginBus;
    //private ConstraintLayout loginConstraintLayout;
    private boolean click = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle(R.string.title_login);
        //loginConstraintLayout = findViewById(R.id.loginContraintLayout);
        edtUsernameLogin = findViewById(R.id.edtUsernameLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        btnLogin1 = findViewById(R.id.btnLogin1);
        btnSignup1 = findViewById(R.id.btnSignup1);
        imgLoginBus = findViewById(R.id.imgLoginBus);

        imgLoginBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (click == false) {

                    imgLoginBus.animate().alpha(0.5f).setDuration(200);
                    imgLoginBus.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    edtUsernameLogin.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    edtPasswordLogin.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    btnLogin1.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    btnSignup1.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    click = true;

                } else if (click == true){

                    imgLoginBus.animate().alpha(1f).setDuration(200);
                    imgLoginBus.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    edtUsernameLogin.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    edtPasswordLogin.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    btnLogin1.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    btnSignup1.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    click = false;

                }


            }
        });

        btnSignup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);

            }
        });

    }


}