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
import android.widget.TextView;
import android.widget.Toast;

import pe.cibertec.proyecto.dao.ClienteDAO;
import pe.cibertec.proyecto.entities.Cliente;

/**
 * Created by JOSE on 03/06/2016.
 */
public class ClienteEditarActivity extends AppCompatActivity {
    public final static String ARG_CLIENTE = "arg_cliente";

    private TextInputLayout tilNombre, tilApellido, tilTelefono, tilCorreo, tilEmpresa, tilDireccion, tilDistrito, tilReferencia;
    private TextView tilidCliente;
    private Cliente mCliente;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_editar_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cliente_editar_activity);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCliente = getIntent().getParcelableExtra(ARG_CLIENTE);
        toolbar.setSubtitle(mCliente.getEmpresa());

        tilNombre = (TextInputLayout) findViewById(R.id.tilNombre);
        tilApellido = (TextInputLayout) findViewById(R.id.tilApellido);
        tilTelefono = (TextInputLayout) findViewById(R.id.tilTelefono);
        tilCorreo = (TextInputLayout) findViewById(R.id.tilCorreo);
        tilEmpresa = (TextInputLayout) findViewById(R.id.tilEmpresa);
        tilDireccion = (TextInputLayout) findViewById(R.id.tilDireccion);
        tilDistrito = (TextInputLayout) findViewById(R.id.tilDistrito);
        tilReferencia = (TextInputLayout) findViewById(R.id.tilReferencia);

        setData();
    }

    private void setData() {
        tilNombre.getEditText().setText(mCliente.getNombre());
        tilApellido.getEditText().setText(mCliente.getApellido());
        tilTelefono.getEditText().setText(mCliente.getTelefono());
        tilCorreo.getEditText().setText(mCliente.getCorreo());
        tilEmpresa.getEditText().setText(mCliente.getEmpresa());
        tilDireccion.getEditText().setText(mCliente.getDireccion());
        tilDistrito.getEditText().setText(mCliente.getDistrito());
        tilReferencia.getEditText().setText(mCliente.getReferencia());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cliente_editar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.cliente_editar_menu_grabar) {
            boolean isOK = true;

            tilNombre.setError(null);
            tilApellido.setError(null);
            tilTelefono.setError(null);
            tilCorreo.setError(null);
            tilEmpresa.setError(null);
            tilDireccion.setError(null);
            tilDistrito.setError(null);
            tilReferencia.setError(null);

            tilNombre.setErrorEnabled(false);
            tilApellido.setErrorEnabled(false);
            tilTelefono.setErrorEnabled(false);
            tilCorreo.setErrorEnabled(false);
            tilEmpresa.setErrorEnabled(false);
            tilDireccion.setErrorEnabled(false);
            tilDistrito.setErrorEnabled(false);
            tilReferencia.setErrorEnabled(false);

            if (tilNombre.getEditText().getText().toString().trim().isEmpty()) {
                tilNombre.setError("Ingrese su nombre");
                tilNombre.setErrorEnabled(true);
                isOK = false;
            }

            if (tilApellido.getEditText().getText().toString().trim().isEmpty()) {
                tilApellido.setError("Ingrese su apellido");
                tilApellido.setErrorEnabled(true);
                isOK = false;
            }

            if (tilTelefono.getEditText().getText().toString().trim().isEmpty()) {
                tilTelefono.setError("Ingrese su teléfono");
                tilTelefono.setErrorEnabled(true);
                isOK = false;
            }

            if (tilCorreo.getEditText().getText().toString().trim().isEmpty()) {
                tilCorreo.setError("Ingrese su correo");
                tilCorreo.setErrorEnabled(true);
                isOK = false;
            }

            if (tilEmpresa.getEditText().getText().toString().trim().isEmpty()) {
                tilEmpresa.setError("Ingrese el nombre dela empresa");
                tilEmpresa.setErrorEnabled(true);
                isOK = false;
            }

            if (tilDireccion.getEditText().getText().toString().trim().isEmpty()) {
                tilDireccion.setError("Ingrese la dirección");
                tilDireccion.setErrorEnabled(true);
                isOK = false;
            }

            if (tilDistrito.getEditText().getText().toString().trim().isEmpty()) {
                tilDistrito.setError("Ingrese el distrito");
                tilDistrito.setErrorEnabled(true);
                isOK = false;
            }

            if (tilReferencia.getEditText().getText().toString().trim().isEmpty()) {
                tilReferencia.setError("Ingrese la referencia");
                tilReferencia.setErrorEnabled(true);
                isOK = false;
            }

            if (isOK) {
                mCliente.setNombre(tilNombre.getEditText().getText().toString().trim());
                mCliente.setApellido(tilApellido.getEditText().getText().toString().trim());
                mCliente.setTelefono(tilTelefono.getEditText().getText().toString().trim());
                mCliente.setCorreo(tilCorreo.getEditText().getText().toString().trim());
                mCliente.setEmpresa(tilEmpresa.getEditText().getText().toString().trim());
                mCliente.setDireccion(tilDireccion.getEditText().getText().toString().trim());
                mCliente.setDistrito(tilDistrito.getEditText().getText().toString().trim());
                mCliente.setReferencia(tilReferencia.getEditText().getText().toString().trim());

                boolean isUpdated = new ClienteDAO().updateCliente(mCliente);
                if (isUpdated) {
                    Toast.makeText(ClienteEditarActivity.this, mCliente.getEmpresa() + " " + " ha sido actualizdo", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    new AlertDialog.Builder(ClienteEditarActivity.this).setTitle(R.string.app_name).setMessage("No se pudo actualizar en la base de datos").setNegativeButton("Aceptar", null).show();
                }

            }

            Intent intent = new Intent(ClienteEditarActivity.this, PrincipalClienteActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    };

}
