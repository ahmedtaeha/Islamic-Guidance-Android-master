package com.districthut.islam.Activities.Quran;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mirfatif.noorulhuda.R;

import java.util.ArrayList;

public class TilawatRecitationActivity extends AppCompatActivity {
    ArrayList<String> names = new ArrayList<>();
    SharedPreferences.Editor editor;
    SharedPreferences pref;
    String reciter="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilawat_translation);
        ListView listView = (ListView)findViewById(R.id.translations_LV1);
        getSupportActionBar().setTitle("Reciters");
        names.add("Abdullah Awad al-Juhani");
        names.add("Abdullah Basfar");
        names.add("Abdur-Rahman as-Sudais");
        names.add("Ali Abdur-Rahman al-Huthaify");
        names.add("AbdulMuhsin al-Qasim");
        names.add("AbdulBari ath-Thubaity");
        names.add("Ahmed ibn Ali al-Ajmy");
        names.add("AbdulAzeez al-Ahmad");
        names.add("Abdullah Ali Jabir [Taraweeh]");
        names.add("AbdulBaset AbdulSamad [Murattal]");
        names.add("AbdulWadud Haneef");
        names.add("Aziz Alili");
        names.add("AbdulBaset AbdulSamad [Mujawwad]");
        names.add("Al-Hussayni Al-'Azazy (with Children)");
        names.add("Abdullah Ali Jabir");
        names.add("Abdullah Khayat");
        names.add("Adel Kalbani");
        names.add("AbdulKareem Al Hazmi");
        names.add("Abdul-Mun'im Abdul-Mubdi'");
        names.add("Abdur-Rashid Sufi");
        names.add("Ahmad al-Huthaify");
        names.add("Abu Bakr al-Shatri ");
        names.add("Abdullah Matroud");
        names.add("Akram Al-Alaqmi");
        names.add("Ali Hajjaj Alsouasi");
        names.add("Bandar Baleela");
        names.add("Dr. Shawqy Hamed");
        names.add("Fares Abbad");
        names.add("Hani ar-Rifai");
        names.add("Hamad Sinan");
        names.add("Hatem Farid");
        names.add("Ibrahim Al-Jibrin");
        names.add("Imad Zuhair Hafez");
        names.add("Ibrahim Al Akhdar");
        names.add("Idrees Abkar");
        names.add("Khalid al-Qahtani");
        names.add("Khalid Al Ghamdi");
        names.add("Mishari Rashid al-`Afasy");
        names.add("Muhammad Siddiq al-Minshawi");
        names.add("Muhammad Jibreel");
        names.add("Muhammad al-Mehysni");
        names.add("Muhammad Siddiq al-Minshawi [Mujawwad]");
        names.add("Muhammad al-Luhaidan");
        names.add("Maher al-Muaiqly");
        names.add("Muhammad Abdul-Kareem");
        names.add("Mustafa al-`Azawi");
        names.add("Muhammad Hassan");
        names.add("Mostafa Ismaeel");
        names.add("Muhammad Sulaiman Patel");
        names.add("Mohammad Ismaeel Al-Muqaddim");
        names.add("Muhammad Ayyoob [Taraweeh]");
        names.add("Masjid Quba Taraweeh 1434");
        names.add("Mahmoud Khaleel Al-Husary");
        names.add("Mahmood Ali Al-Bana");
        names.add("Nabil ar-Rifai");
        names.add("Nasser Al Qatami");
        names.add("Tawfeeq ibn Sa`id as-Sawa'igh");
        names.add("Wadee Hammadi Al Yamani");
        names.add("Yasser ad-Dussary");
        names.add("Sa`ud ash-Shuraym");
        names.add("Sahl Yasin");
        names.add("Salah Bukhatir");
        names.add("Sudais and Shuraym");
        names.add("Saleh al Taleb");
        names.add("Salah al-Budair");
        names.add("Salah Al-Hashim");

