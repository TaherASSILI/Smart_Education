package com.example.user.smart_education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by user on 11/11/2017.
 */

public class UtilisateursBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "utilisateurs.db";
    private static final String TABLE_Utilisateurs = "utilisateurs";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_ROLE = "Role";
    private static final int NUM_COL_ROLE = 1;
    private static final String COL_NOM = "Nom";
    private static final int NUM_COL_NOM = 2;
    private static final String COL_EMAIL = "Email";
    private static final int NUM_COL_EMAIL = 3;
    private static final String COL_PASSWORD = "Password";
    private static final int NUM_COL_PASSWORD = 4;
    private static final String COL_NUMINSCRIPTION = "NumInscription";
    private static final int NUM_COL_NUMINSCRIPTION = 5;

    private SQLiteDatabase bdd;
    private MaBaseSQLite maBaseSQLite;

    public UtilisateursBDD(Context context){
        //On crée la BDD et sa table
        maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertUtilisateur(Utilisateur utilisateur){

        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ROLE, utilisateur.getRole());
        values.put(COL_NOM, utilisateur.getNom());
        values.put(COL_EMAIL, utilisateur.getEmail());
        values.put(COL_PASSWORD, utilisateur.getPassword());
        values.put(COL_NUMINSCRIPTION, utilisateur.getNumInscription());

        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_Utilisateurs, null, values);
    }

    public Utilisateur getUtilisateurWithEmailPassword(String email, String password){
        Cursor c = bdd.query(TABLE_Utilisateurs, new String[] {COL_ID, COL_NOM, COL_ROLE, COL_EMAIL, COL_NUMINSCRIPTION,
                COL_PASSWORD}, COL_EMAIL + " LIKE \"" + email + "\" and " + COL_PASSWORD + " LIKE \"" + password + "\"", null, null, null, null);

        return cursorToUtilisateur(c);
    }

    public Utilisateur getUtilisateurWithEmail(String e) {
        Cursor c = bdd.query(TABLE_Utilisateurs, new String[] {COL_ID, COL_NOM, COL_ROLE, COL_EMAIL, COL_NUMINSCRIPTION,
                COL_PASSWORD}, COL_EMAIL + " LIKE \"" + e + "\"", null, null, null, null);

        return cursorToUtilisateur(c);
    }

    private Utilisateur cursorToUtilisateur(Cursor c){
        if (c.getCount() == 0) {
            return null;
        }

        c.moveToFirst();

        Utilisateur user = new Utilisateur();

        user.setId(c.getInt(NUM_COL_ID));
        user.setNom(c.getString(NUM_COL_NOM));
        user.setEmail(c.getString(NUM_COL_EMAIL));
        user.setRole(c.getString(NUM_COL_ROLE));
        user.setPassword(c.getString(NUM_COL_PASSWORD));
        user.setNumInscription(c.getString(NUM_COL_NUMINSCRIPTION));

        c.close();

        return user;
    }
}
