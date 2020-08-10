package com.desarrollo.notificacioneslizarraga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class NotificacionActivity extends AppCompatActivity {

    private MediaPlayer sonido;
    private TextInputEditText edtTitulo, edtDescripcion;
    private Button botonNotificar;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager notificationManager;
    private static final int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacion);

        crearCanalNotificaciones();

        sonido = MediaPlayer.create(this, R.raw.beginning);
        edtTitulo = findViewById(R.id.edtTitulo);
        edtDescripcion = findViewById(R.id.edtDescripcion);

        botonNotificar = findViewById(R.id.notificar);

        botonNotificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titulo = edtTitulo.getText().toString();
                String descripcion = edtTitulo.getText().toString();

                if (!titulo.equals("") && !descripcion.equals("")){
                    enviarNotification();
                    Toast.makeText(getApplicationContext(), "Notificacion enviada", Toast.LENGTH_SHORT).show();
                    accion1Vibrar();
                    accion2Sonido();
                } else {
                    Toast.makeText(getApplicationContext(), "No hay datos para la notificacion", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void accion2Sonido() {
        sonido.start();
    }

    private void accion1Vibrar() {
        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
    }

    //ENVIAR NOTIFICACION

    private void enviarNotification() {
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        notificationManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }

    //CREAR CANAL DE NOTIFICACION

    private void crearCanalNotificaciones() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //VERIFICAR LA VERSION DE API 26(OREO)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,"Notificacion de Paolo", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification de Paolo Descripcion");
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    //CONTRUIRNOTIFICACION

    private NotificationCompat.Builder getNotificationBuilder() {
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle(edtTitulo.getText().toString())
                .setContentText(edtDescripcion.getText().toString())
                .setSmallIcon(R.drawable.ic_notificacion_2);
        return notifyBuilder;
    }
}