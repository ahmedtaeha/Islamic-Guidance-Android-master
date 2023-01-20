package com.districthut.islam.Activities.Quran;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.districthut.islam.Activities.Quran.model.Translations;
import com.districthut.islam.Activities.Quran.util.Constants;
import com.districthut.islam.Activities.Quran.util.SDCardManager;
import com.mirfatif.noorulhuda.R;


import java.io.File;
import java.util.ArrayList;

import alirezat775.lib.downloader.Downloader;

public class QuranTranslationActivity extends AppCompatActivity {
    ArrayList<Translations> transList;

    SharedPreferences.Editor editor;
    SharedPreferences pref;
    String translator="";
    int translatorPosition=0;
    boolean check = false;
    Translations translations;
    int downloadId;
    ProgressDialog dialog;
    ListView listView;
    ArrayAdapter<Translations> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_translation);
        listView = (ListView)findViewById(R.id.translationsLV);
        getSupportActionBar().setTitle("Translations");
        translations = new Translations();
        transList = translations.GetTranslations(false);


        pref = getApplicationContext().getSharedPreferences("QuranPref", 0); // 0 - for private mode
        editor = pref.edit();
        int temp=0;
        for(Translations s : transList){

            if(s.name.equals(translations.GetTranslationName(pref.getString("translator","")))){
                translatorPosition = temp;
            }
            temp++;
        }

       adapter = new ArrayAdapter<Translations>(this, R.layout.recitations_listview, R.id.reciterName, transList) {

            int selectedPosition = translatorPosition;

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.translations_lv, null);
                    RadioButton r = (RadioButton)v.findViewById(R.id.translationCheck);
                }
                ImageView imageView = (ImageView)v.findViewById(R.id.translation_flag_icon);
                imageView.setImageResource(transList.get(position).imageId);
                TextView tv = (TextView)v.findViewById(R.id.translationName);
                tv.setText(transList.get(position).name);
                TextView tv1 = (TextView)v.findViewById(R.id.translations_Lang);
                tv1.setText(transList.get(position).lang);
                RadioButton r = (RadioButton)v.findViewById(R.id.translationCheck);
                r.setChecked(position == selectedPosition);
                r.setTag(position);
                v.setTag(position);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {

                        String file = translations.GetTranslationCode(transList.get(position).name);
                        String ext = "";
                        String filename = "";
                        if (file.contains(".")) {
                            ext = file.substring(file.lastIndexOf(".")+1);
                            filename = file.substring(0, file.lastIndexOf("."));
                        }


                        if(SDCardManager.getStatus(Constants.TRANSLATIONS_PATH + "/" + file).equals(Constants.MEDIA_NOT_AVAILABLE)) {
                            dialog = new ProgressDialog(QuranTranslationActivity.this);
                            dialog.setTitle("Downloading Translation");
                            dialog.setCancelable(false);
                            dialog.setMessage("Please wait! We're downloading " + transList.get(position).name +".");
                            Downloader downloader = new Downloader.Builder(QuranTranslationActivity.this,
                                    Constants.TRANSLATIONS_URL + "/" + file)
                                    .fileName(filename,ext)
                                    .downloadDirectory(Constants.TRANSLATIONS_PATH + "/")
                                    .downloadListener(new alirezat775.lib.downloader.core.OnDownloadListener() {
                                        @Override
                                        public void onStart() {
                                            dialog.show();
                                        }

                                        @Override
                                        public void onPause() {

                                        }

                                        @Override
                                        public void onResume() {

                                        }

                                        @Override
                                        public void onProgressUpdate(int i, int i1, int i2) {

//                                            downloadingDialog
//                                                    .setMessage(
//                                                            String.format(
//                                                                    context.getString(R.string.download_format)
//                                                                    ,i+"%",getSize(i1),getSize(i2)));
                                        }

                                        @Override
                                        public void onCompleted(File file) {
                                            //dialog.dismiss();
                                            selectedPosition = (Integer) v.getTag();
                                            notifyDataSetChanged();
                                            editor.putString("translator", translations.GetTranslationCode(transList.get(position).name));
                                            editor.apply();
                                            translator = transList.get(position).name;
                                            dialog.dismiss();
                                            Toast.makeText(QuranTranslationActivity.this, "Translation Downloaded.", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onFailure(String s) {
                                            dialog.dismiss();
                                            Toast.makeText(QuranTranslationActivity.this, s, Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onCancel() {
                                            dialog.dismiss();
                                        }
                                    }).build();
                            downloader.download();
//
                        } else {
                            selectedPosition = (Integer) v.getTag();
                            notifyDataSetChanged();
                            editor.putString("translator", translations.GetTranslationCode(transList.get(position).name));
                            editor.apply();
                            translator = transList.get(position).name;
                        }
                    }
                });
                final View finalV = v;
                r.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        String file = translations.GetTranslationCode(transList.get(position).name);
                        String ext = file.split(".")[1];
                        if(SDCardManager.getStatus(Constants.TRANSLATIONS_PATH + "/" + file).equals(Constants.MEDIA_NOT_AVAILABLE)) {
                            dialog = new ProgressDialog(QuranTranslationActivity.this);
                            dialog.setTitle("Downloading Translation");
                            dialog.setCancelable(false);
                            dialog.setMessage("Please wait! We're downloading " + transList.get(position).name +".");
                            Downloader downloader = new Downloader.Builder(QuranTranslationActivity.this,
                                    Constants.TRANSLATIONS_URL + "/" + file)
                                    .fileName(file,ext)
                                    .downloadDirectory(Constants.TRANSLATIONS_PATH + "/")
                                    .downloadListener(new alirezat775.lib.downloader.core.OnDownloadListener() {
                                        @Override
                                        public void onStart() {
                                            dialog.show();
                                        }

                                        @Override
                                        public void onPause() {

                                        }

                                        @Override
                                        public void onResume() {

                                        }

                                        @Override
                                        public void onProgressUpdate(int i, int i1, int i2) {

//                                            downloadingDialog
//                                                    .setMessage(
//                                                            String.format(
//                                                                    context.getString(R.string.download_format)
//                                                                    ,i+"%",getSize(i1),getSize(i2)));
                                        }

                                        @Override
                                        public void onCompleted(File file) {
                                            //dialog.dismiss();
                                            selectedPosition = (Integer) finalV.getTag();
                                            notifyDataSetChanged();
                                            editor.putString("translator", translations.GetTranslationCode(transList.get(position).name));
                                            editor.apply();
                                            translator = transList.get(position).name;
                                            dialog.dismiss();
                                            Toast.makeText(QuranTranslationActivity.this, "Translation Downloaded.", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onFailure(String s) {
                                            dialog.dismiss();
                                            Toast.makeText(QuranTranslationActivity.this, s, Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onCancel() {
                                            dialog.dismiss();
                                        }
                                    }).build();
                            downloader.download();
                        } else {
                            selectedPosition = (Integer) view.getTag();
                            notifyDataSetChanged();
                            editor.putString("translator", translations.GetTranslationCode(transList.get(position).name));
                            editor.apply();
                            translator = transList.get(position).name;
                        }
                    }
                });
                return v;
            }

        };
        listView.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }



    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        SetActivityResult();
        finish();
    }

    void SetActivityResult(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",translations.GetTranslationCode(translator));
        setResult(Activity.RESULT_OK,returnIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            SetActivityResult();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}


