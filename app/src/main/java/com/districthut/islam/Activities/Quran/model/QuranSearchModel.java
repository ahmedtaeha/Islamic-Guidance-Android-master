package com.districthut.islam.Activities.Quran.model;

import org.json.JSONArray;

public class QuranSearchModel {
    private String verse_key, text, highlighted;
    private int verse_id;
    JSONArray translations;

    public QuranSearchModel(String verse_key, String text, String highlighted, int verse_id, JSONArray translations) {
        this.verse_key = verse_key;
        this.text = text;
        this.highlighted = highlighted;
        this.verse_id = verse_id;
        this.translations = translations;
    }

    public String getVerse_key() {
        return verse_key;
    }

    public void setVerse_key(String verse_key) {
        this.verse_key = verse_key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHighlighted() {
        return highlighted;
    }

    public void setHighlighted(String highlighted) {
        this.highlighted = highlighted;
    }

    public int getVerse_id() {
        return verse_id;
    }

    public void setVerse_id(int verse_id) {
        this.verse_id = verse_id;
    }

    public JSONArray getTranslations() {
        return translations;
    }

    public void setTranslations(JSONArray translations) {
        this.translations = translations;
    }
}
