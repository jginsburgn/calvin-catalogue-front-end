package mx.itesm.csf.calvin_catalogue;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

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
import mx.itesm.csf.calvin_catalogue.Controllers.Controller;
import mx.itesm.csf.calvin_catalogue.Controllers.Services;
import mx.itesm.csf.calvin_catalogue.Models.CatalogModel;

public class CatalogActivity extends AppCompatActivity {

    String filter;

    RecyclerView recView;
    RecyclerView.Adapter recAdapter;
    RecyclerView.LayoutManager recLayoutManager;
    List<CatalogModel> catalogElements;

    //Button botonInsertar, botonBorrar;
    ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Get the elements from the CardView and the list
            recView =  findViewById(R.id.recyclerview);
            //botonInsertar = (Button) findViewById(R.id.botonInsertar);
            //botonBorrar = (Button) findViewById(R.id.botonBorrar);
            progressBar = new ProgressDialog(CatalogActivity.this);
            catalogElements = new ArrayList<>();

            Bundle extras = getIntent().getExtras();

        // Get the String from the extra
            try
            {
                // Filter the Data to get the correct one
                if(extras == null)
                    Log.e("FILTER", "NO FILTER FOUND");
                else
                {
                    filter = extras.getString("FILTER");
                    assert filter != null;
                    switch (filter) {
                        case "Modelo":
                            Log.d("FILTER", "Filtered by Model");
                            getJSON();
                            break;
                        case "Color":
                            Log.d("FILTER", "Filtered by Color");
                            getJSON();
                            break;
                        case "Genero":
                            Log.d("FILTER", "Filtered by Genero");
                            getJSON();
                            break;
                        case "Edad":
                            Log.d("FILTER", "Filtered by Edad");
                            getJSON();
                            break;
                    }
                }
            }
            catch(NullPointerException npe)
            {
                Log.e("NPE", "Error: "+ npe);
            }

        // CardView components
            recLayoutManager = new LinearLayoutManager(CatalogActivity.this,LinearLayoutManager.VERTICAL,false);
            recView.setLayoutManager(recLayoutManager);
            recAdapter = new CatalogAdapter(CatalogActivity.this, catalogElements);
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
                                try {
                                    JSONObject data = response.getJSONObject(i);

                                    CatalogModel catalogmodel = new CatalogModel();

                                    // Get the data from the JSON
                                        catalogmodel.setProduct_id(data.getString("Clave_auto"));  /* CAMBIAR A PRODUCTOS */
                                        catalogmodel.setName(data.getString("Nombre"));
                                        catalogmodel.setDesc(data.getString("Clave_marca"));
                                        catalogmodel.setPrice(data.getString("Precio"));
                                        catalogmodel.setImage(data.getString("imagen"));

                                    // Add the data to the List
                                        catalogElements.add(catalogmodel);

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
