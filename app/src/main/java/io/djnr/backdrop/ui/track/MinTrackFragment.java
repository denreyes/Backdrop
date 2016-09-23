package io.djnr.backdrop.ui.track;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.djnr.backdrop.R;
import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.models.soundcloud.Track;
import io.djnr.backdrop.services.TrackService;
import io.djnr.backdrop.ui.playlist.view.PlaylistFragment;
import io.djnr.backdrop.utils.MusicServiceProvider;

/**
 * Created by Dj on 9/5/2016.
 *
 */
public class MinTrackFragment extends Fragment implements PlaylistFragment.PlayerUpdater, MaxTrackFragment.ControlUpdater {
    private static final String TAG = "MinTrackFragment";

    @BindView(R.id.img_album_art)
    ImageView mImageArt;
    @BindView(R.id.text_title)
    TextView mTextTitle;
    @BindView(R.id.text_artist)
    TextView mTextArtist;
    @BindView(R.id.img_btn_control)
    ImageView mImageControl;
    @BindView(R.id.seekBar)
    SeekBar mSeekbar;

    private Playlist mPlaylist;
    private int currentPos;
    private boolean isPlaying;
    private Handler seekHandler = new Handler();

    MusicServiceProvider mMusicServiceCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track_min, container, false);
        ButterKnife.bind(this, view);
        isPlaying = false;
        mSeekbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        try {
            mMusicServiceCallback = (MusicServiceProvider) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Fragment must implement PlayerUpdater");
        }

        return view;
    }

    private Runnable moveSeekThread = new Runnable() {
        public void run() {
            TrackService trackService = mMusicServiceCallback.getTrackService();

            if(trackService.isPlaying()){
                int mediaPos_new = trackService.getPosition();
                int mediaMax_new = trackService.getDuration();
                mSeekbar.setMax(mediaMax_new);
                mSeekbar.setProgress(mediaPos_new);
            }

            seekHandler.postDelayed(this, 200); //Looping the thread after 0.1 second

        }
    };

    @Override
    public void updatePlayer(Playlist playlist, int currentPos) {
        this.mPlaylist = playlist;
        this.currentPos = currentPos;

        Track currentTrack = playlist.getTracks().get(currentPos);
        Glide.with(this).load(currentTrack.getArtworkUrl().replace("large.jpg", "t500x500.jpg"))
                .centerCrop().crossFade().into(mImageArt);
        mTextTitle.setText(currentTrack.getTitle());
        mTextArtist.setText(currentTrack.getUser().getUsername());

        seekHandler.removeCallbacks(moveSeekThread);
        mSeekbar.setProgress(0);
        seekHandler.postDelayed(moveSeekThread, 200);
        mImageControl.setImageResource(R.drawable.ic_pause);
        isPlaying = true;
    }

    @OnClick({R.id.img_btn_control, R.id.frame_control})
    public void onControlClicked() {
        TrackService service = mMusicServiceCallback.getTrackService();
        if (isPlaying) { //if playing pause
            service.pausePlayer();
            mImageControl.setImageResource(R.drawable.ic_play);
            isPlaying = false;
            seekHandler.removeCallbacks(moveSeekThread);
        } else { // if paused play
            service.go();
            mImageControl.setImageResource(R.drawable.ic_pause);
            isPlaying = true;
            seekHandler.postDelayed(moveSeekThread, 200);
        }
    }

    @OnClick(R.id.img_btn_up)
    public void onUpClicked(){
        Bundle args = new Bundle();
        args.putParcelable("PLAYLIST", mPlaylist);
        args.putInt("CURRENT_POS", currentPos);
        args.putBoolean("IS_PLAYING", isPlaying);

        MaxTrackFragment maxTrackFragment = new MaxTrackFragment();
        maxTrackFragment.setArguments(args);

        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_out_up, R.anim.slide_in_up,
                        R.anim.slide_out_up, R.anim.slide_in_up)
                .add(R.id.container, maxTrackFragment)
                .addToBackStack(maxTrackFragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void updateOnPause(boolean isPlaying) {
        this.isPlaying = isPlaying;
        onControlClicked();
    }

    @Override
    public void updateOnSkip(int currentPos) {
        this.currentPos = currentPos;
        Track track = mPlaylist.getTracks().get(currentPos);
        Glide.with(this).load(track.getArtworkUrl().replace("large.jpg", "t500x500.jpg"))
                .centerCrop().crossFade().into(mImageArt);
        mTextTitle.setText(track.getTitle());
        mTextArtist.setText(track.getUser().getUsername());

        seekHandler.removeCallbacks(moveSeekThread);
        mSeekbar.setProgress(0);
        seekHandler.postDelayed(moveSeekThread, 200);
    }

}
