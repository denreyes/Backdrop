package io.djnr.backdrop.ui.activities.main.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
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
import io.djnr.backdrop.interfaces.MinControllerDisplayer;
import io.djnr.backdrop.ui.App;
import io.djnr.backdrop.ui.activities.main.IMain;
import io.djnr.backdrop.ui.fragments.min_track.view.MinTrackFragment;
import io.djnr.backdrop.ui.fragments.spotlight.view.SpotlightFragment;
import io.djnr.backdrop.services.TrackService;
import io.djnr.backdrop.interfaces.TrackServiceProvider;

public class MainActivity extends AppCompatActivity implements IMain.RequiredView, TrackServiceProvider, MinControllerDisplayer {
    private static final String TAG = "MainActivity";

    @BindView(R.id.player_container)
    FrameLayout mPlayerContainer;
    @BindView(R.id.container)
    FrameLayout mContainer;

    @Inject
    IMain.ProvidedPresenter presenter;
    @Inject
    Intent playIntent;
    @Inject
    ServiceConnection musicConnection;
    @Inject
    CoordinatorLayout.LayoutParams params;

    TrackService mTrackService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupComponent();

        bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
        startService(playIntent);

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

    @Override
    public void showMinController() {
        if (mPlayerContainer.getVisibility() != View.VISIBLE) {
            mPlayerContainer.setVisibility(View.VISIBLE);

            params.setMargins(0, 0, 0, 82);
            mContainer.setLayoutParams(params);
        }
    }

    @Override
    public void hideMinController() {
        if (mPlayerContainer.getVisibility() == View.VISIBLE) {
            mPlayerContainer.setVisibility(View.INVISIBLE);

            params.setMargins(0, 0, 0, 0);
            mContainer.setLayoutParams(params);
        }
    }

    @Override
    protected void onDestroy() {
        stopService(playIntent);
        super.onDestroy();
    }

    @Override
    public TrackService getTrackService() {
        return mTrackService;
    }

    public void setTrackService(TrackService trackService) {
        mTrackService = trackService;
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
