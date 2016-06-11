package pe.cibertec.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import pe.cibertec.proyecto.entities.Producto;

/**
 * Created by JOSE on 10/06/2016.
 */
public class ProductoConsultaActivity extends AppCompatActivity {
    public final static String ARG_PRODUCTO = "arg_producto";

    private Producto mProducto;

    private TextView tvPCANombre, tvPCADescripcion, tvPCAPrecioUnitario;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.producto_consulta_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_producto_consulta_activity);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setSubtitle("PRODUCTO DETALLE");

        mProducto = getIntent().getParcelableExtra(ARG_PRODUCTO);

        tvPCANombre = (TextView) findViewById(R.id.tvPCANombre);
        tvPCADescripcion = (TextView) findViewById(R.id.tvPCADescripcion);
        tvPCAPrecioUnitario = (TextView) findViewById(R.id.tvPCAPrecioUnitario);

        setData();
    }

    private void setData() {
        tvPCANombre.setText(mProducto.getNombre());
        tvPCADescripcion.setText(mProducto.getDescripcion());
        tvPCAPrecioUnitario.setText(mProducto.getPrecioUnitario().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.producto_consulta_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        if (item.getItemId() == R.id.producto_consulta_menu_editar) {
            Intent intent = new Intent(ProductoConsultaActivity.this, ProductoEditarActivity.class);
            intent.putExtra(ProductoEditarActivity.ARG_PRODUCTO, mProducto);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
