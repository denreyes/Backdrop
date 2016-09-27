package io.djnr.backdrop.dagger.component;

import dagger.Subcomponent;
import io.djnr.backdrop.dagger.module.PlaylistFragmentModule;
import io.djnr.backdrop.dagger.scope.FragmentScope;
import io.djnr.backdrop.ui.fragments.playlist.view.PlaylistFragment;

/**
 * Created by Dj on 8/21/2016.
 */
@FragmentScope
@Subcomponent(modules = PlaylistFragmentModule.class)
public interface PlaylistFragmentComponent {
    PlaylistFragment inject(PlaylistFragment fragment);
}
