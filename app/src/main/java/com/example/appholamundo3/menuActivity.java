package com.example.appholamundo3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class menuActivity extends AppCompatActivity {

    private CardView crvPrimer, crvImc, crvCambio, crvConversion, crvCotizacion, crvSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        iniciarComponentes();

        //Codificar los eventos clic de las tarjetas

        crvPrimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        crvImc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuActivity.this,imcActivity.class);
                startActivity(intent);
            }
        });

        crvConversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuActivity.this,convertidorgrados.class);
                startActivity(intent);
            }
        });

//        crvCambio.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(menuActivity.this,monedasActivity.class);
//                startActivity(intent);
//            }
//        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void iniciarComponentes(){
        crvPrimer=(CardView) findViewById(R.id.crvHola);
        crvImc=(CardView) findViewById(R.id.crvImc);
        crvCambio=(CardView) findViewById(R.id.crvMoneda);
        crvConversion=(CardView) findViewById(R.id.crvConversion);
        crvCotizacion=(CardView) findViewById(R.id.crvCotizacion);
        crvSpinner=(CardView) findViewById(R.id.crvSpinner);
    }

}