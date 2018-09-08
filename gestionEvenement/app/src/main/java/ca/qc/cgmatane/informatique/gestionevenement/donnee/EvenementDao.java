package ca.qc.cgmatane.informatique.gestionevenement.donnee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.informatique.gestionevenement.modele.Evenement;

public class EvenementDao {

    private static EvenementDao instance = null;

    protected List<Evenement> listeEvenements ;

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
        listeEvenements = new ArrayList<Evenement>();
        prepareListeEvenements();
    }

    /*public List<HashMap<String, String>> recupererListeEvenement() {

        return listeEvenements;
    }*/

    public List<HashMap<String, String>> recuperereListeLivrePourAdapteur() {
        List<HashMap<String, String>> listeEvenementPourAdaptateur;
        listeEvenementPourAdaptateur = new ArrayList<HashMap<String, String>>();

        for(Evenement evenement:listeEvenements){
            listeEvenementPourAdaptateur.add(evenement.obtenirEvenementPourAdaptateur());
        }
        return listeEvenementPourAdaptateur;
    }

    public void ajouterEvenement(Evenement evenement)
    {
        listeEvenements.add(evenement);

    }


    public void prepareListeEvenements()
    {

        listeEvenements.add(new Evenement("reunion", "cegep Matane", 1));
        listeEvenements.add(new Evenement("party", "chez moi", 2));
        listeEvenements.add(new Evenement("recuperer voiture", "chez keke", 3));

        /*HashMap<String,String> evenement;

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
*/
    }
}
