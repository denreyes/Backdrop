package io.djnr.backdrop.ui.spotlight.presenter;

import android.content.Context;

import java.lang.ref.WeakReference;

import io.djnr.backdrop.ui.spotlight.ISpotlight;

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
}
