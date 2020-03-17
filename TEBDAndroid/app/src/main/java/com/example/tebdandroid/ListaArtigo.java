package com.example.tebdandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaArtigo extends AppCompatActivity {

    protected ListView lista;
    protected ArtigoValue artigoValue;
    protected ArrayAdapter<ArtigoValue> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_artigo);
        ArtigoDAO dao = new ArtigoDAO(this);

        int layout = android.R.layout.simple_list_item_1;

        ArrayList<ArtigoValue> artigos = (ArrayList<ArtigoValue>) new ArrayList(dao.getLista());
        dao.close();
        adapter = new ArrayAdapter<ArtigoValue>(this,layout,artigos);
        lista =  findViewById(R.id.listView);
        lista.setAdapter(adapter);
        lista.setOnCreateContextMenuListener(this);
        registerForContextMenu(lista);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArtigoDAO dao = new ArtigoDAO(this);
        ArrayList<ArtigoValue> artigos = new ArrayList(dao.getLista());
        dao.close();
        int	layout = android.R.layout.simple_list_item_1;

        adapter = new ArrayAdapter<ArtigoValue>(this,layout,artigos);

        lista.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_new){
            Intent intent = new Intent(this, Artigo.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_lista_artigos,menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_artigo,menu);
        return true;
    }

    public boolean onContextItemSelected(final MenuItem item){
        artigoValue = (ArtigoValue) this.adapter.getItem(((AdapterView.AdapterContextMenuInfo)item.getMenuInfo()).position);
        int id = item.getItemId();
        if(id == R.id.action_new) {
            Intent intent = new Intent(this, Artigo.class);
            startActivity(intent);
            return true;
        }
        if(id == R.id.action_update) {
            Intent intent = new Intent(this, Artigo.class);
            intent.putExtra("artigoSelecionado", artigoValue);
            startActivity(intent);
            return true;
        }
        if(id == R.id.action_delete) {
            ArtigoDAO dao = new ArtigoDAO(ListaArtigo.this);
            dao.deletar(artigoValue);
            dao.close();
            this.onResume();
            return true;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int codigo, int resultado, @Nullable Intent it) {
        super.onActivityResult(codigo, resultado, it);
        this.adapter.notifyDataSetChanged();
    }
}
