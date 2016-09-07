package io.djnr.backdrop.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.djnr.backdrop.dagger.module.TrackServiceModule;
import io.djnr.backdrop.models.soundcloud.Track;
import io.djnr.backdrop.ui.App;
import io.djnr.backdrop.utils.Config;

/**
 * Created by Dj on 8/31/2016.
 */
public class TrackService extends Service implements
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener {
    private static final String TAG = "TrackService";

    @Inject
    MediaPlayer player;

    private List<Track> songs;
    private int songPosn;

    private final IBinder musicBind = new MusicBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        songPosn = 0;

        setupComponent();
        initMusicPlayer();
    }


    private void setupComponent() {
        App.get(getBaseContext())
                .getAppComponent()
                .getTrackServiceComponent(new TrackServiceModule(this))
                .inject(this);
    }

    public void initMusicPlayer() {
        player.setOnPreparedListener(this);
        player.setOnCompletionListener(this);
        player.setOnErrorListener(this);
    }

    public void setList(List<Track> theSongs) {
        songs = theSongs;
    }

    public class MusicBinder extends Binder {
        public TrackService getService() {
            return TrackService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        player.stop();
        player.release();
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    public void setSong(int songIndex) {
        songPosn = songIndex;
    }

    public void playSong() {
        player.reset();

        try {
            player.setDataSource(songs.get(songPosn).getStreamUrl() + "?client_id=" + Config.SC_CLIENT_KEY);
        } catch (Exception e) {
            Log.e(TAG, "Error setting data source", e);
        }

        player.prepareAsync();
    }

    public void playPrev() {
        songPosn--;
        if (songPosn < 0)
            songPosn = songs.size() - 1;
        playSong();
    }

    public void playNext() {
        songPosn++;
        if (songPosn >= songs.size())
            songPosn = 0;
        playSong();
    }

    public int getPosition() {
        return player.getCurrentPosition();
    }

    public int getDuration() {
        return player.getDuration();
    }

    public boolean isPlaying() {
        return player.isPlaying();
    }

    public void pausePlayer() {
        player.pause();
    }

    public void seek(int posn) {
        player.seekTo(posn);
    }

    public void go() {
        player.start();
    }
}
