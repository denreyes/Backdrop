package io.djnr.backdrop.ui.fragments.max_track.presenter;

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

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
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
    private Bitmap mArtBitmap, mPrevArtBitmap, mNextArtBitmap;
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
        mPrevArtBitmap = args.getParcelable("BITMAP_IMAGE_PREV");
        mNextArtBitmap = args.getParcelable("BITMAP_IMAGE_NEXT");
        mPlaylist = args.getParcelable("PLAYLIST");
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

        Blurry.BitmapComposer composer = Blurry.with(getActivityContext())
                .radius(6).sampling(4)
                .color(Color.argb(204, 0, 0, 0))
                .animate(500)
                .async().from(mArtBitmap);

        getView().setAlbumArt(mArtBitmap);
        getView().setNextAlbumArt(mNextArtBitmap);
        getView().setPrevAlbumArt(mPrevArtBitmap);
        getView().setBlurredAlbumArt(composer);
        getView().setTexts(mTrack.getTitle(), mTrack.getUser().getUsername());
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
    public void setupSeekbar() {
        TrackService trackService = mTrackServiceCallback.getTrackService();
        if(trackService.isPlaying() && isPlaying) {
            getView().setSeekbarProgress(trackService.getPosition(), trackService.getDuration());
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

    @Override
    public void setPosition(int posn) {
        currentPos = posn;
    }

    private Runnable moveSeekThread = new Runnable() {
        public void run() {
            TrackService trackService = mTrackServiceCallback.getTrackService();

            if (trackService.isPlaying()) {
                getView().setSeekbarProgress(trackService.getPosition(), trackService.getDuration());
            }

            seekHandler.postDelayed(this, 500); //Looping the thread after 0.1 second
        }
    };

    @Override
    public void skip() {
        seekHandler.removeCallbacks(moveSeekThread);
        getView().resetSeekbar();
        seekHandler.postDelayed(moveSeekThread, 500);

        mPlayerCallback.updateOnSkip(currentPos);
        mTrack = mPlaylist.getTracks().get(currentPos);

        getView().endDiscAnimation();
        getView().setTexts(mTrack.getTitle(), mTrack.getUser().getUsername());
        setAlbumArt(Utils.largeSCImg(mTrack.getArtworkUrl()),
                Utils.largeSCImg(mPlaylist.getTracks().get(currentPos-1).getArtworkUrl()),
                Utils.largeSCImg(mPlaylist.getTracks().get(currentPos+1).getArtworkUrl()));

        Log.i("DEBUG ME!", "skip: "+mTrack.getTitle());
    }

    private void setAlbumArt(String imageUrl, String prevImageUrl, String nextImageUrl) {
        new AsyncTask<String, Void, List<Bitmap>>() {
            @Override
            protected List<Bitmap> doInBackground(String... params) {
                mArtBitmap = getBitmap(params[0]);
                mPrevArtBitmap = params[1] != null ? getBitmap(params[1]) : null;
                mNextArtBitmap = params[2] != null ? getBitmap(params[2]) : null;

                List<Bitmap> bitmaps = new ArrayList<Bitmap>();
                bitmaps.add(mArtBitmap);
                bitmaps.add(mPrevArtBitmap);
                bitmaps.add(mNextArtBitmap);

                return bitmaps;
            }

            @Override
            protected void onPostExecute(List<Bitmap> bitmaps) {

                Blurry.BitmapComposer composer = Blurry.with(getActivityContext())
                        .radius(6).sampling(4)
                        .color(Color.argb(204, 0, 0, 0))
                        .animate(500)
                        .async().from(bitmaps.get(0));

                getView().setAlbumArt(bitmaps.get(0));
                getView().setPrevAlbumArt(bitmaps.get(1));
                getView().setNextAlbumArt(bitmaps.get(2));

                getView().setBlurredAlbumArt(composer);
                getView().setupDiscAnimation();
                getView().playDiscAnimation();
            }

            private Bitmap getBitmap(String url){
                try {
                    mArtBitmap = Glide.with(getActivityContext())
                            .load(Utils.largeSCImg(url))
                            .asBitmap().into(-1, -1).get();

                    return mArtBitmap;
                } catch (final ExecutionException e) {
                    Log.e(TAG, e.getMessage());
                } catch (final InterruptedException e) {
                    Log.e(TAG, e.getMessage());
                } catch (final NullPointerException e) {
                    Log.e(TAG, e.getMessage());
                }
                return BitmapFactory.decodeResource(getActivityContext().getResources(), R.drawable.no_img);
            }
        }.execute(imageUrl, prevImageUrl, nextImageUrl);
    }
}
