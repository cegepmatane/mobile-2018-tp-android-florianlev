package ca.qc.cgmatane.informatique.gestionevenement.vue;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import ca.qc.cgmatane.informatique.gestionevenement.R;
import ca.qc.cgmatane.informatique.gestionevenement.donnee.EvenementDao;
import ca.qc.cgmatane.informatique.gestionevenement.modele.Evenement;

public class ModifierEvenement extends AppCompatActivity {

    protected Evenement evenement;
    protected EvenementDao accesseurEvenement;

    EditText champTitre;
    EditText champLieu;
    CheckBox checkFait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_modifier_evenement);

        this.accesseurEvenement = EvenementDao.getInstance();

        Bundle parametres = this.getIntent().getExtras();
        String parametre_id_evenement = (String) parametres.get("id_evenement");
        int id_evenement = Integer.parseInt(parametre_id_evenement);

        evenement = accesseurEvenement.trouverEvenement(id_evenement);

        champTitre = (EditText)findViewById(R.id.vue_modifier_evenement_champ_titre);
        champLieu = (EditText)findViewById(R.id.vue_modifier_evenement_champ_lieu);
        checkFait = (CheckBox)findViewById(R.id.vue_modifier_evenement_champ_estFait);


        champTitre.setText(evenement.getTitre());
        champLieu.setText(evenement.getLieu());

        //checkFait.setText(evenement.getEstFait());

        Button actionModifierLivre =
                (Button)findViewById(R.id.action_modifier_evenement);

        actionModifierLivre.setOnClickListener(

                new View.OnClickListener()
                {
                    public void onClick(View arg0) {
                        modifierEvenement();
                    }
                }
        );
    }

    private void modifierEvenement() {

        Evenement evenement = new Evenement(champTitre.getText().toString(),
                champLieu.getText().toString(),
                this.evenement.getId_evenement());
        if (checkFait.isChecked()){
            evenement.setEstFait(true);
        }
        accesseurEvenement.modifierEvenement(evenement);
        naviguerRetourGestionEvenement();
    }

    public void naviguerRetourGestionEvenement()
    {
        this.finish();
    }
}
