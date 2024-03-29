package com.example.apidestrozasuenyos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.apidestrozasuenyos.clases.UniversitaContent.Universita;
import com.example.apidestrozasuenyos.utility.AsyncTaskRunnerApi;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Buscador#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Buscador extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Buscador() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Buscador.
     */
    // TODO: Rename and change types and number of parameters
    public static Buscador newInstance(String param1, String param2) {
        Buscador fragment = new Buscador();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_buscador, container, false);

        Button buscar = (Button) view.findViewById(R.id.bBuscar);

        /* creando el editor de preferencias para guardarlas después */

        Context context = getActivity();

        SharedPreferences.Editor editor = context.getSharedPreferences("busqueda", Context.MODE_PRIVATE).edit();

        EditText etPais = (EditText)view.findViewById(R.id.etPais);
        EditText etUni = (EditText)view.findViewById(R.id.etUni);

        /* creando el recolector de preferencias para recogerlas */

        SharedPreferences prefs = context.getSharedPreferences("busqueda", Context.MODE_PRIVATE);

        etPais.setText(prefs.getString("pais", ""));
        etUni.setText(prefs.getString("uni", ""));

        buscar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                String pais = "";
                String universidad = "";

                // sguardando en shared preferences la búsaqueda

                editor.putString("pais", etPais.getText().toString());
                editor.putString("uni", etUni.getText().toString());
                editor.apply();

                pais = etPais.getText().toString();

                universidad = etUni.getText().toString();

                // creando bundle para enviar datos entre fragmentos

                Bundle bundle = new Bundle();

                bundle.putString("pais", pais);
                bundle.putString("universidad", universidad);

                Log.i("inicio", bundle.getString("pais"));

                getParentFragmentManager().setFragmentResult("Bundlesito", bundle);

                // cambiando de fragmento

                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

                ContainerUniversirares container = new ContainerUniversirares();

                transaction.replace(R.id.fcvGeneral, container, null);

                // hacemos el cambio de fragmento

                transaction.commit();


            }
        });

        // botón para limpiar los textos de búsqueda y shared preferences

        Button clean = (Button) view.findViewById(R.id.clean);

        clean.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                etPais.setText("");
                etUni.setText("");

                editor.putString("pais", "");
                editor.putString("uni", "");
                editor.apply();
            }
        });

        return view;
    }
}