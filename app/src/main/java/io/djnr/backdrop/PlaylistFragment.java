package io.djnr.backdrop;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        ButterKnife.bind(this, view);
        mPlaylist = getActivity().getIntent().getParcelableExtra(SC_PLAYLIST);
        mRecyclerPlaylist.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerPlaylist.setAdapter(new PlaylistAdapter(mPlaylist.getTracks()));

        return view;
    }
}
