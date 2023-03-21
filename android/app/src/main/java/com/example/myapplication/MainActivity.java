package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import DataAdapter.InterventionListAdapter;
import Model.DAO.InterventionManager;
import Model.Entity.Intervention;

public class MainActivity extends AppCompatActivity implements InterventionListAdapter.ItemClickListener {

    private Button mbuttonAddData;
    private ArrayList<Intervention> interventionList;
    // On déclare l'adapteur nécessaire pour lié les datas a la Recycler View
    InterventionListAdapter adapter;
    private InterventionManager interventionmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On parametre notre vue Recycler
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

        // On lie maintenant les données à notre adapteur
        adapter = new InterventionListAdapter(this, interventionList);

        // On ajoute la gestion des evenements sur le clic
        adapter.setClickListener(this);

        // Puis on lie notre adapteur au RecyclerView
        recyclerView.setAdapter(adapter);

        // On ajoute un trait de séparation entre les éléments (pour faciliter la lecture et le clic
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),1);
        recyclerView.addItemDecoration(dividerItemDecoration);

        //on lie les elements d'interface aux objets correspondants
        mbuttonAddData = (Button) findViewById(R.id.buttonAddData);

        mbuttonAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, AddInterventionActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        //On utilise Toast (https://developer.android.com/reference/android/widget/Toast) et sa méthode makeText pour afficher le texte correspondant à l'élément cliqué.
        Toast.makeText(this, "Vous avez cliqué sur " + adapter.getItem(position).getNomClient(), Toast.LENGTH_SHORT).show();
        // ou tout autre traitement de notre choix sur le clic
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        //On vide la liste
        interventionList.clear();

        interventionmanager.open();

        //Puis on réajoute tout le contenu de la liste de la base
        interventionList.addAll(interventionmanager.getInterventions());

        interventionmanager.close();

        // indique a l'adapter que les données ont été mise à jour,
        // et que le contenu de recyclerView doit être réaffiché.
        adapter.notifyDataSetChanged();
    }
}