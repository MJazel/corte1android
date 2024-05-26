package com.example.appholamundo3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class imcActivity extends AppCompatActivity {

    private EditText txtPeso;
    private EditText txtAltura;
    private TextView lblResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtPeso = findViewById(R.id.txtPeso);
        txtAltura = findViewById(R.id.txtAltura);
        lblResultado = findViewById(R.id.lblResultado);
        Button btnCalcular = findViewById(R.id.btnCalcular);
        Button btnLimpiar = findViewById(R.id.btnLimpiar);
        Button btnCerrar = findViewById(R.id.btnCerrar);




        btnCalcular.setOnClickListener(v -> calcularIMC());
        btnLimpiar.setOnClickListener(v -> limpiarCampos());
        btnCerrar.setOnClickListener(v -> finish());
    }
    private void calcularIMC() {
        String pesoStr = txtPeso.getText().toString();
        String alturaStr = txtAltura.getText().toString();

        if (!pesoStr.isEmpty() && !alturaStr.isEmpty()) {
            try {
                double peso = Double.parseDouble(pesoStr);
                double altura = Double.parseDouble(alturaStr);

                if (altura > 0) {
                    double imc = peso / (altura * altura);
                    lblResultado.setText(String.format("IMC es: %.2f", imc));
                } else {
                    Toast.makeText(this, "Por favor, introduce una altura válida", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Por favor, introduce valores válidos", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Por favor, completa ambos campos", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiarCampos() {
        txtPeso.setText("");
        txtAltura.setText("");
        lblResultado.setText("IMC es: ");
    }
}