package com.example.tituloeleitor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RelatorioActivity extends AppCompatActivity {

    private TextView textViewRelatorio;
    private Button buttonCompartilhar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        textViewRelatorio = findViewById(R.id.textViewRelatorio);
        buttonCompartilhar = findViewById(R.id.buttonCompartilhar);

        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome");
        long idade = intent.getLongExtra("idade", 0);

        String relatorio = gerarRelatorio(nome, idade);
        textViewRelatorio.setText(relatorio);

        buttonCompartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compartilharRelatorio(relatorio);
            }
        });
    }

    private String gerarRelatorio(String nome, long idade) {
        String relatorio = nome + ", com " + idade + " anos, ";

        if (idade >= 18 && idade < 70) {
            relatorio += "deve votar nas próximas Eleições!";
        } else if (idade >= 16 && idade < 18) {
            relatorio += "pode tirar Título de Eleitor e votar nas próximas Eleições!";
        } else if (idade >= 15) {
            relatorio += "pode tirar Título de Eleitor.";
        } else {
            relatorio += "ainda não pode tirar Título de Eleitor.";
        }

        return relatorio;
    }

    private void compartilharRelatorio(String relatorio) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, relatorio);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }
}
