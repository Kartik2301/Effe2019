package com.example.android.effe2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class NewDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_details);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent i = new Intent(NewDetails.this,LaunchActivity.class);
        startActivity(i);
    }
}
