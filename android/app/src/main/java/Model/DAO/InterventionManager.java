package Model.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import Model.Entity.Intervention;
import Model.TheSQLiteDB;

import java.util.ArrayList;

public class InterventionManager {

    private static final String TABLE_NAME = "interventions";
    public static final String KEY_ID_INTERVENTION="id";
    public static final String KEY_NOMCLIENT_INTERVENTION="nomClient";
    public static final String KEY_PRENOMCLIENT_INTERVENTION="prenomClient";
    public static final String KEY_ADRESSECLIENT_INTERVENTION="adresseClient";
    public static final String KEY_MARQUECHAUDIERE_INTERVENTION="marqueChaudiere";
    public static final String KEY_MODELCHAUDIERE_INTERVENTION="modelChaudiere";
    public static final String KEY_DATEDEMISEENSERVICE_INTERVENTION="dateDeMiseEnService";
    public static final String KEY_DATEINTERVENTION_INTERVENTION="dateItervention";
    public static final String KEY_NUMERODESERIE_INTERVENTION="numeroDeSerie";
    public static final String KEY_DESCRIPTIONINTERVENTION_INTERVENTION="descriptionIntervention";
    public static final String KEY_TEMPSPASSE_INTERVENTION="tempsPasse";
    public static final String CREATE_TABLE_INTERVENTION = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+KEY_ID_INTERVENTION+" INTEGER primary key," +
            " "+KEY_NOMCLIENT_INTERVENTION+" TEXT," +
            " "+KEY_PRENOMCLIENT_INTERVENTION+" TEXT," +
            " "+KEY_ADRESSECLIENT_INTERVENTION+" TEXT," +
            " "+KEY_MARQUECHAUDIERE_INTERVENTION+" TEXT," +
            " "+KEY_MODELCHAUDIERE_INTERVENTION+" TEXT," +
            " "+KEY_DATEDEMISEENSERVICE_INTERVENTION+" TEXT," +
            " "+KEY_DATEINTERVENTION_INTERVENTION+" TEXT," +
            " "+KEY_NUMERODESERIE_INTERVENTION+" TEXT," +
            " "+KEY_DESCRIPTIONINTERVENTION_INTERVENTION+" TEXT," +
            " "+KEY_TEMPSPASSE_INTERVENTION+" TEXT" +
            ");";
    private TheSQLiteDB maBase; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public InterventionManager(Context context)
    {
        maBase = TheSQLiteDB.getInstance(context);
    }

    public void open()
    {
        //on ouvre la table en lecture/écriture
        db = maBase.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'accès à la BDD
        db.close();
    }

