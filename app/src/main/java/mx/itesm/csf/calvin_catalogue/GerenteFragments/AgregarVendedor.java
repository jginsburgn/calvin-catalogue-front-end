package mx.itesm.csf.calvin_catalogue.GerenteFragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gc.materialdesign.views.ButtonRectangle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mx.itesm.csf.calvin_catalogue.Controllers.Controller;
import mx.itesm.csf.calvin_catalogue.R;

import static mx.itesm.csf.calvin_catalogue.Controllers.Services.INSERT;

public class AgregarVendedor extends android.support.v4.app.Fragment {

    private TextView name;
    private TextView store;
    private ButtonRectangle btn_guardar;
    ProgressDialog barra_de_progreso;

    /* Instance to be shown on the Botom Navigation Activity*/
    public static AgregarVendedor newInstance() {
        return new AgregarVendedor();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_agregar_vendedor, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        name = getView().findViewById(R.id.et_name);
        store = getView().findViewById(R.id.et_store);
        btn_guardar = getView().findViewById(R.id.btn_agregar);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar_datos();

            }
        });
    }

    private void guardar_datos()
    {
        barra_de_progreso.setMessage("Guardando datos");
        barra_de_progreso.setCancelable(false);
        barra_de_progreso.show();

        StringRequest enviaDatos = new StringRequest(Request.Method.POST, INSERT, /************/
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        barra_de_progreso.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(getActivity(), "Respuesta : "+   res.getString("Mensaje") , Toast.LENGTH_SHORT).show();
                            Log.d("Parámetros: ", response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        barra_de_progreso.cancel();
                        Toast.makeText(getActivity(), "Respuesta: Error al insertar datos", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("clave",name.getText().toString());                                         /* ************** CAMBIAR ******************** */
                map.put("nombre",store.getText().toString());
                Log.d("Parámetros enviados: ", INSERT + map.toString());

                return map;
            }
        };

        Controller.getInstance().addToRequestQueue(enviaDatos);
    }


}
