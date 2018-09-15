package ca.qc.cgmatane.informatique.gestionevenement.donnee;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.informatique.gestionevenement.modele.BaseDeDonnees;
import ca.qc.cgmatane.informatique.gestionevenement.modele.Evenement;

public class EvenementDao {

    private static EvenementDao instance = null;
    private BaseDeDonnees accesseurBaseDeDonnees;


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
        this.accesseurBaseDeDonnees = BaseDeDonnees.getInstance();
        listeEvenements = new ArrayList<Evenement>();
        //prepareListeEvenements();
    }

    public List<Evenement> listerEvenement() {
        String LISTER_EVENEMENTS = "SELECT * FROM evenement WHERE fait = '0'";
        Cursor curseur = accesseurBaseDeDonnees.getReadableDatabase().rawQuery(LISTER_EVENEMENTS,
                null);
        this.listeEvenements.clear();
        Evenement evenement;

        int indexId_evenement = curseur.getColumnIndex("id_evenement");
        int indexLieu = curseur.getColumnIndex("lieu");
        int indexTitre = curseur.getColumnIndex("titre");

        for(curseur.moveToFirst();!curseur.isAfterLast();curseur.moveToNext()) {
            int id_evenement = curseur.getInt(indexId_evenement);
            String lieu = curseur.getString(indexLieu);
            String titre = curseur.getString(indexTitre);
            evenement = new Evenement(titre, lieu, id_evenement);
            this.listeEvenements.add(evenement);
        }

        return listeEvenements;
    }

    /*public List<HashMap<String, String>> recupererListeEvenement() {

        return listeEvenements;
    }*/

    public Evenement trouverEvenement(int id_evenement)
    {
        for(Evenement evenementRecherche : this.listeEvenements)
        {
            if(evenementRecherche.getId_evenement() == id_evenement) return evenementRecherche;
        }
        return null;
    }

    public void modifierEvenement(Evenement evenementModifier)
    {
        for(Evenement evenement : this.listeEvenements)
        {
            if(evenement.getId_evenement() == evenementModifier.getId_evenement())
            {
                String MODIFIER_EVENEMENT;
                System.out.println(evenementModifier.getEstFait());
                if(evenementModifier.getEstFait()){

                    MODIFIER_EVENEMENT = "UPDATE evenement SET titre = '" + evenementModifier.getTitre() + "', lieu = '" + evenementModifier.getLieu() + "', fait = '1' WHERE id_evenement = " + evenementModifier.getId_evenement();

                }
                else{
                    MODIFIER_EVENEMENT = "UPDATE evenement SET titre = '" + evenementModifier.getTitre() + "', lieu = '" + evenementModifier.getLieu() + "', fait = '0' WHERE id_evenement = " + evenementModifier.getId_evenement();
                }
                accesseurBaseDeDonnees.getWritableDatabase().execSQL(MODIFIER_EVENEMENT);
                return;
            }
        }
    }

    public List<HashMap<String, String>> recuperereListeEvenementPourAdapteur() {
        List<HashMap<String, String>> listeEvenementPourAdaptateur;
        listeEvenementPourAdaptateur = new ArrayList<HashMap<String, String>>();
        listerEvenement();
        for(Evenement evenement:listeEvenements){
            listeEvenementPourAdaptateur.add(evenement.obtenirEvenementPourAdaptateur());
        }
        return listeEvenementPourAdaptateur;
    }

    public void ajouterEvenement(Evenement evenement)
    {
        //listeEvenements.add(evenement);
        System.out.println("AjouterEvenement");
        String AJOUTER_EVENEMENT;
        AJOUTER_EVENEMENT = "INSERT INTO evenement (titre,lieu,fait) VALUES ('"+ evenement.getTitre() +"','"+ evenement.getLieu()+"','0');";
        accesseurBaseDeDonnees.getWritableDatabase().execSQL(AJOUTER_EVENEMENT);

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
