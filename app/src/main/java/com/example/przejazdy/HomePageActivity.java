package com.example.przejazdy;

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
                FancyToast.makeText(HomePageActivity.this,
                "Wersja: 1.1\nAutor: Kamil",
                Toast.LENGTH_LONG, FancyToast.INFO,
                false).show();
                break;
            case  R.id.settings:
                FancyToast.makeText(HomePageActivity.this,
                        "Ustawienia",
                        Toast.LENGTH_SHORT, FancyToast.INFO,
                        false).show();
                break;
            case R.id.change_password:
                FancyToast.makeText(HomePageActivity.this,
                        "Zmień Hasło",
                        Toast.LENGTH_SHORT, FancyToast.INFO,
                        false).show();
                        break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }
}