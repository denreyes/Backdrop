package io.djnr.backdrop.ui.spotlight;

import android.content.Context;

/**
 * Created by Dj on 8/20/2016.
 */
public interface ISpotlight {

    interface RequiredView {
        Context getAppContext();
        Context getActivityContext();
    };

    interface ProvidedPresenter {

    };

    interface RequiredPresenter {
        Context getAppContext();
        Context getActivityContext();
    };

    interface ProvidedModel {

    };
}
