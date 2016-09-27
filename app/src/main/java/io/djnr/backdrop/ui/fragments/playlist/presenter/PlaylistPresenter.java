package io.djnr.backdrop.ui.fragments.playlist.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;

import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.ui.fragments.playlist.IPlaylist;
import io.djnr.backdrop.ui.fragments.playlist.view.PlaylistFragment;

/**
 * Created by Dj on 8/20/2016.
 */
public class PlaylistPresenter implements IPlaylist.ProvidedPresenter, IPlaylist.RequiredPresenter{
    private WeakReference<IPlaylist.RequiredView> mView;
    private IPlaylist.ProvidedModel mModel;

    public PlaylistPresenter(IPlaylist.RequiredView view){
        mView = new WeakReference<IPlaylist.RequiredView>(view);
    }

    public void setModel(IPlaylist.ProvidedModel model){
        this.mModel = model;
        getDataFromIntent();
    }

    private IPlaylist.RequiredView getView() throws NullPointerException{
        if(mView != null){
            return mView.get();
        }else {
            throw new NullPointerException("View is unavailable.");
        }
    }

    @Override
    public Context getAppContext() {
        return getView().getAppContext();
    }

    @Override
    public Context getActivityContext() {
        return getView().getActivityContext();
    }

    @Override
    public Fragment getFragment() {
        return getView().getFragment();
    }

    @Override
    public void getDataFromIntent() {
        Playlist playlist = (getView().getFragment().getArguments()
                .getParcelable(PlaylistFragment.SC_PLAYLIST));

        getView().setPlaylistRecycler(playlist);
        getView().setupViews(playlist);
    }
}
