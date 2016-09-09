package io.djnr.backdrop.ui.track;

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
import io.djnr.backdrop.R;
import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.models.soundcloud.Track;
import io.djnr.backdrop.ui.MainActivity;

/**
 * Created by Dj on 9/7/2016.
 */
public class TrackFragment extends Fragment{
    @BindView(R.id.img_track_art)
    ImageView mImageArt;
    @BindView(R.id.txt_title)
    TextView mTextTitle;
    @BindView(R.id.txt_artist)
    TextView mTextArtist;
    @BindView(R.id.txt_track_title)
    TextView mTextTrackTitle;
    @BindView(R.id.txt_track_artist)
    TextView mTextTrackArtist;

    private Playlist mPlaylist;
    private int currentPos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track, container, false);
        ButterKnife.bind(this, view);
        ((MainActivity)getActivity()).hideMusicController();

        mPlaylist = getArguments().getParcelable("PLAYLIST");
        currentPos = getArguments().getInt("CURRENT_POS");
        Track track = mPlaylist.getTracks().get(currentPos);

        mTextTitle.setText(mPlaylist.getTitle());
        mTextArtist.setText(mPlaylist.getUser().getUsername());
        mTextTrackTitle.setText(track.getTitle());
        mTextTrackArtist.setText(track.getUser().getUsername());
        Glide.with(this).load(track.getArtworkUrl().replace("large.jpg", "t500x500.jpg"))
                .placeholder(R.drawable.no_img).into(mImageArt);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MainActivity)getActivity()).showMusicController();
    }
}
