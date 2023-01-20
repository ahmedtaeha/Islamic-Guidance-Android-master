package com.districthut.islam.Activities.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.eltohamy.materialhijricalendarview.CalendarDay;
import com.github.eltohamy.materialhijricalendarview.DayViewDecorator;
import com.github.eltohamy.materialhijricalendarview.DayViewFacade;
import com.github.eltohamy.materialhijricalendarview.format.MonthArrayTitleFormatter;
import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;
import com.google.android.gms.common.util.Strings;
import com.google.android.material.snackbar.Snackbar;
import com.mirfatif.noorulhuda.R;
import com.mirfatif.noorulhuda.databinding.ActivityCalendarBinding;
import com.mirfatif.noorulhuda.databinding.ReminderDialogBinding;

import net.alhazmy13.hijridatepicker.date.hijri.HijriDatePickerDialog;
import net.alhazmy13.hijridatepicker.time.TimePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

public class CalendarActivity extends AppCompatActivity {

    ActivityCalendarBinding binding;
    ArrayList<Event> events;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalendarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Realm.init(this);
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<ReminderModel> reminderModels = realm.where(ReminderModel.class)
                .findAll();
        for(ReminderModel reminderModel : reminderModels) {
            Log.e("err->", reminderModel.getMessage());
        }
        realm.commitTransaction();

        binding.calendarView.setTopbarVisible(true);
        binding.calendarView.setTitleFormatter(new MonthArrayTitleFormatter(getResources().getTextArray(R.array.custom_months)));

