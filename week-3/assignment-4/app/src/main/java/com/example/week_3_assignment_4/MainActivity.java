package com.example.week_3_assignment_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String submittedString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putString("SUBMITTED_STRING", "");
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle){
        super.onRestoreInstanceState(bundle);
        submittedString = bundle.getString("SUBMITTED_STRING");
    }

    public void buttonClicked(View view){
        EditText editTextView = findViewById(R.id.editTextView);
        submittedString = editTextView.getText().toString();
        editTextView.setText(submittedString);
    }
}