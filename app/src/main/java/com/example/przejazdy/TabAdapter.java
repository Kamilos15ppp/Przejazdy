package com.example.przejazdy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {

    public TabAdapter(@NonNull FragmentManager fm) {

        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int tabPosition) {

        switch (tabPosition) {

            case 0:
                return new AddTransit();
            case 1:
                return new Search();
            case 2:
                return new Bus();
            case 3:
                return new Tram();
            case 4:
                return new Relic();
            default: return null;
        }

    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {

            case 0:
                return "Przejazdy";
            case 1:
                return "Wyszukaj";
            case 2:
                return "Autobusy";
            case 3:
                return "Tramwaje";
            case 4:
                return "Zabytki";
            default: return null;

        }
        //return super.getPageTitle(position);
    }
}