        binding.calendarView.addDecorator(new DayViewDecorator() {
            private CalendarDay date;

            @Override
            public boolean shouldDecorate(CalendarDay day) {
                date = CalendarDay.today();
                return date != null && day.equals(date);
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new StyleSpan(Typeface.BOLD));
                view.addSpan(new RelativeSizeSpan(1.4f));
            }
        });




        events = new ArrayList<>();

        loadDates();
        DatesAdapter adapter = new DatesAdapter(this,events);
        binding.events.setAdapter(adapter);


    }

    void setAlarm(String event, UmmalquraCalendar now, Calendar selectedTime) {
        Calendar cal = new UmmalquraCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, selectedTime.get(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, selectedTime.get(Calendar.MINUTE));

        Intent intent = new Intent(CalendarActivity.this, CalendarAlarmReceiver.class);
        intent.putExtra("title", "Reminder");
        intent.putExtra("description", event);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S){
            alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    cal.getTimeInMillis(),
                    pendingIntent
            );
        } else {
            if(alarmManager.canScheduleExactAlarms()) {
                alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        cal.getTimeInMillis(), pendingIntent);
            } else {
                Intent settingsIntent = new Intent();
                intent.setAction(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
                startActivity(settingsIntent);
            }
        }
    }

    void loadDates() {
        UmmalquraCalendar now = new UmmalquraCalendar();
        // ------- Al-Hijira ------- //
        now.set(Calendar.DAY_OF_MONTH, 1);
        now.set(Calendar.MONTH, 0);
        addEvent("Al-Hijira", now);
        // ------- Lailat al Miraj ------- //
        now.set(Calendar.DAY_OF_MONTH, 27);
        now.set(Calendar.MONTH, UmmalquraCalendar.RAJAB);
        addEvent("Lailat-al-Miraj", now);
        // ------- Laylat al Baraat ------- //
        now.set(Calendar.DAY_OF_MONTH, 15);
        now.set(Calendar.MONTH, UmmalquraCalendar.SHAABAN);
        addEvent("Laylat-al-Baraat", now);
        // ------- Ramadan ------- //
        now.set(Calendar.DAY_OF_MONTH, 1);
        now.set(Calendar.MONTH, UmmalquraCalendar.RAMADHAN);
        addEvent("Ramadan (start)", now);
        // ------- Eid-Ul-Fitr ------- //
        now.set(Calendar.DAY_OF_MONTH, 1);
        now.set(Calendar.MONTH, UmmalquraCalendar.SHAWWAL);
        addEvent("Eid-ul-Fitr", now);
        // ------- Waqf Al Arafa ------- //
        now.set(Calendar.DAY_OF_MONTH, 9);
        now.set(Calendar.MONTH, UmmalquraCalendar.THUL_HIJJAH);
        addEvent("Al Arafa - Hajj", now);
        // ------- Eid ul Adha ------- //
        now.set(Calendar.DAY_OF_MONTH, 10);
        now.set(Calendar.MONTH, UmmalquraCalendar.THUL_HIJJAH);
        addEvent("Eid-ul-Adha", now);
    }

    void addEvent(String event, UmmalquraCalendar now) {
        Calendar cal = new UmmalquraCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        events.add(new Event(
                event,
                String.format(
                        "%d %s %d AH",
                        now.get(Calendar.DAY_OF_MONTH),
                        now.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH),
                        now.get(Calendar.YEAR)
                ),
                dateFormat.format(cal.getTime())));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calendar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.addReminder) {


            ReminderDialogBinding dialogBinding = ReminderDialogBinding.inflate(getLayoutInflater());
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setView(dialogBinding.getRoot())
                    .create();

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
            dialog.show();

            UmmalquraCalendar selectedDate = new UmmalquraCalendar();
            Calendar selectedTime = Calendar.getInstance();

            dialogBinding.dateBox.setOnClickListener(c-> {
                UmmalquraCalendar now = new UmmalquraCalendar();


                HijriDatePickerDialog dpd = HijriDatePickerDialog.newInstance(
                        (view, year, monthOfYear, dayOfMonth) -> {
                            selectedDate.set(UmmalquraCalendar.YEAR,year);
                            selectedDate.set(UmmalquraCalendar.MONTH,monthOfYear);
                            selectedDate.set(UmmalquraCalendar.DAY_OF_MONTH,dayOfMonth);

                            dialogBinding.dateBox.setText(
                                    String.format(
                                    "%s, %s %d",
                                            selectedDate.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ENGLISH),
                                            selectedDate.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH),
                                            selectedDate.get(Calendar.DAY_OF_MONTH))
                            );

                        },
                        now.get(UmmalquraCalendar.YEAR),
                        now.get(UmmalquraCalendar.MONTH),
                        now.get(UmmalquraCalendar.DAY_OF_MONTH));
                dpd.setAccentColor(getResources().getColor(R.color.green_primary));
                dpd.setMinDate(now);


                dpd.show(getSupportFragmentManager(), "HijriDatePickerDialog");
            });

            dialogBinding.timeBox.setOnClickListener(c-> {
                Calendar now = Calendar.getInstance();

                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        (view, hourOfDay, minute, second) -> {
                            selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            selectedTime.set(Calendar.MINUTE, minute);
                            DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                            dialogBinding.timeBox.setText(dateFormat.format(selectedTime.getTime()));
                            },
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE), false
                );
                tpd.setAccentColor(getResources().getColor(R.color.green_primary));

                tpd.show(getSupportFragmentManager(), "TimePickerDialog");
            });

            dialogBinding.addReminderBtn.setOnClickListener(c-> {
                if(Strings.isEmptyOrWhitespace(dialogBinding.memoBox.getText().toString())) {
                    Snackbar.make(dialogBinding.getRoot(),
                            "What do you want to remind yourself?", Snackbar.LENGTH_SHORT).show();
                    dialogBinding.memoBox.setError("Required");
                    return;
                }

                if(Strings.isEmptyOrWhitespace(dialogBinding.dateBox.getText().toString())) {
                    Snackbar.make(dialogBinding.getRoot(),
                            "When to remind you?", Snackbar.LENGTH_SHORT).show();
                    dialogBinding.dateBox.setError("Required");
                    return;
                }

                if(Strings.isEmptyOrWhitespace(dialogBinding.timeBox.getText().toString())) {
                    Snackbar.make(dialogBinding.getRoot(),
                            "Time to remind your?", Snackbar.LENGTH_SHORT).show();
                    dialogBinding.timeBox.setError("Required");
                    return;
                }

                ReminderModel reminderObj = new ReminderModel(
                        System.currentTimeMillis(),
                        selectedDate.get(UmmalquraCalendar.YEAR),
                        selectedDate.get(UmmalquraCalendar.DAY_OF_MONTH),
                        selectedDate.get(UmmalquraCalendar.MONTH),
                        selectedTime.get(Calendar.HOUR_OF_DAY),
                        selectedTime.get(Calendar.MINUTE),
                        dialogBinding.memoBox.getText().toString()
                );

                setAlarm(dialogBinding.memoBox.getText().toString(), selectedDate, selectedTime);


                realm.beginTransaction();
                realm.insertOrUpdate(reminderObj);
                realm.commitTransaction();

                Snackbar.make(binding.getRoot(),
                        "Reminder Added.", Snackbar.LENGTH_LONG).show();
                dialog.dismiss();
            });
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}