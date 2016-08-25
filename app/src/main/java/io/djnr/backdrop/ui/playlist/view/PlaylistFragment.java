package io.djnr.backdrop.ui.playlist.view;

import android.content.Context;
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
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.djnr.backdrop.R;
import io.djnr.backdrop.dagger.module.PlaylistFragmentModule;
import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.models.soundcloud.Track;
import io.djnr.backdrop.ui.App;
import io.djnr.backdrop.ui.playlist.IPlaylist;
import io.djnr.backdrop.views.DividerItemDeco;

/**
 * Created by Dj on 8/20/2016.
 */
public class PlaylistFragment extends Fragment implements IPlaylist.RequiredView{
    @BindView(R.id.recycler_spotlight)
    RecyclerView mRecyclerPlaylist;
    @BindView(R.id.img_art)
    ImageView mImageArt;

    public static final String SC_PLAYLIST = "SC_PLAYLIST";
    Playlist mPlaylist;

    @Inject
    MediaPlayer mMediaPlayer;
    @Inject
    IPlaylist.ProvidedPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        ButterKnife.bind(this, view);
        mRecyclerPlaylist.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerPlaylist.addItemDecoration(new DividerItemDeco(getActivity()));

        setupComponent();
        return view;
    }

    private void setupComponent() {
        App.get(getActivity())
                .getAppComponent()
                .getPlaylistComponent(new PlaylistFragmentModule(this))
                .inject(this);
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
    public void setPlaylistRecycler(List<Track> tracks) {
        mRecyclerPlaylist.setAdapter(new PlaylistAdapter(tracks, mMediaPlayer));
    }

    @Override
    public void setupViews(Playlist playlist) {
        try {
            Glide.with(getActivity())
                    .load(playlist.getArtworkUrl().toString().replace("large.jpg", "t500x500.jpg"))
                    .centerCrop()
                    .crossFade()
                    .into(mImageArt);
        }catch (NullPointerException e){}
    }
}
