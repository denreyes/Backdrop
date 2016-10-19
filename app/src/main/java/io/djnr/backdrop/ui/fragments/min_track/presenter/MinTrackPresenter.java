package io.djnr.backdrop.ui.fragments.min_track.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;

import io.djnr.backdrop.R;
import io.djnr.backdrop.interfaces.TrackServiceProvider;
import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.models.soundcloud.Track;
import io.djnr.backdrop.services.TrackService;
import io.djnr.backdrop.ui.fragments.max_track.view.MaxTrackFragment;
import io.djnr.backdrop.ui.fragments.min_track.IMinTrack;
import io.djnr.backdrop.ui.fragments.playlist.view.PlaylistFragment;
import io.djnr.backdrop.utils.Utils;
import jp.wasabeef.blurry.Blurry;

/**
 * Created by Dj on 9/27/2016.
 */
public class MinTrackPresenter implements IMinTrack.ProvidedPresenter, IMinTrack.RequiredPresenter {
    private static final String TAG = "MinTrackPresenter";

    private WeakReference<IMinTrack.RequiredView> mView;
    private IMinTrack.ProvidedModel mModel;
    private Handler seekHandler = new Handler();
    private TrackServiceProvider mTrackServiceCallback;

    private Playlist mPlaylist;
    private int currentPos;
    private boolean isPlaying;
    private Bitmap mArtBitmap;

    public MinTrackPresenter(IMinTrack.RequiredView view) {
        mView = new WeakReference<IMinTrack.RequiredView>(view);
        isPlaying = false;
    }

    public void setModel(IMinTrack.ProvidedModel model) {
        this.mModel = model;
    }

    private IMinTrack.RequiredView getView() throws NullPointerException {
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
    public void initTrackServiceCallback() {
        try {
            mTrackServiceCallback = (TrackServiceProvider) getActivityContext();
        } catch (ClassCastException e) {
            throw new ClassCastException("Fragment must implement PlayerUpdater");
        }
    }

    @Override
    public void playPause() {
        TrackService service = mTrackServiceCallback.getTrackService();
        if (isPlaying) { //if playing pause
            service.pausePlayer();
            getView().setImagePausePlay(true);
            isPlaying = false;
            seekHandler.removeCallbacks(moveSeekThread);
        } else { // if paused play
            service.go();
            getView().setImagePausePlay(false);
            isPlaying = true;
            seekHandler.postDelayed(moveSeekThread, 200);
        }
    }

    @Override
    public void switchToMaxView() {
        Bundle args = new Bundle();
        args.putParcelable("PLAYLIST", mPlaylist);
        args.putInt("CURRENT_POS", currentPos);
        args.putBoolean("IS_PLAYING", isPlaying);
        args.putParcelable("BITMAP_IMAGE", mArtBitmap);

        MaxTrackFragment maxTrackFragment = new MaxTrackFragment();
        maxTrackFragment.setArguments(args);

        getView().launchMaxFragment(maxTrackFragment);
    }

    private Runnable moveSeekThread = new Runnable() {
        public void run() {
            TrackService trackService = mTrackServiceCallback.getTrackService();

            if (trackService.isPlaying()) {
                getView().setMaxProgress(trackService.getDuration(), trackService.getPosition());
            }

            seekHandler.postDelayed(this, 500); //Looping the thread after 0.1 second

        }
    };

    private void setAlbumArt(String imageUrl) {
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... params) {
//                Looper.prepare();
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
                getView().setBackgroundArt(bitmap);
            }
        }.execute(imageUrl);
    }

    @Override
    public void updateOnPause(boolean isPlaying) {
        this.isPlaying = isPlaying;
        playPause();
    }

    @Override
    public void updateOnSkip(int currentPos) {
        seekHandler.removeCallbacks(moveSeekThread);
        seekHandler.postDelayed(moveSeekThread, 500);

        this.currentPos = currentPos;
        Track track = mPlaylist.getTracks().get(currentPos);
        setAlbumArt(track.getArtworkUrl());
        getView().setViews(track.getTitle(), track.getUser().getUsername());
    }

    @Override
    public void updatePlayer(Playlist playlist, int currentPos) {
        this.mPlaylist = playlist;
        this.currentPos = currentPos;

        Track currentTrack = playlist.getTracks().get(currentPos);

        setAlbumArt(currentTrack.getArtworkUrl());
        getView().setViews(currentTrack.getTitle(), currentTrack.getUser().getUsername());
        seekHandler.removeCallbacks(moveSeekThread);
        seekHandler.postDelayed(moveSeekThread, 200);

        isPlaying = true;
    }
}
