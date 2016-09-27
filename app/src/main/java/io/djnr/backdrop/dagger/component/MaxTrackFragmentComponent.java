package io.djnr.backdrop.dagger.component;

import dagger.Subcomponent;
import io.djnr.backdrop.dagger.module.MaxTrackFragmentModule;
import io.djnr.backdrop.dagger.scope.FragmentScope;
import io.djnr.backdrop.ui.fragments.max_track.view.MaxTrackFragment;

/**
 * Created by Dj on 9/27/2016.
 */
@FragmentScope
@Subcomponent(modules = MaxTrackFragmentModule.class)
public interface MaxTrackFragmentComponent {
    MaxTrackFragment inject(MaxTrackFragment fragment);
}
