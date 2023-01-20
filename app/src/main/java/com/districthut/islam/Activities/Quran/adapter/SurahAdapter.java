package com.districthut.islam.Activities.Quran.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.districthut.islam.Activities.Quran.model.Surah;
//import com.districthut.islam.Models.quran.Surah;
import com.mirfatif.noorulhuda.R;
import com.mirfatif.noorulhuda.databinding.RowSurahBinding;
import com.mirfatif.noorulhuda.quran.MainActivity;

import java.util.ArrayList;

import alirezat775.lib.downloader.Downloader;

/**
 * Created by Sadmansamee on 7/19/15.
 */
public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.SurahViewHolder> {


    private ArrayList<Surah> surahArrayList;
    private Context context;
    Typeface arabic_font;
    AlertDialog downloadingDialog;
    Downloader downloader;


    public SurahAdapter(ArrayList<Surah> surahArrayList, Context context) {
        this.surahArrayList = surahArrayList;
        this.context = context;
        arabic_font = Typeface.createFromAsset(context.getAssets(),  "fonts/arabic.ttf");
        downloadingDialog = new AlertDialog.Builder(context)
                .setTitle("Downloading")
                .setMessage(R.string.download_format)
                .setCancelable(false)
                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        downloader.cancelDownload();
                    }
                })
                .create();
    }



    @Override
    public SurahViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_surah, parent, false);
        SurahViewHolder viewHolder = new SurahViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SurahViewHolder holder, int position) {

        final Surah surah = surahArrayList.get(position);
//        holder.surah_idTextView.setText(Long.toString(surah.getId()) + ".");
        //arabic_font = Typeface.createFromAsset(context.getAssets(),  "fonts/symbols.ttf");

        holder.binding.surahId.setText(String.valueOf(surah.getId()));
        holder.binding.englishSurahTitleTv.setText(surah.getNameTranslate());
        holder.binding.arabicSurahTitleTv.setText(surah.getSurah_font());
        if(surah.getFont_type() == 1) {
            holder.binding.arabicSurahTitleTv.setTypeface(ResourcesCompat.getFont(context,R.font.surah_font));
        } else if(surah.getFont_type() == 2) {
            holder.binding.arabicSurahTitleTv.setTypeface(ResourcesCompat.getFont(context,R.font.surah_font_2));
        } else if(surah.getFont_type() == 3) {
            holder.binding.arabicSurahTitleTv.setTypeface(ResourcesCompat.getFont(context,R.font.surah_font_3));
        } else if(surah.getFont_type() == 4) {
            holder.binding.arabicSurahTitleTv.setTypeface(ResourcesCompat.getFont(context,R.font.surah_font_4));
        }
        holder.binding.englishSurahTitleDescTv.setText(surah.getType());

//        holder.binding.surahId.setText(String.valueOf(surah.getNumber()));
//        holder.binding.englishSurahTitleTv.setText(surah.getEnglishName());
//        holder.binding.arabicSurahTitleTv.setText(surah.getName());
//        holder.binding.arabicSurahTitleTv.setTypeface(arabic_font);
//        holder.binding.englishSurahTitleDescTv.setText(surah.getEnglishNameTranslation());

//        holder.playBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (
//                        ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                                == PackageManager.PERMISSION_GRANTED
//                                &&
//                                ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
//                                        == PackageManager.PERMISSION_GRANTED) {
//
//
//                    SharedPreferences pref = context.getApplicationContext().getSharedPreferences("QuranPref", 0);
//                    final String reciter = pref.getString("reciter", null);
//                    if (
//                            reciter == null) {
//                        Intent i = new Intent(context, TilawatRecitationActivity.class);
//                        context.startActivity(i);
//                    } else {
//
//                        if (SDCardManager.isSdCardWritable()) {
//                            SDCardManager.CreateFolders(Constants.TILAWAT_PATH + "/" + reciter);
//                            final String reciterPath = Constants.TILAWAT_PATH + "/" + reciter + "/";
//                            final String filename = String.format("%03d", surah.getNo()) + ".mp3";
//                            if (SDCardManager.getStatus(reciterPath + filename).equals(Constants.MEDIA_NOT_AVAILABLE)) {
//                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
//
//                                // Setting Dialog Title
//                                alertDialog.setTitle("Download Surah");
//
//                                // Setting Dialog Message
//                                alertDialog.setMessage("Would you like to download " + surah.getNameTranslate() + "?");
//
//                                // Setting Positive "Yes" Button
//                                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                                    public void onClick(final DialogInterface dialog, int which) {
//                                        downloader = new Downloader.Builder(context,
//                                                Constants.QURANAUDIO_URL + reciter + "/" + filename)
//                                                .fileName(String.format("%03d", surah.getNo()), "mp3")
//                                                .downloadDirectory(reciterPath)
//                                                .downloadListener(new OnDownloadListener() {
//                                                    @Override
//                                                    public void onStart() {
//                                                        downloadingDialog.setTitle(String.format(context.getString(R.string.downloading), surah.getNameTranslate()));
//                                                        downloadingDialog.setMessage(String.format(context.getString(R.string.download_format), "0", "0", "0"));
//                                                        downloadingDialog.show();
//                                                    }
//
//                                                    @Override
//                                                    public void onPause() {
//
//                                                    }
//
//                                                    @Override
//                                                    public void onResume() {
//
//                                                    }
//
//                                                    @Override
//                                                    public void onProgressUpdate(int i, int i1, int i2) {
//                                                        downloadingDialog
//                                                                .setMessage(
//                                                                        String.format(
//                                                                                context.getString(R.string.download_format)
//                                                                                , i + "%", getSize(i1), getSize(i2)));
//                                                    }
//
//                                                    @Override
//                                                    public void onCompleted(File file) {
//                                                        downloadingDialog.dismiss();
//                                                        Toast.makeText(context, "Audio Downloaded.", Toast.LENGTH_SHORT).show();
//                                                    }
//
//                                                    @Override
//                                                    public void onFailure(String s) {
//                                                        downloadingDialog.dismiss();
//                                                        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
//                                                    }
//
//                                                    @Override
//                                                    public void onCancel() {
//                                                        downloadingDialog.dismiss();
//                                                    }
//                                                }).build();
//                                        downloader.download();
////                                    MediaDownloadManager.init(context);
////                                    MediaDownloadManager.startDownload
////                                            (context, surah.getNameEnglish(),
////                                                    Constants.QURANAUDIO_URL + reciter + "/" +
////                                                            filename, reciterPath + filename);
//                                        // Write your code here to invoke YES event
//
//                                    }
//                                });
//
//                                // Setting Negative "NO" Button
//                                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        // Write your code here to invoke NO event
//
//                                        dialog.cancel();
//                                    }
//                                });
//
//                                // Showing Alert Message
//                                alertDialog.show();
//
//                            } else {
//                                try {
//                                    String filePath = reciterPath + filename;
//                                    Intent i = new Intent(context, SurahDetailActivity.class);
//                                    i.putExtra("surah_id", surah.getId());
//                                    i.putExtra("filepath", filePath);
//                                    i.putExtra("surahName", surah.getNameTranslate());
//                                    i.putExtra("play", true);
//                                    context.startActivity(i);
//
//
//                                } catch (Exception e) {
//
//                                }
//                            }
//                        }
//                    }
//                } else {
//                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 5);
//                }
//            }
//        });
////
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MainActivity.class);
                i.putExtra("SURAH_NUM",surah.getId().intValue());
                i.putExtra("AAYAH_NUM",0);
//                i.putExtra("surah_id",surah.getId());
//                i.putExtra("filepath","");
//                i.putExtra("surahName",surah.getNameEnglish());
//                i.putExtra("surahType",surah.getType());
//                i.putExtra("play",false);

//                i.putExtra("surah_id",surah.getNumber());
//                i.putExtra("filepath","");
//                i.putExtra("surahName",surah.getEnglishName());
//                i.putExtra("surahType",surah.getRevelationType());
//                i.putExtra("play",false);
                context.startActivity(i);

            }
        });

    }

    @Override
    public long getItemId(int position) {
        //  Surah surah = surahArrayList.get(position);

        return surahArrayList.get(position).getId();
    }

    public Object getItem(int position) {

        return surahArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return surahArrayList.size();
    }




    public class SurahViewHolder extends RecyclerView.ViewHolder
    {

        RowSurahBinding binding;

        public SurahViewHolder(View view) {
            super(view);
            binding = RowSurahBinding.bind(view);
        }



    }



}

