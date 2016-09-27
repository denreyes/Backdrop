package io.djnr.backdrop.dagger.component;

import dagger.Subcomponent;
import io.djnr.backdrop.dagger.module.MinTrackFragmentModule;
import io.djnr.backdrop.dagger.scope.FragmentScope;
import io.djnr.backdrop.ui.fragments.min_track.view.MinTrackFragment;

/**
 * Created by Dj on 9/27/2016.
 */
@FragmentScope
@Subcomponent(modules = MinTrackFragmentModule.class)
public interface MinTrackFragmentComponent {
    MinTrackFragment inject(MinTrackFragment fragment);
}
