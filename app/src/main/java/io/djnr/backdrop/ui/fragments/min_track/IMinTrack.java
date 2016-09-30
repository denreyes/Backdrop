package io.djnr.backdrop.ui.fragments.min_track;

import android.content.Context;
import android.graphics.Bitmap;

import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.ui.fragments.max_track.view.MaxTrackFragment;

/**
 * Created by Dj on 9/27/2016.
 */
public interface IMinTrack {

    public interface RequiredView{
        Context getAppContext();
        Context getActivityContext();
        void setMaxProgress(int mediaMax_new, int mediaPos_new);
        void setBackgroundArt(Bitmap bitmap);
        void setViews(String title, String username);
        void setImageControl(boolean b);
        void launchMaxFragment(MaxTrackFragment fragment);
    };

    public interface ProvidedPresenter{
        void initTrackServiceCallback();
        void playPause();
        void switchToMaxView();

        void updateOnPause(boolean isPlaying);
        void updateOnSkip(int currentPos);
        void updatePlayer(Playlist playlist, int currentPos);
    };

    public interface RequiredPresenter{
        Context getAppContext();
        Context getActivityContext();
    };

    public interface ProvidedModel{

    };
}
