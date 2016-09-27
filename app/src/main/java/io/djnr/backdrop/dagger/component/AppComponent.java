package io.djnr.backdrop.dagger.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import io.djnr.backdrop.dagger.module.AppModule;
import io.djnr.backdrop.dagger.module.MainActivityModule;
import io.djnr.backdrop.dagger.module.PlaylistFragmentModule;
import io.djnr.backdrop.dagger.module.SpotlightFragmentModule;
import io.djnr.backdrop.dagger.module.TrackServiceModule;
import io.djnr.backdrop.ui.activities.main.view.MainActivity;

/**
 * Created by Dj on 8/21/2016.
 */
@Singleton
@Component( modules = AppModule.class )
public interface AppComponent {
    Application application();
    MainActivityComponent getMainActivityComponent(MainActivityModule module);
    PlaylistFragmentComponent getPlaylistComponent(PlaylistFragmentModule module);
    SpotlightFragmentComponent getSpotlightComponent(SpotlightFragmentModule module);
    TrackServiceComponent getTrackServiceComponent(TrackServiceModule module);
}
