package mx.itesm.csf.calvin_catalogue;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLogTags;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.gc.materialdesign.views.ButtonRectangle;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.jar.Attributes;

import mx.itesm.csf.calvin_catalogue.Controllers.Controller;
import mx.itesm.csf.calvin_catalogue.Controllers.Services;
import mx.itesm.csf.calvin_catalogue.Models.CatalogModel;

import static mx.itesm.csf.calvin_catalogue.Controllers.Services.IMAGES_URL;

public class ProductActivity extends AppCompatActivity {

    ProgressDialog progressBar;
    TextView Name;
    TextView Price;
    ImageView Image;
    TextView Desc;
    TextView Tienda;
    TextView Ubicacion;
    ButtonRectangle Comprar;

    /****************** Oobtener El producto con base en el nombre *************/
    String consult = "http://ubiquitous.csf.itesm.mx/~raulms/content/TC2024/REST/Minisistema2/servicio.autos.php?" ; /* Agregarlo a servicios (el nuestro)*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Name      = findViewById(R.id.tv_name);
        Price     = findViewById(R.id.tv_price);
        Image     = findViewById(R.id.image);
        Desc      = findViewById(R.id.tv_desc);
        Comprar   = findViewById(R.id.btn_comprar);
        Tienda    = findViewById(R.id.tv_tienda);
        Ubicacion = findViewById(R.id.tv_ubicacion);

        String name = getIntentExtras();

        getJSON();

        Comprar.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                                                                                                    /* ***** REALIZAR UPDATE DB ventas *** */
                Toast.makeText(ProductActivity.this, "Se agregó el objeto a la lista de compras", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public String getIntentExtras()
    {
        Bundle extras = getIntent().getExtras();

        if(extras == null) { return null; }
        else               { return extras.getString("Name"); }
    }

    public void getJSON()
    {
        progressBar = new ProgressDialog(ProductActivity.this);

        // Show the progress Bar
        progressBar.setMessage("Cargando datos...");
        progressBar.setCancelable(false);
        progressBar.show();

        /**********************************************************************************/

        /**********************************************************************************/
        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.GET, consult,null, /* CAMBIAR AL NUESTRO*/
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressBar.cancel();

                        /* DEBUG purposes */
                        Log.d("JSON","INFO: " + response.toString());

                        try {

                           // Get the data from the JSON
                                                                                                      /* CAMBIAR A PRODUCTOS */
                            JSONObject resp = response.getJSONObject(0);
                            Name.setText(resp.getString("Nombre"));
                            Price.setText(resp.getString("Precio"));
                            Desc.setText(resp.getString("Description"));
                            //Image
                            //String product_image = (resp.getString("Image"));                        /* ***** Cambiar para que llame a la imagen del producto***** */
                            String product_image = IMAGES_URL + "Frida";
                            Picasso.get().load(product_image+".jpg").into(Image);

                                                                                                    /* ***  Agregar consultar tiendas y mostrarlas en el grid view (ejemplo puede estar en CatalogActivity)  *****/

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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


