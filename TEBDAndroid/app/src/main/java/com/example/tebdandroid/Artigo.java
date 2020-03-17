package com.example.tebdandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Artigo extends AppCompatActivity {

    protected EditText editTextArtigo;
    protected ArtigoValue artigoSelecionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artigo);
        Button button = (Button) findViewById(R.id.botao);

        this.editTextArtigo = (EditText)findViewById(R.id.artigo);
        Intent intent = getIntent();

        artigoSelecionado = (ArtigoValue) intent.getSerializableExtra("artigoSelecionado");
        if(artigoSelecionado!=null){
            button.setText("Alterar");
            editTextArtigo.setText(artigoSelecionado.getArtigo());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)	{
                ArtigoDAO dao = new ArtigoDAO(Artigo.this);
                if(artigoSelecionado==null){
                    ArtigoValue artigoValue = new ArtigoValue();
                    artigoValue.setArtigo(editTextArtigo.getText().toString());
                    dao.salvar(artigoValue);
                }else{
                    artigoSelecionado.setArtigo(editTextArtigo.getText().toString());
                    dao.alterar(artigoSelecionado);
                }
                finish();
            }
        });
    }
}
