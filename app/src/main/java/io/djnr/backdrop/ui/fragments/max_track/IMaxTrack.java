package io.djnr.backdrop.ui.fragments.max_track;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;

import io.djnr.backdrop.R;
import io.djnr.backdrop.ui.fragments.playlist.view.PlaylistAdapter;
import jp.wasabeef.blurry.Blurry;

/**
 * Created by Dj on 9/27/2016.
 */
public interface IMaxTrack {

    public interface RequiredView {
        Context getAppContext();
        Context getActivityContext();
        Fragment getFragment();

        void setAlbumArt(Bitmap bitmap);
        void setNextAlbumArt(Bitmap bitmap);
        void setPrevAlbumArt(Bitmap bitmap);
        void setBlurredAlbumArt(Blurry.BitmapComposer composer);
        void setTexts(String title, String username);
        void setRecyclerAdapter(PlaylistAdapter playlistAdapter);

        void setFabPlay();
        void setFabPause();

        void setSeekbarProgress(int timeProgress, int timeMax);
        void updateSeekbarProgress(int progress);
        void resetSeekbar();

        void setupDiscAnimation();
        void playDiscAnimation();
        void pauseDiscAnimation();
        void endDiscAnimation();
    }

    ;

    public interface ProvidedPresenter {
        void getArguments();

        void initCallbacks();

        void setupViews();

        void setupSeekbar();

        void userSeeked(int progress);

        void showMinController();

        void pausePlay();

        void skip();

        void skipNext();

        void skipPrev();

        void tearDown();

        void setPosition(int posn);
    }

    ;

    public interface RequiredPresenter {
        Context getAppContext();

        Context getActivityContext();

        Fragment getFragment();
    }

    ;

    public interface ProvidedModel {

    }

    ;
}
