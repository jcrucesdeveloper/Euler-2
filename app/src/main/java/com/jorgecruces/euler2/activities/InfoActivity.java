package com.jorgecruces.euler2.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.jorgecruces.euler2.R;
import com.jorgecruces.euler2.sound.MediaPlayerReproducer;

/**
 * Info activity, it contains content info(Problems info), contactInfo, credits
 */
public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    public void onClickGoBackButton(View view) {
        MediaPlayerReproducer.getInstance().reproduceClickSound(this);
        finish();
    }

}