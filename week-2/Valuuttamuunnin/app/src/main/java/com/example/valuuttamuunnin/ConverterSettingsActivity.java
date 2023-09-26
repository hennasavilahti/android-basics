package com.example.valuuttamuunnin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ConverterSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter_settings);

        Intent intent = getIntent();

        if(intent.getExtras() != null) {
            TextView editHomeCurrencyText = findViewById(R.id.settings_editHomeCurrencyText);
            editHomeCurrencyText.setText(intent.getStringExtra("CURRENT_HOME_CURRENCY"));

            TextView editDestinationCurrencyText = findViewById(R.id.settings_editDestinationCurrencyText);
            editDestinationCurrencyText.setText(intent.getStringExtra("CURRENT_DESTINATION_CURRENCY"));
        }

    }

    public void backToConverter(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        EditText settings_editConversionRateNumber = findViewById(R.id.settings_editConversionRateNumber);
        String conversionRateString = settings_editConversionRateNumber.getText().toString();
        float conversionRate = Float.parseFloat(conversionRateString);

        EditText settings_editHomeCurrencyText = findViewById(R.id.settings_editHomeCurrencyText);
        String newHomeCurrency = settings_editHomeCurrencyText.getText().toString();

        EditText settings_editDestinationCurrencyText = findViewById(R.id.settings_editDestinationCurrencyText);
        String newDestinationCurrency = settings_editDestinationCurrencyText.getText().toString();

        intent.putExtra("CONVERSION_RATE", conversionRate);
        intent.putExtra("NEW_HOME_CURRENCY", newHomeCurrency);
        intent.putExtra("NEW_DESTINATION_CURRENCY", newDestinationCurrency);
        startActivity(intent);
    }
}