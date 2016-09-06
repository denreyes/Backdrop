package io.djnr.backdrop.ui.playlist.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.djnr.backdrop.R;
import io.djnr.backdrop.dagger.module.PlaylistFragmentModule;
import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.models.soundcloud.Track;
import io.djnr.backdrop.ui.App;
import io.djnr.backdrop.ui.ambient.AmbientActivity;
import io.djnr.backdrop.ui.playlist.IPlaylist;
import io.djnr.backdrop.ui.MainActivity;
import io.djnr.backdrop.services.TrackService;
import io.djnr.backdrop.ui.spotlight.ISpotlight;

/**
 * Created by Dj on 8/20/2016.
 */
public class PlaylistFragment extends Fragment implements IPlaylist.RequiredView{
    @BindView(R.id.recycler_spotlight)
    RecyclerView mRecyclerPlaylist;
    @BindView(R.id.img_art)
    ImageView mImageArt;
    @BindView(R.id.album_title)
    TextView mTextAlbumTitle;
    @BindView(R.id.album_artist)
    TextView mTextAlbumArtist;

    public static final String SC_PLAYLIST = "SC_PLAYLIST";
    Playlist mPlaylist;
    TrackService mTrackService;
    PlayerUpdater mPlayerCallback;

    @Inject
    IPlaylist.ProvidedPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        ButterKnife.bind(this, view);
        mRecyclerPlaylist.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTrackService = ((MainActivity)getActivity()).getTrackService();
        try {
            mPlayerCallback = (PlayerUpdater) (getActivity().getSupportFragmentManager().findFragmentById(R.id.player_container));
        } catch (ClassCastException e) {
            throw new ClassCastException("Fragment must implement PlayerUpdater");
        }

        setupComponent();

        return view;
    }

    @OnClick(R.id.fab_drop)
    public void onClickDrop(){
        ActivityOptionsCompat options =  ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity());
        startActivity(new Intent(getActivity(), AmbientActivity.class), options.toBundle());
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
    public Fragment getFragment(){
        return this;
    }

    @Override
    public void setPlaylistRecycler(Playlist playlist) {
        PlaylistAdapter adapter = new PlaylistAdapter(playlist, mTrackService, mPlayerCallback);
        mRecyclerPlaylist.setAdapter(adapter);
    }

    @Override
    public void setupViews(Playlist playlist) {
        try {
            Glide.with(getActivity())
                    .load(playlist.getArtworkUrl().toString().replace("large.jpg", "t500x500.jpg"))
                    .centerCrop()
                    .crossFade()
                    .into(mImageArt);
            mTextAlbumTitle.setText(playlist.getTitle());
            mTextAlbumArtist.setText(playlist.getUser().getUsername());
        }catch (NullPointerException e){}
    }

    public interface PlayerUpdater{
        public void updatePlayer(Playlist playlist, int currentPos);
    }
}
