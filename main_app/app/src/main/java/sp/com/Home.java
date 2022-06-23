package sp.com;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    private Button aboutDisplay;
    private Button addItem;
    private Button showList;
    private Button exitApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //Add item button
        addItem = findViewById(R.id.add_item);
        addItem.setOnClickListener(onAdd);

        //About button
        aboutDisplay = findViewById(R.id.about);
        aboutDisplay.setOnClickListener(onAboutClick);

        //Display list button
        showList = findViewById(R.id.display_list);
        showList.setOnClickListener(onShowListClick);

        //exit app button
        exitApp = findViewById(R.id.exit_button);
        exitApp.setOnClickListener(onExitClick);

    }

    private View.OnClickListener onAdd = new View.OnClickListener() {
        public void onClick(View v) {
            //start DataInput Activity

            Intent dataInput_intent;
            dataInput_intent = new Intent(Home.this, DataInput.class);
            startActivity(dataInput_intent);
            Toast.makeText(v.getContext(), "DataInput Activity has been launched!", Toast.LENGTH_LONG).show();
        }
    };

    private View.OnClickListener onAboutClick = new View.OnClickListener() {
        public void onClick(View v) {
            //start About Activity

            Intent about_intent;
            about_intent = new Intent(Home.this, About.class);
            startActivity(about_intent);
            Toast.makeText(v.getContext(), "About Activity has been launched!", Toast.LENGTH_LONG).show();
        }
    };

    private View.OnClickListener onShowListClick = new View.OnClickListener() {
        public void onClick(View v) {
            //start InformationDisplay Activity

            Intent infoDisplay_intent;
            infoDisplay_intent = new Intent(Home.this, InformationDisplay.class);
            startActivity(infoDisplay_intent);
            Toast.makeText(v.getContext(), "InformationDisplay Activity has been launched!", Toast.LENGTH_LONG).show();
        }
    };

    private View.OnClickListener onExitClick = new View.OnClickListener() {
        public void onClick(View v) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(Home.this);
            alertDialog.setTitle("Exit Application");
            alertDialog.setMessage("Are you sure you want to Exit the application?");

            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    //System.exit(0);
                }
            });

            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            alertDialog.show();



        }
    };

}