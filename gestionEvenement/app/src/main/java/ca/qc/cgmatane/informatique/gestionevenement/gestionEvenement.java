package ca.qc.cgmatane.informatique.gestionevenement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.informatique.gestionevenement.donnee.EvenementDao;
import ca.qc.cgmatane.informatique.gestionevenement.modele.BaseDeDonnees;
import ca.qc.cgmatane.informatique.gestionevenement.vue.AjouterEvenement;
import ca.qc.cgmatane.informatique.gestionevenement.vue.ModifierEvenement;

public class GestionEvenement extends AppCompatActivity {

    static final public int ACTIVITE_AJOUTER_EVENEMENT = 1;
    static final public int ACTIVITE_MODIFIER_EVENEMENT = 2;

    protected ListView vueListeEvenement;
    protected List<HashMap<String, String>> listeEvenementPourAdapteur;

    protected Intent intentionNaviguerAjouterEvenement;
    protected EvenementDao accesseurEvenement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_gestion_evenement);

        Log.d("GestionEvenement", "onCreate");

        BaseDeDonnees.getInstance(getApplicationContext());
        accesseurEvenement = EvenementDao.getInstance();

        vueListeEvenement = (ListView) findViewById(R.id.vue_liste_evenement);
        /*
        listeEvenementPourAdapteur = accesseurEvenement.recuperereListeEvenementPourAdapteur();

        SimpleAdapter adapteur = new SimpleAdapter(
                this,
                listeEvenementPourAdapteur,
                android.R.layout.two_line_list_item,
                new String[] {"titre","lieu"},
                new int[] {android.R.id.text1, android.R.id.text2});


        vueListeEvenement.setAdapter(adapteur);
        */

        afficherTousLesEvenements();

        vueListeEvenement.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View vue, int positionDansAdaptateur, long positionItem) {
                        Log.d("GestionEvenement", "onItemClick");
                        ListView vueListeEvenement = (ListView) vue.getParent();

                        @SuppressWarnings("unchecked")
                        HashMap<String, String> evenement =
                                (HashMap<String, String>)
                                        vueListeEvenement.getItemAtPosition((int) positionItem);

                        Toast message = Toast.makeText(getApplicationContext(),
                                "Position " +
                                        positionItem +
                                        " titre " +
                                        evenement.get("titre"),
                                Toast.LENGTH_SHORT);
                        Log.d("GestionEvenement", "onItemClick Position:" + positionItem);
                        Log.d("GestionEvenement", "onItemClick Titre:" + evenement.get("titre"));

                        message.show();
                        Intent intentionNaviguerModiferEvenement = new Intent(
                                GestionEvenement.this,
                                ModifierEvenement.class
                        );

                        intentionNaviguerModiferEvenement.putExtra("id_evenement", evenement.get("id_evenement"));
                        startActivityForResult(intentionNaviguerModiferEvenement, ACTIVITE_MODIFIER_EVENEMENT);

                    }
                }
        );

        intentionNaviguerAjouterEvenement = new Intent(this,
                AjouterEvenement.class);

        Button actionNaviguerAjouterEvenement =
                (Button)findViewById(R.id.action_naviguer_ajouter_evenement);

        actionNaviguerAjouterEvenement.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View arg0) {
                        startActivityForResult(intentionNaviguerAjouterEvenement,ACTIVITE_AJOUTER_EVENEMENT);
                    }
                }
        );
    }

    protected void afficherTousLesEvenements()
    {
        listeEvenementPourAdapteur = accesseurEvenement.recuperereListeEvenementPourAdapteur();

        SimpleAdapter adapteur = new SimpleAdapter(
                this,
                listeEvenementPourAdapteur,
                android.R.layout.two_line_list_item,
                new String[] {"titre","lieu"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueListeEvenement.setAdapter(adapteur);
    }

    protected void onActivityResult(int activite, int resultat, Intent donnees)
    {
        switch(activite)
        {
            case ACTIVITE_AJOUTER_EVENEMENT:
                afficherTousLesEvenements();
                break;
            case ACTIVITE_MODIFIER_EVENEMENT:
                afficherTousLesEvenements();
                break;
        }

    }


    /*public List<HashMap<String,String>> prepareListeEvenements()
    {

        List<HashMap<String,String>> listeEvenements =
                new ArrayList<HashMap<String,String>>();

        HashMap<String,String> evenement;

        evenement = new HashMap<String,String>();
        evenement.put("titre", "reunion");
        evenement.put("lieu", "cegep Matane");
        listeEvenements.add(evenement);

        evenement = new HashMap<String,String>();
        evenement.put("titre", "party");
        evenement.put("lieu", "chez moi");
        listeEvenements.add(evenement);

        evenement = new HashMap<String,String>();
        evenement.put("titre", "recuperer voiture");
        evenement.put("lieu", "chez keke");
        listeEvenements.add(evenement);

        return listeEvenements;
    }*/
}


