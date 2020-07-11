package com.example.przejazdy;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

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

        showImageToast1(imgLoginBus);

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

        btnSignup1.setOnClickListener(LoginActivity.this);
        btnLogin1.setOnClickListener(LoginActivity.this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnSignup1:

                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btnLogin1:



                break;

        }

    }

    private void showImageToast1(View view) {

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        int x = imgLoginBus.getTop();
        int y = 350;
        Toast toast = Toast.makeText(context, R.string.tap_image, duration);
        toast.setGravity(Gravity.TOP, x, y);
        toast.show();

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