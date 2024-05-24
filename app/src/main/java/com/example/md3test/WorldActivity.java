package com.example.md3test;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WorldActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private CountryAdapter countryAdapter;
    ArrayList<Country> countries = new ArrayList<>();
//    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_world);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/us.png","American"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/gb.png", "British"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/ca.png","Canadian"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/cn.png","Chinese"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/hr.png","Croatian"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/nl.png","Dutch"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/eg.png","Egyptian"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/ph.png","Filipino"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/fr.png","French"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/gr.png","Greek"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/in.png","Indian"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/ie.png","Irish"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/it.png","Italian"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/jm.png","Jamaican"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/jp.png","Japanese"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/kn.png","Kenyan"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/my.png","Malaysian"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/mx.png","Mexican"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/ma.png","Moroccan"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/pl.png","Polish"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/pt.png","Portuguese"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/ru.png","Russian"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/es.png","Spanish"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/th.png","Thai"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/tn.png","Tunisian"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/tr.png","Turkish"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/vn.png","Vietnamese"));
        countries.add(new Country("https://www.themealdb.com/images/icons/flags/big/64/no.png","Other"));


        recyclerView = findViewById(R.id.recyclerViewWorld);
//        requestQueue = Volley.newRequestQueue(this);

        countryAdapter = new CountryAdapter(WorldActivity.this, countries);
        recyclerView.setAdapter(countryAdapter);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.worldButton);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            int itemId = item.getItemId();
            if (itemId == R.id.listButton) {
                startActivity(new Intent(this, MainActivity.class));
            } else if (itemId == R.id.randomButton) {
                startActivity(new Intent(this, RandomActivity.class));
            } else if (itemId == R.id.findButton) {
                startActivity(new Intent(this, FindActivity.class));
            } else if (itemId == R.id.worldButton) {
                startActivity(new Intent(this, WorldActivity.class));
            }
            return true;
        });








//        downloadingDataFromAPI();
    }

    private void downloadingDataFromAPI() {

        String url = "https://www.themealdb.com/api/json/v1/1/list.php?a=list";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray mealsArray = response.getJSONArray("meals");
                        List<Country> countries = new ArrayList<>();

                        for (int i = 0; i < mealsArray.length(); i++) {
                            JSONObject mealObject = mealsArray.getJSONObject(i);
                            String countryName = mealObject.getString("strArea");
                            String flagUrl = getFlagUrlForCountry(countryName); // Здесь реализуйте логику получения URL флага

                            countries.add(new Country(countryName, flagUrl));
                        }

                        // Здесь можно обработать список стран
                        // Например, создать RecyclerView и передать список в адаптер
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
            }
        });

        requestQueue.add(request);


    }

    private String getFlagUrlForCountry(String countryName) {
        switch (countryName) {
            case "American":
                return "https://www.themealdb.com/images/icons/flags/big/64/us.png";
            case "British":
                return "https://www.themealdb.com/images/icons/flags/big/64/gb.png";
            case "Vietnamese":
                return "https://www.themealdb.com/images/icons/flags/big/64/vn.png";
            case "Canadian":
                return "https://www.themealdb.com/images/icons/flags/big/64/ca.png";
            case "Chinese":
                return "https://www.themealdb.com/images/icons/flags/big/64/cn.png";
            case "Croatian":
                return "https://www.themealdb.com/images/icons/flags/big/64/hr.png";
            // Добавьте другие страны по аналогии
            default:
                // Если страна не найдена, верните пустую строку или дефолтный URL
                return "";
        }
    }
}