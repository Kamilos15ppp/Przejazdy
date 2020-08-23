package com.example.przejazdy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddingNewTransit extends AppCompatActivity implements View.OnClickListener {

    final String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    public String userName;
    private AutoCompleteTextView edtTabNumAdding, edtLineNumAdding, edtDirectionAdding,
            edtFirstAdding, edtLastAdding;
    private Button btnAddingTransit, btnClearTransit;
    private ArrayList<String> tabNumArrayList, lineArrayList;
    private ArrayAdapter<String> tabArrayAdapter, lineArrayAdapter, stopsArrayAdapter, directionsArrayAdapter;
    private String[] stops = {"Salwator", "Wzgórza Krzesławickie", "Bronowice Małe", "Krowodrza Górka", "Nowy Bieżanów P + R", "Łagiewniki", "Bronowice",
            "Cichy Kącik", "Kurdwanów P + R", "Węgrzynowice", "Zesławice", "Mały Płaszów P + R", "Mistrzejowice", "Szpital Rydygiera", "Zajezdnia Płaszów",
            "Pleszów", "Chałupki", "Nowy Kleparz", "Azory", "Mydlniki", "Przylasek Rusiecki", "Cmentarz Batowice", "Czyżyny Dworzec", "Prądnik Biały",
            "Aleja Przyjaźni", "Ruszcza", "Os.Piastów", "Rżąka", "Zajezdnia Wola Duchacka", "Wróżenice", "Nowy Bieżanów Południe", "Os.Kurdwanów", "Kombinat",
            "Dworzec Główny Zachód", "Kujawy", "Os.Na Stoku", "Dworzec Główny Wschód", "Łososkowice Remiza", "Chobot Leśniczówka", "Czulice Kościół", "Słomniki Osiedle",
            "Goszcza Dworek", "Czerwone Maki P + R", "Niepołomice Dworzec", "Wieliczka Miasto", "Bulwarowa", "Chełmońskiego Pętla", "Cracovia Stadion", "Przybyszewskiego",
            "Teatr Ludowy", "Cienista", "Wańkowicza", "Makuszyńskiego", "Nad Dłubnią", "Zajezdnia Bieńczyce", "Petőfiego", "Władysława Jagiełły", "Jana Kazimierza",
            "Leszka Białego", "Darwina", "Grębałów", "Morcinka", "Kantorowice", "Zesławice Ogródki Działkowe", "Fatimska", "Nowolipki", "Zakład Przeróbki", "Szymańskiego",
            "Branice Pagórek", "Branice Na Dole", "Branice Szkoła", "Branice", "Branice Ośrodek Zdrowia", "Chałupki Górne", "Kąkolowa", "Truskawkowa", "Architektów",
            "Os.Na Stoku Szkoła", "Wiadukty", "Elektromontaż", "Zajezdnia Nowa Huta", "Struga", "Aleja Róż", "Os.Zgody", "Rondo Kocmyrzowskie im.Ks.Gorzelanego", "DH Wanda",
            "Os.Kościuszkowskie", "Dąbrowskiej", "Medweckiego", "Stella - Sawickiego", "Os.Dywizjonu 303", "Wiślicka", "Os.Oświecenia", "Park Wodny", "Słoneckiego", "Rondo Barei",
            "Miechowity", "Strzelców", "Prądnik Czerwony", "Bulwarowa Ogródki Działkowe", "Klasztorna", "Os.Na Skarpie", "Plac Centralny im.R.Reagana", "Os.Kolorowe", "Rondo Czyżyńskie",
            "Centralna", "Rondo 308.Dywizjonu", "M1 Nowohucka", "M1 Al.Pokoju", "Tauron Arena Kraków Al.Pokoju", "Elektrociepłownia Kraków", "Koszykarska", "Stoczniowców", "Lipska",
            "Dworzec Płaszów Estakada", "Kuklińskiego", "Podgórze SKA", "Kamieńskiego Wiadukt", "Bonarka", "Kamieńskiego", "Sławka", "Wola Duchacka", "Nowosądecka", "Beskidzka",
            "Bujaka", "Halszki", "Stojałowskiego", "Plac Inwalidów", "Teatr Bagatela", "Teatr Słowackiego", "Rondo Mogilskie", "Muzeum Narodowe", "Rondo Grunwaldzkie", "Conrada",
            "Os.Mistrzejowice Nowe", "Kleeberga", "Rondo Hipokratesa", "Prokocim Szpital", "Jerzmanowskiego", "Bieżanowska", "Kostrze OSP", "Kolna", "Tyniecka Autostrada",
            "Os.Podwawelskie", "Zielińskiego"};
    private String[] directions = {"Salwator", "Wzgórza Krzesławickie", "Bronowice Małe", "Krowodrza Górka", "Nowy Bieżanów P + R", "Łagiewniki", "Bronowice",
            "Cichy Kącik", "Kurdwanów P + R", "Węgrzynowice", "Zesławice", "Mały Płaszów P + R", "Mistrzejowice", "Szpital Rydygiera", "Zajezdnia Płaszów",
            "Pleszów", "Chałupki", "Nowy Kleparz", "Azory", "Mydlniki", "Przylasek Rusiecki", "Cmentarz Batowice", "Czyżyny Dworzec", "Prądnik Biały",
            "Aleja Przyjaźni", "Ruszcza", "Os.Piastów", "Rżąka", "Zajezdnia Wola Duchacka", "Wróżenice", "Nowy Bieżanów Południe", "Os.Kurdwanów", "Kombinat",
            "Dworzec Główny Zachód", "Kujawy", "Os.Na Stoku", "Dworzec Główny Wschód", "Łososkowice Remiza", "Chobot Leśniczówka", "Czulice Kościół", "Słomniki Osiedle",
            "Goszcza Dworek", "Czerwone Maki P + R", "Niepołomice Dworzec", "Wieliczka Miasto", "Bulwarowa", "Chełmońskiego Pętla", "Cracovia Stadion", "Przybyszewskiego",
            "Tauron Arena Kraków Wieczysta", "Ugorek", "Tyniec Kamieniołom", "Os.Podwawelskie", "Borek Fałęcki"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_new_transit);

        setTitle(R.string.title_adding_new_transit);

        edtTabNumAdding = findViewById(R.id.edtTabNumAdding);
        edtLineNumAdding = findViewById(R.id.edtLineNumAdding);
        edtDirectionAdding = findViewById(R.id.edtDirectionAdding);
        edtFirstAdding = findViewById(R.id.edtFirstAdding);
        edtLastAdding = findViewById(R.id.edtLastAdding);
        btnAddingTransit = findViewById(R.id.btnAddingTransit);
        btnClearTransit = findViewById(R.id.btnClearTransit);

//        ------------------------------------------------------------------------------------------
        tabNumArrayList = new ArrayList<>();
        lineArrayList = new ArrayList<>();
        fillAll();
        tabArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tabNumArrayList);
        lineArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lineArrayList);
        stopsArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stops);
        directionsArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, directions);
        edtTabNumAdding.setAdapter(tabArrayAdapter);
        edtLineNumAdding.setAdapter(lineArrayAdapter);
        edtDirectionAdding.setAdapter(directionsArrayAdapter);
        edtFirstAdding.setAdapter(stopsArrayAdapter);
        edtLastAdding.setAdapter(stopsArrayAdapter);
        edtTabNumAdding.setThreshold(1);
        edtLineNumAdding.setThreshold(1);
        edtDirectionAdding.setThreshold(2);
        edtFirstAdding.setThreshold(2);
        edtLastAdding.setThreshold(2);
