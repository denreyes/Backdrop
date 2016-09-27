package io.djnr.backdrop.dagger.module;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.widget.Toast;

import dagger.Module;
import dagger.Provides;
import io.djnr.backdrop.dagger.scope.ActivityScope;
import io.djnr.backdrop.services.TrackService;
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

    @Provides
    @ActivityScope
    Intent providesTrackServiceIntent(){
        return new Intent(activity, TrackService.class);
    }

    @Provides
    @ActivityScope
    ServiceConnection providesServiceConnection(){
        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                TrackService.MusicBinder binder = (TrackService.MusicBinder) service;
                activity.setTrackService(binder.getService());
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };

        return connection;
    }

    @Provides
    @ActivityScope
    CoordinatorLayout.LayoutParams providesCoordinatorParams(){
        return new CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                CoordinatorLayout.LayoutParams.MATCH_PARENT
        );
    }
}
