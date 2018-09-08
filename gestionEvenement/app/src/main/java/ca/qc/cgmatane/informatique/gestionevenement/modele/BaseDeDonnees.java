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

    }

    public void onOpen(SQLiteDatabase db) {


    }

    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
    }
}
