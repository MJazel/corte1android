package com.example.appholamundo3;

import android.content.Intent;
import android.os.Bundle;
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
    private Button btncoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_cotizacion);

        nameText = findViewById(R.id.nameText);
        btncoti = findViewById(R.id.btncoti);

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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}