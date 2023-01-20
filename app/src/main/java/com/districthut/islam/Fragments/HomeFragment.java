package com.districthut.islam.Fragments;

import static android.content.Context.SENSOR_SERVICE;

import static com.mirfatif.noorulhuda.prayer.PrayerData.METHOD_LOCATIONS;
import static com.mirfatif.noorulhuda.prefs.MySettings.SETTINGS;

import android.Manifest;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.batoulapps.adhan.Coordinates;
import com.districthut.islam.Activities.calendar.CalendarActivity;
import com.districthut.islam.Activities.qibla.QuiblaCalculator;
import com.districthut.islam.Models.GPSTracker;
import com.districthut.islam.UserPreference;
import com.districthut.islam.Utils.AppManager;
import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.districthut.islam.Activities.qibla.QiblaActivity;
import com.districthut.islam.Models.Dua;
import com.mirfatif.noorulhuda.R;
import com.mirfatif.noorulhuda.databinding.CompassDialogBinding;
import com.mirfatif.noorulhuda.databinding.FragmentHomeBinding;
import com.districthut.islam.prayer.util.AppSettings;
import com.districthut.islam.prayer.util.PrayTime;
import com.mirfatif.noorulhuda.prayer.Compass;
import com.mirfatif.noorulhuda.prayer.PrayerData;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

public class HomeFragment extends Fragment {

    AppSettings settings;


    String Language = "";
    SharedPreferences SP ;
    private PendingIntent pendingIntent;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    int color = 0;
    boolean urdu,english,bahasha,transliteration;
    Dua d;

    Typeface arabicFont;
    Typeface urduFont;

    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final int REQUEST_CODE_PERMISSION = 2;

