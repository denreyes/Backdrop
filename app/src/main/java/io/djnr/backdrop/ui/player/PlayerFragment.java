package io.djnr.backdrop.ui.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    @BindView(R.id.img_album_art)
    ImageView mImageArt;
    @BindView(R.id.text_title)
    TextView mTextTitle;
    @BindView(R.id.text_artist)
    TextView mTextArtist;
    @BindView(R.id.img_btn_control)
    ImageView mImageControl;

    TrackService mTrackService;
    int controlRes;
    boolean isPlaying;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        ButterKnife.bind(this, view);
        controlRes = R.drawable.ic_play;
        isPlaying = false;

        return view;
    }

    @Override
    public void updatePlayer(Playlist playlist, int currentPos) {
        Track currentTrack = playlist.getTracks().get(currentPos);
        Glide.with(this).load(currentTrack.getArtworkUrl()).centerCrop().crossFade().into(mImageArt);
        mTextTitle.setText(currentTrack.getTitle());
        mTextArtist.setText(currentTrack.getUser().getUsername());

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
        } else { // if paused play
            service.go();
            mImageControl.setImageResource(R.drawable.ic_pause);
            controlRes = R.drawable.ic_pause;
            isPlaying = true;
        }
    }
}
