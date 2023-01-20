package com.districthut.islam.Activities.Quran.model;

import com.mirfatif.noorulhuda.R;

import java.util.ArrayList;

/**
 * Created by Mian Brothers on 10/16/2017.
 */

public class Translations {
    public String name, lang;
    public int imageId;

    public Translations() {

    }

    public Translations(String name, String lan, int id) {
        this.name = name;
        this.lang = lan;
        this.imageId = id;
    }

    public ArrayList<Translations> GetTranslations(boolean search) {
        ArrayList<Translations> transList = new ArrayList<>();
        if (search)

            transList.add(new Translations("Original Book", "Arabic", R.drawable.quran_icon));
        transList.add(new Translations("Sherif Ahmeti", "Albanian", R.drawable.albania_icon));
        transList.add(new Translations("Feti Mehdiu", "Albanian", R.drawable.albania_icon));
        transList.add(new Translations("Efendi Nahi", "Albanian", R.drawable.albania_icon));
        transList.add(new Translations("Musayev", "Azerbaijani", R.drawable.azerbaijan_icon));
        transList.add(new Translations("Məmmədəliyev & Bünyadov", "Azerbaijani", R.drawable.azerbaijan_icon));
        transList.add(new Translations("জহুরুল হক", "Bengali", R.drawable.bangladesh_icon));
        transList.add(new Translations("মুহিউদ্দীন খান", "Bengali", R.drawable.bangladesh_icon));
        transList.add(new Translations("Korkut", "Bosnian", R.drawable.bosnia_icon));
        transList.add(new Translations("Mlivo", "Bosnian", R.drawable.bosnia_icon));
        transList.add(new Translations("Теофанов", "Bulgarian", R.drawable.bulgaria_icon));
        transList.add(new Translations("Ma Jian", "Chinese", R.drawable.china_icon));
        transList.add(new Translations("Hrbek", "Czech", R.drawable.czech_icon));
        transList.add(new Translations("Nykl", "Czech", R.drawable.czech_icon));
        transList.add(new Translations("Ahmed Ali", "English", R.drawable.eng_icon));
        transList.add(new Translations("Ahmed Raza Khan", "English", R.drawable.eng_icon));
        transList.add(new Translations("Arberry", "English", R.drawable.eng_icon));
        transList.add(new Translations("Daryabadi", "English", R.drawable.eng_icon));
        transList.add(new Translations("Hilali & Khan", "English", R.drawable.eng_icon));
        transList.add(new Translations("Itani", "English", R.drawable.eng_icon));
        transList.add(new Translations("Maududi", "English", R.drawable.eng_icon));
        transList.add(new Translations("Mubarakpuri", "English", R.drawable.eng_icon));
        transList.add(new Translations("Pickthall", "English", R.drawable.eng_icon));
        transList.add(new Translations("Qarai", "English", R.drawable.eng_icon));
        transList.add(new Translations("Qaribullah & Darwish", "English", R.drawable.eng_icon));
        transList.add(new Translations("Saheeh International", "English", R.drawable.eng_icon));
        transList.add(new Translations("Sarwar", "English", R.drawable.eng_icon));
        transList.add(new Translations("Shakir", "English", R.drawable.eng_icon));
        transList.add(new Translations("Wahiduddin Khan", "English", R.drawable.eng_icon));
        transList.add(new Translations("Yusuf Ali", "English", R.drawable.eng_icon));
        transList.add(new Translations("Hamidullah", "French", R.drawable.france_icon));
        transList.add(new Translations("Abu Rida", "German", R.drawable.germany_icon));
        transList.add(new Translations("Bubenheim & Elyas", "German", R.drawable.germany_icon));
        transList.add(new Translations("Khoury", "German", R.drawable.germany_icon));
        transList.add(new Translations("Zaidan", "German", R.drawable.germany_icon));
        transList.add(new Translations("फ़ारूक़ ख़ान & अहमद", "Hindi", R.drawable.india_icon));
        transList.add(new Translations("फ़ारूक़ ख़ान & नदवी", "Hindi", R.drawable.india_icon));
        transList.add(new Translations("Bahasa Indonesia", "Indonesian", R.drawable.indonesia_icon));
        transList.add(new Translations("Quraish Shihab", "Indonesian", R.drawable.indonesia_icon));
        transList.add(new Translations("Tafsir Jalalayn", "Indonesian", R.drawable.indonesia_icon));
        transList.add(new Translations("Unknown J", "Japanese", R.drawable.japan_icon));
        transList.add(new Translations("Unknown", "Korean", R.drawable.korea_flag));
        transList.add(new Translations("Basmeih", "Malay", R.drawable.malaysia_icon));
        transList.add(new Translations("അബ്ദുല്\u200D ഹമീദ് & പറപ്പൂര്\u200D", "Malayalam", R.drawable.india_icon));
        transList.add(new Translations("കാരകുന്ന് & എളയാവൂര്", "Malayalam", R.drawable.india_icon));
        transList.add(new Translations("Einar Berg", "Norwegian", R.drawable.norway_flag));
        transList.add(new Translations("عبدالولي", "Pashto", R.drawable.afghanistan_icon));
        transList.add(new Translations("Mahdi Elahi Ghomshei", "Persian", R.drawable.iran_flag));
        transList.add(new Translations("Hussain Ansarian", "Persian", R.drawable.iran_flag));
        transList.add(new Translations("AbdolMohammad Ayati P", "Persian", R.drawable.iran_flag));
        transList.add(new Translations("Abolfazl Bahrampour", "Persian", R.drawable.iran_flag));
        transList.add(new Translations("Mostafa Khorramdel", "Persian", R.drawable.iran_flag));
        transList.add(new Translations("Baha'oddin Khorramshahi", "Persian", R.drawable.iran_flag));
        transList.add(new Translations("Mohammad Sadeqi Tehrani", "Persian", R.drawable.iran_flag));
        transList.add(new Translations("Mohammad Mahdi Fooladvand", "Persian", R.drawable.iran_flag));
        transList.add(new Translations("Sayyed Jalaloddin Mojtabavi", "Persian", R.drawable.iran_flag));
        transList.add(new Translations("Mohammad Kazem Moezzi", "Persian", R.drawable.iran_flag));
        transList.add(new Translations("Naser Makarem Shirazi", "Persian", R.drawable.iran_flag));
        transList.add(new Translations("Józefa Bielawskiego", "Polish", R.drawable.poland_icon));
        transList.add(new Translations("Samir El-Hayek", "Portuguese", R.drawable.portugal_icon));
        transList.add(new Translations("George Grigore", "Romanian", R.drawable.romania_icon));
        transList.add(new Translations("Абу Адель", "Russian", R.drawable.russia_icon));
        transList.add(new Translations("Аль-Мунтахаб", "Russian", R.drawable.russia_icon));
        transList.add(new Translations("Крачковский", "Russian", R.drawable.russia_icon));
        transList.add(new Translations("Кулиев", "Russian", R.drawable.russia_icon));
        transList.add(new Translations("Кулиев + ас-Саади", "Russian", R.drawable.russia_icon));
        transList.add(new Translations("Османов", "Russian", R.drawable.russia_icon));
        transList.add(new Translations("Порохова", "Russian", R.drawable.russia_icon));
        transList.add(new Translations("Саблуков", "Russian", R.drawable.russia_icon));
        transList.add(new Translations("Taj Mehmood Amroti", "Sindhi", R.drawable.pk_icon));
        transList.add(new Translations("Mahmud Muhammad Abduh", "Somali", R.drawable.somalia_icon));
        transList.add(new Translations("Raúl González Bórnez", "Spanish", R.drawable.spain_icon));
        transList.add(new Translations("Julio Cortes", "Spanish", R.drawable.spain_icon));
        transList.add(new Translations("Muhammad Isa García", "Spanish", R.drawable.spain_icon));
        transList.add(new Translations("Ali Muhsin Al-Barwani", "Swahili", R.drawable.kenya_flag));
        transList.add(new Translations("Knut Bernström", "Swedish", R.drawable.sweden_icon));
        transList.add(new Translations("AbdolMohammad Ayati", "Tajik", R.drawable.tajikistan_icon));
        transList.add(new Translations("Jan Turst Foundation", "Tamil", R.drawable.india_icon));
        transList.add(new Translations("King Fahad Quran Complex", "Thai", R.drawable.thailand_icon));
        transList.add(new Translations("Abdulbaki Golpinarli", "Turkish", R.drawable.turkey_icon));
        transList.add(new Translations("Alİ Bulaç", "Turkish", R.drawable.turkey_icon));
        transList.add(new Translations("Muhammet Abay", "Turkish", R.drawable.turkey_icon));
        transList.add(new Translations("Diyanet Isleri", "Turkish", R.drawable.turkey_icon));
        transList.add(new Translations("Diyanet Vakfi", "Turkish", R.drawable.turkey_icon));
        transList.add(new Translations("Edip Yüksel", "Turkish", R.drawable.turkey_icon));
        transList.add(new Translations("Elmalili Hamdi Yazir", "Turkish", R.drawable.turkey_icon));
        transList.add(new Translations("Yasar Nuri Ozturk", "Turkish", R.drawable.turkey_icon));
        transList.add(new Translations("Suat Yildirim", "Turkish", R.drawable.turkey_icon));
        transList.add(new Translations("Suleyman Ates", "Turkish", R.drawable.turkey_icon));
        transList.add(new Translations("ابوالاعلی مودودی", "اُردو", R.drawable.pk_icon));
        transList.add(new Translations("احمد رضا خان", "اُردو", R.drawable.pk_icon));
        transList.add(new Translations("احمد علی", "اُردو", R.drawable.pk_icon));
        transList.add(new Translations("جالندہری", "اُردو", R.drawable.pk_icon));
        transList.add(new Translations("طاہر القادری", "اُردو", R.drawable.pk_icon));
        transList.add(new Translations("علامہ جوادی", "اُردو", R.drawable.pk_icon));
        transList.add(new Translations("محمد جوناگڑھی", "اُردو", R.drawable.pk_icon));
        transList.add(new Translations("محمد حسین نجفی", "اُردو", R.drawable.pk_icon));
        transList.add(new Translations("Мухаммад Содик", "Uzbek", R.drawable.uzbekistan_icon));

        return transList;
    }

