package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getWeatherData(View view) {
        // Haetaan säätiedot Volley - kirjastosta
        // RequestQueue queue = Volley.newRequestQueue(this);
        String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=tampere&appid=6018b2f91594758c9da952d55a649364&units=metric";
        StringRequest request = new StringRequest(Request.Method.GET, WEATHER_URL, response -> {
            // Säätiedot haettu onnistuneesti. Parsitaan JSON-objekti ja
        }, error -> {
            // Verkkovirhe yms.
        });
        // Lähetetään request Volleylla == Lisätään request requestqueueen
        Volley.newRequestQueue(this).add(request);
        // Tämän jälkeen tullaan jompaan kumpaan callbacking haaraan, kun request on valmis
    }
}