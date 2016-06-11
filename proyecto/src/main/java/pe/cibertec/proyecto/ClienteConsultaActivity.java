package pe.cibertec.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pe.cibertec.proyecto.entities.Cliente;

/**
 * Created by JOSE on 03/06/2016.
 */
public class ClienteConsultaActivity extends AppCompatActivity {
    public final static String ARG_CLIENTE = "arg_cliente";

    private Cliente mCliente;
    private ImageView ivPlace;
    private TextView tvCCANombre, tvCCAApellido, tvCCATelefono, tvCCACorreo, tvCCADireccion;
    private TextView tvCCADistrito, tvCCAReferencia, tvCCAidCliente;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_consulta_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cliente_consulta_activity);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCliente = getIntent().getParcelableExtra(ARG_CLIENTE);
        toolbar.setSubtitle(mCliente.getEmpresa());

        tvCCANombre = (TextView) findViewById(R.id.tvCCANombre);
        tvCCAApellido = (TextView) findViewById(R.id.tvCCAApellido);
        tvCCATelefono = (TextView) findViewById(R.id.tvCCATelefono);
        tvCCACorreo = (TextView) findViewById(R.id.tvCCACorreo);
        tvCCADireccion = (TextView) findViewById(R.id.tvCCADireccion);
        tvCCADistrito = (TextView) findViewById(R.id.tvCCADistrito);
        tvCCAReferencia = (TextView) findViewById(R.id.tvCCAReferencia);

        ivPlace = (ImageView) findViewById(R.id.ivCCAFono);
        ivPlace.setOnClickListener(ivPlaceOnClickListener);

        setData();
    }

    View.OnClickListener ivPlaceOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            IngresarMapa();
        }
    };

    private void IngresarMapa() {
        Intent intent = new Intent(ClienteConsultaActivity.this, ClienteMapaActivity.class);
        intent.putExtra(ClienteEditarActivity.ARG_CLIENTE, mCliente);
        startActivity(intent);
        finish();
    }

    private void setData() {
        tvCCANombre.setText(mCliente.getNombre());
        tvCCAApellido.setText(mCliente.getApellido());
        tvCCATelefono.setText(mCliente.getTelefono());
        tvCCACorreo.setText(mCliente.getCorreo());
        tvCCADireccion.setText(mCliente.getDireccion());
        tvCCADistrito.setText(mCliente.getDistrito());
        tvCCAReferencia.setText(mCliente.getReferencia());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cliente_consulta_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.cliente_consulta_menu_editar) {
            Intent intent = new Intent(ClienteConsultaActivity.this, ClienteEditarActivity.class);
            intent.putExtra(ClienteEditarActivity.ARG_CLIENTE, mCliente);
            startActivity(intent);
        }

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