    public long addIntervention(Intervention intervention) {
        intervention.log();
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_NOMCLIENT_INTERVENTION, intervention.getNomClient());
        values.put(KEY_PRENOMCLIENT_INTERVENTION, intervention.getPrenomClient());
        values.put(KEY_ADRESSECLIENT_INTERVENTION, intervention.getAdresseClient());
        values.put(KEY_MARQUECHAUDIERE_INTERVENTION, intervention.getMarqueChaudiere());
        values.put(KEY_MODELCHAUDIERE_INTERVENTION, intervention.getModelChaudiere());
        values.put(KEY_DATEDEMISEENSERVICE_INTERVENTION, intervention.getDateDeMiseEnService());
        values.put(KEY_DATEINTERVENTION_INTERVENTION, intervention.getDateItervention());
        values.put(KEY_NUMERODESERIE_INTERVENTION, intervention.getNumeroDeSerie());
        values.put(KEY_DESCRIPTIONINTERVENTION_INTERVENTION, intervention.getDescriptionIntervention());
        values.put(KEY_TEMPSPASSE_INTERVENTION, intervention.getTempsPasse());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);
    }

    public int updateIntervention(Intervention intervention) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_NOMCLIENT_INTERVENTION, intervention.getNomClient());
        values.put(KEY_PRENOMCLIENT_INTERVENTION, intervention.getPrenomClient());
        values.put(KEY_ADRESSECLIENT_INTERVENTION, intervention.getAdresseClient());
        values.put(KEY_MARQUECHAUDIERE_INTERVENTION, intervention.getMarqueChaudiere());
        values.put(KEY_MODELCHAUDIERE_INTERVENTION, intervention.getModelChaudiere());
        values.put(KEY_DATEDEMISEENSERVICE_INTERVENTION, intervention.getDateDeMiseEnService());
        values.put(KEY_DATEINTERVENTION_INTERVENTION, intervention.getDateItervention());
        values.put(KEY_NUMERODESERIE_INTERVENTION, intervention.getNumeroDeSerie());
        values.put(KEY_DESCRIPTIONINTERVENTION_INTERVENTION, intervention.getDescriptionIntervention());
        values.put(KEY_TEMPSPASSE_INTERVENTION, intervention.getTempsPasse());

        String where = KEY_ID_INTERVENTION+" = ?";
        String[] whereArgs = {intervention.getId()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int removeIntervention(Intervention intervention) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_INTERVENTION+" = ?";
        String[] whereArgs = {intervention.getId()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    @SuppressLint("Range")
    public Intervention getIntervention(int id) {
        // Retourne l'animal dont l'id est passé en paramètre

        Intervention a=new Intervention(0,"");

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_INTERVENTION+"="+id, null);

        if (c.moveToFirst()) {
            a.setId(c.getInt(c.getColumnIndex(KEY_ID_INTERVENTION)));
            a.setNomClient(c.getString(c.getColumnIndex(KEY_NOMCLIENT_INTERVENTION)));
            a.setPrenomClient(c.getString(c.getColumnIndex(KEY_PRENOMCLIENT_INTERVENTION)));
            a.setAdresseClient(c.getString(c.getColumnIndex(KEY_ADRESSECLIENT_INTERVENTION)));
            a.setMarqueChaudiere(c.getString(c.getColumnIndex(KEY_MARQUECHAUDIERE_INTERVENTION)));
            a.setModelChaudiere(c.getString(c.getColumnIndex(KEY_MODELCHAUDIERE_INTERVENTION)));
            a.setDateDeMiseEnService(c.getString(c.getColumnIndex(KEY_DATEDEMISEENSERVICE_INTERVENTION)));
            a.setDateItervention(c.getString(c.getColumnIndex(KEY_DATEINTERVENTION_INTERVENTION)));
            a.setNumeroDeSerie(c.getString(c.getColumnIndex(KEY_NUMERODESERIE_INTERVENTION)));
            a.setDescriptionIntervention(c.getString(c.getColumnIndex(KEY_DESCRIPTIONINTERVENTION_INTERVENTION)));
            a.setTempsPasse(c.getString(c.getColumnIndex(KEY_TEMPSPASSE_INTERVENTION)));
            c.close();
        }

        return a;
    }

    @SuppressLint("Range")
    public ArrayList<Intervention> getInterventions() {

        ArrayList<Intervention> interventionList = new ArrayList<Intervention>();

        //récupère dans un curseur le résultat du select sur la table
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);

        if (c.moveToFirst()) {
            //parcourt le curseur obtenu, jusqu'a la fin, et créer pour chaque enregistrement un objet Intervention
            do {
                Intervention a = new Intervention(0, "");
                a.setId(c.getInt(c.getColumnIndex(KEY_ID_INTERVENTION)));
                a.setNomClient(c.getString(c.getColumnIndex(KEY_NOMCLIENT_INTERVENTION)));
                a.setPrenomClient(c.getString(c.getColumnIndex(KEY_PRENOMCLIENT_INTERVENTION)));
                a.setAdresseClient(c.getString(c.getColumnIndex(KEY_ADRESSECLIENT_INTERVENTION)));
                a.setMarqueChaudiere(c.getString(c.getColumnIndex(KEY_MARQUECHAUDIERE_INTERVENTION)));
                a.setModelChaudiere(c.getString(c.getColumnIndex(KEY_MODELCHAUDIERE_INTERVENTION)));
                a.setDateDeMiseEnService(c.getString(c.getColumnIndex(KEY_DATEDEMISEENSERVICE_INTERVENTION)));
                a.setDateItervention(c.getString(c.getColumnIndex(KEY_DATEINTERVENTION_INTERVENTION)));
                a.setNumeroDeSerie(c.getString(c.getColumnIndex(KEY_NUMERODESERIE_INTERVENTION)));
                a.setDescriptionIntervention(c.getString(c.getColumnIndex(KEY_DESCRIPTIONINTERVENTION_INTERVENTION)));
                a.setTempsPasse(c.getString(c.getColumnIndex(KEY_TEMPSPASSE_INTERVENTION)));

                // ajoute l'objet créé à la ArrayList de Intervention qui sera renvoyée.
                interventionList.add(a);
            }
            while (c.moveToNext());
        }
        c.close();

        return interventionList;
    }

} // class InterventionManager