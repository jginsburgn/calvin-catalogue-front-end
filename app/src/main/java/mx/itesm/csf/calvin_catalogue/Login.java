package mx.itesm.csf.calvin_catalogue;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.gc.materialdesign.views.ButtonRectangle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mx.itesm.csf.calvin_catalogue.Controllers.Services;

public class Login extends AppCompatActivity {

    public static final String USUARIO = "usuario";
    public static final String PASSWORD = "password";

    private final String TAG = "LOGIN";
    private EditText editTextUsuario;
    private EditText editTextPassword;

    private ButtonRectangle btn_login;

    private Usuario userData;

    private String usuario;
    private String password;

    private static String LOGIN_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsuario  = (EditText) findViewById(R.id.lg_usuario);
        editTextPassword = (EditText) findViewById(R.id.lg_pass);

        btn_login = findViewById(R.id.lg_btn);
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                usuarioLogin();
            }
        });
    }

    private void usuarioLogin()
    {
        /* Think */
            final ProgressDialog progressBar = new ProgressDialog(Login.this);
            progressBar.setMessage("Iniciando sesion...");
            progressBar.show();

        /* Get User and Password*/
            usuario = editTextUsuario.getText().toString().trim();
            password = editTextPassword.getText().toString().trim();

        /* Get the correct login url for the service*/
            LOGIN_URL = Services.LOGIN + USUARIO + "=" + usuario + "&" + PASSWORD + "=" + password;
            Log.d(TAG, LOGIN_URL);

        /* Make Request */
            JsonArrayRequest volley_request = new JsonArrayRequest(LOGIN_URL, new Response.Listener<JSONArray>()
            {
                /* Had Response */

                @Override
                public void onResponse(JSONArray response)
                {
                    /* Hide Progress bar */
                        progressBar.hide();

                        try
                        {
                            JSONObject autenticacion = (JSONObject) response.get(0);
                            String codigo_autenticacion = autenticacion.getString("Codigo");
                            Log.d(TAG, "Response: " + response.toString());

                            if (codigo_autenticacion.equals("01"))
                            {
                                JSONObject username = (JSONObject) response.get(2);

                                /* Revisar Base de Datos */
                                    userData.getInstance().setNombre(username.getString("Nombre"));
                                    userData.getInstance().setAppaterno(username.getString("Appaterno"));
                                    userData.getInstance().setApmaterno(username.getString("Apmaterno"));
                                    userData.getInstance().setusuario(usuario);
                                    userData.getInstance().setPassword(password);
      /********CAMBIAR recibir por DB*/              userData.getInstance().setRol("comprador");                               /* Código para conocer el tipo de usuario */

                                String rol = userData.getInstance().getRol();

                                /* User Accepted Message */
                                    Toast.makeText(Login.this,
                                            "Bienvenido " + username.getString("Nombre"), Toast.LENGTH_LONG).show();

                                    Intent intent;
                                /* Once Logged in, go to the Next Activity */
                                /*  *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   */
                                /*          ADAPT                                               */
                                /*  *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   */
                                    switch (rol) {
                                        case "ceo":
                                            intent = new Intent(getBaseContext(), GerenteActivity.class);
                                            intent.putExtra("puesto","ceo");
                                            break;
                                        case "gerente":
                                            intent = new Intent(getBaseContext(), GerenteActivity.class);
                                            intent.putExtra("puesto","gerente");
                                            break;
                                        case "vendedor":
                                            intent = new Intent(getBaseContext(), VendedorActivity.class);
                                            break;
                                        default:
                                            intent = new Intent(getBaseContext(), MainActivity.class);
                                            break;
                                    }

                                    startActivity(intent);
                            }
                            else
                                if (codigo_autenticacion.equals("04"))
                                {
                                    Toast.makeText(Login.this, "Credenciales incorrectas", Toast.LENGTH_LONG).show();
                                    Log.d(TAG,"Usuario o password incorrecto");
                                }
                        }
                        /* Error con el JSON */
                        catch (JSONException e)
                        {
                            Toast.makeText(Login.this, "Problema en: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                }
            /* Error in the Response */
            }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressBar.hide();
                        Toast.makeText(Login.this, "Error en la conexión", Toast.LENGTH_LONG).show();
                        Log.e(TAG, "Error en: " + error.toString());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError
                    {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put(USUARIO, usuario);
                        map.put(PASSWORD, password);
                        return map;
                    }
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError
                    {
                        Map<String, String> headers = new HashMap<>();
                        String credentiales = usuario + ":" + password;
                        String autenticacion = "Basic " + Base64.encodeToString(credentiales.getBytes(), Base64.NO_WRAP);
                        headers.put("Content-Type", "application/json");
                        headers.put("Authorization", autenticacion);
                        return headers;
                    }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(volley_request);
    }

    public void onClick(View v) {
        usuarioLogin();
    }
}

