package io.djnr.backdrop.ui;

import android.app.Application;
import android.content.Context;

import io.djnr.backdrop.dagger.component.AppComponent;
import io.djnr.backdrop.dagger.component.DaggerAppComponent;
import io.djnr.backdrop.dagger.module.AppModule;

/**
 * Created by Dj on 8/21/2016.
 */
public class App extends Application{

    public static App get(Context context){
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initAppContext();
    }

    private AppComponent appComponent;

    private void initAppContext() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
