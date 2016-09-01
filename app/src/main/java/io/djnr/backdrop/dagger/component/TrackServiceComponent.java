package io.djnr.backdrop.dagger.component;

import dagger.Subcomponent;
import io.djnr.backdrop.dagger.module.TrackServiceModule;
import io.djnr.backdrop.dagger.scope.ServiceScope;
import io.djnr.backdrop.services.TrackService;

/**
 * Created by Dj on 8/31/2016.
 */
@ServiceScope
@Subcomponent(modules = TrackServiceModule.class)
public interface TrackServiceComponent {
    TrackService inject(TrackService service);
}
