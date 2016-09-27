package io.djnr.backdrop.ui.fragments.playlist;

import android.content.Context;
import android.support.v4.app.Fragment;

import io.djnr.backdrop.models.soundcloud.Playlist;

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