//--------------------------------------------------------------------------------------------------

        btnAddingTransit.setOnClickListener(this);
        btnClearTransit.setOnClickListener(this);
    }

    private void fillAll() {

        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("pojazdy");
        parseQuery.orderByAscending("taborowy");
        parseQuery.setLimit(990);
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {

                    if (objects.size() > 0) {

                        for (ParseObject object : objects) {

                            tabNumArrayList.add(object.getString("taborowy"));

                        }

                    }

                } else {

                    FancyToast.makeText(getApplicationContext(),
                            e.getMessage(),
                            Toast.LENGTH_LONG, FancyToast.ERROR,
                            false).show();

                }

            }

        });

        ParseQuery<ParseObject> parseQuery2 = ParseQuery.getQuery("autocomplete");
        parseQuery2.orderByAscending("L");
        parseQuery2.setLimit(200);
        parseQuery2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {

                    if (objects.size() > 0) {

                        for (ParseObject object : objects) {

                            lineArrayList.add(object.getString("L"));

                        }

                    }

                } else {

                    FancyToast.makeText(getApplicationContext(),
                            e.getMessage(),
                            Toast.LENGTH_LONG, FancyToast.ERROR,
                            false).show();

                }

            }

        });

//        ParseQuery<ParseObject> parseQuery3 = ParseQuery.getQuery("autocomplete");
//        parseQuery3.orderByDescending("K");
//        parseQuery3.setLimit(200);
//        parseQuery3.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//                if (e == null) {
//
//                    if (objects.size() > 0) {
//
//                        for (ParseObject object : objects) {
//
//                            directionArrayList.add(object.getString("K"));
//
//                        }
//
//                    }
//
//                } else {
//
//                    FancyToast.makeText(getApplicationContext(),
//                            e.getMessage(),
//                            Toast.LENGTH_LONG, FancyToast.ERROR,
//                            false).show();
//
//                }
//
//            }
//
//        });