    private float currentDegree = 0f;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer, mMagnetometer;
    private float[] mLastAccelerometer = new float[3];
    private float[] mLastMagnetometer = new float[3];
    private float[] mR = new float[9];
    private float[] mOrientation = new float[3];
    private boolean mLastAccelerometerSet = false, switchView = false,
            pointerPosition = true, mLastMagnetometerSet = false, start = false, mapReady = false;
    private double previousAzimuthInDegrees = 0f;
    private long SensorSendTime;
    private float pointerFirstPositionX, pointerFirstPositionY, smallCircleRadius, newX, newY;
    private double lastRoll, lastPitch, lastTime;
    private GPSTracker gps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);



        SP = PreferenceManager.getDefaultSharedPreferences(getContext());
        Language = SP.getString("lang", "en");

        arabicFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/PDMS_Saleem_QuranFont-signed.ttf");
        urduFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/JameelNooriNastaleeq.ttf");

        urdu = SP.getBoolean("urduCheck",true);
        english = SP.getBoolean("englishCheck",true);
        bahasha = SP.getBoolean("bahasaCheck",false);
        transliteration = SP.getBoolean("transliteration",true);

        setDate();

        mSensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        urdu = SP.getBoolean("urduCheck",true);
        english = SP.getBoolean("englishCheck",true);
        bahasha = SP.getBoolean("bahasaCheck",false);
        transliteration = SP.getBoolean("transliteration",true);



        return binding.getRoot();
    }

    public void GetPrayers() {
        Compass mCompass = new Compass(getActivity());
        View compassView = mCompass.getCompassView();
        binding.compassBox.addView(compassView);
        binding.compassBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.compassBox.removeAllViews();
                binding.qiblaCardView.setVisibility(View.INVISIBLE);
                CompassDialogBinding compassDialogBinding = CompassDialogBinding.inflate(LayoutInflater.from(
                        getContext()
                ));
                compassDialogBinding.getRoot().addView(compassView);

                int width = (int) (200 * getContext().getResources().getDisplayMetrics().density + 0.5f);
                int height = (int) (200 * getContext().getResources().getDisplayMetrics().density + 0.5f);
                RelativeLayout.LayoutParams cellParams = new RelativeLayout.LayoutParams(width, height);
                mCompass.mCompassView.compass.setLayoutParams(cellParams);

//                int qiblaWidth = (int) (50 * getContext().getResources().getDisplayMetrics().density + 0.5f);
//                int qiblaHeight = (int) (50 * getContext().getResources().getDisplayMetrics().density + 0.5f);
//                mCompass.mCompassView.qibla.setLayoutParams(new RelativeLayout.LayoutParams(qiblaWidth, qiblaHeight));

                AlertDialog dialog =
                        new AlertDialog.Builder(getActivity())
                                .setView(compassDialogBinding.getRoot()).
                                create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));

                dialog.setOnDismissListener(dialogInterface -> {
                    binding.compassBox.removeAllViews();
                    ((ViewGroup)compassView.getParent()).removeView(compassView);

                    binding.qiblaCardView.setVisibility(View.VISIBLE);

                    mCompass.mCompassView.compass.setLayoutParams(
                            new RelativeLayout.LayoutParams(
                            (int) (60 * getContext().getResources().getDisplayMetrics().density + 0.5f),
                            (int) (60 * getContext().getResources().getDisplayMetrics().density + 0.5f)));

//                        int qiblaWidth = (int) (16 * getContext().getResources().getDisplayMetrics().density + 0.5f);
//                        int qiblaHeight = (int) (16 * getContext().getResources().getDisplayMetrics().density + 0.5f);
//                        mCompass.mCompassView.qibla.setLayoutParams(new RelativeLayout.LayoutParams(qiblaWidth, qiblaHeight));

                    binding.compassBox.addView(compassView);
                });

                dialog.show();
            }
        });

        PrayerData data = PrayerData.getPrayerData();
        UserPreference cache = UserPreference.getInstance(getContext());
            getAddress(Double.valueOf(cache.getString(UserPreference.PREF_LOCATION_LATITUDE,"0")), Double.valueOf(cache.getString(UserPreference.PREF_LOCATION_LONGITUDE,"0")));

        binding.fajarTime.setText(data.strTimes[0]);
        binding.sunriseTime.setText(data.strTimes[1]);
        binding.zuhrTime.setText(data.strTimes[2]);
        binding.asrTime.setText(data.strTimes[3]);
        binding.magribTime.setText(data.strTimes[4]);
        binding.ishaTime.setText(data.strTimes[5]);

        long leftTimer = data.nextPrayerTime - Calendar.getInstance().getTimeInMillis();

        if(data.nextPrayer == 0) {
            binding.fajarLeftLayout.setVisibility(View.VISIBLE);
            binding.fajarTimeLeft.setText(AppManager.getRemainingFormattedTime(leftTimer));
        } else if(data.nextPrayer == 1) {
            binding.sunriseLeftLayout.setVisibility(View.VISIBLE);
            binding.sunriseTimeLeft.setText(AppManager.getRemainingFormattedTime(leftTimer));
        } else if(data.nextPrayer == 2) {
            binding.zuhrLeftLayout.setVisibility(View.VISIBLE);
            binding.duhrTimeLeft.setText(AppManager.getRemainingFormattedTime(leftTimer));
        } else if(data.nextPrayer == 3) {
            binding.asrLeftLayout.setVisibility(View.VISIBLE);
            binding.asrTimeLeft.setText(AppManager.getRemainingFormattedTime(leftTimer));
        } else if(data.nextPrayer == 4) {
            binding.magribLeftLayout.setVisibility(View.VISIBLE);
            binding.magribTimeLeft.setText(AppManager.getRemainingFormattedTime(leftTimer));
        } else if(data.nextPrayer == 5) {
            binding.ishaLeftLayout.setVisibility(View.VISIBLE);
            binding.ishaTimeLeft.setText(AppManager.getRemainingFormattedTime(leftTimer));
        }
    }

