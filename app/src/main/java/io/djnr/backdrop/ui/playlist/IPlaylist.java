package io.djnr.backdrop.ui.playlist;

import android.content.Context;

/**
 * Created by Dj on 8/20/2016.
 */
public interface IPlaylist {

    interface RequiredView{
        Context getAppContext();
        Context getActivityContext();
    };

    interface ProvidedPresenter{

    };

    interface RequiredPresenter{
        Context getAppContext();
        Context getActivityContext();
    };

    interface ProvidedModel{

    };
}
