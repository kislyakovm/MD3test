package com.example.md3test;

import android.widget.ImageView;
import android.widget.TextView;

public class Country {
    private String flagUrl, countryName;

    public Country(String flag, String countryName) {
        this.flagUrl = flag;
        this.countryName = countryName;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flag) {
        this.flagUrl = flag;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
