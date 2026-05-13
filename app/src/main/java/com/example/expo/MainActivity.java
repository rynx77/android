package com.example.expo;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activité principale de l'application GSB.
 */
public class MainActivity extends AppCompatActivity {

    SQLiteDatabaseHelper db;

    EditText nomInput;
    EditText prenomInput;
    EditText typeInput;
    EditText villeInput;
    EditText codePostalInput;
    EditText rechercheInput;

    EditText dateInput;
    EditText heureInput;
    EditText idProInput;

    TextView textListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new SQLiteDatabaseHelper(this);

        nomInput = findViewById(R.id.editTextNom);
        prenomInput = findViewById(R.id.editTextPrenom);
        typeInput = findViewById(R.id.editTextType);
        villeInput = findViewById(R.id.editTextVille);
        codePostalInput = findViewById(R.id.editTextCodePostal);
        rechercheInput = findViewById(R.id.editTextRecherche);

        dateInput = findViewById(R.id.editTextDate);
        heureInput = findViewById(R.id.editTextHeure);
        idProInput = findViewById(R.id.editTextIdPro);

        textListe = findViewById(R.id.textViewListe);
    }

    /**
     * Ajoute un professionnel.
     */
    public void insertionClic(View view) {
        db.insertData(
                nomInput.getText().toString(),
                prenomInput.getText().toString(),
                typeInput.getText().toString(),
                villeInput.getText().toString(),
                codePostalInput.getText().toString()
        );

        textListe.setText("Professionnel ajouté");
    }

    /**
     * Affiche tous les professionnels.
     */
    public void afficherClic(View view) {
        Cursor data = db.getAllData();

        String texte = "";

        while (data.moveToNext()) {
            texte = texte
                    + "ID : " + data.getString(0) + " - "
                    + data.getString(1) + " "
                    + data.getString(2) + " - "
                    + data.getString(3) + " - "
                    + data.getString(4) + " - "
                    + data.getString(5)
                    + "\n";
        }

        textListe.setText(texte);
    }

    /**
     * Recherche un professionnel par ville ou code postal.
     */
    public void rechercheClic(View view) {
        Cursor data = db.searchData(
                rechercheInput.getText().toString()
        );

        String texte = "";

        while (data.moveToNext()) {
            texte = texte
                    + "ID : " + data.getString(0) + " - "
                    + data.getString(1) + " "
                    + data.getString(2) + " - "
                    + data.getString(4) + " - "
                    + data.getString(5)
                    + "\n";
        }

        textListe.setText(texte);
    }

    /**
     * Ajoute un rendez-vous.
     */
    public void rdvClic(View view) {
        db.insertRDV(
                dateInput.getText().toString(),
                heureInput.getText().toString(),
                Integer.parseInt(idProInput.getText().toString())
        );

        textListe.setText("RDV ajouté");
    }

    /**
     * Affiche le planning d'une journée.
     */
    public void planningClic(View view) {
        Cursor data = db.getPlanning(
                dateInput.getText().toString()
        );

        String texte = "";

        while (data.moveToNext()) {
            texte = texte
                    + "RDV : " + data.getString(1)
                    + " à " + data.getString(2)
                    + " avec professionnel ID : " + data.getString(3)
                    + "\n";
        }

        textListe.setText(texte);
    }
}