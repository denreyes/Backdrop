package io.djnr.backdrop.ui.fragments.max_track;

import android.content.Context;

/**
 * Created by Dj on 9/27/2016.
 */
public interface IMaxTrack {

    public interface RequiredView{
        Context getAppContext();
        Context getActivityContext();
    };

    public interface ProvidedPresenter{

    };

    public interface RequiredPresenter{
        Context getAppContext();
        Context getActivityContext();
    };

    public interface ProvidedModel{

    };
}
