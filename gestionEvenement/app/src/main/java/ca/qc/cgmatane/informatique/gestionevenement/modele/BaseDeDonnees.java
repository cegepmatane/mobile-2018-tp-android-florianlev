package ca.qc.cgmatane.informatique.gestionevenement.modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonnees extends SQLiteOpenHelper {

    private static BaseDeDonnees instance = null;

    public static BaseDeDonnees getInstance(Context contexte)
    {
        instance = new BaseDeDonnees(contexte);
        return instance;
    }

    public static BaseDeDonnees getInstance()
    {
        return instance;
    }

    public BaseDeDonnees(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BaseDeDonnees(Context contexte) {
        super(contexte, "gestionEvenement", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table evenement(id_evenement INTEGER PRIMARY KEY, titre TEXT, lieu TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    public void onOpen(SQLiteDatabase db) {
        String DELETE = "delete from evenement where 1 = 1";
        db.execSQL(DELETE);

        String INSERT_1 = "insert into evenement(titre, lieu) VALUES('Faire le menage', 'maison')";
        String INSERT_2 = "insert into evenement(titre, lieu) VALUES('fete', 'chez gege')";
        String INSERT_3 = "insert into evenement(titre, lieu) VALUES('haha', 'lolo')";

        db.execSQL(INSERT_1);
        db.execSQL(INSERT_2);
        db.execSQL(INSERT_3);

    }

    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        String CREER_TABLE = "create table evenement(id_evenement INTEGER PRIMARY KEY, titre TEXT, lieu TEXT)";
        db.execSQL(CREER_TABLE);
    }
}
