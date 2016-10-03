package io.djnr.backdrop.ui.fragments.max_track.presenter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.LinearInterpolator;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.djnr.backdrop.R;
import io.djnr.backdrop.interfaces.MinControllerDisplayer;
import io.djnr.backdrop.interfaces.TrackServiceProvider;
import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.models.soundcloud.Track;
import io.djnr.backdrop.services.TrackService;
import io.djnr.backdrop.ui.fragments.max_track.IMaxTrack;
import io.djnr.backdrop.ui.fragments.max_track.view.MaxTrackFragment;
import io.djnr.backdrop.ui.fragments.max_track.view.MaxTrackFragment.ControlUpdater;
import io.djnr.backdrop.ui.fragments.playlist.view.PlaylistAdapter;
import io.djnr.backdrop.utils.Utils;
import jp.wasabeef.blurry.Blurry;

/**
 * Created by Dj on 9/27/2016.
 */
public class MaxTrackPresenter implements IMaxTrack.ProvidedPresenter, IMaxTrack.RequiredPresenter {
    private static final String TAG = "MaxTrackPresenter";
    WeakReference<IMaxTrack.RequiredView> mView;
    IMaxTrack.ProvidedModel mModel;

    private Playlist mPlaylist;
    private Bitmap mArtBitmap;
    private Track mTrack;
    private int currentPos;
    private boolean isPlaying;

    private ControlUpdater mPlayerCallback;
    private TrackServiceProvider mTrackServiceCallback;
    private MinControllerDisplayer mDisplayerCallback;

    private Handler seekHandler = new Handler();

    public MaxTrackPresenter(IMaxTrack.RequiredView view) {
        mView = new WeakReference<IMaxTrack.RequiredView>(view);
    }

    public void setModel(IMaxTrack.ProvidedModel model) {
        this.mModel = model;
    }

    private IMaxTrack.RequiredView getView() throws NullPointerException {
        if (mView != null)
            return mView.get();
        else
            throw new NullPointerException("View is unavailable.");
    }

    @Override
    public Context getAppContext() {
        return getView().getAppContext();
    }

    @Override
    public Context getActivityContext() {
        return getView().getActivityContext();
    }

    @Override
    public Fragment getFragment() {
        return getView().getFragment();
    }

    @Override
    public void getArguments() {
        Bundle args = getFragment().getArguments();
        currentPos = args.getInt("CURRENT_POS");
        isPlaying = args.getBoolean("IS_PLAYING");
        mArtBitmap = args.getParcelable("BITMAP_IMAGE");
        mPlaylist = ((Playlist) args.getParcelable("PLAYLIST"));
        mTrack = mPlaylist.getTracks().get(currentPos);

        Log.i(TAG, "playing on prep: "+isPlaying);
    }

    @Override
    public void initCallbacks() {
        try {
            mTrackServiceCallback = (TrackServiceProvider) getActivityContext();
            mDisplayerCallback = (MinControllerDisplayer) getActivityContext();
            mPlayerCallback = (MaxTrackFragment.ControlUpdater) (((AppCompatActivity) getView().getActivityContext())
                    .getSupportFragmentManager()
                    .findFragmentById(R.id.player_container));
        } catch (ClassCastException e) {
            throw new ClassCastException("Fragment must implement PlayerUpdater");
        }
    }

    @Override
    public void setupViews() {
        mDisplayerCallback.hideMinController();

        getView().setTexts(mTrack.getTitle(), mTrack.getUser().getUsername());
        getView().setAlbumArt(mArtBitmap);
        getView().setupDiscAnimation();
        getView().setRecyclerAdapter(new PlaylistAdapter(mPlaylist, mTrackServiceCallback.getTrackService()));

        if (isPlaying) {
            getView().setFabPause();
            getView().playDiscAnimation();
            seekHandler.postDelayed(moveSeekThread, 200);
            getView().resetSeekbar();
        }
    }

    @Override
    public void userSeeked(int progress) {
        TrackService trackService = mTrackServiceCallback.getTrackService();
        trackService.seek(progress);
        getView().updateSeekbarProgress(progress);
    }

    @Override
    public void showMinController() {
        mDisplayerCallback.showMinController();
    }

    @Override
    public void pausePlay() {
        mPlayerCallback.updateOnPause(isPlaying);
        if (isPlaying) { //if playing pause
            getView().setFabPlay();
            getView().pauseDiscAnimation();
            isPlaying = false;
        } else { // if paused play
            getView().setFabPause();
            getView().playDiscAnimation();
            isPlaying = true;
        }
    }

    @Override
    public void skipNext() {
        this.currentPos++;
        mTrackServiceCallback.getTrackService().playNext();
        skip();
    }

    @Override
    public void skipPrev() {
        this.currentPos--;
        mTrackServiceCallback.getTrackService().playPrev();
        skip();
    }

    @Override
    public void tearDown() {
        seekHandler.removeCallbacks(moveSeekThread);
        Log.i(TAG, "playing on tear: "+isPlaying);
    }

    private Runnable moveSeekThread = new Runnable() {
        public void run() {
            TrackService trackService = mTrackServiceCallback.getTrackService();

            if (trackService.isPlaying()) {
                getView().setSeekbarProgress(trackService.getDuration(), trackService.getPosition());
            }

            seekHandler.postDelayed(this, 500); //Looping the thread after 0.1 second
        }
    };

    private void skip() {
        mPlayerCallback.updateOnSkip(currentPos);
        mTrack = mPlaylist.getTracks().get(currentPos);

        getView().endDiscAnimation();
        getView().setTexts(mTrack.getTitle(), mTrack.getUser().getUsername());
        setAlbumArt(Utils.largeSCImg(mTrack.getArtworkUrl()));

        seekHandler.removeCallbacks(moveSeekThread);
        getView().resetSeekbar();
        seekHandler.postDelayed(moveSeekThread, 500);
    }

    private void setAlbumArt(String imageUrl) {
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... params) {
                Looper.prepare();
                mArtBitmap = BitmapFactory.decodeResource(getActivityContext().getResources(), R.drawable.no_img);
                try {
                    mArtBitmap = Glide.with(getActivityContext())
                            .load(Utils.largeSCImg(params[0]))
                            .asBitmap().into(-1, -1).get();
                } catch (final ExecutionException e) {
                    Log.e(TAG, e.getMessage());
                } catch (final InterruptedException e) {
                    Log.e(TAG, e.getMessage());
                } catch (final NullPointerException e) {
                    Log.e(TAG, e.getMessage());
                }
                return mArtBitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                getView().setAlbumArt(bitmap);
                getView().setupDiscAnimation();
            }
        }.execute(imageUrl);
    }
}
