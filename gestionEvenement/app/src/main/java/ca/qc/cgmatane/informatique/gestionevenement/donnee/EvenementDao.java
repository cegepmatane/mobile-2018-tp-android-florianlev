package ca.qc.cgmatane.informatique.gestionevenement.donnee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EvenementDao {

    private static EvenementDao instance = null;

    protected List<HashMap<String, String>> listeEvenements ;

    public static EvenementDao getInstance()
    {
        if(null == instance)
        {
            instance = new EvenementDao();
        }
        return instance;
    }

    public EvenementDao()
    {
        listeEvenements = new ArrayList<HashMap<String, String>>();
        prepareListeEvenements();
    }

    public List<HashMap<String, String>> recupererListeEvenement() {

        return listeEvenements;
    }


    public void prepareListeEvenements()
    {

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

    }
}
