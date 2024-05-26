package com.example.appholamundo3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class monedasActivity extends AppCompatActivity {

    private EditText txtCantidad;
    private TextView txtResultado;
    private Spinner spmMoneda;
    private Button btnCalcular, btnLimpiar, btnCerrar;

    private int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_monedas);

        iniciarComponentes();

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtCantidad.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Falto capturar", Toast.LENGTH_SHORT).show();
                } else {
                    float cantidad = Float.parseFloat(txtCantidad.getText().toString());
                    float resultado;
                    switch (pos) {
                        case 0: // Convertir a Dolar Amricano
                            resultado = cantidad / 16.70f;
                            txtResultado.setText("Resultado: " + resultado);
                            break;
                        case 1: // Convertir a Dolar Canadiense
                            resultado = cantidad / 12.30f;
                            txtResultado.setText("Resultado: " + resultado);
                            break;
                        case 2: // Convertir a Euro
                            resultado = cantidad / 18.20f;
                            txtResultado.setText("Resultado: " + resultado);
                            break;
                        case 3: // Convertir a Libras
                            resultado = cantidad / 21.35f;
                            txtResultado.setText("Resultado: " + resultado);
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCantidad.setText("");
                txtResultado.setText("");
                spmMoneda.setSelection(0);
                pos = 0;
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual
            }
        });

        spmMoneda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void iniciarComponentes() {
        txtCantidad = (EditText) findViewById(R.id.txtCantidad);
        txtResultado = (TextView) findViewById(R.id.txtResultado);
        spmMoneda = (Spinner) findViewById(R.id.spnMoneda);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        btnCerrar = (Button) findViewById(R.id.btnCerrar);

        //Adaptar el array a Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.monedas));
        spmMoneda.setAdapter(adapter);
    }
}