package mx.itesm.csf.calvin_catalogue.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFlat;

import java.util.List;

import mx.itesm.csf.calvin_catalogue.Models.VendedorModel;
import mx.itesm.csf.calvin_catalogue.R;

/**
 * Created by rodo on 04/05/2018.
 */


// RecyclerView needs to specify an adapter and a Design Admin
// Extend the Class RecyclerView.Adapter.
public class ReportesVendedorAdapter extends RecyclerView.Adapter<ReportesVendedorAdapter.ReportesVendedorContainer> {

        // List to add every JSON element
        private List<VendedorModel> vendedores;
        // Context
        private Context context;

        /* Adapter to send Context and Element list */
        public ReportesVendedorAdapter(Context context, List<VendedorModel> elements)
        {
            this.vendedores = elements;
            this.context = context;
        }

        /* Creating the ViewHolder with an element (not personalized yet) */
        @NonNull
        @Override
        public ReportesVendedorContainer onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
        /* Get the Layout */
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View layout = layoutInflater.inflate(R.layout.reporte_vendedores_list_layout, parent,false);
        /* Create a DataContainer Instance with the layout*/
            return new ReportesVendedorContainer(layout);
        }


        /* Personalizing the ViewHolder */
        @Override
        public void onBindViewHolder(@NonNull final ReportesVendedorContainer holder, int position)
        {
        /* Vendedor Model instance based on position */
            VendedorModel vendedorModel = vendedores.get(position);

        /* Get the name from the model and set the name in the holder*/
            holder.vendedor_id.setText(vendedorModel.getId());
            holder.vendedor_name.setText(vendedorModel.getName());
            holder.vendedor_store.setText(vendedorModel.getStore());
            holder.vendedor_sales.setText(vendedorModel.getSales_num());

            holder.vm = vendedorModel;

                                                                                                    /* *    *   *   *   *   *   *   *   *   *   *   */
                                                                                                    /*         Database change to delivered */
        }

        /* Get the size of the List */
        @Override
        public int getItemCount() {
            return vendedores.size();
        }

        /* Data Container class */
        class ReportesVendedorContainer extends RecyclerView.ViewHolder
        {
            TextView vendedor_id;
            TextView vendedor_name;
            TextView vendedor_store;
            TextView vendedor_sales;

            VendedorModel vm;

            // Define the View
            public  ReportesVendedorContainer (View itemView)
            {
                super(itemView);

                // Find the view Components
                vendedor_id     =  itemView.findViewById(R.id.v_id);
                vendedor_name   =  itemView.findViewById(R.id.v_name);
                vendedor_store  =  itemView.findViewById(R.id.v_store);
                vendedor_sales  =  itemView.findViewById(R.id.v_sales);
            }

        }
}
