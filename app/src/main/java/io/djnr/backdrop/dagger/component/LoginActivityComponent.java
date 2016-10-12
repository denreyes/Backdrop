package io.djnr.backdrop.dagger.component;

import dagger.Subcomponent;
import io.djnr.backdrop.dagger.module.LoginActivityModule;
import io.djnr.backdrop.dagger.module.MainActivityModule;
import io.djnr.backdrop.dagger.scope.ActivityScope;
import io.djnr.backdrop.ui.activities.LoginActivity;
import io.djnr.backdrop.ui.activities.main.view.MainActivity;

/**
 * Created by Dj on 10/12/2016.
 */
@ActivityScope
@Subcomponent(modules = LoginActivityModule.class)
public interface LoginActivityComponent {
    LoginActivity inject(LoginActivity activity);
}