package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Model.DAO.InterventionManager;
import Model.Entity.Intervention;

public class AddInterventionActivity extends AppCompatActivity {

    private Button madd;
    private InterventionManager contactmanager;
    private EditText mNom;
    private EditText mPrenom;
    private EditText mAdresse;
    private EditText mMarque;
    private EditText mModel;
    private EditText mNumeroDeSerie;
    private EditText mTempsPasse;
    private EditText mDateDeMiseEnService;
    private EditText mDateIntervention;
    private EditText mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_intervention);

        mNom = (EditText) findViewById(R.id.editTextNom);
        mPrenom = (EditText) findViewById(R.id.editTextPrenom);
        mAdresse = (EditText) findViewById(R.id.editTextAdresse);
        mMarque = (EditText) findViewById(R.id.editTextMarque);
        mModel = (EditText) findViewById(R.id.editTextModel);
        mNumeroDeSerie = (EditText) findViewById(R.id.editTextNumeroDeSerie);
        mTempsPasse = (EditText) findViewById(R.id.editTextTempsPasse);
        mDateDeMiseEnService = (EditText) findViewById(R.id.editTextDateDeMiseEnService);
        mDateIntervention = (EditText) findViewById(R.id.editTextDateIntervention);
        mDescription = (EditText) findViewById(R.id.editTextDescription);

        contactmanager = new InterventionManager(this);

        madd = (Button) findViewById(R.id.addcontactButton);
        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ouvre l'acces a la base de données
                contactmanager.open();

                //Crée un nouveau contact et peuple l'objet avec les valeurs des champs de l'activité
                Intervention newIntervention = new Intervention(0,"");
                newIntervention.setNomClient(mNom.getText().toString());
                newIntervention.setPrenomClient(mPrenom.getText().toString());
                newIntervention.setAdresseClient(mAdresse.getText().toString());
                newIntervention.setMarqueChaudiere(mMarque.getText().toString());
                newIntervention.setModelChaudiere(mModel.getText().toString());
                newIntervention.setNumeroDeSerie(mNumeroDeSerie.getText().toString());
                newIntervention.setTempsPasse(mTempsPasse.getText().toString());
                newIntervention.setDateDeMiseEnService(mDateDeMiseEnService.getText().toString());
                newIntervention.setDateItervention(mDateIntervention.getText().toString());
                newIntervention.setDescriptionIntervention(mDescription.getText().toString());

                //Ajoute le contact à la base de données
                contactmanager.addIntervention(newIntervention);

                // Ferme l'acces a la base
                contactmanager.close();

                // Ferme l'activité une fois l'ajout terminé.
                AddInterventionActivity.this.finish();
            }
        });
    }
}