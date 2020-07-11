package com.example.przejazdy;

import android.app.ProgressDialog;
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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUsernameSignup, edtPasswordSignup;
    private Button btnSignup2, btnLogin2;
    private ImageView imgSignupBus;
    //private ConstraintLayout signupConstraintLayout;
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

        showImageToast2(imgSignupBus);

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

        btnLogin2.setOnClickListener(this);
        btnSignup2.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {

            //ParseUser.getCurrentUser().logOut();
            //transitionSocialMediaActivity();

        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnSignup2:

                if (edtUsernameSignup.getText().toString().equals("") ||
                        edtPasswordSignup.getText().toString().equals("") ) {

                    FancyToast.makeText(SignupActivity.this,
                            getString(R.string.fancy_username_pass_required),
                            Toast.LENGTH_SHORT, FancyToast.INFO,
                            false).show();

                } else {

                    final ParseUser appUser = new ParseUser();
                    appUser.setUsername(edtUsernameSignup.getText().toString());
                    appUser.setPassword(edtPasswordSignup.getText().toString());

                    Context context = SignupActivity.this;
                    final ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage(String.format("%s %s", getString(R.string.progressdialog_signingup), edtUsernameSignup.getText().toString()));
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {

                            if (e == null) {

                                FancyToast.makeText(SignupActivity.this,
                                        appUser.getUsername() + " " + getString(R.string.fancy_user_signedup),
                                        Toast.LENGTH_SHORT, FancyToast.SUCCESS,
                                        false).show();
                                //transitionPrzejazdy();

                            } else {

                                FancyToast.makeText(SignupActivity.this,
                                        getString(R.string.fancy_user_signedup_error) + " " + e.getMessage(),
                                        Toast.LENGTH_LONG, FancyToast.ERROR,
                                        false).show();

                            }

                            progressDialog.dismiss();

                        }
                    });

                }
                break;
            case R.id.btnLogin2:

                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                break;

        }

    }

    private void showImageToast2(View view) {

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        int x = imgSignupBus.getTop();
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