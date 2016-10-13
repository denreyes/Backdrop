package io.djnr.backdrop.dagger.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import io.djnr.backdrop.dagger.module.ActivityModule;
import io.djnr.backdrop.dagger.module.AppModule;
import io.djnr.backdrop.dagger.module.MaxTrackFragmentModule;
import io.djnr.backdrop.dagger.module.MinTrackFragmentModule;
import io.djnr.backdrop.dagger.module.PlaylistFragmentModule;
import io.djnr.backdrop.dagger.module.SpotlightFragmentModule;
import io.djnr.backdrop.dagger.module.TrackServiceModule;

/**
 * Created by Dj on 8/21/2016.
 */
@Singleton
@Component( modules = AppModule.class )
public interface AppComponent {
    Application application();
    ActivityComponent getActivityComponent(ActivityModule module);
    PlaylistFragmentComponent getPlaylistFragmentComponent(PlaylistFragmentModule module);
    SpotlightFragmentComponent getSpotlightFragmentComponent(SpotlightFragmentModule module);
    MinTrackFragmentComponent getMinTrackFragmentComponent(MinTrackFragmentModule module);
    MaxTrackFragmentComponent getMaxTrackFragmentComponent(MaxTrackFragmentModule module);
    TrackServiceComponent getTrackServiceComponent(TrackServiceModule module);
}
