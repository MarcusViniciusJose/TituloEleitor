package com.example.tituloeleitor;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNome, editTextDataNascimento;
    public Button buttonProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.editTextNome);
        editTextDataNascimento = findViewById(R.id.editTextDataNascimento);
        buttonProximo = findViewById(R.id.buttonProximo);

        buttonProximo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String nome = editTextNome.getText().toString();
                String dataNascimento = editTextDataNascimento.getText().toString();

                if (TextUtils.isEmpty(nome)) {
                    editTextNome.setError("Por favor, insira seu nome.");
                    return;
                }

                if (TextUtils.isEmpty(dataNascimento)) {
                    editTextDataNascimento.setError("Por favor, insira sua data de nascimento.");
                    return;
                }

                // Calcular idade
                LocalDate dataNasc = LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                long anos = Period.between(dataNasc, LocalDate.now()).get(ChronoUnit.YEARS);

                // Passar para a próxima Activity com os dados do usuário
                Intent intent = new Intent(getApplicationContext(), RelatorioActivity.class);
                intent.putExtra("nome", nome);
                intent.putExtra("idade", anos);
                startActivity(intent);
            }
        });
    }
}
