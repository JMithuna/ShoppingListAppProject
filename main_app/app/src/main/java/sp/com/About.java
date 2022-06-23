package sp.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class About extends AppCompatActivity {

    private TextView app_name;
    private TextView app_concept;
    private TextView app_purpose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        app_name = findViewById(R.id.appName);
        app_concept = findViewById(R.id.concept);
        app_purpose = findViewById(R.id.purpose);

        app_name.setText("SHOPPING LIST APP");
        app_concept.setText("CONCEPT");
        app_purpose.setText("PURPOSE");


    }





}