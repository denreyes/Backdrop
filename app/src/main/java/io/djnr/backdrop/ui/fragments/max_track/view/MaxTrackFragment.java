package io.djnr.backdrop.ui.fragments.max_track.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.djnr.backdrop.R;
import io.djnr.backdrop.dagger.module.MaxTrackFragmentModule;
import io.djnr.backdrop.dagger.module.MinTrackFragmentModule;
import io.djnr.backdrop.interfaces.MinControllerDisplayer;
import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.models.soundcloud.Track;
import io.djnr.backdrop.services.TrackService;
import io.djnr.backdrop.ui.App;
import io.djnr.backdrop.ui.activities.main.view.MainActivity;
import io.djnr.backdrop.ui.fragments.max_track.IMaxTrack;
import io.djnr.backdrop.ui.fragments.min_track.IMinTrack;
import io.djnr.backdrop.ui.fragments.playlist.view.PlaylistAdapter;
import io.djnr.backdrop.interfaces.TrackServiceProvider;
import jp.wasabeef.blurry.Blurry;

/**
 * Created by Dj on 9/7/2016.
 */
public class MaxTrackFragment extends Fragment implements IMaxTrack.RequiredView{
    @BindView(R.id.img_bg)
    ImageView mImageBg;
    @BindView(R.id.img_track_art)
    CircleImageView mImageArt;
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
    IMaxTrack.ProvidedPresenter presenter;

    private List<Track> mTracks;
    private int currentPos;
    private boolean isPlaying;
    private ObjectAnimator mRotate;
    private Bitmap mArtBitmap;
    private long mAnimationTime = 0;
    private Handler seekHandler = new Handler();

    private ControlUpdater mPlayerCallback;
    private TrackServiceProvider mTrackServiceCallback;
    private MinControllerDisplayer mDisplayerCallback;

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

        setupComponent();

        ((MainActivity) getActivity()).setSupportActionBar(mToolbar);
        Playlist playlist = ((Playlist) getArguments().getParcelable("PLAYLIST"));
        currentPos = getArguments().getInt("CURRENT_POS");
        isPlaying = getArguments().getBoolean("IS_PLAYING");
        mArtBitmap = getArguments().getParcelable("BITMAP_IMAGE");
        mTracks = playlist.getTracks();
        Track track = mTracks.get(currentPos);

        try {
            mTrackServiceCallback = (TrackServiceProvider) getActivity();
            mDisplayerCallback = (MinControllerDisplayer) getActivity();
            mPlayerCallback = (ControlUpdater) (getActivity().getSupportFragmentManager().findFragmentById(R.id.player_container));
        } catch (ClassCastException e) {
            throw new ClassCastException("Fragment must implement PlayerUpdater");
        }

        mDisplayerCallback.hideMusicController();
        mTextTrackTitle.setText(track.getTitle());
        mTextTrackArtist.setText(track.getUser().getUsername());
        if (isPlaying) {
            mFabPlay.setImageResource(R.drawable.ic_pause);
        }

        String imgUrl = track.getArtworkUrl().replace("large.jpg", "t500x500.jpg");

        mImageBg.setImageBitmap(mArtBitmap);
        mImageArt.setImageBitmap(mArtBitmap);

        new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        Blurry.with(getActivity()).radius(6).sampling(4)
                                .color(Color.argb(204, 0, 0, 0))
                                .animate(500)
                                .async().capture(mImageBg).into(mImageBg);

