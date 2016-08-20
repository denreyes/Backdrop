package io.djnr.backdrop;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.djnr.backdrop.models.soundcloud.Playlist;

/**
 * Created by Dj on 8/20/2016.
 */
public class PlaylistFragment extends Fragment{
    @BindView(R.id.recycler_spotlight)
    RecyclerView mRecyclerPlaylist;

    public static final String SC_PLAYLIST = "SC_PLAYLIST";
    Playlist mPlaylist;

    MediaPlayer mMediaPlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        ButterKnife.bind(this, view);
        mPlaylist = getActivity().getIntent().getParcelableExtra(SC_PLAYLIST);

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                togglePlayPause();
            }
        });

        mRecyclerPlaylist.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerPlaylist.setAdapter(new PlaylistAdapter(mPlaylist.getTracks(), mMediaPlayer));

        return view;
    }

    private void togglePlayPause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
//            mPlayerControl.setImageResource(R.drawable.ic_play);
        } else {
            mMediaPlayer.start();
//            mPlayerControl.setImageResource(R.drawable.ic_pause);
        }
    }
}
