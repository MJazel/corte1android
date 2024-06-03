package com.example.appholamundo3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class mainCotizacion extends AppCompatActivity {

    private EditText nameText;
    private Button btncoti, btnLimpiar, btnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_cotizacion);

        nameText = (EditText) findViewById(R.id.nameText);
        btncoti = (Button) findViewById(R.id.btncoti);
        btnCerrar = (Button) findViewById(R.id.btnCerrar);
        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);

        btncoti.setOnClickListener(view -> {
            String clientName = nameText.getText().toString();
            if(clientName.isEmpty()){
                Toast.makeText(getApplicationContext(), "Faltan datos por llenar", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(mainCotizacion.this, cotizacionActivity.class);
            intent.putExtra("CLIENT_NAME", clientName);
            startActivity(intent);
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameText.setText("");
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}