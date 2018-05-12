package mx.itesm.csf.calvin_catalogue.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

//import mx.itesm.csf.calvin_catalogue.InsertData;
import mx.itesm.csf.calvin_catalogue.Models.CatalogModel;
import mx.itesm.csf.calvin_catalogue.ProductActivity;
import mx.itesm.csf.calvin_catalogue.R;

/**
 * Created by rodo on 30/04/2018.
 */

// RecyclerView needs to specify an adapter and a Design Admin
// Extend the Class RecyclerView.Adapter.
public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.DataContainer>{

    // List to add every JSON element
        private List<CatalogModel> catalogElements;
    // Context
         private Context context;

    /* Adapter to send Context and Element list */
        public CatalogAdapter (Context context, List<CatalogModel> elements)
        {
            this.catalogElements = elements;
            this.context = context;
        }

    /* Creating the ViewHolder with an element (not personalized yet) */
        @NonNull
        @Override
        public DataContainer onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            /* Get the Layout */
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                View layout = layoutInflater.inflate(R.layout.list_layout, parent,false);
            /* Create a DataContainer Instance with the layout*/

            return new DataContainer(layout);
        }

    /* Personalizing the ViewHolder */
        @Override
        public void onBindViewHolder(@NonNull DataContainer holder, int position)
        {

            /* Catalog Model instance based on position */
                CatalogModel catalogModel  = catalogElements.get(position);
            /* Get the name from the model and set the name in the holder*/
                holder.Name.setText(catalogModel.getName());
                //holder.Image.setImageBitmap(catalogModel.getImage());
                Picasso.get().load(catalogModel.getImageN()).into(holder.Image);
                holder.Price.setText(catalogModel.getPrice());


            holder.catalogModel = catalogModel;
        }

    /* Get the size of the List */
        @Override
        public int getItemCount() {
        return catalogElements.size();
    }

    /* Data Container class */
    class DataContainer extends RecyclerView.ViewHolder
    {

        TextView Name;                                                                              /* ****CAMBIARRRRR**** */
        TextView Price;
        ImageView Image;

        CatalogModel catalogModel;

        // Define the View
            public  DataContainer (final View itemView)
            {
                super(itemView);

                // Find the view Components
                    Name         =  itemView.findViewById(R.id.tv_name);
                    Image        =  itemView.findViewById(R.id.product_image);
                    Price        =  itemView.findViewById(R.id.product_price);
                    //Product_id   =  itemView.findViewById(R.id.product_id);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        Intent intent = new Intent(context, ProductActivity.class);
                        intent.putExtra("Name", Name.getText().toString());
                        itemView.getContext().startActivity(intent);
                    }
                });

            }

    }
}

