package io.djnr.backdrop.ui.activities.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import io.djnr.backdrop.services.TrackService;

/**
 * Created by Dj on 9/27/2016.
 */
public interface IMain {

    interface RequiredView{
        Context getAppContext();
        Activity getActivity();
    };

    interface ProvidedPresenter{
    };

    interface RequiredPresenter{
        Context getAppContext();
        Activity getActivity();
    };

    interface ProvidedModel{

    };
}
