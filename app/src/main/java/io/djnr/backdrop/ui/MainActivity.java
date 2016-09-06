package io.djnr.backdrop.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.djnr.backdrop.R;
import io.djnr.backdrop.ui.player.PlayerFragment;
import io.djnr.backdrop.ui.spotlight.view.SpotlightFragment;
import io.djnr.backdrop.services.TrackService;

public class MainActivity extends AppCompatActivity {

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
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new SpotlightFragment())
                .replace(R.id.player_container, new PlayerFragment()).commit();

        if (mPlayerContainer.getVisibility() == View.VISIBLE) {
            CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(
                    CoordinatorLayout.LayoutParams.MATCH_PARENT,
                    CoordinatorLayout.LayoutParams.MATCH_PARENT
            );
            params.setMargins(0, 0, 0, 82);
            mContainer.setLayoutParams(params);

            Toast.makeText(this, "is Shown", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "not Shown", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (playIntent == null) {
            playIntent = new Intent(this, TrackService.class);
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
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
        Log.i("TrackService", "Service Destroyed: ");
        stopService(playIntent);
        musicSrv = null;
        super.onDestroy();
    }

    public TrackService getTrackService() {
        return musicSrv;
    }
}
