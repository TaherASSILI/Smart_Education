package com.example.user.smart_education;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void login(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void AjouterUtilisateur(View view) {
        EditText nom;
        EditText email;
        EditText numInscription;
        EditText password;
        String n, e, num, p;

        nom = (EditText) findViewById(R.id.editText3);
        email = (EditText) findViewById(R.id.editText4);
        numInscription = (EditText) findViewById(R.id.editText5);
        password = (EditText) findViewById(R.id.editText6);

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailToValide = email.getText().toString().trim();

        if (nom.getText().toString().matches("") || email.getText().toString().matches("")|| numInscription.getText().toString().matches("") || password.getText().toString().matches("")) {
            Toast.makeText(this, "Vous devez remplir tous les champs.", Toast.LENGTH_LONG).show();
        } else if (!emailToValide.matches(emailPattern)) {
            Toast.makeText(this, "L'adresse e-mail saisie est invalide.", Toast.LENGTH_LONG).show();
        } else {
            n = nom.getText().toString();
            e = email.getText().toString();
            num = numInscription.getText().toString();
            p = password.getText().toString();

            UtilisateursBDD utilisateursBDD = new UtilisateursBDD(this);
            utilisateursBDD.open();

            Utilisateur user = utilisateursBDD.getUtilisateurWithEmail(e);

            if (user != null) {
                Toast.makeText(this, "L'adresse email existe déjà.", Toast.LENGTH_LONG).show();
            } else {
                Utilisateur utilisateur = new Utilisateur("parent", n, p, e, num);
                utilisateursBDD.insertUtilisateur(utilisateur);

                Intent intent = new Intent(this, IndexActivity.class);
                startActivity(intent);
            }
        }
    }
}
