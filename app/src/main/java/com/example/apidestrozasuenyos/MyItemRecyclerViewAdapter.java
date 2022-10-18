package com.example.apidestrozasuenyos;

import androidx.recyclerview.widget.RecyclerView;

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
        mValues = mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentUniversiraresBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.Nombre.setText(mValues.get(position).nombre);
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

        return this.mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView Nombre;
        private Button link; //necesita k en onclick sea webview

        public ViewHolder(FragmentUniversiraresBinding binding) {
            super(binding.getRoot());
            Nombre = binding.Nombre;
            link = binding.link;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + Nombre.getText() + "'";
        }

        @Override
        public void onClick(View view) {

        }
    }
}