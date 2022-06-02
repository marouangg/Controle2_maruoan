package com.example.controle2_maruoan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;



public class Liste_entreprise extends AppCompatActivity {

    MyDatabase db;
    ListView ls_view;
    Baseentreprise bs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_entreprise);

        db=new MyDatabase(this);

        ls_view=findViewById(R.id.lst);

        ArrayList<Entreprise> list_produit=MyDatabase.getAllEntreprise(db.getReadableDatabase());



      bs=new Baseentreprise(list_produit);
       ls_view.setAdapter(bs);
    }
    public class Baseentreprise extends BaseAdapter {

        ArrayList<Entreprise> list_entreprise=new ArrayList<>() ;
        public Baseentreprise(ArrayList<Entreprise> lp){
            this.list_entreprise=lp;
        }

        @Override
        public int getCount() {
            return list_entreprise.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater li = getLayoutInflater();
            View v =li.inflate(R.layout.grid,null);
            TextView tid,t_raison,tfamil,t_capital;

            t_raison=v.findViewById(R.id.t_raison);
            t_capital=v.findViewById(R.id.t_capital);

            t_raison.setText(list_entreprise.get(i).getRaison_social());

            t_capital.setText(String.format("%.2f",list_entreprise.get(i).getCapital()));
            return v;
        }
    }

}