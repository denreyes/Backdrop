package io.djnr.backdrop.ui.fragments.max_track.view;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.djnr.backdrop.R;
import io.djnr.backdrop.dagger.module.MaxTrackFragmentModule;
import io.djnr.backdrop.services.TrackService;
import io.djnr.backdrop.ui.App;
import io.djnr.backdrop.ui.activities.main.view.MainActivity;
import io.djnr.backdrop.ui.fragments.max_track.IMaxTrack;
import io.djnr.backdrop.ui.fragments.playlist.view.PlaylistAdapter;
import io.djnr.backdrop.utils.Utils;
import jp.wasabeef.blurry.Blurry;

/**
 * Created by Dj on 9/7/2016.
 */
public class MaxTrackFragment extends Fragment implements IMaxTrack.RequiredView {
    private static final String TAG = "MaxTrackFragment";

    @BindView(R.id.img_bg)
    ImageView mImageBg;
    @BindView(R.id.img_track_art)
    CircleImageView mImageArt;
    @BindView(R.id.img_prev_track_art)
    CircleImageView mImagePrevArt;
    @BindView(R.id.img_next_track_art)
    CircleImageView mImageNextArt;
    @BindView(R.id.txt_track_title)
    TextView mTextTrackTitle;
    @BindView(R.id.txt_track_artist)
    TextView mTextTrackArtist;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab_play)
    FloatingActionButton mFabPlay;
    @BindView(R.id.seekBar)
    SeekBar mSeekbar;
    @BindView(R.id.txt_seek_progress)
    TextView mTextProgress;
    @BindView(R.id.txt_seek_duration)
    TextView mTextDuration;
    @BindView(R.id.recycler_tracks)
    RecyclerView mRecyclerTracks;

    @Inject
    IMaxTrack.ProvidedPresenter mPresenter;

    private ObjectAnimator mRotate;
    private long mAnimationTime = 0;
    private IntentFilter mIntentFilter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track_max, container, false);
        ButterKnife.bind(this, view);
        mRecyclerTracks.setLayoutManager(new LinearLayoutManager(getActivity()));
        ((MainActivity) getActivity()).setSupportActionBar(mToolbar);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(TrackService.broadcastStringAction);

        setupComponent();

        mPresenter.getArguments();
        mPresenter.initCallbacks();
        mPresenter.setupViews();
        mPresenter.setupSeekbar();

        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mPresenter.userSeeked(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(mReceiver, mIntentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(mReceiver);
    }

    private void setupComponent() {
        App.get(getActivity())
                .getAppComponent()
                .getMaxTrackFragmentComponent(new MaxTrackFragmentModule(this))
                .inject(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.track_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.min_player) {
            getActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.showMinController();
        mPresenter.tearDown();
    }

    @Override
    public Context getAppContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    //View Setup
    @Override
    public void setAlbumArt(Bitmap bitmap) {
        mImageArt.setImageBitmap(bitmap);
    }

    @Override
    public void setNextAlbumArt(Blurry.BitmapComposer composer) {
        composer.into(mImageNextArt);
    }

    @Override
    public void setPrevAlbumArt(Blurry.BitmapComposer composer) {
        composer.into(mImagePrevArt);
    }

    @Override
    public void setBlurredAlbumArt(Blurry.BitmapComposer composer) {
        composer.into(mImageBg);
    }

    @Override
    public void hideNextAlbumArt() {
        mImageNextArt.setVisibility(View.GONE);
    }

    @Override
    public void hidePrevAlbumArt() {
        mImagePrevArt.setVisibility(View.GONE);
    }

    @Override
    public void setTexts(String title, String username) {
        mTextTrackTitle.setText(title);
        mTextTrackArtist.setText(username);
    }

    @Override
    public void setRecyclerAdapter(PlaylistAdapter playlistAdapter) {
        mRecyclerTracks.setAdapter(playlistAdapter);
    }

    //FAB
    @Override
    public void setFabPlay() {
        mFabPlay.setImageResource(R.drawable.ic_play);
    }

    @Override
    public void setFabPause() {
        mFabPlay.setImageResource(R.drawable.ic_pause);
    }

    //Seekbar
    @Override
    public void resetSeekbar() {
        mTextDuration.setText("0:00");
        mTextProgress.setText("0:00");
        mSeekbar.setProgress(0);
    }

    @Override
    public void updateSeekbarProgress(int progress) {
        mSeekbar.setProgress(progress);
        mTextProgress.setText(Utils.getMinutesFormat(progress));
    }

    @Override
    public void setSeekbarProgress(int timeProgress, int timeMax) {
        mSeekbar.setProgress(timeProgress);
        mSeekbar.setMax(timeMax);
        mTextProgress.setText(Utils.getMinutesFormat(timeProgress));
        mTextDuration.setText(Utils.getMinutesFormat(timeMax));
    }

    //Spinning Disc Animation
    @Override
    public void setupDiscAnimation() {
        mRotate = ObjectAnimator.ofFloat(mImageArt,
                "rotation", 0f, 359f);
        mRotate.setRepeatCount(ObjectAnimator.INFINITE);
        mRotate.setRepeatMode(ObjectAnimator.RESTART);
        mRotate.setDuration(20000);
        mRotate.setInterpolator(new LinearInterpolator());
    }

    @Override
    public void playDiscAnimation() {
        if (mRotate != null) {
            mRotate.start();
            mRotate.setCurrentPlayTime(mAnimationTime);
        }
    }

    @Override
    public void pauseDiscAnimation() {
        if (mRotate != null) {
            mAnimationTime = mRotate.getCurrentPlayTime();
            mRotate.cancel();
        }
    }

    @Override
    public void endDiscAnimation() {
        mRotate.end();
        mAnimationTime = 0;
    }

    //On Clicks
    @OnClick(R.id.fab_play)
    public void onPlayPauseClicked() {
        mPresenter.pausePlay();
    }

//    @OnClick(R.id.img_skip_next)
//    public void onSkipNextClicked() {
//        mPresenter.skipNext();
//    }
//
//    @OnClick(R.id.img_skip_previous)
//    public void onSkipPreviousClicked() {
//        mPresenter.skipPrev();
//    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            mSeekbar.setProgress(0);
            int posn = intent.getIntExtra("SONG_POSN", -1);
            if (posn != -1) {
                mPresenter.setPosition(posn);
                Log.i("DEBUG ME!", "onReceive: skip on Fragment");
                mPresenter.skip();
            }
            if (posn == 0) {
                mFabPlay.setImageResource(R.drawable.ic_play);
            }
        }
    };

    public interface ControlUpdater {
        public void updateOnPause(boolean isPlaying);

        public void updateOnSkip(int currentPos);
    }
}
