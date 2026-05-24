package com.example.expo;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabaseHelper db;

    EditText nomInput;
    EditText prenomInput;
    EditText typeInput;
    EditText villeInput;
    EditText codePostalInput;
    EditText rechercheInput;

    EditText heureInput;
    EditText idProInput;

    CalendarView calendarView;

    TextView textListe;

    String dateChoisie = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new SQLiteDatabaseHelper(this);

        nomInput = findViewById(R.id.editTextNom);
        prenomInput = findViewById(R.id.editTextPrenom);
        typeInput = findViewById(R.id.editTextType);
        villeInput = findViewById(R.id.editTextVille);

        codePostalInput =
                findViewById(R.id.editTextCodePostal);

        rechercheInput =
                findViewById(R.id.editTextRecherche);

        heureInput =
                findViewById(R.id.editTextHeure);

        idProInput =
                findViewById(R.id.editTextIdPro);

        calendarView =
                findViewById(R.id.calendarView);

        textListe =
                findViewById(R.id.textViewListe);


        calendarView.setOnDateChangeListener(
                (view, year, month, day) -> {

                    dateChoisie =
                            day + "/"
                                    + (month+1)
                                    + "/"
                                    + year;
                }
        );

    }



    public void insertionClic(View view){

        db.insertData(

                nomInput.getText().toString(),

                prenomInput.getText().toString(),

                typeInput.getText().toString(),

                villeInput.getText().toString(),

                codePostalInput.getText().toString()

        );

        textListe.setText(
                "Professionnel ajouté"
        );

    }



    public void afficherClic(View view){

        Cursor data =
                db.getAllData();

        String texte="";

        while(data.moveToNext()){

            texte =
                    texte
                            + data.getString(1)
                            +" "
                            + data.getString(2)
                            +"\n";

        }

        textListe.setText(texte);

    }



    public void rechercheClic(View view){

        Cursor data =
                db.searchData(

                        rechercheInput
                                .getText()
                                .toString()

                );

        String texte="";

        while(data.moveToNext()){

            texte =
                    texte
                            + data.getString(1)
                            +" "
                            + data.getString(2)
                            +"\n";

        }

        textListe.setText(texte);

    }




    public void rdvClic(View view){

        db.insertRDV(

                dateChoisie,

                heureInput
                        .getText()
                        .toString(),

                Integer.parseInt(

                        idProInput
                                .getText()
                                .toString()

                )

        );

        textListe.setText(
                "RDV ajouté"
        );

    }





    public void planningClic(View view){

        Cursor data =
                db.getPlanning(
                        dateChoisie
                );

        String texte="";

        while(data.moveToNext()){

            texte =
                    texte
                            +"Date : "
                            +data.getString(1)

                            +" Heure : "

                            +data.getString(2)

                            +"\n";

        }

        textListe.setText(
                texte
        );

    }

}