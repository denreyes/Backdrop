package io.djnr.backdrop.ui.fragments.max_track.presenter;

import android.content.Context;

import java.lang.ref.WeakReference;

import io.djnr.backdrop.ui.fragments.max_track.IMaxTrack;

/**
 * Created by Dj on 9/27/2016.
 */
public class MaxTrackPresenter implements IMaxTrack.ProvidedPresenter, IMaxTrack.RequiredPresenter{
    WeakReference<IMaxTrack.RequiredView> mView;
    IMaxTrack.ProvidedModel mModel;

    public MaxTrackPresenter(IMaxTrack.RequiredView view){
        mView = new WeakReference<IMaxTrack.RequiredView>(view);
    }

    public void setModel(IMaxTrack.ProvidedModel model){
        this.mModel = model;
    }

    private IMaxTrack.RequiredView getView() throws NullPointerException{
        if (mView != null)
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
