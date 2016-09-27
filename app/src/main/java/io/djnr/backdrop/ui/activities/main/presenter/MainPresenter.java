package io.djnr.backdrop.ui.activities.main.presenter;

import android.app.Activity;
import android.content.Context;

import java.lang.ref.WeakReference;

import io.djnr.backdrop.ui.activities.main.IMain;

/**
 * Created by Dj on 9/27/2016.
 */
public class MainPresenter implements IMain.ProvidedPresenter, IMain.RequiredPresenter{
    WeakReference<IMain.RequiredView> mView;
    IMain.ProvidedModel mModel;

    public MainPresenter(IMain.RequiredView view){
        mView = new WeakReference<IMain.RequiredView>(view);
    }

    public void setModel(IMain.ProvidedModel model){
        mModel = model;
    }

    private IMain.RequiredView getView() throws NullPointerException{
        if(mView != null){
            return mView.get();
        } else {
            throw new NullPointerException("View is unavailable.");
        }
    }

    @Override
    public Context getAppContext() {
        return getView().getAppContext();
    }

    @Override
    public Activity getActivity() {
        return getView().getActivity();
    }
}
