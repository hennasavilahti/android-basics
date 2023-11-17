package com.example.week_3_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String message = "Counter: ";
    private int clickCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle){
        // Järjestelmä kutsuu tätä metodia tarpeen mukaan, jotta
        // voimme tallettaa aktiviteetin tilan halutessamme
        super.onSaveInstanceState(bundle);
        // Talletetaan counterin nykyinen arvo
        bundle.putInt("COUNTER_VALUE", clickCounter);

    }
    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        clickCounter = bundle.getInt("COUNTER_VALUE", 0);
        // Luodaan referenssi käyttöliittymäelementtiin TextView
        TextView helloText = findViewById(R.id.helloTextView);
        helloText.setText(message + clickCounter);
    }

    public void buttonClicked(View view) {
        clickCounter++;
        TextView helloText = findViewById(R.id.helloTextView);
        helloText.setText(message + clickCounter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("HELLO","Hello TAMK activity onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("HELLO","Hello TAMK activity onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("HELLO","Hello TAMK activity onDestroy");
    }
}