package com.districthut.islam.Activities.Quran.model;

/**
 * Created by Mian Brothers on 10/26/2017.
 */

public class Dua {
    private int DUA_ID;
    private String DUA_ARABIC;
    private String DUA_URDU;
    private String DUA_ENGLISH;
    private String DUA_BAHASA;
    private String DUA_TITLE;
    private String DUA_FEELING;
    private String DUA_REFERENCE;
    private String TRANSLITERATION;

    public void setDUA_ID(int dua_id){
        this.DUA_ID = dua_id;
    }

    public int getDUA_ID(){
        return this.DUA_ID;
    }

    public void setDUA_ARABIC(String dua_arabic){
        this.DUA_ARABIC = dua_arabic;
    }

    public String getDUA_ARABIC(){
        return this.DUA_ARABIC;
    }

    public void setDUA_URDU(String dua_urdu){
        this.DUA_URDU = dua_urdu;
    }

    public String getDUA_URDU(){
        return this.DUA_URDU;
    }

    public void setDUA_ENGLISH(String dua_english){
        this.DUA_ENGLISH = dua_english;
    }

    public String getDUA_ENGLISH(){
        return this.DUA_ENGLISH;
    }

    public void setDUA_TITLE(String dua_title){
        this.DUA_TITLE = dua_title;
    }

    public String getDUA_TITLE(){
        return this.DUA_TITLE;
    }

    public void setDUA_FEELING(String dua_feeling){
        this.DUA_FEELING = dua_feeling;
    }

    public String getDUA_FEELING(){
        return this.DUA_FEELING;
    }

    public void setDUA_REFERENCE(String dua_reference){
        this.DUA_REFERENCE = dua_reference;
    }

    public String getDUA_REFERENCE(){
        return this.DUA_REFERENCE;
    }

    public void setTRANSLITERATION(String transliteration)
    {
        this.TRANSLITERATION = transliteration;
    }

    public String getTRANSLITERATION(){
        return this.TRANSLITERATION;
    }

    public void setDUA_BAHASA(String dua_bahasa){
        this.DUA_BAHASA = dua_bahasa;
    }

    public String getDUA_BAHASA(){
        return this.DUA_BAHASA;
    }
}
