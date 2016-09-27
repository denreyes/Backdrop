package io.djnr.backdrop.dagger.component;

import dagger.Subcomponent;
import io.djnr.backdrop.dagger.module.MainActivityModule;
import io.djnr.backdrop.dagger.scope.ActivityScope;
import io.djnr.backdrop.ui.activities.main.view.MainActivity;

/**
 * Created by Dj on 9/27/2016.
 */
@ActivityScope
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent {
    MainActivity inject(MainActivity activity);
}
