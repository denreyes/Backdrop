package io.djnr.backdrop.dagger.module;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.djnr.backdrop.services.TrackService;

/**
 * Created by Dj on 8/21/2016.
 */
@Module
public class AppModule {

    private Application app;

    public AppModule(Application app){
        this.app = app;
    }

    @Provides
    @Singleton
    public Application providesApplication(){
        return app;
    }

//    @Provides
//    @Singleton
//    public GoogleSignInOptions providesGoogleSignInOptions(){
//        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestProfile()
//                .requestEmail()
//                .build();
//    }
//
//    @Provides
//    @Singleton
//    public GoogleApiClient providesGoogleSignInClient(GoogleSignInOptions gso, AppCompatActivity activity, GoogleApiClient.OnConnectionFailedListener listener){
//        GoogleApiClient client = new GoogleApiClient.Builder(activity)
//                .enableAutoManage(activity, listener)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
//
//        return client;
//    }

}
