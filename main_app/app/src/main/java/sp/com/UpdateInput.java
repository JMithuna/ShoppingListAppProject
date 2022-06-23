package sp.com;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UpdateInput extends AppCompatActivity {
    //declare EditText variables
    private EditText itemNameU;
    private EditText itemQuantityU;
    private EditText shopNameU;
    private EditText lat_valU;
    private EditText lon_valU;

    //declare button variable
    private Button updateU;
    private Button deleteU;

    //declare radio group
    private RadioGroup itemCategoryU;

    String id, catitem, theItem, theQuantity, theShop, theLat, theLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_input);

        itemNameU = findViewById(R.id.u_enter_name);
        itemQuantityU = findViewById(R.id.u_enter_quantity);
        itemCategoryU = findViewById(R.id.u_category_list);
        shopNameU = findViewById(R.id.u_enter_shopname);
        lat_valU = findViewById(R.id.u_latitude_value);
        lon_valU = findViewById(R.id.u_longitude_value);

        getAndSetIntentData();

        updateU = findViewById(R.id.u_button_update);
        updateU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingHelper myDB = new ShoppingHelper(UpdateInput.this);
                theItem = itemNameU.getText().toString().trim();
                theQuantity = itemQuantityU.getText().toString().trim();
                theShop = shopNameU.getText().toString().trim();
                theLat = lat_valU.getText().toString().trim();
                theLon = lon_valU.getText().toString().trim();
                myDB.updateData(id, theItem, theQuantity, theShop, theLat, theLon);
                finish();
            }
        });

        deleteU = findViewById(R.id.u_button_delete);
        deleteU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteDialog();
            }
        });



    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id")  && getIntent().hasExtra("nameItem") &&
                getIntent().hasExtra("quantityItem") && getIntent().hasExtra("nameshop") &&
                getIntent().hasExtra("vallat") && getIntent().hasExtra("vallon")) {

            //getting data from intent
            id = getIntent().getStringExtra("id");
            //catitem = getIntent().getStringExtra("categoryItem");
            theItem = getIntent().getStringExtra("nameItem");
            theQuantity = getIntent().getStringExtra("quantityItem");
            theShop = getIntent().getStringExtra("nameshop");
            theLat = getIntent().getStringExtra("vallat");
            theLon = getIntent().getStringExtra("vallon");

            //setting intent data
            itemNameU.setText(theItem);
            itemQuantityU.setText(theQuantity);
            shopNameU.setText(theShop);
            lat_valU.setText(theLat);
            lon_valU.setText(theLon);

        } else {
            Toast.makeText(this, "no data", Toast.LENGTH_LONG).show();
        }
    }

    void confirmDeleteDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(UpdateInput.this);
        alertDialog.setTitle("Confirm DELETE?");
        alertDialog.setMessage("Are you sure you want to Delete the item?");

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                ShoppingHelper myDB = new ShoppingHelper(UpdateInput.this);
                myDB.deleteOneRow(id);
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

}