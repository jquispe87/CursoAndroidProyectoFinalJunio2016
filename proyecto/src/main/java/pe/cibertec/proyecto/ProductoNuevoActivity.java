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
public class ProductoNuevoActivity extends AppCompatActivity {

    private TextInputLayout tilPNANombre, tilPNADescripcion, tilPNAPrecioUnitario;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.producto_nuevo_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cliente_nuevo_activity);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setSubtitle("PRODUCTO NUEVO");

        tilPNANombre = (TextInputLayout) findViewById(R.id.tilPNANombre);
        tilPNADescripcion = (TextInputLayout) findViewById(R.id.tilPNADescripcion);
        tilPNAPrecioUnitario = (TextInputLayout) findViewById(R.id.tilPNAPrecioUnitario);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.producto_nuevo_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Capturo el click de la flecha hacia atrás
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else  //Capturo el click del guardar
            if (item.getItemId() == R.id.producto_nuevo_menu_grabar) {
                save();
                return true;
            }

        return super.onOptionsItemSelected(item);
    }

    private void save() {
        if (tilPNANombre.getEditText().getText().toString().trim().isEmpty())
            showMessage("Ingrese el nombre");
        else if (tilPNADescripcion.getEditText().getText().toString().trim().isEmpty())
            showMessage("Ingrese la descripción");
        else if (tilPNAPrecioUnitario.getEditText().getText().toString().trim().isEmpty())
            showMessage("Ingrese el precio unitario");
        else {
            Producto producto = new Producto();
            producto.setNombre(tilPNANombre.getEditText().getText().toString().trim());
            producto.setDescripcion(tilPNADescripcion.getEditText().getText().toString().trim());
            producto.setPrecioUnitario(Double.parseDouble(tilPNAPrecioUnitario.getEditText().getText().toString().trim()));

            boolean isInserted = new ProductoDAO().insertProducto(producto);
            if (isInserted) {
                Toast.makeText(ProductoNuevoActivity.this, producto.getNombre() + " " + " ha sido registrado", Toast.LENGTH_LONG).show();
                finish();
            } else {
                new AlertDialog.Builder(ProductoNuevoActivity.this).setTitle(R.string.app_name).setMessage("No se pudo regristrar en la base de datos").setNegativeButton("Aceptar", null).show();
            }

            Intent intent = new Intent();
            intent.putExtra("producto", producto);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private void showMessage(String message) {
        new AlertDialog.Builder(ProductoNuevoActivity.this).setTitle(R.string.app_name).setMessage(message).setPositiveButton("Aceptar", null).show();
    }
}
