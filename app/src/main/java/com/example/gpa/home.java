package com.example.gpa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity {

    private Button manageSubjectsButton;
    private Button calculateGpaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        manageSubjectsButton = findViewById(R.id.manage_subjects_button);
        calculateGpaButton = findViewById(R.id.calculate_gpa_button);

        manageSubjectsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, MainActivity.class);
                startActivity(intent);
            }
        });

        calculateGpaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, calculateGPA.class);
                startActivity(intent);
            }
        });
    }
}
