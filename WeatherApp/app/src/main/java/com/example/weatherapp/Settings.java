package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.materialswitch.MaterialSwitch;

public class Settings extends AppCompatActivity {

    private Boolean isImperial;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();

        if (intent.getExtras() != null) {
            isImperial = intent.getBooleanExtra("CURRENT_IS_IMPERIAL", false);
            location = intent.getStringExtra("CURRENT_LOCATION");
        }

        MaterialSwitch imperialSwitch = findViewById(R.id.imperialSwitch);
        imperialSwitch.setChecked(isImperial);
    }

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


    public void openMain(View view) {
        MaterialSwitch imperialSwitch = findViewById(R.id.imperialSwitch);
        isImperial = imperialSwitch.isChecked();

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("NEW_IS_IMPERIAL", isImperial);
        intent.putExtra("NEW_LOCATION", location);
        startActivity(intent);
    }
}