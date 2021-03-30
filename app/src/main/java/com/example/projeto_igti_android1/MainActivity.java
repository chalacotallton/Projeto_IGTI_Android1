package com.example.projeto_igti_android1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String SALARIO_BRUTO = "com.example.projeto_igti_android1.SALARIOBRUTO";
    public static final String DEPENDENTES = "com.example.projeto_igti_android1.DEPENDENTES";
    public static final String DESCONTOS = "com.example.projeto_igti_android1.DESCONTOS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCalcular = (Button)findViewById(R.id.btnCalcular);

        final EditText edtTxtSalario = (EditText)findViewById(R.id.edtTxtSalarioBruto);
        final EditText edtTxtDependentes = (EditText)findViewById(R.id.edtTxtDependentes);
        final EditText edtTxtDescontos = (EditText)findViewById(R.id.edtTxtDescontos);

        btnCalcular.setOnClickListener((view) ->  {
            Intent messageIntent = new Intent(MainActivity.this, ResultadosActivity.class);

            validateData();
            messageIntent.putExtra(SALARIO_BRUTO, Double.parseDouble(edtTxtSalario.getText().toString()));
            messageIntent.putExtra(DEPENDENTES, Integer.parseInt(edtTxtDependentes.getText().toString()));
            messageIntent.putExtra(DESCONTOS, Double.parseDouble(edtTxtDescontos.getText().toString()));

            startActivity(messageIntent);
        });

    }
    private void validateData() {
        EditText edtTxtSalario = (EditText)findViewById(R.id.edtTxtSalarioBruto);
        EditText edtTxtDependentes = (EditText)findViewById(R.id.edtTxtDependentes);
        EditText edtTxtDescontos = (EditText)findViewById(R.id.edtTxtDescontos);

        if (edtTxtSalario.getText().length() <=0) {
            edtTxtSalario.setText("0");
        }
        if (edtTxtDependentes.getText().length() <=0) {
            edtTxtDependentes.setText("0");
        }
        if (edtTxtDescontos.getText().length() <=0) {
            edtTxtDescontos.setText("0");
        }
    }
}