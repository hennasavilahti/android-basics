package com.example.valuuttamuunnin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private float conversionRate = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        if(intent.getExtras() != null) {
            conversionRate = intent.getFloatExtra("CONVERSION_RATE", 1.0F);

            TextView homeCurrencyTextView = findViewById(R.id.homeCurrencyTextView);
            homeCurrencyTextView.setText(intent.getStringExtra("NEW_HOME_CURRENCY"));

            TextView destinationCurrencyTextView = findViewById(R.id.destinationCurrencyTextView);
            destinationCurrencyTextView.setText(intent.getStringExtra("NEW_DESTINATION_CURRENCY"));
        }

        TextView conversionRateTextView = findViewById(R.id.conversionRateTextView);
        conversionRateTextView.setText("");
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, ConverterSettingsActivity.class);

        TextView homeCurrencyTextView = findViewById(R.id.homeCurrencyTextView);
        String currentHomeCurrency = homeCurrencyTextView.getText().toString();

        TextView destinationCurrencyTextView = findViewById(R.id.destinationCurrencyTextView);
        String currentDestinationCurrency = destinationCurrencyTextView.getText().toString();

        intent.putExtra("CURRENT_HOME_CURRENCY", currentHomeCurrency);
        intent.putExtra("CURRENT_DESTINATION_CURRENCY", currentDestinationCurrency);

        startActivity( intent );
    }

    public void convertCurrency(View view) {
        Intent intent = getIntent();

        EditText homeCurrencyEditText = findViewById(R.id.homeCurrencyEditText);
        TextView destinationCurrencyEditText = findViewById(R.id.destinationCurrencyEditText);

        String homeCurrencyInput = homeCurrencyEditText.getText().toString();
        float homeCurrencyAmount = Float.parseFloat(homeCurrencyInput);

        float destinationCurrency = homeCurrencyAmount * conversionRate;

        destinationCurrencyEditText.setText(String.format("%s", destinationCurrency));

        conversionRate = intent.getFloatExtra("CONVERSION_RATE", 1.0F);
        TextView conversionRateTextView = findViewById(R.id.conversionRateTextView);
        conversionRateTextView.setText(String.format("%s%s", getString(R.string.conversionRateWas), conversionRate));
    }

    public void switchCurrencies(View view) {
        TextView homeCurrencyTextView = findViewById(R.id.homeCurrencyTextView);
        TextView destinationCurrencyTextView = findViewById(R.id.destinationCurrencyTextView);

        String homeCurrencyTemp = homeCurrencyTextView.getText().toString();
        String destinationCurrencyTemp = destinationCurrencyTextView.getText().toString();

        homeCurrencyTextView.setText(destinationCurrencyTemp);
        destinationCurrencyTextView.setText(homeCurrencyTemp);
    }

}