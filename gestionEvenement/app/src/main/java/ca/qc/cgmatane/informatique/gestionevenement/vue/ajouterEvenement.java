package ca.qc.cgmatane.informatique.gestionevenement.vue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.qc.cgmatane.informatique.gestionevenement.R;
import ca.qc.cgmatane.informatique.gestionevenement.donnee.EvenementDao;


public class AjouterEvenement extends AppCompatActivity {
    protected EditText champTitre;
    protected EditText champLieu;

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
                        enregistrerEvenement();
                    }
                }
        );
    }

    private void enregistrerEvenement()
    {

        /*Toast message = Toast.makeText(getApplicationContext(),
                "Titre "+champTitre.getText().toString(),
                Toast.LENGTH_SHORT);

        message.show();*/

        //accesseurEvenement.ajouterEvenement();
        naviguerRetourGestionEvenement();

    }

    public void naviguerRetourGestionEvenement()
    {
        this.finish();
    }
}
