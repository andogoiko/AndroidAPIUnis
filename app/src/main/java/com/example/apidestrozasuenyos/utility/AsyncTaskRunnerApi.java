package com.example.apidestrozasuenyos.utility;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.apidestrozasuenyos.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class AsyncTaskRunnerApi extends AsyncTask<String, String, String> {

    private String resp;
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
    protected String doInBackground(String... params) {
        publishProgress("Sleeping..."); // Calls onProgressUpdate()
        try {
            int time = Integer.parseInt(params[0])*1000;

            LeerApi(pais, universidad);
            resp = "Detenido por " + params[0] + " segundos";
        } catch (Exception e) {
            e.printStackTrace();
            resp = e.getMessage();
        }
        return resp;
    }


    @Override
    protected void onPostExecute(String result) {
        // execution of result of Long time consuming operation
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


    private static void LeerApi(String pais, String uni) {

        pais = pais.replace(' ', '+');
        uni = uni.replace(' ', '+');

        String url = "http://universities.hipolabs.com/search?country=" + pais + "&name=" + uni;

        StringRequest postResquest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //text.setText(jsonObject.getString("userId"));
                    String title = jsonObject.getString("title");
                    //text.setText(title);
                    //text.setText(jsonObject.getString("body"));

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
        //Volley.newRequestQueue(this).add(postResquest);
    }

}

