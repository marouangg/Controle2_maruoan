package com.example.controle2_maruoan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Ajouter_entreprise extends AppCompatActivity {

    EditText e1, e2, e3;
    MyDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_entreprise);

        db = new MyDatabase(this);

        e1 = findViewById(R.id.raiss);
        e2 = findViewById(R.id.addre);
        e3 = findViewById(R.id.cap);

    }


    public void ajouterEntreprise(View view) {

        if(e1.getText().toString().isEmpty()){
            Toast.makeText(this, "raison social vide", Toast.LENGTH_SHORT).show();
            e1.requestFocus();
            return;
        }
        if(e2.getText().toString().isEmpty()){
            Toast.makeText(this, "adresse vide", Toast.LENGTH_SHORT).show();
            e2.requestFocus();
            return;
        }
        if(e3.getText().toString().isEmpty()){
            Toast.makeText(this, "capital vide", Toast.LENGTH_SHORT).show();
            e3.requestFocus();
            return;
        }

        Entreprise p = new Entreprise();

        p.setRaison_social(e1.getText().toString());
        p.setAdress(e2.getText().toString());
        p.setCapital(Double.parseDouble(e3.getText().toString()));


        if(MyDatabase.AddEntreprise(db.getWritableDatabase(),p)==-1)
            Toast.makeText(this, "Insertion echoue", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Insertion reussie", Toast.LENGTH_SHORT).show();



    }

    public void annuler_form(View view) {
        finish();
    }
}