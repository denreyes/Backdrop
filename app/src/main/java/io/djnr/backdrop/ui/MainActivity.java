package io.djnr.backdrop.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import io.djnr.backdrop.R;
import io.djnr.backdrop.ui.spotlight.view.SpotlightFragment;
import io.djnr.backdrop.services.TrackService;

public class MainActivity extends AppCompatActivity {

    @Inject
    TrackService musicSrv;
    private Intent playIntent;
    private boolean musicBound=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_layout);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new SpotlightFragment()).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(playIntent==null){
            playIntent = new Intent(this, TrackService.class);
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }

    private ServiceConnection musicConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            TrackService.MusicBinder binder = (TrackService.MusicBinder)service;
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
        musicSrv=null;
        super.onDestroy();
    }

    public TrackService getTrackService(){
        return musicSrv;
    }
}
