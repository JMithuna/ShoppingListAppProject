package sp.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DataInput extends AppCompatActivity {

    //declare EditText variables
    private EditText itemName;
    private EditText itemQuantity;
    private EditText shopName;
    private EditText lat_val;
    private EditText lon_val;

    //declare button variable
    private Button save;
    private Button delete;

    //declare radio group
    private RadioGroup itemCategory;

    //map related
    private double latitude = 0.0d;
    private double longitude = 0.0d;

    //database related
    private ShoppingHelper helper = null;

    //image related
    private ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_input);

        //bind EditText variables with EditText widget
        itemName = findViewById(R.id.enter_name);
        itemQuantity = findViewById(R.id.enter_quantity);
        shopName = findViewById(R.id.enter_shopname);
        lat_val = findViewById(R.id.latitude_value);
        lon_val = findViewById(R.id.longitude_value);


        //bind Button variables with Button widget
        save = findViewById(R.id.button_save);
        //register save button to event listener. when it is clicked on view, the program
        //will jump to onsave view listener object where object can start to read data from UI
        save.setOnClickListener(onSave);

        //delete = findViewById(R.id.button_delete);
        //delete.setOnClickListener(onDelete);

        //bind the radiogroup variable to the widget
        itemCategory = findViewById(R.id.u_category_list);

        //image
        imageView = findViewById(R.id.imageView);

        //for database and display
        helper = new ShoppingHelper(DataInput.this);

        //call the get and set intent first before calling update




        //to delete a row in recycler view


    }

    /*
    private View.OnClickListener onDelete = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ShoppingHelper myDB = new ShoppingHelper(DataInput.this);
            myDB.deleteOneRow(itemName);
        }
    };

     */

    private View.OnClickListener onSave = new View.OnClickListener() {
        @Override
        public void onClick (View v) {
            //use getText() method provided by EditText widget to read the data from UI
            String categoryItem = "";
            String nameItem = itemName.getText().toString();
            String quantity = itemQuantity.getText().toString();
            String shop = shopName.getText().toString();
            //String latVal = lat_val.getText().toString();
            //String lonVal = lon_val.getText().toString();
            //double lat1 = Double.parseDouble(latVal);
            //double lon1 = Double.parseDouble(lonVal);

            /*
            Intent i = new Intent(DataInput.this, CustomAdapter.class);
            Bundle extras = new Bundle();
            extras.putString("latitudeValue", latVal);
            extras.putString("longitudeValue", lonVal);
            extras.putDouble("latDouble", lat1);
            extras.putDouble("lonDouble", lon1);
            i.putExtras(extras);

            */




            //codes for the map display (so this will be linked with the onclicklistener in info display activity

            /*
            if (latVal != null && lonVal != null) {
                latitude = lat1;
                longitude = lon1;
                Toast.makeText(getApplicationContext(), "The shop is at - \nLat: " + latitude + "\nLong: " + longitude,
                        Toast.LENGTH_LONG).show();

            }


            if (latVal != null && lonVal != null) {

                Intent intent = new Intent(DataInput.this, ShoppingMap.class);
                intent.putExtra("LATITUDE", latitude);
                intent.putExtra("LONGITUDE", longitude);
                intent.putExtra("NAME", shopName.getText().toString());
                //startActivity(intent);

                Toast.makeText(getApplicationContext(), "Map not shown", Toast.LENGTH_LONG).show();

            }

             */



            //to read selection of category of item from radiogroup widget
            switch (itemCategory.getCheckedRadioButtonId()) {
                case R.id.fruit_veg:
                    categoryItem = "Fruits Vegetables";
                    break;
                case R.id.dairy:
                    categoryItem = "Dairy";
                    break;
                case R.id.meat_fish:
                    categoryItem = "Meat Fish";
                    break;
                case R.id.spices:
                    categoryItem = "Spices Condiments";
                    break;
                case R.id.frozen:
                    categoryItem = "Frozen";
                    break;
                case R.id.snacks:
                    categoryItem = "Snacks";
                    break;
                case R.id.beverages:
                    categoryItem = "Beverages";
                    break;
                case R.id.house_item:
                    categoryItem = "Household Items";
                    break;
                case R.id.others:
                    categoryItem = "Others";
                    break;

            }

            String combineStr = shop + "\n" + quantity + "\n" + categoryItem + "\n" + nameItem;
            Toast.makeText(v.getContext(), combineStr, Toast.LENGTH_LONG).show();

            helper.insert(categoryItem, itemName.getText().toString(), itemQuantity.getText().toString(), shopName.getText().toString(), lat_val.getText().toString(),
                    lon_val.getText().toString());


            //Toast.makeText(getApplicationContext(), "Item added to database", Toast.LENGTH_LONG).show();
            /*
            model = helper.getAll();
            adapter.swapCursor(model);


             */
        }

    };






}