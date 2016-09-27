package io.djnr.backdrop.dagger.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import io.djnr.backdrop.dagger.scope.ActivityScope;
import io.djnr.backdrop.ui.activities.main.IMain;
import io.djnr.backdrop.ui.activities.main.model.MainModel;
import io.djnr.backdrop.ui.activities.main.presenter.MainPresenter;
import io.djnr.backdrop.ui.activities.main.view.MainActivity;

/**
 * Created by Dj on 9/27/2016.
 */
@Module
public class MainActivityModule {
    private MainActivity activity;

    public MainActivityModule (MainActivity activity){
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    MainActivity providesMainActivity(){
        return activity;
    }

    @Provides
    @ActivityScope
    IMain.ProvidedPresenter providedPresenter(){
        MainPresenter presenter = new MainPresenter(activity);
        MainModel model = new MainModel(presenter);
        presenter.setModel(model);
        return presenter;
    }

}
