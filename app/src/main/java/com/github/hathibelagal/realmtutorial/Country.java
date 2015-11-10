package com.github.hathibelagal.realmtutorial;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Hathi on 10/11/15.
 */
public class Country extends RealmObject {

    private String name;
    private int population;

    public Country() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @PrimaryKey
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}