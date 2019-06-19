package com.example.unicash;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

public class ReminderActivity extends ListActivity {
    private ReminderListAdapter mAdapter;
    private ReminderDBSupporter dbHelper = new ReminderDBSupporter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); mContext = this;
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.reminder_list);
        mAdapter = new ReminderListAdapter(this, dbHelper.getReminders());
        setListAdapter(mAdapter);
    }
    public void setReminderEnabled(long id, boolean isEnabled) {
        ReminderHelper.cancelReminders(this);
        ReminderModel model = dbHelper.getReminder(id);
        model.isEnabled = isEnabled;
        dbHelper.updateReminder(model);
        ReminderHelper.setReminders(this);
    }
    public void startReminderDetailsActivity(long id) {
        Intent intent = new Intent(this, ReminderDetails.class);
        intent.putExtra("id", id); startActivityForResult(intent, 0);
    }
    public void deleteReminder(long id) {
        final long reminderId = id;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please confirm")
                .setTitle("Delete set?")
                .setCancelable(true)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok", new OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int which) {
                        ReminderHelper.cancelReminders(mContext);
                        dbHelper.deleteReminder(reminderId);
                        mAdapter.setReminders(dbHelper.getReminders());
                        mAdapter.notifyDataSetChanged();
                        ReminderHelper.setReminders(mContext);
                    }
                }).show();
    }
}

