package com.example.apidestrozasuenyos.utility;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.apidestrozasuenyos.MainActivity;
import com.example.apidestrozasuenyos.MyItemRecyclerViewAdapter;
import com.example.apidestrozasuenyos.Universirares;
import com.example.apidestrozasuenyos.clases.UniversitaContent.Universita;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AsyncTaskRunnerApi extends AsyncTask<String, String, ArrayList<Universita>> {

    private String resp;
    private ArrayList<Universita> unis = new ArrayList<>();
    ProgressDialog progressDialog;

    private MainActivity parentActivity;

    private String pais;
    private String universidad;

    public AsyncTaskRunnerApi(MainActivity parentActivity, String pais, String universidad){
        this.parentActivity = parentActivity;
        this.pais = pais;
        this.universidad = universidad;
    }

    @Override
    protected ArrayList<Universita> doInBackground(String... params) {
        publishProgress("Sleeping..."); // Calls onProgressUpdate()
        try {
            //int time = Integer.parseInt(params[0])*1000;

            resp = LeerApi(pais, universidad);
            //resp = "Detenido por " + params[0] + " segundos";
        } catch (Exception e) {
            e.printStackTrace();
            resp = e.getMessage();
        }

        try {
            JSONArray jsonArray = new JSONArray(resp);

            for (int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject=jsonArray.getJSONObject(i);

                //limpiamos los links de las webs

                String web = jsonObject.getString("web_pages").substring(2).replace("\\","");

                unis.add(new Universita(
                        jsonObject.getString("country"),
                        jsonObject.getString("name"),
                        web.substring(0, web.indexOf('"')) // algunas tienen más de 1 link, escogemos el primero
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyItemRecyclerViewAdapter awa = new MyItemRecyclerViewAdapter(unis);

        awa.addList(unis);

        return unis;
    }


    @Override
    protected void onPostExecute(ArrayList<Universita> result) {
        // execution of result of Long time consuming operation
        super.onPostExecute(unis);
        progressDialog.dismiss();
    }


    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(parentActivity,
                "Cargando",
                "Obteniendo los datos solicitados", true);
    }


    @Override
    protected void onProgressUpdate(String... text) {
        //resultadoFinal.setText(text[0]);

    }


    private static String LeerApi(String pais, String uni) throws IOException {

        pais = pais.replace(' ', '+');
        uni = uni.replace(' ', '+');

        StringBuilder result = new StringBuilder();

        String urlStr = "http://universities.hipolabs.com/search?country=" + pais + "&name=" + uni;

        URL urlConn = new URL(urlStr);

        HttpURLConnection conn = (HttpURLConnection) urlConn.openConnection();

        conn.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }finally {
            conn.disconnect();
        }

        String respuesta = result.toString();

        return respuesta;

        // al ser asíncronos el asynctask y el volley no se puede, ya que no puedes meter algo asíncrono en algo asíncrono

        /*StringRequest postResquest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //text.setText(jsonObject.getString("userId"));
                    String title = jsonObject.getString("title");
                    //text.setText(title);
                    //text.setText(jsonObject.getString("body"));

                    View root = inflater.inflate(R.layout.fragment_home, container, false);

                    RecyclerView recyclerView = (RecyclerView) root;

                    recyclerView.setAdapter(new MyItemRecyclerViewAdapter(lista,fm));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
            }
        });
        //Volley.newRequestQueue(this).add(postResquest);*/
    }

}

