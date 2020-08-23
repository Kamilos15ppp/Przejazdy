package com.example.przejazdy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

public class Search extends Fragment implements View.OnClickListener{

    private AutoCompleteTextView edtSearchData;
    private Button btnSearch, btnClear;
    private RadioButton rbtnButton;
    private RadioGroup rbtnGroup;
    private String columnName;

    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter arrayAdapter;
    private String userName;


    private ArrayAdapter<String> autofillArrayAdapter;
    private String[] autofillData = {"Salwator", "Wzgórza Krzesławickie", "Bronowice Małe", "Krowodrza Górka", "Nowy Bieżanów P + R", "Łagiewniki", "Bronowice",
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
            "Os.Podwawelskie", "Zielińskiego", "Tauron Arena Kraków Wieczysta", "Ugorek", "Tyniec Kamieniołom", "Os.Podwawelskie", "Borek Fałęcki"};

    public Search() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        listView = view.findViewById(R.id.listView);
        arrayList = new ArrayList();
        Context context = getContext();
        arrayAdapter =new ArrayAdapter(context, android.R.layout.simple_list_item_1, arrayList);

        rbtnGroup = view.findViewById(R.id.radioGroup);
        btnSearch = view.findViewById(R.id.btnSearch);
        btnClear = view.findViewById(R.id.btnClear);
        edtSearchData = view.findViewById(R.id.edtSearchData);

        autofillArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, autofillData);
        edtSearchData.setAdapter(autofillArrayAdapter);
        edtSearchData.setThreshold(1);

        rbtnGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                 rbtnButton = (RadioButton) group.findViewById(checkedId);

//                if (null != rb && checkedId > -1) {
//                    Toast.makeText(getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
//                }

            }
        });

        btnSearch.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        return view;
    }

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnSearch:

                int index = rbtnGroup.indexOfChild(rbtnButton.findViewById(rbtnGroup.getCheckedRadioButtonId()));
                //rbtnButton = (RadioButton) rbtnGroup.findViewById(rbtnGroup.getCheckedRadioButtonId());
                //Toast.makeText(getContext(), String.valueOf(index), Toast.LENGTH_SHORT).show();

                switch (index) {

                    case 0:
                        columnName = "taborowy";
                        break;
                    case 1:
                        columnName = "linia";
                        break;
                    case 2:
                        columnName = "poczatkowy";
                        break;
                    case 3:
                        columnName = "koncowy";
                        break;
                    case 4:
                        columnName = "data";
                        break;

                }
                //Toast.makeText(getContext(), columnName, Toast.LENGTH_SHORT).show();
                displayingObject();

                break;
            case R.id.btnClear:

                clearAll();
                break;

        }

    }

    private void displayingObject() {

        arrayList.clear();

        ParseUser parseUser = ParseUser.getCurrentUser();
        userName = parseUser.getUsername().toLowerCase();
        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("przejazdy_" + userName);
        parseQuery.whereEqualTo(columnName, edtSearchData.getText().toString());
        parseQuery.orderByDescending("createdAt");
        parseQuery.setLimit(250);
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                int i = 0;
                if (e == null) {

                    if (objects.size() > 0) {

                        for (ParseObject object : objects) {

                            arrayList.add(object.getString("taborowy")
                                    + " | " + object.getString("linia")
                                    + " | " + object.getString("kierunek")
                                    + " | " + object.getString("poczatkowy")
                                    + " | " + object.getString("koncowy")
                                    + " | " + object.getString("data"));

                            i++;
                        }

                        arrayList.add("Ilość przejazdów: " + i);
                        listView.setAdapter(arrayAdapter);
                        listView.setVisibility(View.VISIBLE);

                    }

                } else {

                    FancyToast.makeText(getContext(),
                            e.getMessage(),
                            Toast.LENGTH_LONG, FancyToast.ERROR,
                            false).show();

                }
                if (i == 0) {

                    FancyToast.makeText(getContext(),
                            getString(R.string.fancy_nothing_search),
                            Toast.LENGTH_LONG, FancyToast.INFO,
                            false).show();

                }

            }

        });

    }

    private void clearAll() {

        edtSearchData.setText("");
        rbtnGroup.clearCheck();
        arrayList.clear();
        listView.setAdapter(arrayAdapter);
    }
}