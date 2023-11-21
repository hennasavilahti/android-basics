package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getWeatherData(View view) {
        // Haetaan säätiedot Volley - kirjastosta
        String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=tampere&appid=6018b2f91594758c9da952d55a649364&units=metric";
        // Säätiedot haettu onnistuneesti. Parsitaan JSON-objekti ja laitetaan käyttöliittymään haetut tiedot
        StringRequest request = new StringRequest(Request.Method.GET, WEATHER_URL, this::parseWeatherJsonAndUpdateUi, error -> {
            // Verkkovirhe yms.
            Toast.makeText(this, "VERKKOVIRHE", Toast.LENGTH_LONG).show();
        });
        // Lähetetään request Volleylla == Lisätään request requestqueueen
        Volley.newRequestQueue(this).add(request);
    }

    private void parseWeatherJsonAndUpdateUi(String response) {
        // Parsitaan JSON ja päivitetään näytölle lämpötila, säätila ja tuulen nopeus
        // Muunnetaan merkkijono JSON objektiksi
        try {
            JSONObject weatherJSON = new JSONObject(response);
            String weather = weatherJSON.getJSONArray("weather").getJSONObject(0).getString("main");
            double temperature = weatherJSON.getJSONObject("main").getDouble("temp");
            double wind = weatherJSON.getJSONObject("wind").getDouble("speed");

            TextView weatherTextView = findViewById(R.id.weatherTextView);
            weatherTextView.setText(weather);
            TextView temperatureTextView = findViewById(R.id.temperatureTextView);
            temperatureTextView.setText("" + temperature + "C");
            TextView windTextView = findViewById(R.id.windTextView);
            windTextView.setText("" + wind + " m/s");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void openWebPage(View view) {
        String foreca = "https://foreca.fi";
        Uri forecaUri = Uri.parse(foreca);
        Intent intent = new Intent(Intent.ACTION_VIEW, forecaUri);
        try {
            startActivity(intent);
        }
        catch (Exception e) {
        }
    }
}