package io.djnr.backdrop.dagger.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import io.djnr.backdrop.dagger.module.AppModule;
import io.djnr.backdrop.dagger.module.PlaylistFragmentModule;

/**
 * Created by Dj on 8/21/2016.
 */
@Singleton
@Component( modules = AppModule.class )
public interface AppComponent {
    Application application();
    PlaylistFragmentComponent getPlaylistComponent(PlaylistFragmentModule module);
}
