package pe.cibertec.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import pe.cibertec.proyecto.dao.ProductoDAO;
import pe.cibertec.proyecto.entities.Producto;

/**
 * Created by JOSE on 10/06/2016.
 */
public class ProductoEditarActivity extends AppCompatActivity {
    public final static String ARG_PRODUCTO = "arg_producto";

    private TextInputLayout tilPEANombre, tilPEADescripcion, tilPEAPrecioUnitario;
    private Producto mProducto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.producto_editar_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_producto_editar_activity);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setSubtitle("PRODUCTO DETALLE");

        mProducto = getIntent().getParcelableExtra(ARG_PRODUCTO);

        tilPEANombre = (TextInputLayout) findViewById(R.id.tilPEANombre);
        tilPEADescripcion = (TextInputLayout) findViewById(R.id.tilPEADescripcion);
        tilPEAPrecioUnitario = (TextInputLayout) findViewById(R.id.tilPEAPrecioUnitario);

        setData();
    }

    private void setData() {
        tilPEANombre.getEditText().setText(mProducto.getNombre());
        tilPEADescripcion.getEditText().setText(mProducto.getDescripcion());
        tilPEAPrecioUnitario.getEditText().setText(mProducto.getPrecioUnitario().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.producto_editar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.producto_editar_menu_grabar) {
            boolean isOK = true;

            tilPEANombre.setError(null);
            tilPEADescripcion.setError(null);
            tilPEAPrecioUnitario.setError(null);

            tilPEANombre.setErrorEnabled(false);
            tilPEADescripcion.setErrorEnabled(false);
            tilPEAPrecioUnitario.setErrorEnabled(false);

            if (tilPEANombre.getEditText().getText().toString().trim().isEmpty()) {
                tilPEANombre.setError("Ingrese el nombre");
                tilPEANombre.setErrorEnabled(true);
                isOK = false;
            }

            if (tilPEADescripcion.getEditText().getText().toString().trim().isEmpty()) {
                tilPEADescripcion.setError("Ingrese la descripción");
                tilPEADescripcion.setErrorEnabled(true);
                isOK = false;
            }

            if (tilPEAPrecioUnitario.getEditText().getText().toString().trim().isEmpty()) {
                tilPEAPrecioUnitario.setError("Ingrese el precio unitario");
                tilPEAPrecioUnitario.setErrorEnabled(true);
                isOK = false;
            }

            if (isOK) {
                mProducto.setNombre(tilPEANombre.getEditText().getText().toString().trim());
                mProducto.setDescripcion(tilPEADescripcion.getEditText().getText().toString().trim());
                mProducto.setPrecioUnitario(Double.parseDouble(tilPEAPrecioUnitario.getEditText().getText().toString().trim()));

                boolean isUpdated = new ProductoDAO().updateProducto(mProducto);
                if (isUpdated) {
                    Toast.makeText(ProductoEditarActivity.this, mProducto.getNombre() + " " + " ha sido actualizdo", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    new AlertDialog.Builder(ProductoEditarActivity.this).setTitle(R.string.app_name).setMessage("No se pudo actualizar en la base de datos").setNegativeButton("Aceptar", null).show();
                }

            }

            Intent intent = new Intent(ProductoEditarActivity.this, PrincipalProductoActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    };
}
