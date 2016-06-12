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
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

import pe.cibertec.proyecto.adapter.recyclerview.RVPrimerAdapter;
import pe.cibertec.proyecto.adapter.recyclerview.interfaces.IRVPrimerAdapter;
import pe.cibertec.proyecto.dao.ClienteDAO;
import pe.cibertec.proyecto.dao.DataBaseHelper;
import pe.cibertec.proyecto.dao.DataBaseSingleton;
import pe.cibertec.proyecto.entities.Cliente;

/**
 * Created by JOSE on 03/06/2016.
 */
public class PrincipalClienteActivity extends AppCompatActivity implements IRVPrimerAdapter {

    private RVPrimerAdapter mRVPrimerAdapter;
    private RecyclerView rvPrimerClientes;
    private ClienteDAO mClienteDAO;

    private ImageView ivPersona, ivProducto, ivPedido, ivCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_cliente_activity);

        ivPersona = (ImageView) findViewById(R.id.ivPersona);
        ivPersona.setOnClickListener(ivPersonaOnClickListener);

        ivProducto = (ImageView) findViewById(R.id.ivProducto);
        ivProducto.setOnClickListener(ivProductoOnClickListener);

        ivPedido = (ImageView) findViewById(R.id.ivPedido);
        ivPedido.setOnClickListener(ivPedidoOnClickListener);

        ivCerrar = (ImageView) findViewById(R.id.ivCerrar);
        ivCerrar.setOnClickListener(ivCerrarOnClickListener);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_principal_cliente_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setSubtitle("CAPTURA PEDIDO");

        rvPrimerClientes = (RecyclerView) findViewById(R.id.rvPCACliente);
        rvPrimerClientes .setLayoutManager(new LinearLayoutManager(PrincipalClienteActivity.this));

        //mRVPrimerAdapter = new RVPrimerAdapter(null);
        mRVPrimerAdapter = new RVPrimerAdapter(PrincipalClienteActivity.this);

        rvPrimerClientes.setAdapter(mRVPrimerAdapter);
        rvPrimerClientes.setHasFixedSize(true);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(PrincipalClienteActivity.this);
        try {
            dataBaseHelper.createDataBase();
            new DataBaseSingleton(PrincipalClienteActivity.this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mClienteDAO = new ClienteDAO();
        mRVPrimerAdapter.clearAndAddAll(mClienteDAO.listCliente());

    }

    View.OnClickListener ivPersonaOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(PrincipalClienteActivity.this, PrincipalClienteActivity.class);
            startActivity(intent);
            finish();
        }
    };

    View.OnClickListener ivProductoOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            IngresarProducto();
        }
    };

    private void IngresarProducto() {
        Intent intent = new Intent(this, PrincipalProductoActivity.class);
        this.startActivity(intent);
        finish();
    }

    View.OnClickListener ivPedidoOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(PrincipalClienteActivity.this, PrincipalPedidoActivity.class);
            startActivity(intent);
            finish();
        }
    };

    View.OnClickListener ivCerrarOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        mRVPrimerAdapter.clearAndAddAll(new ClienteDAO().listCliente());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal_cliente_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.principal_cliente_menu_agregar) {
            Intent intent = new Intent(PrincipalClienteActivity.this, ClienteNuevoActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == android.R.id.home) {
        }

        return super.onOptionsItemSelected(item);
    };

    @Override
    public void onSelectItem(Cliente cliente) {
        Intent intent = new Intent(PrincipalClienteActivity.this, ClienteConsultaActivity.class);
        intent.putExtra(ClienteConsultaActivity.ARG_CLIENTE, cliente);
        startActivity(intent);
    }



}
