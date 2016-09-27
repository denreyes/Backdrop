package io.djnr.backdrop.ui.fragments.min_track.presenter;

import android.content.Context;

import java.lang.ref.WeakReference;

import io.djnr.backdrop.ui.fragments.min_track.IMinTrack;

/**
 * Created by Dj on 9/27/2016.
 */
public class MinTrackPresenter implements IMinTrack.ProvidedPresenter, IMinTrack.RequiredPresenter{
    WeakReference<IMinTrack.RequiredView> mView;
    IMinTrack.ProvidedModel mModel;

    public MinTrackPresenter(IMinTrack.RequiredView view){
        mView = new WeakReference<IMinTrack.RequiredView>(view);
    }

    public void setModel(IMinTrack.ProvidedModel model){
        this.mModel = model;
    }

    private IMinTrack.RequiredView getView() throws NullPointerException{
        if(mView != null)
            return mView.get();
        else
            throw new NullPointerException("View is unavailable.");
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
