package com.example.gpa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    database myDB;
    ArrayList<String> Subcode, Credits, Results;
    cusadapter cusadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        add_button = findViewById(R.id.addmember_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, add.class);
                startActivity(intent);
            }
        });

        myDB = new database(MainActivity.this);
        Subcode = new ArrayList<>();
        Credits = new ArrayList<>();
        Results = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cusadapter = new cusadapter(MainActivity.this, Subcode, Credits, Results);
        recyclerView.setAdapter(cusadapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void refreshData() {
        Subcode.clear();
        Credits.clear();
        Results.clear();


        Cursor cursor = myDB.readsub();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                Subcode.add(cursor.getString(0));
                Credits.add(cursor.getString(1));
                Results.add(cursor.getString(2));
            }
        }
            cusadapter.notifyDataSetChanged();
        }

        private void clearInputFields() {
            EditText subcodeInput = findViewById(R.id.subcode_input);
            EditText creditInput = findViewById(R.id.credits_input);
            EditText resultInput = findViewById(R.id.results_input);

            subcodeInput.setText("");
            creditInput.setText("");
            resultInput.setText("");

        }
            @Override
            protected void onActivityResult ( int requestCode, int resultCode, Intent data){
                super.onActivityResult(requestCode, resultCode, data);
                if (requestCode == 1 && resultCode == RESULT_OK) {
                    refreshData();
                    clearInputFields();
                }
            }
        }
