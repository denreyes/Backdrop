package io.djnr.backdrop.ui.playlist.presenter;

import android.content.Context;

import java.lang.ref.WeakReference;

import io.djnr.backdrop.ui.playlist.IPlaylist;

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
}
