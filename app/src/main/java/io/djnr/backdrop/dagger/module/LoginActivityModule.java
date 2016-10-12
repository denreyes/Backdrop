package io.djnr.backdrop.dagger.module;

import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.djnr.backdrop.dagger.scope.ActivityScope;
import io.djnr.backdrop.ui.activities.LoginActivity;

/**
 * Created by Dj on 10/12/2016.
 */
@Module
public class LoginActivityModule {
    private LoginActivity activity;

    public LoginActivityModule (LoginActivity activity){
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    LoginActivity providesLoginActivity(){
        return activity;
    }

//    @Provides
//    @ActivityScope
//    ILogin.ProvidedPresenter providedPresenter(){
//        LoginPresenter presenter = new LoginPresenter(activity);
//        LoginModel model = new LoginModel(presenter);
//        presenter.setModel(model);
//        return presenter;
//    }
}