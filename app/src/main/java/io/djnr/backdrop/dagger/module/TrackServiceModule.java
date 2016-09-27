package io.djnr.backdrop.dagger.module;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.djnr.backdrop.dagger.scope.ServiceScope;
import io.djnr.backdrop.services.TrackService;

/**
 * Created by Dj on 8/31/2016.
 */
@Module
public class TrackServiceModule {
    private TrackService service;

    public TrackServiceModule(TrackService service){
        this.service = service;
    }

    @Provides
    @ServiceScope
    TrackService providesTrackService(){
        return service;
    }

    @Provides
    @ServiceScope
    MediaPlayer providesMediaPlayer(){
        Log.i("TrackService", "providesMediaPlayer: ");
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (mp.isPlaying()) {
                    mp.pause();
                } else {
                    mp.start();
                }
            }
        });
        return mediaPlayer;
    }
}
