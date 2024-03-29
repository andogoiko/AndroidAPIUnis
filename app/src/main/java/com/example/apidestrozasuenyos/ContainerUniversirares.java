package com.example.apidestrozasuenyos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apidestrozasuenyos.clases.UniversitaContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContainerUniversirares#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContainerUniversirares extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String pais = "";
    private String universidad = "";

    public ContainerUniversirares() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContainerUniversirares.
     */
    // TODO: Rename and change types and number of parameters
    public static ContainerUniversirares newInstance(String param1, String param2) {
        ContainerUniversirares fragment = new ContainerUniversirares();
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
        View view =  inflater.inflate(R.layout.fragment_container_universirares, container, false);

        // recogiemdo y utilizando internamente los datos del bundle recibido tras ser cargado

        getParentFragmentManager().setFragmentResultListener("Bundlesito", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {

                // recogemos las variables

                pais = bundle.getString("pais");
                universidad = bundle.getString("universidad");

                // creamos otro bundle para enviarlo al fragmento hijo

                Bundle result = new Bundle();
                result.putString("fatherPais", pais);
                result.putString("fatherUniversidad", universidad);

                // modificamos el texto que aparece sobre la lista de unis

                TextView khePais = (TextView) view.findViewById(R.id.textView);

                if(pais.equals("") && universidad.equals("")){
                    khePais.setText("Universidades del mundo:");
                }else if ( universidad.equals("")){
                    khePais.setText("Universidades de " + pais + ":");
                }else{
                    khePais.setText("Universidades de " + universidad + " en " + pais + ":");
                }

                // enviamos el bundle al fragmento hijo

                getChildFragmentManager().setFragmentResult("papiBundle", result);
            }
        });

        // botón para volver a la anterior oantalla (que es un fragment)

        view.findViewById(R.id.bVolver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create new fragment and transaction
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);


                // Replace whatever is in the fragment_container view with this fragment
                transaction.replace(R.id.fcvGeneral, Buscador.class, null);

                // Commit the transaction
                transaction.commit();

            }
        });

        return view;

    }
}