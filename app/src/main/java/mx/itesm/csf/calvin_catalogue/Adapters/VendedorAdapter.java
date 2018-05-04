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
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFlat;

import java.util.List;


import mx.itesm.csf.calvin_catalogue.Login;
import mx.itesm.csf.calvin_catalogue.Models.VendedorModel;
import mx.itesm.csf.calvin_catalogue.R;
import mx.itesm.csf.calvin_catalogue.VendedorActivity;

/**
 * Created by rodo on 03/05/2018.
 */

// RecyclerView needs to specify an adapter and a Design Admin
// Extend the Class RecyclerView.Adapter.
public class VendedorAdapter extends RecyclerView.Adapter<VendedorAdapter.VendedorContainer>{

    // List to add every JSON element
        private List<VendedorModel> vendedorElements;
    // Context
        private Context context;

    /* Adapter to send Context and Element list */
    public VendedorAdapter (Context context, List<VendedorModel> elements)
    {
        this.vendedorElements = elements;
        this.context = context;
    }

    /* Creating the ViewHolder with an element (not personalized yet) */
    @NonNull
    @Override
    public VendedorContainer onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        /* Get the Layout */
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View layout = layoutInflater.inflate(R.layout.vendedor_list_layout, parent,false);
        /* Create a DataContainer Instance with the layout*/
            return new VendedorContainer(layout);
    }


    /* Personalizing the ViewHolder */
    @Override
    public void onBindViewHolder(@NonNull final VendedorContainer holder, int position)
    {
        /* Vendedor Model instance based on position */
            VendedorModel vendedorModel  = vendedorElements.get(position);

        /* Get the name from the model and set the name in the holder*/
            holder.venta_id.setText(vendedorModel.getVenta_id());
            holder.product_name.setText(vendedorModel.getProduct_name());
            holder.client_name.setText(vendedorModel.getClient_name());
            holder.product_specs.setText(vendedorModel.getProduct_specs());
            holder.product_price.setText(vendedorModel.getProduct_price());

            holder.vendedorModel = vendedorModel;

                                                                                                    /* *    *   *   *   *   *   *   *   *   *   *   */
                                                                                                    /*         Database change to delivered */
            holder.btn_entregado.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Éste elemento será eliminado " + holder.product_name.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* Get the size of the List */
    @Override
    public int getItemCount() {
        return vendedorElements.size();
    }

    /* Data Container class */
    class VendedorContainer extends RecyclerView.ViewHolder
    {
        TextView venta_id;
        TextView product_name;
        TextView client_name;
        TextView product_specs;
        TextView product_price;

        ButtonFlat btn_entregado;

        VendedorModel vendedorModel;

        // Define the View
        public  VendedorContainer (View itemView)
        {
            super(itemView);

            // Find the view Components
            venta_id       =  itemView.findViewById(R.id.id_venta);
            product_name   =  itemView.findViewById(R.id.product_name);
            client_name    =  itemView.findViewById(R.id.client_name);
            product_specs  =  itemView.findViewById(R.id.product_specs);
            product_price  =  itemView.findViewById(R.id.product_price);
            btn_entregado  =  itemView.findViewById(R.id.btn_entregado);

        }

    }
}

