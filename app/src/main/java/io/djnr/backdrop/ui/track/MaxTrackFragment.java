package io.djnr.backdrop.ui.track;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.djnr.backdrop.R;
import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.models.soundcloud.Track;
import io.djnr.backdrop.services.TrackService;
import io.djnr.backdrop.ui.MainActivity;
import io.djnr.backdrop.utils.MusicServiceProvider;
import jp.wasabeef.blurry.Blurry;

/**
 * Created by Dj on 9/7/2016.
 */
public class MaxTrackFragment extends Fragment {
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

    private List<Track> mTracks;
    private int currentPos;
    private boolean isPlaying;
    private ObjectAnimator mRotate;
    private Bitmap mArtBitmap;

    private ControlUpdater mPlayerCallback;
    private MusicServiceProvider mMusicServiceCallback;
    private long mAnimationTime = 0;

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
        ((MainActivity) getActivity()).hideMusicController();

        ((MainActivity) getActivity()).setSupportActionBar(mToolbar);
        Playlist playlist = ((Playlist) getArguments().getParcelable("PLAYLIST"));
        currentPos = getArguments().getInt("CURRENT_POS");
        isPlaying = getArguments().getBoolean("IS_PLAYING");
        mArtBitmap = getArguments().getParcelable("BITMAP_IMAGE");
        mTracks = playlist.getTracks();
        Track track = mTracks.get(currentPos);

        try {
            mMusicServiceCallback = (MusicServiceProvider) getActivity();
            mPlayerCallback = (ControlUpdater) (getActivity().getSupportFragmentManager().findFragmentById(R.id.player_container));
        } catch (ClassCastException e) {
            throw new ClassCastException("Fragment must implement PlayerUpdater");
        }

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

        return view;
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
        ((MainActivity) getActivity()).showMusicController();
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
        mMusicServiceCallback.getTrackService().playNext();
        mPlayerCallback.updateOnSkip(currentPos);
        Track track = mTracks.get(currentPos);

        mRotate.end();
        mAnimationTime = 0;
        spinningRecordImage(track.getArtworkUrl().replace("large.jpg", "t500x500.jpg"), mImageArt);

        mTextTrackTitle.setText(track.getTitle());
        mTextTrackArtist.setText(track.getUser().getUsername());
    }

    @OnClick(R.id.img_skip_previous)
    public void onSkipPreviousClicked() {
        this.currentPos--;
        mMusicServiceCallback.getTrackService().playPrev();
        mPlayerCallback.updateOnSkip(currentPos);
        Track track = mTracks.get(currentPos);

        mRotate.end();
        mAnimationTime = 0;
        spinningRecordImage(track.getArtworkUrl().replace("large.jpg", "t500x500.jpg"), mImageArt);

        mTextTrackTitle.setText(track.getTitle());
        mTextTrackArtist.setText(track.getUser().getUsername());
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

    public interface ControlUpdater {
        public void updateOnPause(boolean isPlaying);

        public void updateOnSkip(int currentPos);
    }
}
