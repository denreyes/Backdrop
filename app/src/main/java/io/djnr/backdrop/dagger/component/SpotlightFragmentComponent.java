package io.djnr.backdrop.dagger.component;

import dagger.Subcomponent;
import io.djnr.backdrop.dagger.module.SpotlightFragmentModule;
import io.djnr.backdrop.dagger.scope.FragmentScope;
import io.djnr.backdrop.ui.spotlight.view.SpotlightFragment;

/**
 * Created by Dj on 8/22/2016.
 */
@FragmentScope
@Subcomponent(modules = SpotlightFragmentModule.class)
public interface SpotlightFragmentComponent {
    SpotlightFragment inject(SpotlightFragment fragment);
}
