package com.example.myapplication;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DataAdapter.InterventionListAdapter;
import Model.DAO.InterventionManager;
import Model.Entity.Intervention;

public class MainActivity extends AppCompatActivity implements InterventionListAdapter.ItemClickListener {

    private Button mbuttonEnvoyer;
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

        mbuttonEnvoyer = (Button) findViewById(R.id.buttonEnvoyer);

        mbuttonEnvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                String name = sh.getString("name", "");
                if (name == "") {
                    Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                    MainActivity.this.startActivity(myIntent);
                    return;
                }
                sendInterventions(name);
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

    private void sendInterventions(String name) {
        // url to post our data
        String url = "http://localhost:8000/api/save";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // inside on response method we are
                // hiding our progress bar
                // and setting data to edit text as empty

                // vider la liste
                interventionmanager.open();
                for (Intervention intervention : interventionList) {
                    System.out.println(intervention.getNomClient());
                    interventionmanager.removeIntervention(intervention);
                }
                interventionmanager.close();
                interventionList.clear();
                adapter.notifyDataSetChanged();

                // on below line we are displaying a success toast message.
                Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();
//                try {
//                    // on below line we are parsing the response
//                    // to json object to extract data from it.
//                    JSONObject respObj = new JSONObject(response);
//
//                    // below are the strings which we
//                    // extract from our json object.
//                    String name = respObj.getString("name");
//                    String job = respObj.getString("job");
//
//                    // on below line we are setting this string s to our text view.
//                    System.out.println("et la");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(MainActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our key
                // and value pair to our parameters.

                List<Map<String, String>> interventions = new ArrayList<Map<String, String>>();
                for (Intervention intervention : interventionList) {
                    interventions.add(intervention.toMap());
                }

                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String json = objectMapper.writeValueAsString(interventions);
                    params.put("intervention", json);
                    params.put("name", name);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

                // at last we are
                // returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }
}