//        ParseQuery<ParseObject> parseQuery4 = ParseQuery.getQuery("autocomplete");
//        parseQuery4.orderByAscending("P");
//        parseQuery4.setLimit(200);
//        parseQuery4.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//                if (e == null) {
//
//                    if (objects.size() > 0) {
//
//                        for (ParseObject object : objects) {
//
//                            firstLastArrayList.add(object.getString("P"));
//
//                        }
//
//                    }
//
//                } else {
//
//                    FancyToast.makeText(getApplicationContext(),
//                            e.getMessage(),
//                            Toast.LENGTH_LONG, FancyToast.ERROR,
//                            false).show();
//
//                }
//
//            }
//
//        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnAddingTransit:

                if (edtTabNumAdding.getText().toString().equals("") ||
                        edtLineNumAdding.getText().toString().equals("") ||
                        edtDirectionAdding.getText().toString().equals("") ||
                        edtFirstAdding.getText().toString().equals("") ||
                        edtLastAdding.getText().toString().equals("")) {

                    FancyToast.makeText(AddingNewTransit.this,
                            getString(R.string.fancy_adding_new_transit_required),
                            Toast.LENGTH_SHORT, FancyToast.INFO,
                            false).show();

                } else {

                    addingNewTransit();

                }
                break;
            case R.id.btnClearTransit:

                edtTabNumAdding.setText("");
                edtLineNumAdding.setText("");
                edtDirectionAdding.setText("");
                edtFirstAdding.setText("");
                edtLastAdding.setText("");
                break;
        }


    }

    private void addingNewTransit() {

        ParseUser parseUser = ParseUser.getCurrentUser();
        userName = parseUser.getUsername().toLowerCase();
        ParseObject przejazd = new ParseObject("przejazdy_" + userName);
        przejazd.put("taborowy", edtTabNumAdding.getText().toString());
        przejazd.put("linia", edtLineNumAdding.getText().toString());
        przejazd.put("kierunek", edtDirectionAdding.getText().toString());
        przejazd.put("poczatkowy", edtFirstAdding.getText().toString());
        przejazd.put("koncowy", edtLastAdding.getText().toString());
        przejazd.put("data", currentDate);
        przejazd.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null) {

                    FancyToast.makeText(AddingNewTransit.this,
                            getString(R.string.fancy_adding_new_transit),
                            Toast.LENGTH_SHORT, FancyToast.SUCCESS,
                            false).show();

                    transitionHomePageActivity();

                } else {

                    FancyToast.makeText(AddingNewTransit.this,
                            e.getMessage(),
                            Toast.LENGTH_LONG, FancyToast.ERROR,
                            false).show();

                }

            }
        });

    }

    private void transitionHomePageActivity() {

//        Intent intent = new Intent(AddingNewTransit.this, Transits.class);
//        startActivity(intent);

        @SuppressLint("HandlerLeak") Handler h = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                Intent i = new Intent().setClass(AddingNewTransit.this, HomePageActivity.class);
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