package com.example.android.effe2019;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.util.List;

public class TeamAdapter extends ArrayAdapter {
    private Activity context;
    //list of users
    List<DataforTeam> Users;
    public TeamAdapter(Activity context, List<DataforTeam> Users) {
        super(context, R.layout.list_itemforteam, Users);
        this.context = context;
        this.Users = Users;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.list_itemforteam, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.profile_name);
        TextView role = (TextView) convertView.findViewById(R.id.designation) ;
        ImageView imageView = (ImageView) convertView.findViewById(R.id.profile_img);
        ImageButton imageButton = (ImageButton) convertView.findViewById(R.id.phone);

        final DataforTeam task = (DataforTeam) getItem(position);
        name.setText(task.getNameofTeamMember());
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = String.valueOf(task.getContactofTeamMember());
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+s));
                context.startActivity(callIntent);
            }
        });
        role.setText(task.getPositionofTeamMember());
        Glide.with(imageView.getContext())
                .load(task.getUrlofTeamMember())
                .into(imageView);
        if(position == 0){
            CardView cv = (CardView) convertView.findViewById(R.id.card);
            cv.setCardBackgroundColor(Color.parseColor("#FFE0B2"));

        }
        return convertView;
    }
}
