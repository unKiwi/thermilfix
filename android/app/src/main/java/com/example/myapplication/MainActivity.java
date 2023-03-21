package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import Model.DAO.InterventionManager;
import Model.Entity.Intervention;

public class MainActivity extends AppCompatActivity {

    private Button mbuttonAddData;
    private ArrayList<Intervention> interventionList;
    private InterventionManager interventionmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On parametre notre vue Recycler en liant son id
        RecyclerView recyclerView = findViewById(R.id.MyrvData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // On créer un ContactManager pour pouvoir récupérer la liste de contact
        interventionmanager = new InterventionManager(this);

        // on se connecte à la base de données
        interventionmanager.open();

        // On récupère la liste de contacts contenus dans la table Contact
        interventionList = interventionmanager.getInterventions();

        // on ferme la connexion
        interventionmanager.close();

        //on lie les elements d'interface aux objets correspondants
        mbuttonAddData = (Button) findViewById(R.id.buttonAddData);

        mbuttonAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}