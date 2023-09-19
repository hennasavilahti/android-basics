package com.example.testi_tuntiesimerkki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        int currentColor = ((ColorDrawable) v.getBackground()).getColor();

        int blueColor = ContextCompat.getColor(this, R.color.blue);
        int yellowColor = ContextCompat.getColor(this, R.color.yellow);

        if (currentColor == blueColor) {
            v.setBackgroundColor(yellowColor);
            ((Button) v).setTextColor(Color.BLACK);
        } else if (currentColor == yellowColor) {
            v.setBackgroundColor(blueColor);
            ((Button) v).setTextColor(Color.WHITE);
        }
    }
}