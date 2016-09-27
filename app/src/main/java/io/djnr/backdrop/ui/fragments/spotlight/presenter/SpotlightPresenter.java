package io.djnr.backdrop.ui.fragments.spotlight.presenter;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.List;

import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.ui.fragments.spotlight.ISpotlight;

/**
 * Created by Dj on 8/22/2016.
 */
public class SpotlightPresenter implements ISpotlight.ProvidedPresenter, ISpotlight.RequiredPresenter{
    WeakReference<ISpotlight.RequiredView> mView;
    ISpotlight.ProvidedModel mModel;

    public SpotlightPresenter(ISpotlight.RequiredView view){
        mView = new WeakReference<ISpotlight.RequiredView>(view);
    }

    public void setModel(ISpotlight.ProvidedModel model){
        this.mModel = model;
        fetchSpotlight();
    }

    private ISpotlight.RequiredView getView() throws NullPointerException{
        if(mView!=null){
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
    public void setSpotlightPlaylists(List<Playlist> playlists) {
        getView().displaySpotlightPlaylists(playlists);
    }

    @Override
    public void fetchSpotlight() {
        mModel.getSpotlightPlaylists();
    }
}