package com.example.unicash;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Use the current time as the default values for the picker.
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
// Create a new instance of TimePickerDialog and return it.
        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Set the activity to the Main Activity.
        RemindersAct activity = (RemindersAct) getActivity();
        // Invoke Main Activity's processTimePickerResult() method.
        activity.processTimePickerResult(hourOfDay, minute);

    }

}
