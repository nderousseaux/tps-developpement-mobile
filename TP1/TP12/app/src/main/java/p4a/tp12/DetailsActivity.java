package p4a.tp12;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent startIntent = getIntent();
        Bundle extras = startIntent.getExtras();

        ((TextView)findViewById(R.id.textViewFirstname)).setText(extras.get(getString(R.string.firstname)).toString());
        ((TextView)findViewById(R.id.textViewLastame)).setText(extras.get(getString(R.string.lastname)).toString());
        ((TextView)findViewById(R.id.textViewAddress)).setText(extras.get(getString(R.string.address)).toString());
        ((TextView)findViewById(R.id.textViewPhone)).setText(extras.get(getString(R.string.phone)).toString());
    }
}
