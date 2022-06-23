package sp.com;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList rowID, categoryOfItem, nameOfItem, quantityOfItem, nameOfShop, valueOfLatitude, valueOfLongitude;

    int pos;

    CustomAdapter(Activity activity, Context context, ArrayList rowID, ArrayList categoryOfItem, ArrayList nameOfItem, ArrayList quantityOfItem,
                  ArrayList nameOfShop, ArrayList valueOfLatitude, ArrayList valueOfLongitude) {

        this.context = context;
        this.activity = activity;
        this.rowID = rowID;
        this.categoryOfItem = categoryOfItem;
        this.nameOfItem = nameOfItem;
        this.quantityOfItem = quantityOfItem;
        this.nameOfShop = nameOfShop;
        this.valueOfLatitude = valueOfLatitude;
        this.valueOfLongitude = valueOfLongitude;

    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, final int position) {

        holder.txt_rowId.setText(String.valueOf(rowID.get(position)));
        holder.txt_categoryOfItem.setText(String.valueOf(categoryOfItem.get(position)));
        holder.txt_nameItem.setText(String.valueOf(nameOfItem.get(position)));
        holder.txt_itemQuantity.setText(String.valueOf(quantityOfItem.get(position)));
        holder.txt_nameShop.setText(String.valueOf(nameOfShop.get(position)));
        holder.txt_latitude.setText(String.valueOf(valueOfLatitude.get(position)));
        holder.txt_longitude.setText(String.valueOf(valueOfLongitude.get(position)));


        double latitude1 = Double.parseDouble(String.valueOf(valueOfLatitude.get(position)));
        double longitude1 = Double.parseDouble(String.valueOf(valueOfLongitude.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i1 = new Intent(context, ShoppingMap.class);
                i1.putExtra("LATITUDE", latitude1);
                i1.putExtra("LONGITUDE",longitude1);
                context.startActivity(i1);

            }
        });

        this.pos = pos;
        holder.mainLayout.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Intent i2 = new Intent(context, UpdateInput.class);
                i2.putExtra("id", String.valueOf(rowID.get(pos)));
                //intent.putExtra("categoryItem", String.valueOf(categoryOfItem.get(position)));
                i2.putExtra("nameItem", String.valueOf(nameOfItem.get(pos)));
                i2.putExtra("quantityItem", String.valueOf(quantityOfItem.get(pos)));
                i2.putExtra("nameshop", String.valueOf(nameOfShop.get(pos)));
                i2.putExtra("vallat", String.valueOf(valueOfLatitude.get(pos)));
                i2.putExtra("vallon", String.valueOf(valueOfLongitude.get(pos)));
                activity.startActivityForResult(i2, 1);
                return true;
            }
        });






    }

    @Override
    public int getItemCount() {

        return rowID.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_rowId, txt_categoryOfItem, txt_nameItem, txt_itemQuantity, txt_nameShop, txt_latitude, txt_longitude;
        ImageView imgView;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            txt_rowId = itemView.findViewById(R.id.rowId);
            txt_categoryOfItem = itemView.findViewById(R.id.catOfItem);
            txt_nameItem = itemView.findViewById(R.id.itName);
            txt_itemQuantity = itemView.findViewById(R.id.itemQuantity);
            txt_nameShop = itemView.findViewById(R.id.name_Shop);
            txt_latitude = itemView.findViewById(R.id.latitude_shop);
            txt_longitude = itemView.findViewById(R.id.longitude_shop);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }//Hi Aunty - i shall lave my mark here - Aunty Varshini was here :))
    

}
