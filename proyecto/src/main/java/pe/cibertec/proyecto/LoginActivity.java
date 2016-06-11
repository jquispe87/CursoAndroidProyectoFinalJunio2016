package pe.cibertec.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by JOSE on 03/06/2016.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText etSecondUsuario, etSecondContrasena;
    private Button btSecondIngresar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        etSecondUsuario = (EditText) findViewById(R.id.etSecondUsuario);
        etSecondContrasena = (EditText) findViewById(R.id.etSecondContrasena);
        btSecondIngresar = (Button) findViewById(R.id.btSecondIngresar);

        btSecondIngresar.setOnClickListener(btSecondIngresarOnClickListener);

    }

    View.OnClickListener btSecondIngresarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (etSecondUsuario.getText().toString().trim().isEmpty()) {
                setMesage("Ingrese su usuario");
                return;
            }

            if (etSecondContrasena.getText().toString().trim().isEmpty()) {
                setMesage("Ingrese su contraseña");
                return;
            }


            IngresarSecond();
        }
    };

    private void IngresarSecond() {
        Intent intent = new Intent(LoginActivity.this, PrincipalClienteActivity.class);

        startActivity(intent);

        finish();
    }

    private void setMesage(String mesage) {
        new AlertDialog.Builder(LoginActivity.this).setTitle("Proyecto").setMessage(mesage).setNegativeButton("Aceptar", null).show();
    }
}
