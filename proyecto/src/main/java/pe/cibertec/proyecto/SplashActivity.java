package pe.cibertec.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView ivFirstLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        ivFirstLogo = (ImageView) findViewById(R.id.ivFirstLogo);
        ivFirstLogo.setOnClickListener(ivFirstLogoOnClickListener);
    }

    View.OnClickListener ivFirstLogoOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Ingresar();
        }
    };

    private void Ingresar() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
