package io.djnr.backdrop.ui.playlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.djnr.backdrop.R;
import io.djnr.backdrop.ui.playlist.view.PlaylistFragment;

/**
 * Created by Dj on 8/20/2016.
 */
public class PlaylistActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_layout);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new PlaylistFragment()).commit();
    }
}
