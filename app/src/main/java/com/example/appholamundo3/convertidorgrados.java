package com.example.appholamundo3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class convertidorgrados extends AppCompatActivity {

    private EditText txtCantidad;
    private RadioButton rdbCel, rdbFa;
    private TextView txtResultado;
    private Button btnCalcular, btnLimpiar, btnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_convertidorgrados);
        iniciarComponentes();

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCantidad.setText("");
                txtResultado.setText("");
            }
        });
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cantidadStr = txtCantidad.getText().toString();
                if (!cantidadStr.isEmpty()) {
                    double cantidad = Double.parseDouble(cantidadStr);
                    double resultado;
                    if (rdbCel.isChecked()) {
                        resultado = (cantidad * 9 / 5) + 32;
                        txtResultado.setText("Resultado: " + resultado + " °F");
                    } else if (rdbFa.isChecked()) {
                        resultado = (cantidad - 32) * 5 / 9;
                        txtResultado.setText("Resultado: " + resultado + " °C");
                    }
                }else{
                    Toast.makeText(convertidorgrados.this,"El campo debe de ser completado", Toast.LENGTH_LONG).show();
                }
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
    public void iniciarComponentes(){
        txtCantidad = (EditText) findViewById(R.id.txtCantidad);
        rdbCel = (RadioButton) findViewById(R.id.rdbCel);
        rdbFa = (RadioButton) findViewById(R.id.rdbFa);
        txtResultado =(TextView) findViewById(R.id.txtResultado);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        btnCerrar = (Button) findViewById(R.id.btnCerrar);}


}