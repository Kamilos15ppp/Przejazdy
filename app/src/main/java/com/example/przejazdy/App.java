package com.example.przejazdy;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("BTXPNC2WNRsJFiu5ov1Nm53dMFJzTdd40jCLO83A")
                .clientKey("2mODTrV84MuJlYuroXUIhYU0zIvxyJ4pQlYQEZiv")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }

}
