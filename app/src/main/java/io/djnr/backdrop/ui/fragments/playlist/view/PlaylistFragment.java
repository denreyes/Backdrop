package io.djnr.backdrop.ui.fragments.playlist.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.djnr.backdrop.R;
import io.djnr.backdrop.dagger.module.PlaylistFragmentModule;
import io.djnr.backdrop.interfaces.NavDrawerToggle;
import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.ui.App;
import io.djnr.backdrop.ui.activities.ambient.AmbientActivity;
import io.djnr.backdrop.ui.fragments.playlist.IPlaylist;
import io.djnr.backdrop.interfaces.TrackServiceProvider;

/**
 * Created by Dj on 8/20/2016.
 */
public class PlaylistFragment extends Fragment implements IPlaylist.RequiredView{
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_spotlight)
    RecyclerView mRecyclerPlaylist;
    @BindView(R.id.img_art)
    ImageView mImageArt;
    @BindView(R.id.album_title)
    TextView mTextAlbumTitle;
    @BindView(R.id.album_artist)
    TextView mTextAlbumArtist;

    public static final String SC_PLAYLIST = "SC_PLAYLIST";
    private Playlist mPlaylist;
    private PlayerUpdater mMinCallback;
    private TrackServiceProvider mMusicServiceCallback;

    @Inject
    IPlaylist.ProvidedPresenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        ButterKnife.bind(this, view);
        mRecyclerPlaylist.setLayoutManager(new LinearLayoutManager(getActivity()));
        try {
            mMusicServiceCallback = (TrackServiceProvider) getActivity();
            mMinCallback = (PlayerUpdater) (getActivity().getSupportFragmentManager().findFragmentById(R.id.player_container));
        } catch (ClassCastException e) {
            throw new ClassCastException("Fragment must implement PlayerUpdater");
        }

        setupComponent();

        showNavToggle();
        return view;
    }

    private void showNavToggle() {
        ((NavDrawerToggle) getActivity()).setupNavToggle(mToolbar);
    }

    @OnClick(R.id.fab_drop)
    public void onClickDrop(){
        ActivityOptionsCompat options =  ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity());
        startActivity(new Intent(getActivity(), AmbientActivity.class), options.toBundle());
    }

    private void setupComponent() {
        App.get(getActivity())
                .getAppComponent()
                .getPlaylistFragmentComponent(new PlaylistFragmentModule(this))
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
        PlaylistAdapter adapter = new PlaylistAdapter(playlist, mMusicServiceCallback.getTrackService(), mMinCallback);
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
