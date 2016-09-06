package io.djnr.backdrop.ui.playlist;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;

import java.util.List;

import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.models.soundcloud.Track;

/**
 * Created by Dj on 8/20/2016.
 */
public interface IPlaylist {

    interface RequiredView{
        Context getAppContext();
        Context getActivityContext();
        Fragment getFragment();
        void setPlaylistRecycler(Playlist playlist);
        void setupViews(Playlist playlist);
    };

    interface ProvidedPresenter{
        void getDataFromIntent();
    };

    interface RequiredPresenter{
        Context getAppContext();
        Context getActivityContext();
        Fragment getFragment();
    };

    interface ProvidedModel{

    };
}
