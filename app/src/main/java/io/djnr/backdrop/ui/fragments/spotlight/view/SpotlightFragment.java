package io.djnr.backdrop.ui.fragments.spotlight.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.djnr.backdrop.R;
import io.djnr.backdrop.dagger.module.SpotlightFragmentModule;
import io.djnr.backdrop.interfaces.NavDrawerToggle;
import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.ui.App;
import io.djnr.backdrop.ui.activities.ambient.AmbientActivity;
import io.djnr.backdrop.ui.activities.main.view.MainActivity;
import io.djnr.backdrop.ui.fragments.spotlight.ISpotlight;

/**
 * Created by Dj on 8/18/2016.
 */
public class SpotlightFragment extends Fragment implements ISpotlight.RequiredView {
    private static final String TAG = "SpotlightPresenter";
    @BindView(R.id.recycler_spotlight)
    RecyclerView mRecyclerSpotlight;
    @BindView(R.id.img_ambient)
    ImageView mImageAmbient;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private int mAmbienceKey;
    private int[] mAmbientResId = {R.drawable.img_rain_white, R.drawable.img_cafe_white, R.drawable.img_storm_white,
            R.drawable.img_park_white, R.drawable.img_night_white, R.drawable.img_diner_white};

    @Inject
    ISpotlight.ProvidedPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spotlight, container, false);
        ButterKnife.bind(this, view);
        mRecyclerSpotlight.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ((MainActivity) getActivity()).setSupportActionBar(mToolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("");
        setupComponent();

        showNavToggle();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefAmbience = getActivity().getSharedPreferences("AMBIENCE_PREF", getActivity().MODE_PRIVATE);
        mAmbienceKey = prefAmbience.getInt("AMBIENCE", -1);
        if (mAmbienceKey != -1) {
            mImageAmbient.setImageResource(mAmbientResId[mAmbienceKey]);
        }
    }

    private void showNavToggle() {
        ((NavDrawerToggle) getActivity()).setupNavToggle(mToolbar);
    }

    @OnClick(R.id.fab_drop)
    public void onClickDrop() {
        ActivityOptionsCompat options;

//        if (mAmbienceKey != -1)
//            options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), (View) mImageAmbient, "ambient");
//        else
            options =  ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity());

        startActivity(new Intent(getActivity(), AmbientActivity.class), options.toBundle());
    }

    private void setupComponent() {
        App.get(getActivity())
                .getAppComponent()
                .getSpotlightFragmentComponent(new SpotlightFragmentModule(this))
                .inject(this);
    }

    @Override
    public Context getAppContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public void displaySpotlightPlaylists(List<Playlist> playlists) {
        SpotlightAdapter adapter = new SpotlightAdapter(playlists);
        mRecyclerSpotlight.setAdapter(adapter);
    }
}
