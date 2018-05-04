package mx.itesm.csf.calvin_catalogue.GerenteFragments;

/**
 * Created by rodo on 04/05/2018.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mx.itesm.csf.calvin_catalogue.Adapters.VendedorAdapter;
import mx.itesm.csf.calvin_catalogue.Controllers.Controller;
import mx.itesm.csf.calvin_catalogue.Controllers.Services;
import mx.itesm.csf.calvin_catalogue.Models.VendedorModel;
import mx.itesm.csf.calvin_catalogue.R;
import mx.itesm.csf.calvin_catalogue.VendedorActivity;

public class ReporteVentas extends Fragment {

    RecyclerView recView;
    RecyclerView.Adapter recAdapter;
    RecyclerView.LayoutManager recLayoutManager;
    List<VendedorModel> vendedorElements;

    ProgressDialog progressBar;

    public static ReporteVentas newInstance() {
        return new ReporteVentas();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_vendedor, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Get the elements from the CardView and the list
        recView =  getView().findViewById(R.id.recyclerview_vendedor);
        //botonInsertar = (Button) findViewById(R.id.botonInsertar);
        //botonBorrar = (Button) findViewById(R.id.botonBorrar);
        progressBar = new ProgressDialog(getContext());
        vendedorElements = new ArrayList<>();

        getJSON();

        // CardView components
        recLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recView.setLayoutManager(recLayoutManager);
        recAdapter = new VendedorAdapter(getContext(), vendedorElements);
        recView.setAdapter(recAdapter);

    }


    private void getJSON()
    {
        // Show the progress Bar
        progressBar.setMessage("Cargando datos...");
        progressBar.setCancelable(false);
        progressBar.show();

        /**********************************************************************************/

        /**********************************************************************************/
        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.PUT, Services.AUTOS,null, /* CAMBIAR AL NUESTRO*/
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressBar.cancel();

                            /* DEBUG purposes */
                        Log.d("JSON","INFO: " + response.toString());

                        for(int i = 0 ; i < response.length(); i++)
                        {
                            try
                            {
                                JSONObject data = response.getJSONObject(i);

                                VendedorModel vendedormodel = new VendedorModel();

                                // Get the data from the JSON
                                vendedormodel.setVenta_id(data.getString("Clave_auto"));  /* CAMBIAR A PRODUCTOS */
                                vendedormodel.setProduct_name(data.getString("Nombre"));
                                vendedormodel.setClient_name(data.getString("Clave_marca"));
                                vendedormodel.setProduct_specs(data.getString("imagen"));
                                vendedormodel.setProduct_price(data.getString("Precio"));

                                // Add the data to the List
                                vendedorElements.add(vendedormodel);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        recAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.cancel();
                        Log.d("VOLLEY", "Error : " + error.getMessage());
                    }
                });

        Controller.getInstance().addToRequestQueue(reqData);
    }
}
