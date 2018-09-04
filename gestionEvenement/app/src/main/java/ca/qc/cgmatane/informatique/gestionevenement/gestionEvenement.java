package ca.qc.cgmatane.informatique.gestionevenement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.informatique.gestionevenement.donnee.EvenementDao;

public class GestionEvenement extends AppCompatActivity {

    protected ListView vueListeEvenement;
    protected List<HashMap<String, String>> listeEvenement;

    protected EvenementDao accesseurLivre = EvenementDao.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_gestion_evenement);

        vueListeEvenement = (ListView) findViewById(R.id.vue_liste_evenement);
        listeEvenement = accesseurLivre.recupererListeEvenement();

        SimpleAdapter adapteur = new SimpleAdapter(
                this,
                listeEvenement,
                android.R.layout.two_line_list_item,
                new String[] {"titre","auteur"},
                new int[] {android.R.id.text1, android.R.id.text2});


        vueListeEvenement.setAdapter(adapteur);

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


