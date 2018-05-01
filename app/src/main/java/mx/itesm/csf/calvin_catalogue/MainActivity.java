package mx.itesm.csf.calvin_catalogue;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import mx.itesm.csf.calvin_catalogue.Adapters.GridAdapter;

public class MainActivity extends AppCompatActivity
{

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            /* Hide ActionBar*/
                ActionBar m_myActionBar = getSupportActionBar();
                m_myActionBar.hide();
        }
        catch (NullPointerException npe){
            Log.e("ACTION BAR", "ERROR: "+ npe);
        }

        setTheme(android.R.style.ThemeOverlay_Material_Dark);

        /* Get the GridView*/
            GridView gridview = (GridView) findViewById(R.id.gridView);

        /* Initialize GridAdapter object*/
            final GridAdapter gridadapter = new GridAdapter(this);
            gridview.setAdapter(gridadapter);

        /* Set on click Listener */
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id)
                {
                    int image_pos = (int) gridadapter.getItem(position);

                    Log.d("POS", "THIS IS "+image_pos);

                    /* Create the activity intent*/
                        Intent i = new Intent(MainActivity.this, CatalogActivity.class);
                        String extra = "";

                    /* Get the element on which the data will be filtered */
                        if(image_pos == R.drawable.c_modelo)
                            extra = "Modelo";

                        if(image_pos == R.drawable.c_genero)
                           extra = "Genero";

                        if(image_pos == R.drawable.c_color)
                            extra = "Color";

                        if(image_pos == R.drawable.c_edad)
                            extra = "Edad";

                    /* Set de thata to the Intent*/
                        i.putExtra("FILTER", extra);

                    /* Go to the New Activity */
                        startActivity(i);
                }
            });
    }
}
