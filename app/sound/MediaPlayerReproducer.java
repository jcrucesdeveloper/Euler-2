package com.jorgecruces.euler1.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.jorgecruces.euler1.R;

/**
 * Singleton that reproduce the sound across the App
 */
public class MediaPlayerReproducer {

    private static final MediaPlayerReproducer mp = new MediaPlayerReproducer();
    private boolean isAudioReproducing = true;

    private MediaPlayerReproducer()
    {

    }

    /**
     * Change between reproduce sound or do nothing
     */
    public void changeAudioReproducing() {
        if (isAudioReproducing)
        {
            isAudioReproducing = false;
        } else
        {
            isAudioReproducing = true;
        }
    }

    public static MediaPlayerReproducer getInstance()
    {
        return mp;
    }

    /**
     * Reproduce click Sound
     * @param context activity
     */
    public void reproduceClickSound(Context context)
    {
        reproduceSound(context, R.raw.click);
    }

    /**
     * Reproduce Win Sound
     * @param context activity
     */
    public void reproduceWinSound(Context context)
    {
        reproduceSound(context, R.raw.win_sound);
    }

    /**
     * Reproduce sound
     * @param context the activity where we reproduce the sound
     * @param R the sound file from Resources
     */
    private void reproduceSound(Context context, int R)
    {
        if(!isAudioReproducing)
        {
            return;
        }

        MediaPlayer mp = MediaPlayer.create(context, R);
        try
        {
            mp.start();
        } catch (IllegalStateException e)
        {
            e.printStackTrace();
        }


    }

}
