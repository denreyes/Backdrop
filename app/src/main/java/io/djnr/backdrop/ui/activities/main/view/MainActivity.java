package io.djnr.backdrop.ui.activities.main.view;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.djnr.backdrop.R;
import io.djnr.backdrop.dagger.module.MainActivityModule;
import io.djnr.backdrop.dagger.module.PlaylistFragmentModule;
import io.djnr.backdrop.ui.App;
import io.djnr.backdrop.ui.activities.main.IMain;
import io.djnr.backdrop.ui.fragments.track.MinTrackFragment;
import io.djnr.backdrop.ui.fragments.spotlight.view.SpotlightFragment;
import io.djnr.backdrop.services.TrackService;
import io.djnr.backdrop.utils.MusicServiceProvider;

public class MainActivity extends AppCompatActivity implements MusicServiceProvider, IMain.RequiredView{
    private static final String TAG = "MainActivity";

    @Inject
    TrackService musicSrv;
    private Intent playIntent;
    private boolean musicBound = false;

    @BindView(R.id.player_container)
    FrameLayout mPlayerContainer;
    @BindView(R.id.container)
    FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupComponent();

        if (playIntent == null) {
            playIntent = new Intent(this, TrackService.class);
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new SpotlightFragment())
                .replace(R.id.player_container, new MinTrackFragment())
                .commit();
    }

    private void setupComponent() {
        App.get(this)
                .getAppComponent()
                .getMainActivityComponent(new MainActivityModule(this))
                .inject(this);
    }

    public void showMusicController() {
        if(mPlayerContainer.getVisibility() != View.VISIBLE) {
            mPlayerContainer.setVisibility(View.VISIBLE);

            CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(
                    CoordinatorLayout.LayoutParams.MATCH_PARENT,
                    CoordinatorLayout.LayoutParams.MATCH_PARENT
            );

            params.setMargins(0, 0, 0, 82);
            mContainer.setLayoutParams(params);
        }
    }

    public void hideMusicController(){
        if(mPlayerContainer.getVisibility() == View.VISIBLE) {
            mPlayerContainer.setVisibility(View.INVISIBLE);

            CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(
                    CoordinatorLayout.LayoutParams.MATCH_PARENT,
                    CoordinatorLayout.LayoutParams.MATCH_PARENT
            );

            params.setMargins(0, 0, 0, 0);
            mContainer.setLayoutParams(params);
        }
    }

    private ServiceConnection musicConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            TrackService.MusicBinder binder = (TrackService.MusicBinder) service;
            musicSrv = binder.getService();
            musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }
    };

    @Override
    protected void onDestroy() {
        stopService(playIntent);
        musicSrv = null;
        super.onDestroy();
    }

    @Override
    public TrackService getTrackService() {
        return musicSrv;
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
