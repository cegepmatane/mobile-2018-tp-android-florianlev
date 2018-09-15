package ca.qc.cgmatane.informatique.gestionevenement.modele;
import java.util.HashMap;

public class Evenement {
    protected String titre;
    protected String lieu;
    protected boolean estFait;
    protected int id_evenement;

    public String getTitre() {
        return titre;
    }

    public boolean getEstFait() {
        return estFait;
    }

    public void setEstFait(boolean estFait) {
        this.estFait = estFait;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getId_evenement()
    {
        return id_evenement;
    }



    public Evenement(String titre, String lieu)
    {
        this.titre = titre;
        this.lieu = lieu;
    }

    public Evenement(String titre, String lieu, int id_evenement)
    {
        this.titre = titre;
        this.lieu = lieu;
        this.id_evenement = id_evenement;
    }


    public HashMap<String, String> obtenirEvenementPourAdaptateur()
    {
        HashMap<String, String> evenementPourAdaptateur = new HashMap<String,String>();
        evenementPourAdaptateur.put("titre", this.titre);
        evenementPourAdaptateur.put("lieu", this.lieu);
        evenementPourAdaptateur.put("id_evenement", "" + this.id_evenement);
        return evenementPourAdaptateur;
    }

}