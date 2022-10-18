package com.example.apidestrozasuenyos.clases;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniversitaContent {

    public static final List<Universita> Unis = new ArrayList<Universita>();

    //public static final Map<String, Universita> ITEM_MAP = new HashMap<String, Universita>();

    private static void addItem(Universita item) {
        Unis.add(item);
        //ITEM_MAP.put(item.id, item);
    }

    private static Universita createUniversita(int position) {
        return new Universita(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A placeholder item representing a piece of content.
     */
    public static class Universita implements Parcelable {
        public final String pais;
        public final String nombre;
        public final String web;

        public Universita(String pais, String nombre, String web) {
            this.pais = pais;
            this.nombre = nombre;
            this.web = web;
        }

        protected Universita(Parcel in) {
            pais = in.readString();
            nombre = in.readString();
            web = in.readString();
        }

        public static final Creator<Universita> CREATOR = new Creator<Universita>() {
            @Override
            public Universita createFromParcel(Parcel in) {
                return new Universita(in);
            }

            @Override
            public Universita[] newArray(int size) {
                return new Universita[size];
            }
        };

        @Override
        public String toString() {
            return pais + " " + nombre + " " + web;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(pais);
            parcel.writeString(nombre);
            parcel.writeString(web);
        }
    }
}
