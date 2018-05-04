package mx.itesm.csf.calvin_catalogue;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mx.itesm.csf.calvin_catalogue.Adapters.CatalogAdapter;
import mx.itesm.csf.calvin_catalogue.Adapters.VendedorAdapter;
import mx.itesm.csf.calvin_catalogue.Controllers.Controller;
import mx.itesm.csf.calvin_catalogue.Controllers.Services;
import mx.itesm.csf.calvin_catalogue.Models.CatalogModel;
import mx.itesm.csf.calvin_catalogue.Models.VendedorModel;

public class VendedorActivity extends AppCompatActivity {

    RecyclerView recView;
    RecyclerView.Adapter recAdapter;
    RecyclerView.LayoutManager recLayoutManager;
    List<VendedorModel> vendedorElements;

    //Button botonInsertar, botonBorrar;
    ProgressDialog progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendedor);

        // Get the elements from the CardView and the list
        recView =  findViewById(R.id.recyclerview_vendedor);
        //botonInsertar = (Button) findViewById(R.id.botonInsertar);
        //botonBorrar = (Button) findViewById(R.id.botonBorrar);
        progressBar = new ProgressDialog(VendedorActivity.this);
        vendedorElements = new ArrayList<>();

        getJSON();

        // CardView components
        recLayoutManager = new LinearLayoutManager(VendedorActivity.this,LinearLayoutManager.VERTICAL,false);
        recView.setLayoutManager(recLayoutManager);
        recAdapter = new VendedorAdapter(VendedorActivity.this, vendedorElements);
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
