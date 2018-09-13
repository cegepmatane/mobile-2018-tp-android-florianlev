package ca.qc.cgmatane.informatique.gestionevenement.vue;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import ca.qc.cgmatane.informatique.gestionevenement.R;
import ca.qc.cgmatane.informatique.gestionevenement.donnee.EvenementDao;
import ca.qc.cgmatane.informatique.gestionevenement.modele.Evenement;


public class AjouterEvenement extends AppCompatActivity{
    protected EditText champTitre;
    protected EditText champLieu;
    protected EditText champDate;
    protected Calendar dateActuel;
    protected Evenement evenement;

    protected EvenementDao accesseurEvenement = EvenementDao.getInstance();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_ajouter_evenement);

        champTitre = (EditText)findViewById(R.id.vue_ajouter_evenement_titre);
        champLieu = (EditText)findViewById(R.id.vue_ajouter_evenement_lieu);

        Button actionEnregistrerEvenement =
                (Button)findViewById(R.id.action_enregistrer_evenement);
        actionEnregistrerEvenement.setOnClickListener(

                new View.OnClickListener()
                {
                    public void onClick(View arg0) {
                        System.out.println("AJOUT");
                        enregistrerEvenement();
                    }
                }
        );

        champDate = (EditText)findViewById(R.id.vue_ajouter_evenement_date);

        champDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vue) {
                dateActuel = Calendar.getInstance();
                int annee = dateActuel.get(Calendar.YEAR);
                int mois = dateActuel.get(Calendar.MONTH);
                int jour = dateActuel.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialogueChoixDate = new DatePickerDialog(AjouterEvenement.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectionAnnee, int selectionMois, int selectionJour) {
                        champDate.setText(selectionJour+"-"+selectionMois+"-"+selectionAnnee);
                        dateActuel.set(selectionAnnee,selectionMois,selectionJour);
                    }
                }, annee, mois, jour);
                dialogueChoixDate.show();
            }
        });
    }

    private void enregistrerEvenement()
    {

        /*Toast message = Toast.makeText(getApplicationContext(),
                "Titre "+champTitre.getText().toString(),
                Toast.LENGTH_SHORT);

        message.show();*/

        //accesseurEvenement.ajouterEvenement();
        evenement = new Evenement(champTitre.getText().toString(),champLieu.getText().toString());
        accesseurEvenement.ajouterEvenement(evenement);
        naviguerRetourGestionEvenement();

    }

    public void naviguerRetourGestionEvenement()
    {
        this.finish();
    }

}
