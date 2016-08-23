package io.djnr.backdrop.ui.spotlight;

import android.content.Context;

import java.util.List;

import io.djnr.backdrop.models.soundcloud.Playlist;

/**
 * Created by Dj on 8/20/2016.
 */
public interface ISpotlight {

    interface RequiredView {
        Context getAppContext();
        Context getActivityContext();
        void displaySpotlightPlaylists(List<Playlist> playlists);
    };

    interface ProvidedPresenter {
        void fetchSpotlight();
    };

    interface RequiredPresenter {
        Context getAppContext();
        Context getActivityContext();
        void setSpotlightPlaylists(List<Playlist> playlists);
    };

    interface ProvidedModel {
        void getSpotlightPlaylists();
    };
}
