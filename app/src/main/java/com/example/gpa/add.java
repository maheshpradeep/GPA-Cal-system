package com.example.gpa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class add extends AppCompatActivity {

    EditText  subcodeInput, creditInput, resultInput;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        subcodeInput = findViewById(R.id.subcode_input);
        creditInput = findViewById(R.id.credits_input);
        resultInput = findViewById(R.id.results_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database myDB = new database(add.this);
                myDB.addsub(subcodeInput.getText().toString().trim(),
                        creditInput.getText().toString().trim(),
                        resultInput.getText().toString().trim()); // Change to pass String

                // Clear input fields after adding data
                clearInputFields();
            }
        });
    }

    // Method to clear input fields
    private void clearInputFields() {
        subcodeInput.setText("");
        creditInput.setText("");
        resultInput.setText("");
    }
}