        pref = getApplicationContext().getSharedPreferences("QuranPref", 0); // 0 - for private mode
        editor = pref.edit();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.recitations_listview, R.id.reciterName, names) {

            int selectedPosition = names.indexOf(GetReciterName(pref.getString("reciter","")));

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.recitations_listview, null);
                    RadioButton r = (RadioButton)v.findViewById(R.id.reciterCheck);
                }
                TextView tv = (TextView)v.findViewById(R.id.reciterName);
                tv.setText(names.get(position));
                RadioButton r = (RadioButton)v.findViewById(R.id.reciterCheck);
                r.setChecked(position == selectedPosition);
                r.setTag(position);
                v.setTag(position);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedPosition = (Integer)v.getTag();
                        notifyDataSetChanged();
                        editor.putString("reciter",GetReciterCode(names.get(position)));
                        editor.apply();
                        reciter = names.get(position);
                    }
                });
                r.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectedPosition = (Integer)view.getTag();
                        notifyDataSetChanged();
                        editor.putString("reciter",GetReciterCode(names.get(position)));
                        editor.apply();
                        reciter = names.get(position);
                    }
                });
                return v;
            }

        };
        listView.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    String GetReciterName(String Name){
        switch(Name){
            case "abdullaah_3awwaad_al-juhaynee":
                return "Abdullah Awad al-Juhani";
            case "abdullaah_basfar":
                return "Abdullah Basfar";
            case "abdurrahmaan_as-sudays":
                return "Abdur-Rahman as-Sudais";
            case "huthayfi":
                return "Ali Abdur-Rahman al-Huthaify";
            case "abdul_muhsin_alqasim":
                return "AbdulMuhsin al-Qasim";
            case "thubaity":
                return "AbdulBari ath-Thubaity";
            case "ahmed_ibn_3ali_al-3ajamy":
                return "Ahmed ibn Ali al-Ajmy";
            case "abdulazeez_al-ahmad":
                return "AbdulAzeez al-Ahmad";
            case "abdullaah_alee_jaabir":
                return "Abdullah Ali Jabir [Taraweeh]";
            case "abdul_basit_murattal":
                return "AbdulBaset AbdulSamad [Murattal]";
            case "abdulwadood_haneef":
                return "AbdulWadud Haneef";
            case "aziz_alili":
                return "Aziz Alili";
            case "abdulbaset_mujawwad":
                return "AbdulBaset AbdulSamad [Mujawwad]";
            case "alhusaynee_al3azazee_with_children":
                return "Al-Hussayni Al-'Azazy (with Children)";
            case "abdullaah_alee_jaabir_studio":
                return "Abdullah Ali Jabir";
            case "khayat":
                return "Abdullah Khayat";
            case "adel_kalbani":
                return "Adel Kalbani";
            case "abdulkareem_al_hazmi":
                return "AbdulKareem Al Hazmi";
            case "abdulmun3im_abdulmubdi2":
                return "Abdul-Mun'im Abdul-Mubdi'";
            case "abdurrashid_sufi":
                return "Abdur-Rashid Sufi";
            case "ahmad_alhuthayfi":
                return "Ahmad al-Huthaify";
            case "abu_bakr_ash-shatri_tarawee7":
                return "Abu Bakr al-Shatri ";
            case "abdullah_matroud":
                return "Abdullah Matroud";
            case "akram_al_alaqmi":
                return "Akram Al-Alaqmi";
            case "ali_hajjaj_alsouasi":
                return "Ali Hajjaj Alsouasi";
            case "bandar_baleela":
                return "Bandar Baleela";
            case "dr.shawqy_7amed/murattal":
                return "Dr. Shawqy Hamed [Murattal]a";
            case "fares":
                return "Fares Abbad";
            case "rifai":
                return "Hani ar-Rifai";
            case "hamad_sinan":
                return "Hamad Sinan";
            case "hatem_farid/collection":
                return "Hatem Farid";
            case "jibreen":
                return "Ibrahim Al-Jibrin";
            case "imad_zuhair_hafez":
                return "Imad Zuhair Hafez";
            case "ibrahim_al_akhdar":
                return "Ibrahim Al Akhdar";
            case "idrees_akbar":
                return "Idrees Abkar";
            case "khaalid_al-qahtaanee":
                return "Khalid al-Qahtani";
            case "khalid_alghamdi":
                return "Khalid Al Ghamdi";
            case "mishaari_raashid_al_3afaasee":
                return "Mishari Rashid al-`Afasy";
            case "muhammad_siddeeq_al-minshaawee":
                return "Muhammad Siddiq al-Minshawi";
            case "muhammad_jibreel/complete":
                return "Muhammad Jibreel";
            case "mehysni":
                return "Muhammad al-Mehysni";
            case "minshawi_mujawwad":
                return "Muhammad Siddiq al-Minshawi [Mujawwad]";
            case "muhammad_alhaidan":
                return "Muhammad al-Luhaidan";
            case "maher_256":
                return "Maher al-Muaiqly";
            case "muhammad_abdulkareem":
                return "Muhammad Abdul-Kareem";
            case "mustafa_al3azzawi":
                return "Mustafa al-`Azawi";
            case "mu7ammad_7assan":
                return "Muhammad Hassan";
            case "mostafa_ismaeel":
                return "Mostafa Ismaeel";
            case "":
                return "Mohamed Al-Tablawi";
            case "muhammad_patel":
                return "Muhammad Sulaiman Patel";
            case "mohammad_ismaeel_almuqaddim":
                return "Mohammad Ismaeel Al-Muqaddim";
            case "muhammad_ayyoob_hq":
                return "Muhammad Ayyoob [Taraweeh]";
            case "masjid_quba_1434":
                return "Masjid Quba Taraweeh 1434";
            case "mahmood_khaleel_al-husaree_iza3a":
                return "Mahmoud Khaleel Al-Husary";
            case "mahmood_ali_albana":
                return "Mahmood Ali Al-Bana";
            case "nabil_rifa3i":
                return "Nabil ar-Rifai";
            case "nasser_bin_ali_alqatami":
                return "Nasser Al Qatami";
            case "tawfeeq_bin_saeed-as-sawaaigh":
                return "Tawfeeq ibn Sa`id as-Sawa'igh";
            case "wadee_hammadi_al-yamani":
                return "Wadee Hammadi Al Yamani";
            case "yasser_ad-dussary":
                return "Yasser ad-Dussary";
            case "sa3ood_al-shuraym":
                return "Sa`ud ash-Shuraym";
            case "sahl_yaaseen":
                return "Sahl Yasin";
            case "salaah_bukhaatir":
                return "Salah Bukhatir";
            case "sodais_and_shuraim":
                return "Sudais and Shuraym";
            case "saleh_al_taleb":
                return "Saleh al Taleb";
            case "salahbudair":
                return "Salah al-Budair";
            case "salah_alhashim":
                return "Salah Al-Hashim";
            default:
                return "";
        }
    }

    String GetReciterCode(String Name){
        switch(Name){
            case "Abdullah Awad al-Juhani":
                return "abdullaah_3awwaad_al-juhaynee";
            case "Abdullah Basfar":
                return "abdullaah_basfar";
            case "Abdur-Rahman as-Sudais":
                return "abdurrahmaan_as-sudays";
            case "Ali Abdur-Rahman al-Huthaify":
                return "huthayfi";
            case "AbdulMuhsin al-Qasim":
                return "abdul_muhsin_alqasim";
            case "AbdulBari ath-Thubaity":
                return "thubaity";
            case "Ahmed ibn Ali al-Ajmy":
                return "ahmed_ibn_3ali_al-3ajamy";
            case "AbdulAzeez al-Ahmad":
                return "abdulazeez_al-ahmad";
            case "Abdullah Ali Jabir [Taraweeh]":
                return "abdullaah_alee_jaabir";
            case "AbdulBaset AbdulSamad [Murattal]":
                return "abdul_basit_murattal";
            case "AbdulWadud Haneef":
                return "abdulwadood_haneef";
            case "Aziz Alili":
                return "aziz_alili";
            case "AbdulBaset AbdulSamad [Mujawwad]":
                return "abdulbaset_mujawwad";
            case "Al-Hussayni Al-'Azazy (with Children)":
                return "alhusaynee_al3azazee_with_children";
            case "Abdullah Ali Jabir":
                return "abdullaah_alee_jaabir_studio";
            case "Abdullah Khayat":
                return "khayat";
            case "Adel Kalbani":
                return "adel_kalbani";
            case "AbdulKareem Al Hazmi":
                return "abdulkareem_al_hazmi";
            case "Abdul-Mun'im Abdul-Mubdi'":
                return "abdulmun3im_abdulmubdi2";
            case "Abdur-Rashid Sufi":
                return "abdurrashid_sufi";
            case "Ahmad al-Huthaify":
                return "ahmad_alhuthayfi";
            case "Abu Bakr al-Shatri ":
                return "abu_bakr_ash-shatri_tarawee7";
            case "Abdullah Matroud":
                return "abdullah_matroud";
            case "Akram Al-Alaqmi":
                return "akram_al_alaqmi";
            case "Ali Hajjaj Alsouasi":
                return "ali_hajjaj_alsouasi";
            case "Bandar Baleela":
                return "bandar_baleela";
            case "Dr. Shawqy Hamed [Murattal]a":
                return "dr.shawqy_7amed/murattal";
            case "Fares Abbad":
                return "fares";
            case "Hani ar-Rifai":
                return "rifai";
            case "Hamad Sinan":
                return "hamad_sinan";
            case "Hatem Farid":
                return "hatem_farid/collection";
            case "Ibrahim Al-Jibrin":
                return "jibreen";
            case "Imad Zuhair Hafez":
                return "imad_zuhair_hafez";
            case "Ibrahim Al Akhdar":
                return "ibrahim_al_akhdar";
            case "Idrees Abkar":
                return "idrees_akbar";
            case "Khalid al-Qahtani":
                return "khaalid_al-qahtaanee";
            case "Khalid Al Ghamdi":
                return "khalid_alghamdi";
            case "Mishari Rashid al-`Afasy":
                return "mishaari_raashid_al_3afaasee";
            case "Muhammad Siddiq al-Minshawi":
                return "muhammad_siddeeq_al-minshaawee";
            case "Muhammad Jibreel":
                return "muhammad_jibreel/complete";
            case "Muhammad al-Mehysni":
                return "mehysni";
            case "Muhammad Siddiq al-Minshawi [Mujawwad]":
                return "minshawi_mujawwad";
            case "Muhammad al-Luhaidan":
                return "muhammad_alhaidan";
            case "Maher al-Muaiqly":
                return "maher_256";
            case "Muhammad Abdul-Kareem":
                return "muhammad_abdulkareem";
            case "Mustafa al-`Azawi":
                return "mustafa_al3azzawi";
            case "Muhammad Hassan":
                return "mu7ammad_7assan";
            case "Mostafa Ismaeel":
                return "mostafa_ismaeel";
            case "Muhammad Sulaiman Patel":
                return "muhammad_patel";
            case "Mohammad Ismaeel Al-Muqaddim":
                return "mohammad_ismaeel_almuqaddim";
            case "Muhammad Ayyoob [Taraweeh]":
                return "muhammad_ayyoob_hq";
            case "Masjid Quba Taraweeh 1434":
                return "masjid_quba_1434";
            case "Mahmoud Khaleel Al-Husary":
                return "mahmood_khaleel_al-husaree_iza3a";
            case "Mahmood Ali Al-Bana":
                return "mahmood_ali_albana";
            case "Nabil ar-Rifai":
                return "nabil_rifa3i";
            case "Nasser Al Qatami":
                return "nasser_bin_ali_alqatami";
            case "Tawfeeq ibn Sa`id as-Sawa'igh":
                return "tawfeeq_bin_saeed-as-sawaaigh";
            case "Wadee Hammadi Al Yamani":
                return "wadee_hammadi_al-yamani";
            case "Yasser ad-Dussary":
                return "yasser_ad-dussary";
            case "Sa`ud ash-Shuraym":
                return "sa3ood_al-shuraym";
            case "Sahl Yasin":
                return "sahl_yaaseen";
            case "Salah Bukhatir":
                return "salaah_bukhaatir";
            case "Sudais and Shuraym":
                return "sodais_and_shuraim";
            case "Saleh al Taleb":
                return "saleh_al_taleb";
            case "Salah al-Budair":
                return "salahbudair";
            case "Salah Al-Hashim":
                return "salah_alhashim";
            default:
                return "";
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if(reciter.isEmpty() || reciter.equals(""))
            {
                editor.putString("reciter","abdurrahmaan_as-sudays");
                editor.commit();
            }
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}