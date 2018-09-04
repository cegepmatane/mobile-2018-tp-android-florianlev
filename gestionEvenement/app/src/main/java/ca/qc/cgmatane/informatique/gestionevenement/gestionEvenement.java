package ca.qc.cgmatane.informatique.gestionevenement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.HashMap;
import java.util.List;

public class gestionEvenement extends AppCompatActivity {

    protected ListView vueListeEvenement;
    protected List<HashMap<String, String>> listeEvenement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_gestion_evenement);

        vueListeEvenement = (ListView) findViewById(R.id.vue_liste_evenement);

    }
}


