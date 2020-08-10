package com.desarrollo.notificacioneslizarraga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Thread.sleep(1000);
            setTheme(R.style.SplashTheme);
            CambiarActividad();
            finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void CambiarActividad() {
        startActivity(new Intent(MainActivity.this, NotificacionActivity.class));
    }
}