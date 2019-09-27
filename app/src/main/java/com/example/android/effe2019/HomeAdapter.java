package com.example.android.effe2019;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HomeAdapter extends ArrayAdapter {
    private Activity context;
    //list of users
    List<DataforHome> Users;
    public HomeAdapter(Activity context, List<DataforHome> Users) {
        super(context, R.layout.list_item_homefragment, Users);
        this.context = context;
        this.Users = Users;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.list_item_homefragment, parent, false);
        }
        TextView title = (TextView) convertView.findViewById(R.id.updates);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView desc = (TextView) convertView.findViewById(R.id.desc);
        final DataforHome task = (DataforHome) getItem(position);
        //long timeStamp = task.getTimeStampofEvent();

        title.setText(task.getTitle());
        desc.setText(task.getDesciprionofEvent());
        //date.setText(convertTime(timeStamp));

        return convertView;
    }
    public String convertTime(long time){
        Date date = new Date(time);
        DateFormat dateFormat = new SimpleDateFormat("hh:mm a dd-MM-yyyy");
        return dateFormat.format(date);
    }

}
