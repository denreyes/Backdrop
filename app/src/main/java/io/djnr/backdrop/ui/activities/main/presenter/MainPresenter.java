package io.djnr.backdrop.ui.activities.main.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.lang.ref.WeakReference;

import io.djnr.backdrop.ui.activities.LoginActivity;
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

    @Override
    public void logOut(GoogleApiClient client) {

        Auth.GoogleSignInApi.signOut(client).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        getView().launchLoginScreen();
                    }
                });
    }
}
