package com.example.deviceapidemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSensors(View view) {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Listataan kaikki sensorit ja tutkaillaan, mitä laitteesta löytyy
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s : sensors) {
            Toast.makeText(this, s.getName(), Toast.LENGTH_SHORT).show();
        }

        // Rekisteröidytään kuuntelemaan kiihtyvyysanturin lukemia x y ja z
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) { // Kiihtyvyysanturi löytyy
            sensorManager.registerListener(new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    // Kiihtyvyysantorin arvo muuttui. Päivitetään lukemat UI:lle
                    float x, y, z;
                    x = event.values[0];
                    y = event.values[1];
                    z = event.values[2];
                    TextView sensorTextView = findViewById(R.id.sensorTextView);
                    sensorTextView.setText("X: " + x + " Y: " + y + " Z: " + z);
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            }, accelerometer, SensorManager.SENSOR_DELAY_UI);
        }
    }

    public void startGPS(View view) {
        // Tsekataan, onko oikeudet paikkatietoon, jos ei ole, pyydetään oikeudet dialogilla
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Ei oikeuksia -> pyydetään oikeudet
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            return;
        }
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        // Jos on oikeudet, luetaan GPS ja/tai rekisteröidytään kuuntelemaan LAT LNG -parametreja
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                TextView gpsTextView = findViewById(R.id.gpsTextView);
                gpsTextView.setText("Lat: " + latitude + " Lng: " + longitude);
            }
        });
    }
}