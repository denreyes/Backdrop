package io.djnr.backdrop.dagger.module;

import dagger.Module;
import dagger.Provides;
import io.djnr.backdrop.dagger.scope.ServiceScope;
import io.djnr.backdrop.services.TrackService;

/**
 * Created by Dj on 8/31/2016.
 */
@Module
public class TrackServiceModule {
    private TrackService service;

    public TrackServiceModule(TrackService service){
        this.service = service;
    }

    @Provides
    @ServiceScope
    TrackService providesTrackService(){
        return service;
    }
}
