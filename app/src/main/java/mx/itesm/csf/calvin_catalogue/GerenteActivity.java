package mx.itesm.csf.calvin_catalogue;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import mx.itesm.csf.calvin_catalogue.GerenteFragments.ReporteVentas;

public class GerenteActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Fragment selectedFragment = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_ventas:
                    mTextMessage.setText("Reporte de Ventas");
                    selectedFragment = ReporteVentas.newInstance();
                    return true;
                case R.id.navigation_vendedor:
                    mTextMessage.setText("Reporte Vendedores");
                    return true;
                case R.id.navigation_agregar:
                    mTextMessage.setText("Agregar un Vendedor");
                    return true;
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

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, ReporteVentas.newInstance());
        transaction.commit();
    }

}
