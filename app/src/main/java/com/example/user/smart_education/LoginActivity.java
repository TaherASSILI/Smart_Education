package com.example.user.smart_education;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final ImageView iv = (ImageView) findViewById(R.id.imageView);
        iv.setBackgroundResource(R.drawable.my_frame_animation);
        AnimationDrawable anim = (AnimationDrawable) iv.getBackground();
        anim.start();
    }

    public void Inscrire(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void Se_connecter(View view) {

        EditText email;
        EditText password;
        String e,p;

        email = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);

        if (email.getText().toString().matches("")|| password.getText().toString().matches("")) {
            Toast.makeText(this, "Vous devez remplir tous les champs.", Toast.LENGTH_LONG).show();
        } else {
            e = email.getText().toString();
            p = password.getText().toString();

            UtilisateursBDD utilisateursBDD = new UtilisateursBDD(this);
            utilisateursBDD.open();

            Utilisateur user = utilisateursBDD.getUtilisateurWithEmailPassword(e, p);
            if(user != null) {
                Intent intent = new Intent(this, IndexActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "La combinaison email et mot de passe est invalide.", Toast.LENGTH_LONG).show();
            }


        }
    }
}
