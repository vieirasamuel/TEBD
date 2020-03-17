package com.example.tebdandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        TextView textView = (TextView) this.findViewById(R.id.textView);
        textView.setText("Aguarde Carregando.......");

        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new JSONParse().execute();
            }
        }, SPLASH_TIME_OUT);
    }

    public class JSONParse extends AsyncTask<String,String, JSONObject> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(SplashScreen.this);

            pDialog.setMessage("Obtendo Dados");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            JSONObject json	= null;
            ArtigoDAO artigoDAO	= new ArtigoDAO(SplashScreen.this);
            artigoDAO.dropAll();
            JSONArray link = null;
            json = Json();
            int count=0;
            try	{
                //	Getting	JSON	Array
                link = json.getJSONArray("artigos");
                for	(int i = 0; i < link.length(); i++)	{
                    JSONObject c = link.getJSONObject(i);
                    ArtigoValue artigoValue	= new ArtigoValue();
                    artigoValue.setArtigo(c.getString("artigo"));
                    artigoDAO.salvar(artigoValue);
                    artigoDAO.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try{
                pDialog.dismiss();
                Intent i = new Intent(SplashScreen.this, ListaArtigo.class);
                startActivity(i);
                finish();
            } catch (Exception e) {

            }
        }
    }

    public JSONObject Json(){
        JSONObject json	= null;
        String resp=null;
        try	{
            URL	url1 = new URL("http://192.168.122.1:3333/import/mobile");
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            try {
                InputStream in = new BufferedInputStream(conn.getInputStream());
                resp = convertStreamToString(in);
                json = new JSONObject(resp);
            } finally {
                conn.disconnect();
            }/*
            conn.setRequestProperty("Content-Type",	"application/json");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            InputStream inputStream	= conn.getInputStream();
            resp = IOUtils.toString(inputStream);
            json = new JSONObject(resp);*/

            Log.i("Teste", json.toString());
            return json;
        }catch (Exception e){
            e.printStackTrace();
        }
        return	null;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


}
