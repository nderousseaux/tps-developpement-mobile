package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Activite crée", Toast.LENGTH_SHORT).show();
        Log.d("onCreate","Activité crée");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Activite lancée", Toast.LENGTH_SHORT).show();
        Log.d("onStart","Activité lancée");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Activite resume", Toast.LENGTH_SHORT).show();
        Log.d("onResume","Activité resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Activite en pause", Toast.LENGTH_SHORT).show();
        Log.d("onPause","Activité en pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Activite stoppée", Toast.LENGTH_SHORT).show();
        Log.d("onStop","Activité stoppée");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Activite détruite", Toast.LENGTH_SHORT).show();
        Log.d("onDestroy","Activité détruite");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Activite redémarrée", Toast.LENGTH_SHORT).show();
        Log.d("onRestart","Activité redémarrée");
    }
}