    public String GetTranslationName(String Name) {
        switch (Name) {
            case "arabic":
                return "Original Book";
            case "sq.ahmeti":
                return "Sherif Ahmeti";
            case "sq.mehdiu":
                return "Feti Mehdiu";
            case "sq.nahi":
                return "Efendi Nahi";
            case "az.musayev":
                return "Musayev";
            case "az.mammadaliyev":
                return "Məmmədəliyev & Bünyadov";
            case "bn.hoque":
                return "জহুরুল হক";
            case "bn.bengali":
                return "মুহিউদ্দীন খান";
            case "bs.korkut":
                return "Korkut";
            case "bs.mlivo":
                return "Mlivo";
            case "bg.theophanov":
                return "Теофанов";
            case "zh.jian":
                return "Ma Jian";
            case "cs.hrbek":
                return "Hrbek";
            case "cs.nykl":
                return "Nykl";
            case "en.ahmedali":
                return "Ahmed Ali";
            case "en.ahmedraza":
                return "Ahmed Raza Khan";
            case "en.arberry":
                return "Arberry";
            case "en.daryabadi":
                return "Daryabadi";
            case "en.hilali":
                return "Hilali & Khan";
            case "en.itani":
                return "Itani";
            case "en.maududi":
                return "Maududi";
            case "en.mubarakpuri":
                return "Mubarakpuri";
            case "en.pickthall":
                return "Pickthall";
            case "en.qaribullah":
                return "Qaribullah & Darwish";
            case "en.sahih":
                return "Saheeh International";
            case "en.sarwar":
                return "Sarwar";
            case "en.shakir":
                return "Shakir";
            case "en.wahiduddin":
                return "Wahiduddin Khan";
            case "en.yusufali":
                return "Yusuf Ali";
            case "fr.hamidullah":
                return "Hamidullah";
            case "de.aburida":
                return "Abu Rida";
            case "de.bubenheim":
                return "Bubenheim & Elyas";
            case "de.khoury":
                return "Khoury";
            case "de.zaidan":
                return "Zaidan";
            case "hi.farooq":
                return "फ़ारूक़ ख़ान & अहमद";
            case "hi.hindi":
                return "फ़ारूक़ ख़ान & नदवी";
            case "id.indonesian":
                return "Bahasa Indonesia";
            case "id.muntakhab":
                return "Quraish Shihab";
            case "id.jalalayn":
                return "Tafsir Jalalayn";
            case "ja.japanese":
                return "Unknown J";
            case "ko.korean":
                return "Unknown K";
            case "ms.basmeih":
                return "Basmeih";
            case "ml.abdulhameed":
                return "അബ്ദുല്\u200D ഹമീദ് & പറപ്പൂര്\u200D";
            case "ml.karakunnu":
                return "കാരകുന്ന് & എളയാവൂര്";
            case "no.berg":
                return "Einar Berg";
            case "ps.abdulwali":
                return "عبدالولي";
            case "fa.ghomshei":
                return "Mahdi Elahi Ghomshei";
            case "fa.ansarian":
                return "Hussain Ansarian";
            case "fa.ayati":
                return "AbdolMohammad Ayati P";
            case "fa.bahrampour":
                return "Abolfazl Bahrampour";
            case "fa.khorramdel":
                return "Mostafa Khorramdel";
            case "fa.khorramshahi":
                return "Baha'oddin Khorramshahi";
            case "fa.sadeqi":
                return "Mohammad Sadeqi Tehrani";
            case "fa.fooladvand":
                return "Mohammad Mahdi Fooladvand";
            case "fa.mojtabavi":
                return "Sayyed Jalaloddin Mojtabavi";
            case "fa.moezzi":
                return "Mohammad Kazem Moezzi";
            case "fa.makarem":
                return "Naser Makarem Shirazi";
            case "pl.bielawskiego":
                return "Józefa Bielawskiego";
            case "pt.elhayek":
                return "Samir El-Hayek";
            case "ro.grigore":
                return "George Grigore";
            case "ru.abuadel":
                return "Абу Адель";
            case "ru.muntahab":
                return "Аль-Мунтахаб";
            case "ru.krachkovsky":
                return "Крачковский";
            case "ru.kuliev":
                return "Кулиев";
            case "ru.kuliev-alsaadi":
                return "Кулиев + ас-Саади";
            case "ru.osmanov":
                return "Османов";
            case "ru.porokhova":
                return "Порохова";
            case "ru.sablukov":
                return "Саблуков";
            case "sd.amroti":
                return "Taj Mehmood Amroti";
            case "so.abduh":
                return "Mahmud Muhammad Abduh";
            case "es.bornez":
                return "Raúl González Bórnez";
            case "es.cortes":
                return "Julio Cortes";
            case "es.garcia":
                return "Muhammad Isa García";
            case "sw.barwani":
                return "Ali Muhsin Al-Barwani";
            case "sv.bernstrom":
                return "Knut Bernström";
            case "tg.ayati":
                return "AbdolMohammad Ayati";
            case "ta.tamil":
                return "Jan Turst Foundation";
            case "th.thai":
                return "King Fahad Quran Complex";
            case "tr.golpinarli":
                return "Abdulbaki Golpinarli";
            case "tr.bulac":
                return "Alİ Bulaç";
            case "tr.transliteration":
                return "Muhammet Abay";
            case "tr.diyanet":
                return "Diyanet Isleri";
            case "tr.vakfi":
                return "Diyanet Vakfi";
            case "tr.yuksel":
                return "Edip Yüksel";
            case "tr.yazir":
                return "Elmalili Hamdi Yazir";
            case "tr.ozturk":
                return "Yasar Nuri Ozturk";
            case "tr.yildirim":
                return "Suat Yildirim";
            case "tr.ates":
                return "Suleyman Ates";
            case "ur.maududi":
                return "ابوالاعلی مودودی";
            case "ur.kanzuliman":
                return "احمد رضا خان";
            case "ur.ahmedali":
                return "احمد علی";
            case "ur.jalandhry":
                return "جالندہری";
            case "ur.qadri":
                return "طاہر القادری";
            case "ur.jawadi":
                return "علامہ جوادی";
            case "ur.junagarhi":
                return "محمد جوناگڑھی";
            case "ur.najafi":
                return "محمد حسین نجفی";
            case "uz.sodik":
                return "Мухаммад Содик";
            default:
                return "";
        }
    }


