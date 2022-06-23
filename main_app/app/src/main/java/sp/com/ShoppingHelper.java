package sp.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
//import android.util.Log;

public class ShoppingHelper extends SQLiteOpenHelper {

    //database name = shoppinglist.db
    //Table's name = shopping_table
    private static final String DATABASE_NAME = "shoppinglist.db"; //name of database
    private static final int SCHEMA_VERSION = 1;
    private Context context;

    public ShoppingHelper(Context context) {

        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //will be called once when the database is not created
        db.execSQL("CREATE TABLE shopping_table (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " categoryItem TEXT, itemName TEXT, itemQuantity TEXT, shopName TEXT, lat_val TEXT, lon_val TEXT);");

        //List<ShoppingList> shoppingList = new ArrayList<>();

        //how to add radio button and image into database?
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "shopping_table");
        onCreate(db);
    }


    public Cursor getAllList() {
        /*
        return (getReadableDatabase().rawQuery(
                "SELECT _id, itemName, itemQuantity, lat_val," +
                        "lon_val FROM shopping_table ORDER by shopName", null));
         */

        String query = "SELECT * FROM " + "shopping_table";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);

        }
        return cursor;

    }



    void updateData(String row_id, String itemName, String itemQuantity,
                    String shopName, String lat_val, String lon_val) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        //cv.put("categoryItem", categoryItem);
        cv.put("itemName", itemName);
        cv.put("itemQuantity", itemQuantity);
        cv.put("shopName", shopName);
        cv.put("lat_val", lat_val);
        cv.put("lon_val", lon_val);

        long result = db.update("shopping_table", cv, "_id=?", new String[]{row_id});

        if (result == -1 ) {
            Toast.makeText(context, "failed to update", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Success to update", Toast.LENGTH_LONG).show();
        }
        }





    public void insert(String categoryItem, String itemName, String itemQuantity,
                       String shopName, String lat_val, String lon_val) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("categoryItem", categoryItem);
        cv.put("itemName", itemName);
        cv.put("itemQuantity", itemQuantity);
        cv.put("shopName", shopName);
        cv.put("lat_val", lat_val);
        cv.put("lon_val", lon_val);
        long result = db.insert("shopping_table", null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add item to list.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Successfully added item to list.", Toast.LENGTH_LONG).show();
        }

        //long result = getWritableDatabase().insert("shopping_table", "shopName", cv);


    }


    void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("shopping_table", "_id=?", new String[]{row_id});
        if (result == - 1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_LONG).show();
        }

    }



}

/*
    public String getitemName(Cursor c) {
        return (c.getString(1));
    }


    public String getitemQuantity(Cursor c) {
        return (c.getString(2));
    }


    public String getshopName(Cursor c) {
        return (c.getString(3));
    }

    public String getlat_val(Cursor c) {
        return (c.getString(4));
    }

    public String getlon_val(Cursor c) { return (c.getString(5));}



 */
