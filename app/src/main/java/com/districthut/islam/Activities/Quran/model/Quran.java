package com.districthut.islam.Activities.Quran.model;

/**
 * Created by Sadmansamee on 7/25/15.
 */
public class Quran {
    private long quranId;
    private long surahId;
    private String surahName;
    private String surahNameArabic;
    private String quranArabic;
    private String quranTranslate;
    private String quranTransliteration;

    public String getQuranArabic() {
        return quranArabic;
    }

    public void setQuranArabic(String quranArabic) {
        this.quranArabic = quranArabic;
    }

    public String getQuranTranslate() {
        return quranTranslate;
    }

    public void setQuranTranslate(String quranTranslate) {
        this.quranTranslate = quranTranslate;
    }

    public String getquranTransliteration() {
        return quranTransliteration;
    }

    public void setquranTransliteration(String quranTransliteration) {
        this.quranTransliteration = quranTransliteration;
    }

    public long getquranId() {
        return quranId;
    }

    public void setQuranId(long quranId) {
        this.quranId = quranId;
    }

    public long getsurahId() {
        return surahId;
    }

    public void setsurahId(long surahId) {
        this.surahId = surahId;
    }

    public String getsurahName() {
        return surahName;
    }

    public void setsurahName(String surahName) {
        this.surahName = surahName;
    }

    public String getsurahNameArabic() {
        return surahNameArabic;
    }

    public void setsurahNameArabic(String surahNameArabic) {
        this.surahNameArabic = surahNameArabic;
    }
}
