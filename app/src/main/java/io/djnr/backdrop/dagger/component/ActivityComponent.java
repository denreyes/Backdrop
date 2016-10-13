package io.djnr.backdrop.dagger.component;

import dagger.Subcomponent;
import io.djnr.backdrop.dagger.module.ActivityModule;
import io.djnr.backdrop.dagger.scope.ActivityScope;
import io.djnr.backdrop.ui.activities.LoginActivity;
import io.djnr.backdrop.ui.activities.main.view.MainActivity;

/**
 * Created by Dj on 9/27/2016.
 */
@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    MainActivity inject(MainActivity activity);
    LoginActivity inject(LoginActivity activity);
}
