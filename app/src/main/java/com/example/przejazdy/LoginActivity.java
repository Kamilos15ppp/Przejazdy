package com.example.przejazdy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

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

        //showImageToast1(imgLoginBus);

//        imgLoginBus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (click == false) {
//
//                    imgLoginBus.animate().alpha(0.5f).setDuration(200);
//                    imgLoginBus.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
//                    edtUsernameLogin.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
//                    edtPasswordLogin.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
//                    btnLogin1.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
//                    btnSignup1.animate().translationYBy((Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
//                    click = true;
//
//                } else if (click == true){
//
//                    imgLoginBus.animate().alpha(1f).setDuration(200);
//                    imgLoginBus.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
//                    edtUsernameLogin.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
//                    edtPasswordLogin.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
//                    btnLogin1.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
//                    btnSignup1.animate().translationYBy(-(Resources.getSystem().getDisplayMetrics().heightPixels / 3) ).setDuration(400);
//                    click = false;
//
//                }
//
//
//            }
//        });

        btnSignup1.setOnClickListener(this);
        btnLogin1.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }

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

                if (edtUsernameLogin.getText().toString().equals("") ||
                        edtPasswordLogin.getText().toString().equals("") ) {

                    FancyToast.makeText(LoginActivity.this,
                            getString(R.string.fancy_username_pass_required),
                            Toast.LENGTH_SHORT, FancyToast.INFO,
                            false).show();

                } else {

                    ParseUser.logInInBackground(edtUsernameLogin.getText().toString(),
                            edtPasswordLogin.getText().toString(),
                            new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {

                                    if (user != null && e == null) {

                                        FancyToast.makeText(LoginActivity.this,
                                                user.getUsername() + " " + getString(R.string.fancy_user_login),
                                                Toast.LENGTH_SHORT, FancyToast.SUCCESS,
                                                false).show();
                                        transitionHomePageActivity();

                                    } else {

                                        edtUsernameLogin.setText("");
                                        edtPasswordLogin.setText("");
                                        FancyToast.makeText(LoginActivity.this,
                                                getString(R.string.fancy_user_login_user_pass),
                                                Toast.LENGTH_SHORT, FancyToast.ERROR,
                                                false).show();

                                    }

                                }
                            });
                }

                break;

        }

    }

//    private void showImageToast1(View view) {
//
//        Context context = getApplicationContext();
//        int duration = Toast.LENGTH_SHORT;
//        int x = imgLoginBus.getTop();
//        int y = 350;
//        Toast toast = Toast.makeText(context, R.string.tap_image, duration);
//        toast.setGravity(Gravity.TOP, x, y);
//        toast.show();
//
//    }

    public void rootLayoutTapped(View view) {

        try {

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    private void transitionHomePageActivity() {

        Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
        startActivity(intent);
        finish();

    }
}