/*

    public void GetPrayerName() {
        try {
            if (ActivityCompat.checkSelfPermission(getActivity(), mPermission)
                    != getContext().getPackageManager().PERMISSION_GRANTED) {
                Log.e("uncheck", "unchecked.");
//                binding.timer.setVisibility(View.INVISIBLE);
//                binding.prayerName.setVisibility(View.INVISIBLE);
//
//                binding.prayerTime.setVisibility(View.INVISIBLE);
//                binding.timeFormat.setVisibility(View.INVISIBLE);
//                binding.tvLocationMsg.setVisibility(View.VISIBLE);
//                binding.auto.setVisibility(View.VISIBLE);
                // If any permission above not allowed by user, this condition will
            }
            else {

                Calendar now = Calendar.getInstance(TimeZone.getDefault());
                now.setTimeInMillis(System.currentTimeMillis());
                // Set the alarm's trigger time to 8:30 a.m.
                int alarmIndex = 0;

                Calendar then = Calendar.getInstance(TimeZone.getDefault());
                then.setTimeInMillis(System.currentTimeMillis());

                AppSettings settings = AppSettings.getInstance(getActivity());

                double lat = settings.getLatFor(alarmIndex);
                double lng = settings.getLngFor(alarmIndex);
                LinkedHashMap<String, String> prayerTimes = PrayTime.getPrayerTimes(getActivity(), alarmIndex, lat, lng, PrayTime.TIME_24);
                List<String> prayerNames = new ArrayList<>(prayerTimes.keySet());
                if(lat != 0 && lng != 0)
                    getAddress(lat,lng);
                String nameOfPrayerFound = null;
                for (String prayer : prayerNames) {

                    then = getCalendarFromPrayerTime(then, prayerTimes.get(prayer));
                    nameOfPrayerFound = prayer;
                    if (then.after(now)) {
                        nameOfPrayerFound = prayer;
                        break;
                    }
                }
                DateFormat dateFormat = new SimpleDateFormat("hh:mm a");

                if(now.after(then)) {
                    if (nameOfPrayerFound.equalsIgnoreCase("Isha")) {

                        Calendar nextDayCalendar = Calendar.getInstance(TimeZone.getDefault());
                        nextDayCalendar.add(Calendar.DAY_OF_MONTH, 1);
                        LinkedHashMap<String, String> nextDayprayerTimes = PrayTime.getPrayerTimesByDate(getActivity(), alarmIndex, lat, lng, PrayTime.TIME_24, nextDayCalendar);
                        String timeForFajar = nextDayprayerTimes.get("Fajr");

                        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
                        try {
                            Date date = format.parse(timeForFajar);
                            nextDayCalendar.set(Calendar.HOUR_OF_DAY, date.getHours());
                            nextDayCalendar.set(Calendar.MINUTE, date.getMinutes());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

//                        binding.prayerName.setText("Fajr");
//                        binding.prayerTime.setText(timeForFajar);
//                        binding.timeFormat.setText("AM");
//
//                        binding.timer.setEndTime(nextDayCalendar.getTimeInMillis());
//                        binding.timer.setVisibility(View.VISIBLE);
//                        binding.prayerName.setVisibility(View.VISIBLE);
//
//                        binding.prayerTime.setVisibility(View.VISIBLE);
//                        binding.timeFormat.setVisibility(View.VISIBLE);
//                        binding.tvLocationMsg.setVisibility(View.INVISIBLE);
//                        binding.auto.setVisibility(View.INVISIBLE);

                        Log.e("prayer_found", nameOfPrayerFound);
                        Log.e("timeForFajar", timeForFajar);

                        if(nameOfPrayerFound.equalsIgnoreCase("fajr")) {
                            binding.fajarLeftLayout.setVisibility(View.VISIBLE);
                        } else if(nameOfPrayerFound.equalsIgnoreCase("Sunrise")) {
                            binding.sunriseLeftLayout.setVisibility(View.VISIBLE);
                        } else if(nameOfPrayerFound.equalsIgnoreCase("Dhuhr")) {
                            binding.zuhrLeftLayout.setVisibility(View.VISIBLE);
                        } else if(nameOfPrayerFound.equalsIgnoreCase("Asr")) {
                            binding.asrLeftLayout.setVisibility(View.VISIBLE);
                        } else if(nameOfPrayerFound.equalsIgnoreCase("Maghrib") || nameOfPrayerFound.equalsIgnoreCase("Sunset")) {
                            binding.magribLeftLayout.setVisibility(View.VISIBLE);
                        } else if(nameOfPrayerFound.equalsIgnoreCase("Isha")) {
                            binding.ishaLeftLayout.setVisibility(View.VISIBLE);
                        }

                        return;
                    } else {
                        Log.e("prayer_found_other", nameOfPrayerFound);
                    }
                } else {
                    Log.e("prayer_found_other", nameOfPrayerFound);
                }

                if(nameOfPrayerFound.equalsIgnoreCase("fajr")) {
                    binding.fajarLeftLayout.setVisibility(View.VISIBLE);
                } else if(nameOfPrayerFound.equalsIgnoreCase("Sunrise")) {
                    binding.sunriseLeftLayout.setVisibility(View.VISIBLE);
                } else if(nameOfPrayerFound.equalsIgnoreCase("Dhuhr")) {
                    binding.zuhrLeftLayout.setVisibility(View.VISIBLE);
                } else if(nameOfPrayerFound.equalsIgnoreCase("Asr")) {
                    binding.asrLeftLayout.setVisibility(View.VISIBLE);
                } else if(nameOfPrayerFound.equalsIgnoreCase("Maghrib") || nameOfPrayerFound.equalsIgnoreCase("Sunset")) {
                    binding.magribLeftLayout.setVisibility(View.VISIBLE);
                } else if(nameOfPrayerFound.equalsIgnoreCase("Isha")) {
                    binding.ishaLeftLayout.setVisibility(View.VISIBLE);
                }

//                binding.timer.setVisibility(View.VISIBLE);
//                binding.prayerName.setVisibility(View.VISIBLE);
//
//                binding.prayerTime.setVisibility(View.VISIBLE);
//                binding.timeFormat.setVisibility(View.VISIBLE);
//                binding.timer.setEndTime(then.getTime().getTime());
//                binding.prayerName.setText(nameOfPrayerFound);
//                String[] timing = dateFormat.format(then.getTime()).split(" ");
//                binding.prayerTime.setText(timing[0]);
//                binding.timeFormat.setText(timing[1]);
//                if (nameOfPrayerFound.equals("Sunset")) {
//                    binding.prayerName.setText("Maghrib");
//                }
//                binding.tvLocationMsg.setVisibility(View.INVISIBLE);
//                binding.auto.setVisibility(View.INVISIBLE);

                if(lat != 0 && lng != 0)
                    getAddress(lat,lng);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void GetPrayerNames() {
        try {
            if (ActivityCompat.checkSelfPermission(getActivity(), mPermission)
                    != getContext().getPackageManager().PERMISSION_GRANTED) {
                Log.e("uncheck", "unchecked.");
//                binding.timer.setVisibility(View.INVISIBLE);
//                binding.prayerName.setVisibility(View.INVISIBLE);
//
//                binding.prayerTime.setVisibility(View.INVISIBLE);
//                binding.timeFormat.setVisibility(View.INVISIBLE);
//                binding.tvLocationMsg.setVisibility(View.VISIBLE);
//                binding.auto.setVisibility(View.VISIBLE);
                // If any permission above not allowed by user, this condition will
            }
            else {

                Calendar now = Calendar.getInstance(TimeZone.getDefault());
                now.setTimeInMillis(System.currentTimeMillis());
                // Set the alarm's trigger time to 8:30 a.m.
                int alarmIndex = 0;

                Calendar then = Calendar.getInstance(TimeZone.getDefault());
                then.setTimeInMillis(System.currentTimeMillis());

                AppSettings settings = AppSettings.getInstance(getActivity());

                double lat = settings.getLatFor(alarmIndex);
                double lng = settings.getLngFor(alarmIndex);
                LinkedHashMap<String, String> prayerTimes = PrayTime.getPrayerTimes(getActivity(), alarmIndex, lat, lng, PrayTime.TIME_24);
                List<String> prayerNames = new ArrayList<>(prayerTimes.keySet());
                if(lat != 0 && lng != 0)
                    getAddress(lat,lng);

                DateFormat dateFormat = new SimpleDateFormat("hh:mm a");

                SimpleDateFormat format = new SimpleDateFormat("hh:mm");
                String nameOfPrayerFound = "";
                for (String prayer : prayerNames) {

                    then = getCalendarFromPrayerTime(then, prayerTimes.get(prayer));
                    long leftTime = then.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
                    if(prayer.equalsIgnoreCase("fajr")) {
                        binding.fajarTimeLeft.setText(getRemainingFormattedTime(leftTime));
                    } else if(prayer.equalsIgnoreCase("Sunrise")) {
                        binding.sunriseTimeLeft.setText(getRemainingFormattedTime(leftTime));
                    } else if(prayer.equalsIgnoreCase("Dhuhr")) {
                        binding.duhrTimeLeft.setText(getRemainingFormattedTime(leftTime));
                    } else if(prayer.equalsIgnoreCase("Asr")) {
                        binding.asrTimeLeft.setText(getRemainingFormattedTime(leftTime));
                    } else if(prayer.equalsIgnoreCase("Maghrib")) {
                        binding.magribTimeLeft.setText(getRemainingFormattedTime(leftTime));
                    } else if(prayer.equalsIgnoreCase("Isha")) {
                        binding.ishaTimeLeft.setText(getRemainingFormattedTime(leftTime));
                    }

                }


                binding.fajarTime.setText(dateFormat.format(format.parse(prayerTimes.get("Fajr"))));
                binding.zuhrTime.setText(dateFormat.format(format.parse(prayerTimes.get("Dhuhr"))));
                binding.asrTime.setText(dateFormat.format(format.parse(prayerTimes.get("Asr"))));
                binding.magribTime.setText(dateFormat.format(format.parse(prayerTimes.get("Maghrib"))));
                binding.ishaTime.setText(dateFormat.format(format.parse(prayerTimes.get("Isha"))));
                binding.sunriseTime.setText(dateFormat.format(format.parse(prayerTimes.get("Sunrise"))));



//                binding.tvLocationMsg.setVisibility(View.INVISIBLE);
//                binding.auto.setVisibility(View.INVISIBLE);
                if(lat != 0 && lng != 0)
                    getAddress(lat,lng);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

*/

    private Calendar getCalendarFromPrayerTime(Calendar cal, String prayerTime) {
        String[] time = prayerTime.split(":");
        cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(time[0]));
        cal.set(Calendar.MINUTE, Integer.valueOf(time[1]));
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    public int getColor(){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(130), rnd.nextInt(70), rnd.nextInt(100));
        return color;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    void setDate() {

        UmmalquraCalendar cal = new UmmalquraCalendar();

        binding.islamicDate.setText(
                String.format("%d %s, %d AH",
                        cal.get(Calendar.DAY_OF_MONTH),
                        cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, new Locale("en")),
                        cal.get(Calendar.YEAR))
        );

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("EEEE, dd MMMM", Locale.getDefault());
        String formattedDate = df.format(c);
        binding.currentDate.setText(formattedDate);

        View.OnClickListener dateClickListener = view -> startActivity(new Intent(getActivity(), CalendarActivity.class));
        binding.currentDate.setOnClickListener(dateClickListener);
        binding.islamicDate.setOnClickListener(dateClickListener);

    }

    public void getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            binding.city.setText(obj.getLocality());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}