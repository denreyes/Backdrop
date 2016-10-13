package io.djnr.backdrop.dagger.module;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Singleton;

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
public class ActivityModule {
    private AppCompatActivity activity;
    private GoogleApiClient.OnConnectionFailedListener listener;

    public ActivityModule(AppCompatActivity activity, GoogleApiClient.OnConnectionFailedListener listener){
        this.activity = activity;
        this.listener = listener;
    }

    @Provides
    @ActivityScope
    AppCompatActivity providesMainActivity(){
        return activity;
    }

    @Provides
    @ActivityScope
    IMain.ProvidedPresenter providedPresenter(){
        MainPresenter presenter = new MainPresenter((MainActivity)activity);
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
                ((MainActivity)activity).setTrackService(binder.getService());
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

    @Provides
    @ActivityScope
    public GoogleSignInOptions providesGoogleSignInOptions() {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail()
                .build();
    }

    @Provides
    @ActivityScope
    public GoogleApiClient providesGoogleSignInClient(GoogleSignInOptions gso) {
        GoogleApiClient client = new GoogleApiClient.Builder(activity)
                .enableAutoManage(activity, listener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        return client;
    }
}
