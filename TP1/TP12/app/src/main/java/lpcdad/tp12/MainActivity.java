package lpcdad.tp12;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        ((EditText)findViewById(R.id.editTextFirstname)).setText( prefs.getString(getString(R.string.firstname),getString(R.string.firstname)) );
        ((EditText)findViewById(R.id.editTextLastname)).setText( prefs.getString(getString(R.string.lastname),getString(R.string.lastname)) );
        ((EditText)findViewById(R.id.editTextAddress)).setText( prefs.getString(getString(R.string.address),getString(R.string.address)) );
        ((EditText)findViewById(R.id.editTextPhone)).setText( prefs.getString(getString(R.string.phone),getString(R.string.phone)) );

        Button buttonCancel = findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EditText)findViewById(R.id.editTextFirstname)).setText("");
                ((EditText)findViewById(R.id.editTextLastname)).setText("");
                ((EditText)findViewById(R.id.editTextAddress)).setText("");
                ((EditText)findViewById(R.id.editTextPhone)).setText("");
            }
        });

        Button buttonOk = findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(view.getContext(), DetailsActivity.class);
                startIntent.putExtra(getString(R.string.firstname),((EditText)findViewById(R.id.editTextFirstname)).getText());
                startIntent.putExtra(getString(R.string.lastname),((EditText)findViewById(R.id.editTextLastname)).getText());
                startIntent.putExtra(getString(R.string.address),((EditText)findViewById(R.id.editTextAddress)).getText());
                startIntent.putExtra(getString(R.string.phone),((EditText)findViewById(R.id.editTextPhone)).getText());
                startActivity(startIntent);
            }
        });
    }

    @Override
    protected void onStop() {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(getString(R.string.firstname), ((EditText)findViewById(R.id.editTextFirstname)).getText().toString());
        editor.putString(getString(R.string.lastname), ((EditText)findViewById(R.id.editTextLastname)).getText().toString());
        editor.putString(getString(R.string.address), ((EditText)findViewById(R.id.editTextAddress)).getText().toString());
        editor.putString(getString(R.string.phone), ((EditText)findViewById(R.id.editTextPhone)).getText().toString());
        editor.apply();
        super.onStop();
    }

}
