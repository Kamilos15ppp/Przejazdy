package com.example.przejazdy;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SignupActivity extends AppCompatActivity {

    private EditText edtUsernameSignup, edtPasswordSignup;
    private Button btnSignup2, btnLogin2;
    private ImageView imgSignupBus;
    private ConstraintLayout signupConstraintLayout;
    private boolean click2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setTitle(R.string.title_signup);
        //signupConstraintLayout = findViewById(R.id.signupConstraintLayout);
        edtUsernameSignup = findViewById(R.id.edtUsernameSignup);
        edtPasswordSignup = findViewById(R.id.edtPasswordSignup);
        btnSignup2 = findViewById(R.id.btnSignup2);
        btnLogin2 = findViewById(R.id.btnLogin2);
        imgSignupBus = findViewById(R.id.imgSignupBus);

        imgSignupBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (click2 == false) {

                    imgSignupBus.animate().alpha(0.5f).setDuration(200);
                    imgSignupBus.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    edtUsernameSignup.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    edtPasswordSignup.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    btnSignup2.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    btnLogin2.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    click2 = true;

                } else if (click2 == true){

                    imgSignupBus.animate().alpha(1f).setDuration(200);
                    imgSignupBus.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    edtUsernameSignup.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    edtPasswordSignup.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    btnSignup2.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    btnLogin2.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
                    click2 = false;

                }

            }
        });

        btnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

    }
}