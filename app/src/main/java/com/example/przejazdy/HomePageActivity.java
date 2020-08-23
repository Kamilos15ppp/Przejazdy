package com.example.przejazdy;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class HomePageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabAdapter tabAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark2));
        }

        ParseUser parseUser = ParseUser.getCurrentUser();
        String user = parseUser.getUsername();
        setTitle(getString(R.string.title_homepage) + " " + user);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewPager);
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager, false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.about:
                String version = null;
                //int verCode = 0;
                try {
                    Context context = this;
                    PackageInfo pInfo = context.getPackageManager().getPackageInfo(getPackageName(), 0);
                    version = pInfo.versionName;
                    //verCode = pInfo.versionCode;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }

                FancyToast.makeText(HomePageActivity.this,
                        getString(R.string.fancy_menu_version) + " " + 
                                version + "\n" + 
                                getString(R.string.fancy_menu_autor),
                        Toast.LENGTH_LONG,
                        FancyToast.INFO,
                        false).show();
                break;
            case  R.id.settings:
//                FancyToast.makeText(HomePageActivity.this,
//                        "Ustawienia",
//                        Toast.LENGTH_SHORT, FancyToast.INFO,
//                        false).show();

                //transitionSettingsActivity();

                break;
            case R.id.change_password:

                transitionChangePassActivity();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void transitionChangePassActivity() {

        Intent intent = new Intent(HomePageActivity.this, ChangePasswordActivity.class);
        startActivity(intent);
        finish();

    }

    private void transitionSettingsActivity() {

        Intent intent = new Intent(HomePageActivity.this, SettingsActivity.class);
        startActivity(intent);

    }

}