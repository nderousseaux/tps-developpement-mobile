package com.example.tp1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Validation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);

        //On récupère les données de l'intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String lastname = intent.getStringExtra("lastname");
        String phone = intent.getStringExtra("phone");
        String email = intent.getStringExtra("mail");
        String address = intent.getStringExtra("address");

        //On récupère les textview
        TextView txtName = findViewById(R.id.txtName);
        TextView txtLastname = findViewById(R.id.txtLastname);
        TextView txtPhone = findViewById(R.id.txtPhone);
        TextView txtMail = findViewById(R.id.txtMail);
        TextView txtAddress = findViewById(R.id.txtAddress);

        //On modifie les textview
        txtName.setText(": " + name);
        txtLastname.setText(": " + lastname);
        txtPhone.setText(": " + phone);
        txtMail.setText(": " + email);
        txtAddress.setText(": " + address);


        Button btnValidate = findViewById(R.id.buttonValidate);


        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

                intent.putExtra(ContactsContract.Intents.Insert.NAME, name + " " + lastname);
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
                intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
                intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);

                startActivity(intent);
            }
        });
    }
}