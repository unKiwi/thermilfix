package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Model.DAO.InterventionManager;
import Model.Entity.Intervention;

public class LoginActivity extends AppCompatActivity {

    private EditText mNom;
    private Button mbuttonSauvegarder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mNom = (EditText) findViewById(R.id.editTextTextPersonName);

        mbuttonSauvegarder = (Button) findViewById(R.id.button);
        mbuttonSauvegarder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

                // Creating an Editor object to edit(write to the file)
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("name", mNom.getText().toString());
                myEdit.commit();

                LoginActivity.this.finish();
            }
        });
    }
}