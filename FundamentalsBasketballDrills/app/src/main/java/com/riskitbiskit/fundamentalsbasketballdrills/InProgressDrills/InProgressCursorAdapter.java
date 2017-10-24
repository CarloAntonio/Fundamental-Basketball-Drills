package com.riskitbiskit.fundamentalsbasketballdrills.InProgressDrills;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.riskitbiskit.fundamentalsbasketballdrills.Drills.DrillContract;
import com.riskitbiskit.fundamentalsbasketballdrills.R;

public class InProgressCursorAdapter extends CursorAdapter {
    public InProgressCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_drill, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView drillNameTV = (TextView) view.findViewById(R.id.item_drill_name);
        TextView drillCompletionTV = (TextView) view.findViewById(R.id.item_drill_completion);

        String drillName = cursor.getString(cursor.getColumnIndex(DrillContract.DrillListEntry.SKILL_NAME));
        int drillCompletion = cursor.getInt(cursor.getColumnIndex(DrillContract.DrillListEntry.SKILL_COMPLETION));

        String drillCompletionConversion = ConvertDrillCompletion(drillCompletion);

        drillNameTV.setText(drillName);
        drillCompletionTV.setText(drillCompletionConversion);
    }

    private String ConvertDrillCompletion(int drillCompletion) {
        switch (drillCompletion) {
            case 1:
                return "1/4";
            case 2:
                return "2/4";
            case 3:
                return "3/4";
            case 4:
                return "4/4";
            default:
                return "4/4";
        }
    }
}
