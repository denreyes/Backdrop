package io.djnr.backdrop.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Dj on 10/18/2016.
 */
public class AmbientService extends Service implements MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnSeekCompleteListener,
        MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener {
    private static final String TAG = "AmbientService";

    private MediaPlayer mAmbientPlayer;

    @Override
    public void onCreate() {
        super.onCreate();

        mAmbientPlayer = new MediaPlayer();
        mAmbientPlayer.setOnCompletionListener(this);
        mAmbientPlayer.setOnErrorListener(this);
        mAmbientPlayer.setOnPreparedListener(this);
        mAmbientPlayer.setOnBufferingUpdateListener(this);
        mAmbientPlayer.setOnSeekCompleteListener(this);
        mAmbientPlayer.reset();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String mAudioLink = intent.getExtras().getString("AUDIO_LINK");
        Log.i(TAG, "HOIIIIIIIIIIIIIIIIIIIIIII!!!!!!!!!!!!!!!!!!!!!");
        mAmbientPlayer.reset();
        if (!mAmbientPlayer.isPlaying()) {
            try {
                mAmbientPlayer.setDataSource(mAudioLink);
                mAmbientPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mAmbientPlayer != null) {
            if (mAmbientPlayer.isPlaying()) {
                mAmbientPlayer.stop();
            }
            mAmbientPlayer.release();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        stopMedia();
        stopSelf();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                Toast.makeText(this,
                        "Media Error: not Valid for Progressive Playback " + extra,
                        Toast.LENGTH_LONG).show();
                break;
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                Toast.makeText(this,
                        "Media Error: Server Died " + extra,
                        Toast.LENGTH_LONG).show();
                break;
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                Toast.makeText(this,
                        "Media Error: Unknown " + extra,
                        Toast.LENGTH_LONG).show();
                break;
        }

        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        playMedia();
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {

    }

    public void playMedia() {
        if (!mAmbientPlayer.isPlaying()) {
            mAmbientPlayer.start();
            mAmbientPlayer.setLooping(true);
        }
    }

    public void stopMedia() {
        if (mAmbientPlayer.isPlaying()) {
            mAmbientPlayer.stop();
        }
    }
}
