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

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class SponsorAdapter extends ArrayAdapter {
    private Activity context;
    //list of users
    List<DataforSponsors> Users;
    public SponsorAdapter(Activity context, List<DataforSponsors> Users) {
        super(context, R.layout.list_itemforsponsors, Users);
        this.context = context;
        this.Users = Users;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.list_itemforsponsors, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView role = (TextView) convertView.findViewById(R.id.role) ;
        ImageView imageView = (ImageView) convertView.findViewById(R.id.img_view);
        final DataforSponsors task = (DataforSponsors) getItem(position);
        name.setText(task.getNameofSponsor());
        List<String> l = task.getCategoriesoftheSponsor();
        String str="";
        for(int i=0;i<l.size();i++){
            if(i==l.size()-1){
                str = str + l.get(i);
            }else
            str = str +l.get(i)+ " ," ;
        }
        char ch = ',';
        role.setText(str);
        Glide.with(imageView.getContext())
                .load(task.getUrlofSponsor())
                .into(imageView);
        /*new DownloadImageTask(imageView)
        .execute(task.getUrlofSponsor());*/
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
