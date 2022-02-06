package com.jorgecruces.euler2.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.ads.AudienceNetworkAds;
import com.jorgecruces.euler2.R;
import com.jorgecruces.euler2.sound.MediaPlayerReproducer;

import java.util.Random;

/**
 * Entry point of the App, the screen with Euler image and Play Button
 */
public class MainActivity extends AppCompatActivity
{

    TextView textViewQuote, textViewTitle;
    ImageView imageViewInfoButton, imageViewPlayButton,imageViewEulerPrincipal, imageViewVolumeButton;

    private boolean soundOn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeAds();

        // Title and Quote
        textViewQuote = (TextView) findViewById(R.id.textViewQuote);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);

        // InfoButton, PlayButton,  and Euler image
        imageViewInfoButton = (ImageView) findViewById(R.id.buttonInfoActivity);
        imageViewPlayButton = (ImageView) findViewById(R.id.buttonPlay);
        imageViewEulerPrincipal = (ImageView) findViewById(R.id.imageViewEuler);
        imageViewVolumeButton = (ImageView) findViewById(R.id.volumeButton);

        soundOn = true;
        imageViewVolumeButton.setOnClickListener(view -> {onClickVolumeButton();});


        // At the start we fade and select a quote
        fadeInAnimation();
        selectQuote();


    }

    /**
     * Initialize Ads necessary to show ads
     */
    public void initializeAds()
    {
        AudienceNetworkAds.initialize(this);
    }

    /**
     * Go to NumbersLevelActivity
     * @param view the button being clicked
     */
    public void onClickPlay(View view)
    {
        // Sound
        MediaPlayerReproducer.getInstance().reproduceClickSound(this);

        Intent intentNumbersLevel = new Intent(getApplicationContext(), NumbersLevelsActivity.class);
        startActivity(intentNumbersLevel);

    }

    /**
     * Erase sharedPreferences level info and restart levels
     * @param view
     */
    public void onClickResetLevels(View view)
    {
        // Sound
        MediaPlayerReproducer.getInstance().reproduceClickSound(this);

        Dialog confirmResetButtonDialog = new Dialog(this);
        confirmResetButtonDialog.setContentView(R.layout.confirm_reset_level_dialog);

        Button confirmButton = (Button) confirmResetButtonDialog.findViewById(R.id.confirmResetLevelsButton);
        confirmButton.setOnClickListener(button -> resetLevels(confirmResetButtonDialog));

        confirmResetButtonDialog.show();
    }

    /**
     * Go to InfoActivity
     * @param view
     */
    public void onClickInfoActivity(View view)
    {
        // Sound
        MediaPlayerReproducer.getInstance().reproduceClickSound(this);

        Intent intentNumbersLevel = new Intent(getApplicationContext(), InfoActivity.class);
        startActivity(intentNumbersLevel);
    }

    /**
     * Reset the levels, erase SharedPreferences memory
     * @param confirmResetButtonDialog Dialog of the button
     */
    public void resetLevels(Dialog confirmResetButtonDialog)
    {
        SharedPreferences sharedPreferences = getSharedPreferences(String.valueOf(R.string.app_name),MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Reset the levels to 0
        editor.putInt(getString(R.string.level), 1);
        editor.apply();

        Toast.makeText(this, "Levels were reset", Toast.LENGTH_SHORT).show();
        confirmResetButtonDialog.dismiss();
    }
    /**
     * Fade animation of the textViews and the Euler image
     */
    public void fadeInAnimation()
    {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this,R.anim.fadein);
        textViewTitle.startAnimation(fadeInAnimation);
        textViewQuote.startAnimation(fadeInAnimation);
        imageViewEulerPrincipal.startAnimation(fadeInAnimation);
    }

    /**
     * Select a random quote to put in the MainActivity
     */
    public void selectQuote()
    {
        String[] quotes = getResources().getStringArray(R.array.quotes_from_euler);
        Random randomNumber = new Random();
        int quoteNumber = randomNumber.nextInt(quotes.length);
        String quoteToDisplay = quotes[quoteNumber];
        textViewQuote.setText(quoteToDisplay);
    }

    /**
     * Enable or disable sound and change the image of the VolumeButton
     */
    public void onClickVolumeButton()
    {
        // Enable sound or disable
        MediaPlayerReproducer.getInstance().changeAudioReproducing();

        // Change image of VolumeButton
        if (soundOn)
        {
            this.imageViewVolumeButton.setImageResource(R.drawable.volume_off);
            soundOn = false;
        }
        else
        {
            this.imageViewVolumeButton.setImageResource(R.drawable.volume_on);
            soundOn = true;
        }
    }





}