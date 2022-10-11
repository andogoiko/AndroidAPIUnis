package com.example.apidestrozasuenyos.clases;

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
    public static class Universita {
        public final String id;
        public final String content;
        public final String details;

        public Universita(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
