package com.example.tp1_2;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //On récupère tout les composants
        EditText edtLastname = findViewById(R.id.edtLastname);
        EditText edtName = findViewById(R.id.edtName);
        EditText edtPhone = findViewById(R.id.edtPhone);
        EditText edtMail = findViewById(R.id.edtMail);
        EditText edtAddress = findViewById(R.id.edtAddress);
        Button btnOk = findViewById(R.id.btnOk);
        Button btnDel = findViewById(R.id.btnDelete);

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtLastname.setText("");
                edtName.setText("");
                edtPhone.setText("");
                edtMail.setText("");
                edtAddress.setText("");
            }
        });


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Validation.class);
                myIntent.putExtra("name", edtName.getText().toString());
                myIntent.putExtra("lastname", edtLastname.getText().toString());
                myIntent.putExtra("phone", edtPhone.getText().toString());
                myIntent.putExtra("mail", edtMail.getText().toString());
                myIntent.putExtra("address", edtAddress.getText().toString());
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

}
