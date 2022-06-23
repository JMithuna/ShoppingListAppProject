package sp.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class InformationDisplay extends AppCompatActivity {

    RecyclerView recyclerView;
    ShoppingHelper myDB;
    ArrayList<String> row_id, categoryOfItem, nameOfItem, quantityOfItem, nameOfShop, valueOfLatitude, valueOfLongitude;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_display);

        recyclerView = findViewById(R.id.recyclerView);


        myDB = new ShoppingHelper(InformationDisplay.this);
        row_id = new ArrayList<>();
        categoryOfItem = new ArrayList<>();
        nameOfItem = new ArrayList<>();
        quantityOfItem = new ArrayList<>();
        nameOfShop = new ArrayList<>();
        valueOfLatitude = new ArrayList<>();
        valueOfLongitude = new ArrayList<>();


        storeListItemsInArray();

        customAdapter = new CustomAdapter(InformationDisplay.this, this, row_id, categoryOfItem, nameOfItem, quantityOfItem, nameOfShop,
                valueOfLatitude, valueOfLongitude);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(InformationDisplay.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            recreate();
        }
        else {
            Toast.makeText(getApplicationContext(), "no update done", Toast.LENGTH_LONG).show();
        }
    }

    void storeListItemsInArray () {
        Cursor cursor = myDB.getAllList();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data in list", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                row_id.add(cursor.getString(0));
                categoryOfItem.add(cursor.getString(1));
                nameOfItem.add(cursor.getString(2));
                quantityOfItem.add(cursor.getString(3));
                nameOfShop.add(cursor.getString(4));
                valueOfLatitude.add(cursor.getString(5));
                valueOfLongitude.add(cursor.getString(6));
            }
        }
    }

}