package pe.cibertec.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;

import pe.cibertec.proyecto.adapter.recyclerview.RVSegundoAdapter;
import pe.cibertec.proyecto.adapter.recyclerview.interfaces.IRVSegundoAdapter;
import pe.cibertec.proyecto.dao.DataBaseHelper;
import pe.cibertec.proyecto.dao.DataBaseSingleton;
import pe.cibertec.proyecto.dao.ProductoDAO;
import pe.cibertec.proyecto.entities.Producto;

/**
 * Created by JOSE on 10/06/2016.
 */
public class PrincipalProductoActivity extends AppCompatActivity implements IRVSegundoAdapter {

    private RVSegundoAdapter mRVSegundoAdapter;
    private RecyclerView rvPrimerProductos;
    private ProductoDAO mProductoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_producto_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_principal_producto_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setSubtitle("PRODUCTOS");

        rvPrimerProductos = (RecyclerView) findViewById(R.id.rvPPAProducto);
        rvPrimerProductos .setLayoutManager(new LinearLayoutManager(PrincipalProductoActivity.this));

        mRVSegundoAdapter = new RVSegundoAdapter(PrincipalProductoActivity.this);

        rvPrimerProductos.setAdapter(mRVSegundoAdapter);
        rvPrimerProductos.setHasFixedSize(true);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(PrincipalProductoActivity.this);
        try {
            dataBaseHelper.createDataBase();
            new DataBaseSingleton(PrincipalProductoActivity.this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mProductoDAO = new ProductoDAO();
        mRVSegundoAdapter.clearAndAddAll(mProductoDAO.listProducto());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRVSegundoAdapter.clearAndAddAll(new ProductoDAO().listProducto());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal_producto_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        if (item.getItemId() == R.id.principal_producto_menu_agregar) {
            Intent intent = new Intent(PrincipalProductoActivity.this, ProductoNuevoActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    };

    @Override
    public void onSelectItem(Producto producto) {
        Intent intent = new Intent(PrincipalProductoActivity.this, ProductoConsultaActivity.class);
        intent.putExtra(ProductoConsultaActivity.ARG_PRODUCTO, producto);
        startActivity(intent);
    }
}
