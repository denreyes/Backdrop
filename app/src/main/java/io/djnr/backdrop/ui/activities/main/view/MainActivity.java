package io.djnr.backdrop.ui.activities.main.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.djnr.backdrop.R;
import io.djnr.backdrop.dagger.module.ActivityModule;
import io.djnr.backdrop.interfaces.MinControllerDisplayer;
import io.djnr.backdrop.interfaces.NavDrawerToggle;
import io.djnr.backdrop.ui.App;
import io.djnr.backdrop.ui.activities.LoginActivity;
import io.djnr.backdrop.ui.activities.main.IMain;
import io.djnr.backdrop.ui.fragments.min_track.view.MinTrackFragment;
import io.djnr.backdrop.ui.fragments.spotlight.view.SpotlightFragment;
import io.djnr.backdrop.services.TrackService;
import io.djnr.backdrop.interfaces.TrackServiceProvider;

public class MainActivity extends AppCompatActivity implements IMain.RequiredView,
        TrackServiceProvider, MinControllerDisplayer, NavDrawerToggle, GoogleApiClient.OnConnectionFailedListener{
    private static final String TAG = "MainActivity";

    @BindView(R.id.player_container)
    FrameLayout mPlayerContainer;
    @BindView(R.id.container)
    FrameLayout mContainer;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    //Nav Views
    ImageView mImageProf;
    TextView mTextName;

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_SEARCH = "search";
    private static final String TAG_SPOTLIGHT = "spotlight";
    private static final String TAG_SAVED = "saved";
    private static final String TAG_SPOTIFY = "spotify";
    public static String CURRENT_TAG = TAG_SPOTLIGHT;

    @Inject
    IMain.ProvidedPresenter mPresenter;
    @Inject
    Intent playIntent;
    @Inject
    ServiceConnection musicConnection;
    @Inject
    CoordinatorLayout.LayoutParams params;
    @Inject
    GoogleApiClient mClient;

    TrackService mTrackService;
    ActionBarDrawerToggle mActionBarDrawerToggle;

    GoogleSignInAccount mAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        View headerView = mNavView.getHeaderView(0);
        mImageProf = ButterKnife.findById(headerView, R.id.img_prof_pic);
        mTextName = ButterKnife.findById(headerView, R.id.txt_name);

        mAccount = getIntent().getParcelableExtra("ACCOUNT");
        mTextName.setText(mAccount.getGivenName());
        Glide.with(this).load(mAccount.getPhotoUrl()).centerCrop().crossFade().into(mImageProf);

        setupComponent();

        bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
        startService(playIntent);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new SpotlightFragment())
                .replace(R.id.player_container, new MinTrackFragment())
                .commit();
    }

    private void setupComponent() {
        App.get(this)
                .getAppComponent()
                .getActivityComponent(new ActivityModule(this, this))
                .inject(this);
    }

    @Override
    public void showMinController() {
        if (mPlayerContainer.getVisibility() != View.VISIBLE) {
            mPlayerContainer.setVisibility(View.VISIBLE);

            params.setMargins(0, 0, 0, 82);
            mContainer.setLayoutParams(params);
        }
    }

    @Override
    public void hideMinController() {
        if (mPlayerContainer.getVisibility() == View.VISIBLE) {
            mPlayerContainer.setVisibility(View.INVISIBLE);

            params.setMargins(0, 0, 0, 0);
            mContainer.setLayoutParams(params);
        }
    }

    @Override
    protected void onDestroy() {
        stopService(playIntent);
        super.onDestroy();
    }

    @Override
    public TrackService getTrackService() {
        return mTrackService;
    }

    public void setTrackService(TrackService trackService) {
        mTrackService = trackService;
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void launchLoginScreen() {
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    @Override
    public void setupNavToggle(Toolbar toolbar) {
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_search:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_SEARCH;
                        break;
                    case R.id.nav_spotlight:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_SPOTLIGHT;
                        break;
                    case R.id.nav_saved:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_SAVED;
                        break;
                    case R.id.nav_spotify:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_SPOTIFY;
                        break;
                    case R.id.nav_logout:
                        mDrawerLayout.closeDrawers();

                        mPresenter.logOut(mClient);

                        break;
                    default:
                        navItemIndex = 0;
                }

                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }

                return true;
            }
        });

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name,R.string.app_name);
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }
}
