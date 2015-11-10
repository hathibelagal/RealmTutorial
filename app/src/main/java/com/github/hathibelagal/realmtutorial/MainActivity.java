package com.github.hathibelagal.realmtutorial;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends Activity {

    private Realm myRealm;
    private Realm myOtherRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myRealm = Realm.getInstance(this);

        myOtherRealm =
                Realm.getInstance(
                        new RealmConfiguration.Builder(this)
                                .name("myOtherRealm.realm")
                                .build()
        );

        // writeData();
        readData();
    }

    /**
     * This method can be called only once
     */
    private void writeData(){

        myRealm.beginTransaction();

        // Create an object
        Country country1 = myRealm.createObject(Country.class);

        // Set its fields
        country1.setName("Norway");
        country1.setPopulation(5165800);
        country1.setCode("NO");

        myRealm.commitTransaction();

        // Create the object
        Country country2 = new Country();
        country2.setName("Russia");
        country2.setPopulation(146430430);
        country2.setCode("RU");

        myRealm.beginTransaction();
        Country copyOfCountry2 = myRealm.copyToRealm(country2);
        myRealm.commitTransaction();

        Log.d("writeData", "Two records added");
    }

    private void readData(){

        // Basic query
        RealmResults<Country> results1 =
                myRealm.where(Country.class)
                        .findAll();

        for(Country c:results1) {
            Log.d("results1", c.getName());
        }

        // Condition
        RealmResults<Country> results2 =
                myRealm.where(Country.class)
                        .greaterThan("population", 100000000)
                        .findAll();

        for(Country c:results2) {
            Log.d("results2", c.getName());
        }

        // Sort results
        RealmResults<Country> results3 =
                myRealm.where(Country.class)
                        .findAllSorted("name", false);

        for(Country c:results3) {
            Log.d("results3", c.getName());
        }
    }
}
