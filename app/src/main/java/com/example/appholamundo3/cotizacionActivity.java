package com.example.appholamundo3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class cotizacionActivity extends AppCompatActivity {

    private EditText descripcionAuto, valorAuto, pagoInicial;
    private RadioGroup termRadioGroup;
    private Button btnCalcular,btnLimpiar,btnCerrar;
    private TextView resPI,txtNombreCliente, resPM,folioTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cotizacion);

        descripcionAuto = (EditText) findViewById(R.id.descripcionAuto);
        valorAuto = (EditText) findViewById(R.id.valorAuto);
        pagoInicial = (EditText) findViewById(R.id.pagoInicial);
        termRadioGroup = (RadioGroup) findViewById(R.id.termRadioGroup);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        resPI = (TextView) findViewById(R.id.resPI);
        resPM = (TextView) findViewById(R.id.resPM);
        txtNombreCliente = (TextView) findViewById(R.id.txtNombreCliente);
        btnCerrar = (Button) findViewById(R.id.btnCerrar);
        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        folioTextView = (TextView) findViewById(R.id.folioTextViw);


        String clientName = getIntent().getStringExtra("CLIENT_NAME");
        txtNombreCliente.setText(clientName);

        btnCalcular.setOnClickListener(view -> calculateQuote());

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resPI.setText("Pago inicial: ");
                resPM.setText("Pago mensual: ");
                folioTextView.setText("Folio: ");
                descripcionAuto.setText("");
                valorAuto.setText("");
                pagoInicial.setText("");
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void calculateQuote() {
        String carPriceStr = valorAuto.getText().toString();
        String downPaymentPercentageStr = pagoInicial.getText().toString();
        String carDescriptionEditTextStr = descripcionAuto.getText().toString();
        if (carPriceStr.isEmpty() || downPaymentPercentageStr.isEmpty() ||  carDescriptionEditTextStr.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Faltan datos por llenar", Toast.LENGTH_SHORT).show();
            return;
        }
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        int sixDigitNumber = (int) (Math.random() * 900000) + 100000;
        String folio = Integer.toString(sixDigitNumber);
        folioTextView.setText("Folio: " + folio);

        double carPrice = Double.parseDouble(carPriceStr);
        double downPaymentPercentage = Double.parseDouble(downPaymentPercentageStr) / 100;

        double downPayment = carPrice * downPaymentPercentage;
        double totalToFinance = carPrice - downPayment;

        int selectedTermId = termRadioGroup.getCheckedRadioButtonId();
        int term = 0;

        if (selectedTermId == R.id.term12) {
            term = 12;
        } else if (selectedTermId == R.id.term18) {
            term = 18;
        } else if (selectedTermId == R.id.term24) {
            term = 24;
        } else if (selectedTermId == R.id.term36) {
            term = 36;
        }

        double monthlyPayment = totalToFinance / term;

        resPI.setText("Pago inicial: $" + String.format("%.2f", downPayment));
        resPM.setText("Pago mensual: $" + String.format("%.2f", monthlyPayment));
    }
}