    public String GetTranslationCode(String Name) {
        switch (Name) {
            case "Original Book":
                return "arabic";
            case "Sherif Ahmeti":
                return "sq.ahmeti";
            case "Feti Mehdiu":
                return "sq.mehdiu";
            case "Efendi Nahi":
                return "sq.nahi";
            case "Musayev":
                return "az.musayev";
            case "Məmmədəliyev & Bünyadov":
                return "az.mammadaliyev";
            case "জহুরুল হক":
                return "bn.hoque";
            case "মুহিউদ্দীন খান":
                return "bn.bengali";
            case "Korkut":
                return "bs.korkut";
            case "Mlivo":
                return "bs.mlivo";
            case "Теофанов":
                return "bg.theophanov";
            case "Ma Jian":
                return "zh.jian";
            case "Hrbek":
                return "cs.hrbek";
            case "Nykl":
                return "cs.nykl";
            case "Ahmed Ali":
                return "en.ahmedali";
            case "Ahmed Raza Khan":
                return "en.ahmedraza";
            case "Arberry":
                return "en.arberry";
            case "Daryabadi":
                return "en.daryabadi";
            case "Hilali & Khan":
                return "en.hilali";
            case "Itani":
                return "en.itani";
            case "Maududi":
                return "en.maududi";
            case "Mubarakpuri":
                return "en.mubarakpuri";
            case "Pickthall":
                return "en.pickthall";
            case "Qaribullah & Darwish":
                return "en.qaribullah";
            case "Saheeh International":
                return "en.sahih";
            case "Sarwar":
                return "en.sarwar";
            case "Shakir":
                return "en.shakir";
            case "Wahiduddin Khan":
                return "en.wahiduddin";
            case "Yusuf Ali":
                return "en.yusufali";
            case "Hamidullah":
                return "fr.hamidullah";
            case "Abu Rida":
                return "de.aburida";
            case "Bubenheim & Elyas":
                return "de.bubenheim";
            case "Khoury":
                return "de.khoury";
            case "Zaidan":
                return "de.zaidan";
            case "फ़ारूक़ ख़ान & अहमद":
                return "hi.farooq";
            case "फ़ारूक़ ख़ान & नदवी":
                return "hi.hindi";
            case "Bahasa Indonesia":
                return "id.indonesian";
            case "Quraish Shihab":
                return "id.muntakhab";
            case "Tafsir Jalalayn":
                return "id.jalalayn";
            case "Unknown J":
                return "ja.japanese";
            case "Unknown K":
                return "ko.korean";
            case "Basmeih":
                return "ms.basmeih";
            case "അബ്ദുല്\u200D ഹമീദ് & പറപ്പൂര്\u200D":
                return "ml.abdulhameed";
            case "കാരകുന്ന് & എളയാവൂര്":
                return "ml.karakunnu";
            case "Einar Berg":
                return "no.berg";
            case "عبدالولي":
                return "ps.abdulwali";
            case "Mahdi Elahi Ghomshei":
                return "fa.ghomshei";
            case "Hussain Ansarian":
                return "fa.ansarian";
            case "AbdolMohammad Ayati P":
                return "fa.ayati";
            case "Abolfazl Bahrampour":
                return "fa.bahrampour";
            case "Mostafa Khorramdel":
                return "fa.khorramdel";
            case "Baha'oddin Khorramshahi":
                return "fa.khorramshahi";
            case "Mohammad Sadeqi Tehrani":
                return "fa.sadeqi";
            case "Mohammad Mahdi Fooladvand":
                return "fa.fooladvand";
            case "Sayyed Jalaloddin Mojtabavi":
                return "fa.mojtabavi";
            case "Mohammad Kazem Moezzi":
                return "fa.moezzi";
            case "Naser Makarem Shirazi":
                return "fa.makarem";
            case "Józefa Bielawskiego":
                return "pl.bielawskiego";
            case "Samir El-Hayek":
                return "pt.elhayek";
            case "George Grigore":
                return "ro.grigore";
            case "Абу Адель":
                return "ru.abuadel";
            case "Аль-Мунтахаб":
                return "ru.muntahab";
            case "Крачковский":
                return "ru.krachkovsky";
            case "Кулиев":
                return "ru.kuliev";
            case "Кулиев + ас-Саади":
                return "ru.kuliev-alsaadi";
            case "Османов":
                return "ru.osmanov";
            case "Порохова":
                return "ru.porokhova";
            case "Саблуков":
                return "ru.sablukov";
            case "Taj Mehmood Amroti":
                return "sd.amroti";
            case "Mahmud Muhammad Abduh":
                return "so.abduh";
            case "Raúl González Bórnez":
                return "es.bornez";
            case "Julio Cortes":
                return "es.cortes";
            case "Muhammad Isa García":
                return "es.garcia";
            case "Ali Muhsin Al-Barwani":
                return "sw.barwani";
            case "Knut Bernström":
                return "sv.bernstrom";
            case "AbdolMohammad Ayati":
                return "tg.ayati";
            case "Jan Turst Foundation":
                return "ta.tamil";
            case "King Fahad Quran Complex":
                return "th.thai";
            case "Abdulbaki Golpinarli":
                return "tr.golpinarli";
            case "Alİ Bulaç":
                return "tr.bulac";
            case "Muhammet Abay":
                return "tr.transliteration";
            case "Diyanet Isleri":
                return "tr.diyanet";
            case "Diyanet Vakfi":
                return "tr.vakfi";
            case "Edip Yüksel":
                return "tr.yuksel";
            case "Elmalili Hamdi Yazir":
                return "tr.yazir";
            case "Yasar Nuri Ozturk":
                return "tr.ozturk";
            case "Suat Yildirim":
                return "tr.yildirim";
            case "Suleyman Ates":
                return "tr.ates";
            case "ابوالاعلی مودودی":
                return "ur.maududi";
            case "احمد رضا خان":
                return "ur.kanzuliman";
            case "احمد علی":
                return "ur.ahmedali";
            case "جالندہری":
                return "ur.jalandhry";
            case "طاہر القادری":
                return "ur.qadri";
            case "علامہ جوادی":
                return "ur.jawadi";
            case "محمد جوناگڑھی":
                return "ur.junagarhi";
            case "محمد حسین نجفی":
                return "ur.najafi";
            case "Мухаммад Содик":
                return "uz.sodik";

            default:
                return "";
        }
    }
}

