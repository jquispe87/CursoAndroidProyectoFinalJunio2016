package pe.cibertec.proyecto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import pe.cibertec.proyecto.entities.Cliente;

/**
 * Created by JOSE on 03/06/2016.
 */
public class ClienteMapaActivity extends AppCompatActivity implements OnMapReadyCallback {
    public final static String ARG_CLIENTE = "arg_cliente";

    public GoogleMap mGoogleMap;
    private Cliente mCliente;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_mapa_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cliente_mapa_activity);
        setSupportActionBar(toolbar);

        mCliente = getIntent().getParcelableExtra(ARG_CLIENTE);
        toolbar.setSubtitle(mCliente.getEmpresa());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ((MapFragment) getFragmentManager().findFragmentById(R.id.fragFirstMap)).getMapAsync(ClienteMapaActivity.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(mCliente.getLatitud(), mCliente.getLongitud())));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
