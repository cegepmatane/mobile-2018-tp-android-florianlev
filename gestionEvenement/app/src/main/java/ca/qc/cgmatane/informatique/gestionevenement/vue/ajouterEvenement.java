package ca.qc.cgmatane.informatique.gestionevenement.vue;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.AlarmManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import android.text.format.DateFormat;
import java.util.Calendar;

import ca.qc.cgmatane.informatique.gestionevenement.AlertReceiver;
import ca.qc.cgmatane.informatique.gestionevenement.R;
import ca.qc.cgmatane.informatique.gestionevenement.donnee.EvenementDao;
import ca.qc.cgmatane.informatique.gestionevenement.modele.Evenement;


public class AjouterEvenement extends AppCompatActivity {
    protected EditText champTitre;
    protected EditText champLieu;
    protected EditText champDate;
    protected Calendar dateActuel;
    protected Evenement evenement;
    int heure, minute;

    protected EvenementDao accesseurEvenement = EvenementDao.getInstance();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_ajouter_evenement);

        champTitre = (EditText)findViewById(R.id.vue_ajouter_evenement_titre);
        champLieu = (EditText)findViewById(R.id.vue_ajouter_evenement_lieu);

        Button actionEnregistrerEvenement =
                (Button)findViewById(R.id.action_enregistrer_evenement);
        actionEnregistrerEvenement.setOnClickListener(

                new View.OnClickListener()
                {
                    public void onClick(View arg0) {
                        System.out.println("AJOUT");
                        enregistrerEvenement();
                    }
                }
        );

        champDate = (EditText)findViewById(R.id.vue_ajouter_evenement_date);

        champDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vue) {
                dateActuel = Calendar.getInstance();
                int annee = dateActuel.get(Calendar.YEAR);
                int mois = dateActuel.get(Calendar.MONTH);
                int jour = dateActuel.get(Calendar.DAY_OF_MONTH);
                heure = dateActuel.get(Calendar.HOUR_OF_DAY);
                minute = dateActuel.get(Calendar.MINUTE);
                DatePickerDialog dialogueChoixDate = new DatePickerDialog(AjouterEvenement.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, final int selectionAnnee, final int selectionMois, final int selectionJour) {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(AjouterEvenement.this, new TimePickerDialog.OnTimeSetListener(){

                            @Override
                            public void onTimeSet(TimePicker vue, int selectionHeure, int selectionMinute) {
                                champDate.setText(selectionJour+"-"+selectionMois+"-"+selectionAnnee + " " + selectionHeure + "h:" + selectionMinute +"mn");
                                dateActuel.set(selectionAnnee,selectionMois,selectionJour,heure,minute);
                                demarerAlarme(dateActuel);

                            }
                        }, heure, minute, DateFormat.is24HourFormat(AjouterEvenement.this));
                        timePickerDialog.show();
                    }
                }, annee, mois, jour);
                dialogueChoixDate.show();
            }
        });
    }

    private void demarerAlarme(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);


    }

    private void enregistrerEvenement()
    {
        evenement = new Evenement(champTitre.getText().toString(),champLieu.getText().toString());
        evenement.setEstFait(false);
        accesseurEvenement.ajouterEvenement(evenement);
        naviguerRetourGestionEvenement();

    }

    public void naviguerRetourGestionEvenement()
    {
        this.finish();
    }


}