                        mRotate = ObjectAnimator.ofFloat(mImageArt,
                                "rotation", 0f, 359f);
                        mRotate.setRepeatCount(ObjectAnimator.INFINITE);
                        mRotate.setRepeatMode(ObjectAnimator.RESTART);
                        mRotate.setDuration(20000);
                        mRotate.setInterpolator(new LinearInterpolator());
                        if (isPlaying)
                            mRotate.start();
                    }
        });

        if(isPlaying){
            seekHandler.postDelayed(moveSeekThread, 200);
            mTextDuration.setText("0:00");
        }

        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    TrackService trackService = mTrackServiceCallback.getTrackService();
                    trackService.seek(progress);
                    seekBar.setProgress(progress);
                    mTextProgress.setText(getMinutesFormat(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mRecyclerTracks.setAdapter(new PlaylistAdapter(playlist, mTrackServiceCallback.getTrackService()));
        return view;
    }

    private void setupComponent() {
        App.get(getActivity())
                .getAppComponent()
                .getMaxTrackFragmentComponent(new MaxTrackFragmentModule(this))
                .inject(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.trackfragment, menu);
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
        mDisplayerCallback.showMusicController();
    }

    private Runnable moveSeekThread = new Runnable() {
        public void run() {
            TrackService trackService = mTrackServiceCallback.getTrackService();

            if(trackService.isPlaying()){
                int timeProgress = trackService.getPosition();
                int timeMax = trackService.getDuration();
                mSeekbar.setMax(timeMax);
                mSeekbar.setProgress(timeProgress);
                mTextDuration.setText(getMinutesFormat(timeMax));
                mTextProgress.setText(getMinutesFormat(timeProgress));
            }

            seekHandler.postDelayed(this, 500); //Looping the thread after 0.1 second
        }
    };

    private String getMinutesFormat(int time){
        return String.format("%d:%02d", TimeUnit.MILLISECONDS.toMinutes((long) time),
                TimeUnit.MILLISECONDS.toSeconds((long) time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) time)));
    }

    private void spinningRecordImage(String imgUrl, ImageView imageView) {
        Glide.with(this).load(imgUrl)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Rotate Animation
                                mRotate = ObjectAnimator.ofFloat(mImageArt,
                                        "rotation", 0f, 359f);
                                mRotate.setRepeatCount(ObjectAnimator.INFINITE);
                                mRotate.setRepeatMode(ObjectAnimator.RESTART);
                                mRotate.setDuration(20000);
                                mRotate.setInterpolator(new LinearInterpolator());
                                if (isPlaying)
                                    mRotate.start();
                            }
                        }, 1000);
                        return false;
                    }
                })
                .into(imageView);
    }

    @OnClick(R.id.fab_play)
    public void onControlClicked() {
        mPlayerCallback.updateOnPause(isPlaying);
        if (isPlaying) { //if playing pause
            mFabPlay.setImageResource(R.drawable.ic_play);
            stopAnimation();
            isPlaying = false;
        } else { // if paused play
            mFabPlay.setImageResource(R.drawable.ic_pause);
            playAnimation();
            isPlaying = true;
        }
    }

    @OnClick(R.id.img_skip_next)
    public void onSkipNextClicked() {
        this.currentPos++;
        mTrackServiceCallback.getTrackService().playNext();
        skipUpdate();
    }

    @OnClick(R.id.img_skip_previous)
    public void onSkipPreviousClicked() {
        this.currentPos--;
        mTrackServiceCallback.getTrackService().playPrev();
        skipUpdate();
    }

    private void skipUpdate(){
        mPlayerCallback.updateOnSkip(currentPos);
        Track track = mTracks.get(currentPos);

        mRotate.end();
        mAnimationTime = 0;
        spinningRecordImage(track.getArtworkUrl().replace("large.jpg", "t500x500.jpg"), mImageArt);

        mTextTrackTitle.setText(track.getTitle());
        mTextTrackArtist.setText(track.getUser().getUsername());

        seekHandler.removeCallbacks(moveSeekThread);
        mSeekbar.setProgress(0);
        seekHandler.postDelayed(moveSeekThread, 500);
    }

    private void stopAnimation() {
        if (mRotate != null) {
            mAnimationTime = mRotate.getCurrentPlayTime();
            mRotate.cancel();
        }
    }

    private void playAnimation() {
        if (mRotate != null) {
            mRotate.start();
            mRotate.setCurrentPlayTime(mAnimationTime);
        }
    }

    @Override
    public Context getAppContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    public interface ControlUpdater {
        public void updateOnPause(boolean isPlaying);

        public void updateOnSkip(int currentPos);
    }
}
