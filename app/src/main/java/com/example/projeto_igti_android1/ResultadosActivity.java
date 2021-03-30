package com.example.projeto_igti_android1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultadosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        Intent intent = getIntent();
        int dependentes = intent.getIntExtra(MainActivity.DEPENDENTES,0);
        double salarioBruto = intent.getDoubleExtra(MainActivity.SALARIO_BRUTO,0);
        double descontos = intent.getDoubleExtra(MainActivity.DESCONTOS,0);

        buildLayout(salarioBruto, dependentes, descontos);

    }

    static double[] calcularRemuneracao(double remuneracaoBruta, int numeroDependentes, double outrosDescontos)
    {
        double[] listaRemuneratoria = new double[5];
        listaRemuneratoria[0] = remuneracaoBruta;
        //Cálculo INSS
        if(remuneracaoBruta <= 1045.0) {
            listaRemuneratoria[1] = remuneracaoBruta*0.075;
        } else if(remuneracaoBruta <= 2089.60) {
            listaRemuneratoria[1] = remuneracaoBruta*0.09 - 15.67;
        } else if(remuneracaoBruta <= 3134.40) {
            listaRemuneratoria[1] = remuneracaoBruta*0.12 - 78.36;
        } else if(remuneracaoBruta <= 6101.06) {
            listaRemuneratoria[1] = remuneracaoBruta*0.14 - 141.05;
        } else {
            listaRemuneratoria[1] = 713.10;
        }


        //Cálculo IRRF
        double baseCalculo = remuneracaoBruta - listaRemuneratoria[1] - numeroDependentes*189.59;
        if(baseCalculo <= 1903.98) {
            listaRemuneratoria[2] = 0;
        } else if(baseCalculo <= 2826.65) {
            listaRemuneratoria[2] = baseCalculo*0.075-142.80;
        } else if(baseCalculo <= 3751.05) {
            listaRemuneratoria[2] = baseCalculo*0.15-354.80;
        } else if(baseCalculo <= 4664.68) {
            listaRemuneratoria[2] = baseCalculo*0.225-636.13;
        } else {
            listaRemuneratoria[2] = baseCalculo*0.275-869.36;
        }

        //Salário Líquido
        listaRemuneratoria[3] = listaRemuneratoria[0]-listaRemuneratoria[1]-listaRemuneratoria[2]-outrosDescontos;
        //porcentagem
        listaRemuneratoria[4] = (listaRemuneratoria[0] - listaRemuneratoria[3])/listaRemuneratoria[0]*100;

        return listaRemuneratoria;
    }

    private void buildLayout(double salarioBruto, int dependentes, double descontos) {

        double[] listaRemuneratoria = calcularRemuneracao(salarioBruto, dependentes, descontos);


        TextView txtTitulo = (TextView)findViewById(R.id.titulo);
        txtTitulo.setText("Resultados");

        TextView txtSalarioBrutoLabel = (TextView)findViewById(R.id.txtSalarioBrutoLabel);
        txtSalarioBrutoLabel.setText("Salário Bruto");

        TextView txtInssLabel = (TextView)findViewById(R.id.txtInssLabel);
        txtInssLabel.setText("INSS");

        TextView txtIrrfLabel = (TextView)findViewById(R.id.txtIrrfLabel);
        txtIrrfLabel.setText("IRRF");

        TextView txtOutrosDescontosLabel = (TextView)findViewById(R.id.txtOutrosDescontosLabel);
        txtOutrosDescontosLabel.setText("Outros Descontos");

        TextView txtSalarioLiquidoLabel = (TextView)findViewById(R.id.txtSalarioLiquidoLabel);
        txtSalarioLiquidoLabel.setText("Salário Líquido");

        TextView txtDescontosLabel = (TextView)findViewById(R.id.txtDescontosLabel);
        txtDescontosLabel.setText("Descontos");

        TextView txtSalarioBruto = (TextView)findViewById(R.id.txtSalarioBruto);
        txtSalarioBruto.setText(String.valueOf(salarioBruto));

        TextView txtOutrosDescontos = (TextView)findViewById(R.id.txtOutrosDescontos);
        txtOutrosDescontos.setText(String.valueOf(descontos));

        TextView txtInss = (TextView)findViewById(R.id.txtInss);
        txtInss.setText(String.valueOf(listaRemuneratoria[1]));

        TextView txtIrff = (TextView)findViewById(R.id.txtIrff);
        txtIrff.setText(String.valueOf(listaRemuneratoria[2]));

        TextView txtSalarioLiquido = (TextView)findViewById(R.id.txtSalarioLiquido);
        txtSalarioLiquido.setText(String.valueOf(listaRemuneratoria[3]));

        TextView txtDescontos = (TextView)findViewById(R.id.txtDescontos);
        txtDescontos.setText(String.valueOf(listaRemuneratoria[4])+"%");





        //Falta colocar o código do jsfiddle aqui para calcular os valores!!

    }

    public void voltar(View view) {
        Intent messageIntent = new Intent(this, MainActivity.class);
        startActivity(messageIntent);
    }

}
