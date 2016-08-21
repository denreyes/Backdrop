package io.djnr.backdrop.dagger.module;

import android.app.Application;
import android.media.AudioManager;
import android.media.MediaPlayer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.djnr.backdrop.dagger.scope.FragmentScope;

/**
 * Created by Dj on 8/21/2016.
 */
@Module
public class AppModule {

    private Application app;

    public AppModule(Application app){
        this.app = app;
    }

    @Provides
    @Singleton
    public Application providesApplication(){
        return app;
    }

    @Provides
    @Singleton
    MediaPlayer providesMediaPlayer(){
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
