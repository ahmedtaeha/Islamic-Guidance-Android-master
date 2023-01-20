package com.districthut.islam.Utils.datasource;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.districthut.islam.Activities.Quran.model.Quran;
import com.districthut.islam.Utils.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Sadmansamee on 7/25/15.
 */
public class QuranDataSource {

    private final static String QURAN_TABLE = "quran";


    private final static String QURAN_ID = "_id";
    private final static String QURAN_SURAH_ID = "surah_id";
    private final static String QURAN_VERSE_ID = "verse_id";
    private final static String QURAN_ARABIC = "arabic_indopak";
    private final static String QURAN_ENGLSIH = "english";
    private final static String QURAN_BANGLA = "bangla";
    private final static String QURAN_INDO = "indo";
    private final static String QURAN_TRANSLITERATION = "transliteration";
    private final static String QURAN_BOOKMARK = "bookmark";
    private final static String QURAN_NOTE = "note";

    private static Cursor quranCursor;
    private DatabaseHelper databaseHelper;

    public QuranDataSource(Context context) {

        databaseHelper = new DatabaseHelper(context);
    }

    public ArrayList<Quran> getEnglishQuranBySurah(long surah_id) {
        ArrayList<Quran> quranArrayList = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        quranCursor = db.rawQuery("SELECT surah_id,name_english,name_arabic,verse_id,arabic_indopak,transliteration from quran join surah_name on quran.surah_id = surah_name.surah_no WHERE quran.surah_id = " + surah_id, null);
        quranCursor.moveToFirst();

        while (!quranCursor.isAfterLast()) {
            Quran quran = new Quran();
            quran.setsurahNameArabic(quranCursor.getString(quranCursor.getColumnIndex("name_arabic")));
            quran.setsurahName(quranCursor.getString(quranCursor.getColumnIndex("name_english")));
            quran.setsurahId(quranCursor.getLong(quranCursor.getColumnIndex(QURAN_SURAH_ID)));
            quran.setQuranId(quranCursor.getLong(quranCursor.getColumnIndex(QURAN_VERSE_ID)));
            quran.setQuranArabic(quranCursor.getString(quranCursor.getColumnIndex(QURAN_ARABIC)));
            quran.setquranTransliteration(quranCursor.getString(quranCursor.getColumnIndex(QURAN_TRANSLITERATION)));
            quranArrayList.add(quran);
            quranCursor.moveToNext();
        }

        quranCursor.close();
        db.close();
        return quranArrayList;
    }

    public ArrayList<Quran> searchQuranbyText(String query) {
        ArrayList<Quran> quranArrayList = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        quranCursor = db.rawQuery("SELECT surah_id,name_english,name_arabic,verse_id,arabic_indopak,transliteration from quran join surah_name on quran.surah_id = surah_name.surah_no WHERE quran.arabic_indopak like '%"+query+"%'", null);
        quranCursor.moveToFirst();

        while (!quranCursor.isAfterLast()) {
            Quran quran = new Quran();
            quran.setsurahNameArabic(quranCursor.getString(quranCursor.getColumnIndex("name_arabic")));
            quran.setsurahName(quranCursor.getString(quranCursor.getColumnIndex("name_english")));
            quran.setsurahId(quranCursor.getLong(quranCursor.getColumnIndex(QURAN_SURAH_ID)));
            quran.setQuranId(quranCursor.getLong(quranCursor.getColumnIndex(QURAN_VERSE_ID)));
            quran.setQuranArabic(quranCursor.getString(quranCursor.getColumnIndex(QURAN_ARABIC)));
            quran.setquranTransliteration(quranCursor.getString(quranCursor.getColumnIndex(QURAN_TRANSLITERATION)));
            quranArrayList.add(quran);
            quranCursor.moveToNext();
        }

        quranCursor.close();
        db.close();
        return quranArrayList;
    }




    public ArrayList<Quran> searchQuranbyID(String query) {
        ArrayList<Quran> quranArrayList = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        quranCursor = db.rawQuery("SELECT surah_id,name_english,name_arabic,verse_id,arabic_indopak,transliteration from quran join surah_name on quran.surah_id = surah_name.surah_no WHERE quran._id = " +query, null);
        quranCursor.moveToFirst();

        while (!quranCursor.isAfterLast()) {
            Quran quran = new Quran();
            quran.setsurahNameArabic(quranCursor.getString(quranCursor.getColumnIndex("name_arabic")));
            quran.setsurahName(quranCursor.getString(quranCursor.getColumnIndex("name_english")));
            quran.setsurahId(quranCursor.getLong(quranCursor.getColumnIndex(QURAN_SURAH_ID)));
            quran.setQuranId(quranCursor.getLong(quranCursor.getColumnIndex(QURAN_VERSE_ID)));
            quran.setQuranArabic(quranCursor.getString(quranCursor.getColumnIndex(QURAN_ARABIC)));
            quran.setquranTransliteration(quranCursor.getString(quranCursor.getColumnIndex(QURAN_TRANSLITERATION)));
            quranArrayList.add(quran);
            quranCursor.moveToNext();
        }

        quranCursor.close();
        db.close();
        return quranArrayList;
    }



    public Quran getAyatbyId(int aya_id) {
        Quran quran = new Quran();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        quranCursor = db.rawQuery("SELECT quran._id,surah_id,name_english,name_arabic,verse_id,arabic_indopak,transliteration from quran join surah_name on quran.surah_id = surah_name.surah_no WHERE quran._id = " + aya_id, null);
        //quranCursor.moveToFirst();

        while (quranCursor != null && quranCursor.moveToFirst()) {
            quran.setsurahNameArabic(quranCursor.getString(quranCursor.getColumnIndex("name_arabic")));
            quran.setsurahName(quranCursor.getString(quranCursor.getColumnIndex("name_english")));
            quran.setsurahId(quranCursor.getLong(quranCursor.getColumnIndex(QURAN_SURAH_ID)));
            quran.setQuranId(quranCursor.getLong(quranCursor.getColumnIndex(QURAN_VERSE_ID)));
            quran.setQuranArabic(quranCursor.getString(quranCursor.getColumnIndex(QURAN_ARABIC)));
            quran.setquranTransliteration(quranCursor.getString(quranCursor.getColumnIndex(QURAN_TRANSLITERATION)));
            quranCursor.moveToNext();
        }
        Log.e("ee",quran.getQuranArabic());
        quranCursor.close();
        db.close();
        return quran;
    }

}
