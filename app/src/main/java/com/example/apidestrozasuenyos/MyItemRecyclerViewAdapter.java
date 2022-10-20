package com.example.apidestrozasuenyos;

import static android.app.PendingIntent.getActivity;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.apidestrozasuenyos.clases.UniversitaContent;
import com.example.apidestrozasuenyos.clases.UniversitaContent.Universita;
import com.example.apidestrozasuenyos.databinding.FragmentUniversiraresBinding;

import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private List<Universita> mValues;

    public MyItemRecyclerViewAdapter(List<Universita> mValues) {
        this.mValues = mValues;
    }

    Context context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();

        return new ViewHolder(FragmentUniversiraresBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.nombre.setText(mValues.get(position).nombre);

        int thisUni = position;

        // añado a cada botón de recyclerview un listener para abrir una nueva actividad con webview

        holder.web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // lo hacemos mediante intent y me llevo el dato de url a la actividad para usarlo

                Intent myIntent = new Intent(context, weboView.class);

                myIntent.putExtra("url", mValues.get(thisUni).web); //Optional parameters
                context.startActivity(myIntent);
            }
        });

    }

    public void addList(List<Universita> mValues){
        this.mValues = mValues;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        if(this.mValues == null){
            return 0;
        }

        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nombre;
        private Button web; //necesita k en onclick sea webview

        public ViewHolder(FragmentUniversiraresBinding binding) {
            super(binding.getRoot());
            nombre = binding.nombre;
            web = binding.web;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + nombre.getText() + "'";
        }

        @Override
        public void onClick(View view) {

        }
    }
}