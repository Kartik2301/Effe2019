package com.example.android.effe2019;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class TaskAdapter extends ArrayAdapter {
    private Activity context;
    //list of users
    List<Data> Users;
    public TaskAdapter(Activity context, List<Data> Users) {
        super(context, R.layout.list_itemfordevs, Users);
        this.context = context;
        this.Users = Users;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.list_itemfordevs, parent, false);
        }
        TextView titleTextView = (TextView) convertView.findViewById(R.id.profile_name);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.profile_img) ;
        ImageButton button = (ImageButton) convertView.findViewById(R.id.gotogit);
        TextView designation = (TextView) convertView.findViewById(R.id.designation);
        //TextView dateTextView = (TextView) convertView.findViewById(R.id.date);
        //TextView noteTextView = (TextView) convertView.findViewById(R.id.desc);

        final Data task = (Data) getItem(position);

        titleTextView.setText(task.getNameofDev());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = task.getGithubDev();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });
        designation.setText(task.getDesignationDev());
        int[] mTestArray = {R.drawable.rahul_image, R.drawable.akshit_image, R.drawable.kartik_image, R.drawable.icon};
        imageView.setImageResource(mTestArray[position]);
//        new DownloadImageTask(imageView)
//                .execute(task.getImageofDev());


//        dateTextView.setText(task.getNameofDev());
//        noteTextView.setText(task.getNameofDev());

        return convertView;
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
