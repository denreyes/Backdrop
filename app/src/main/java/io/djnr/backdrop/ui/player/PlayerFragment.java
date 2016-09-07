package io.djnr.backdrop.ui.player;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.djnr.backdrop.R;
import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.models.soundcloud.Track;
import io.djnr.backdrop.services.TrackService;
import io.djnr.backdrop.ui.MainActivity;
import io.djnr.backdrop.ui.playlist.view.PlaylistFragment;

/**
 * Created by Dj on 9/5/2016.
 */
public class PlayerFragment extends Fragment implements PlaylistFragment.PlayerUpdater {
    private static final String TAG = "PlayerFragment";

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

    TrackService mTrackService;
    int controlRes;
    boolean isPlaying;
    Handler seekHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        ButterKnife.bind(this, view);
        controlRes = R.drawable.ic_play;
        isPlaying = false;
        mSeekbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        return view;
    }

    private Runnable moveSeekThread = new Runnable() {
        public void run() {
            TrackService trackService = ((MainActivity)getActivity()).getTrackService();

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
        Track currentTrack = playlist.getTracks().get(currentPos);
        Glide.with(this).load(currentTrack.getArtworkUrl()).centerCrop().crossFade().into(mImageArt);
        mTextTitle.setText(currentTrack.getTitle());
        mTextArtist.setText(currentTrack.getUser().getUsername());

        Log.i(TAG, "updatePlayer: UPDATED");
        seekHandler.removeCallbacks(moveSeekThread);
        seekHandler.postDelayed(moveSeekThread, 200);
        mImageControl.setImageResource(R.drawable.ic_pause);
        controlRes = R.drawable.ic_pause;
        isPlaying = true;
    }

    @OnClick({R.id.img_btn_control, R.id.frame_control})
    public void onControlClicked() {
        TrackService service = ((MainActivity) getActivity()).getTrackService();
        if (isPlaying) { //if playing pause
            service.pausePlayer();
            mImageControl.setImageResource(R.drawable.ic_play);
            controlRes = R.drawable.ic_play;
            isPlaying = false;
            seekHandler.removeCallbacks(moveSeekThread);
        } else { // if paused play
            service.go();
            mImageControl.setImageResource(R.drawable.ic_pause);
            controlRes = R.drawable.ic_pause;
            isPlaying = true;
            seekHandler.postDelayed(moveSeekThread, 200);
        }
    }
}
