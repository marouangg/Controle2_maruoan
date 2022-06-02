package com.example.controle2_maruoan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class editer_entreprise extends AppCompatActivity {


    Spinner sp;
    EditText elrai, efad, eca;
    MyDatabase db;
    ArrayList<Entreprise> lst_entreprise;
    ArrayAdapter ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editer_entreprise);


        db = new MyDatabase(this);
        sp= findViewById(R.id.speentrerise);
        elrai = findViewById(R.id.ed_rais);
        efad = findViewById(R.id.ed_adress);
        eca = findViewById(R.id.ed_capi);


        getProduit_up();

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Entreprise p = lst_entreprise.get(i);
                elrai.setText(p.getRaison_social());
                efad.setText(p.getAdress());
                eca.setText(String.valueOf(p.getCapital()));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

   void getProduit_up(){
        lst_entreprise = MyDatabase.getAllEntreprise(db.getReadableDatabase());

        ArrayList<String> raison_social = new ArrayList<>();
        for(Entreprise pp: lst_entreprise)
            raison_social.add(pp.getId() + " - " + pp.getRaison_social());

        ad = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,raison_social);
        sp.setAdapter(ad);
    }

    public void modifier(View view) {

        AlertDialog.Builder lb = new AlertDialog.Builder(editer_entreprise.this);
        lb.setTitle("Message update");
        lb.setMessage("Voulez vous vraiment modifier cette Entreprise");

        lb.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Entreprise p=lst_entreprise.get(sp.getSelectedItemPosition());
                p.setRaison_social(elrai.getText().toString());
                p.setAdress(efad.getText().toString());
                p.setCapital(Double.parseDouble(eca.getText().toString()));


                long l=MyDatabase.UpdateEntreprise(db.getWritableDatabase(),p);

                if(l==-1)
                    Toast.makeText(editer_entreprise.this, "Eroor", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(editer_entreprise.this, "nice", Toast.LENGTH_SHORT).show();

                getProduit_up();
            }
        });



        lb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(editer_entreprise.this, "Opiration annuler", Toast.LENGTH_SHORT).show();
            }
        });

        lb.show();
    }

    public void supprimer(View view) {

        AlertDialog.Builder lb = new AlertDialog.Builder(editer_entreprise.this);
        lb.setTitle("Message update");
        lb.setMessage("Are you sure you want to Delete This Entrerise");


        lb.setPositiveButton("Supprimmer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Entreprise p = lst_entreprise.get(sp.getSelectedItemPosition());
                long l=MyDatabase.DeleteEntreprise(db.getWritableDatabase(),p.getId());
                if(l!=-1)
                    Toast.makeText(editer_entreprise.this, "reusir", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(editer_entreprise.this, "Eroor", Toast.LENGTH_SHORT).show();
                getProduit_up();
            }
        });
        lb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(editer_entreprise.this, "Opiration annuler", Toast.LENGTH_SHORT).show();
            }
        });

        lb.show();
    }
}