package com.example.gpa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class update extends AppCompatActivity {

    EditText creditInput, resultInput;
    Button updateButton, deleteButton;

    String subcode, credit, results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        creditInput = findViewById(R.id.last_name_input);
        resultInput = findViewById(R.id.mobile_input);
        updateButton = findViewById(R.id.update_button); // Initialize update button
        deleteButton = findViewById(R.id.delete_button); // Initialize delete button

        getAndSetIntentData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database myDB = new database(update.this);

                credit = creditInput.getText().toString().trim();
                results = resultInput.getText().toString().trim();
                myDB.updatesub(subcode, credit, results);

                // Set result to indicate data was updated
                setResult(RESULT_OK);
                finish(); // Close the activity after updating the data
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database myDB = new database(update.this);
                myDB.deleteMember(subcode);

                // Set result to indicate data was deleted
                setResult(RESULT_OK);
                finish(); // Close the activity after deleting the data
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("cardNo") && getIntent().hasExtra("firstName") &&
                getIntent().hasExtra("lastName")) {
            // Getting data from intent
            subcode = getIntent().getStringExtra("cardNo");
            credit = getIntent().getStringExtra("firstName");
            results = getIntent().getStringExtra("lastName");

            // Setting intent data
            creditInput.setText(credit);
            resultInput.setText(results);

        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}
