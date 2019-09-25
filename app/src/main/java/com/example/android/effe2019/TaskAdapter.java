package com.example.android.effe2019;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends ArrayAdapter {
    private Activity context;
    //list of users
    List<Data> Users;
    public TaskAdapter(Activity context, List<Data> Users) {
        super(context, R.layout.list_item_homefragment, Users);
        this.context = context;
        this.Users = Users;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.list_item_homefragment, parent, false);
        }
        TextView titleTextView = (TextView) convertView.findViewById(R.id.updates);
        TextView dateTextView = (TextView) convertView.findViewById(R.id.date);
        TextView noteTextView = (TextView) convertView.findViewById(R.id.desc);

        Data task = (Data) getItem(position);

        titleTextView.setText(task.getTitle());
        dateTextView.setText(task.getDate());
        noteTextView.setText(task.getNote());

        return convertView;
    }
}
