package com.example.weatherapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Boolean isImperial = false;
    private String location;
    private Double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        isImperial = intent.getBooleanExtra("NEW_IS_IMPERIAL", false);
        location = (savedInstanceState != null)
                ? savedInstanceState.getString("SUBMITTED_LOCATION", getString(R.string.default_location))
                : (intent.getStringExtra("NEW_LOCATION") != null)
                ? intent.getStringExtra("NEW_LOCATION")
                : getString(R.string.default_location);

        if (location != null) {
            getWeatherDataByLocation(location);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("SUBMITTED_LOCATION", location);
        bundle.putBoolean("SUBMITTED_IS_IMPERIAL", isImperial);
    }

    @Override
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        location = bundle.getString("SUBMITTED_LOCATION");
        isImperial = bundle.getBoolean("SUBMITTED_IS_IMPERIAL");
    }

    public void getNewLocation(View view) {
        EditText citySearchEditText = findViewById(R.id.citySearchEditText);
        location = citySearchEditText.getText().toString();
        getWeatherDataByLocation(location);

        citySearchEditText.setText(null);
        citySearchEditText.setHint(getString(R.string.placeholder));
    }

    public void getWeatherDataByLocation(String location) {
        String WEATHER_URL = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=6018b2f91594758c9da952d55a649364&units=%s", location.toLowerCase(), isImperial ? "imperial" : "metric");

        StringRequest request = new StringRequest(Request.Method.GET, WEATHER_URL, this::parseWeatherJsonAndUpdateUi, error -> {
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show();
        });

        Volley.newRequestQueue(this).add(request);
    }

    public void getWeatherDataByLatLon(Double lat, Double lon) {
        String WEATHER_URL = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%.2f&lon=%.2f&appid=6018b2f91594758c9da952d55a649364&units=%s", lat, lon, isImperial ? "imperial" : "metric");

        StringRequest request = new StringRequest(Request.Method.GET, WEATHER_URL, this::parseWeatherJsonAndUpdateUi, error -> {
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show();
        });

        Volley.newRequestQueue(this).add(request);
    }

    private void parseWeatherJsonAndUpdateUi(String response) {
        try {
            JSONObject weatherJSON = new JSONObject(response);
            String city = weatherJSON.getString("name");
            String country = weatherJSON.getJSONObject("sys").getString("country");
            String iconCode = weatherJSON.getJSONArray("weather").getJSONObject(0).getString("icon");
            int temperature = (int) weatherJSON.getJSONObject("main").getDouble("temp");
            int windSpeed = (int) weatherJSON.getJSONObject("wind").getDouble("speed");

            TextView locationTextView = findViewById(R.id.locationTextView);
            String locationString = String.format("%s, %s", city, country);
            locationTextView.setText(locationString);

            ImageView weatherImageView = findViewById(R.id.weatherImageView);
            String iconURL = String.format("https://openweathermap.org/img/wn/%s@4x.png", iconCode);
            Picasso.get().load(iconURL).into(weatherImageView);

            String temperatureString = String.format("%d%s", temperature, isImperial ? "°F" : "°C");
            String windString = String.format("%s: %d %s", getString(R.string.wind), windSpeed, isImperial ? "mph" : "m/s");

            TextView temperatureTextView = findViewById(R.id.temperatureTextView);
            temperatureTextView.setText(temperatureString);
            TextView windTextView = findViewById(R.id.windTextView);
            windTextView.setText(windString);

            location = city;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    public void startGPS(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            return;
        }

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 300000, 5000, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                getWeatherDataByLatLon(latitude, longitude);
            }
        });

    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, Settings.class);

        intent.putExtra("CURRENT_IS_IMPERIAL", isImperial);
        intent.putExtra("CURRENT_LOCATION", location);

        startActivity(intent);
    }
}