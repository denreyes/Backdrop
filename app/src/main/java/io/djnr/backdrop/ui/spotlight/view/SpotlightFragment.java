package io.djnr.backdrop.ui.spotlight.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.djnr.backdrop.R;
import io.djnr.backdrop.dagger.module.SpotlightFragmentModule;
import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.remote.SoundcloudAPI;
import io.djnr.backdrop.ui.App;
import io.djnr.backdrop.ui.spotlight.ISpotlight;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dj on 8/18/2016.
 */
public class SpotlightFragment extends Fragment implements ISpotlight.RequiredView {
    private static final String TAG = "SpotlightPresenter";
    @BindView(R.id.recycler_spotlight)
    RecyclerView mRecyclerSpotlight;

    @Inject
    ISpotlight.ProvidedPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spotlight, container, false);
        ButterKnife.bind(this, view);
        mRecyclerSpotlight.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        setupComponent();
        return view;
    }

    private void setupComponent() {
        App.get(getActivity())
                .getAppComponent()
                .getSpotlightComponent(new SpotlightFragmentModule(this))
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
