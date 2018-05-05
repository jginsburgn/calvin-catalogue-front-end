package mx.itesm.csf.calvin_catalogue;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import mx.itesm.csf.calvin_catalogue.GerenteFragments.ReporteVendedor;
import mx.itesm.csf.calvin_catalogue.GerenteFragments.ReporteVentas;

public class GerenteActivity extends AppCompatActivity {

    Fragment selectedFragment = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                /* Gerente quiere visualizar el Reporte de Ventas */
                    case R.id.navigation_ventas:
                        selectedFragment = ReporteVentas.newInstance();
                        break;
                /* Gerente quiere visualizar el Reporte de Vendedores */
                    case R.id.navigation_vendedor:
                        selectedFragment = ReporteVendedor.newInstance();
                        break;
                /* Gerente quiere agregar un nuevo Vendedor */
                    case R.id.navigation_agregar:
                        break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, selectedFragment);
            transaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerente);

        /* Obtener el Bottom navigation View y agregar listener */
            BottomNavigationView navigation =  findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        /* Mostrar el primer fragment al ingresar a la actividad */
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, ReporteVentas.newInstance());
            transaction.commit();
    }

}
