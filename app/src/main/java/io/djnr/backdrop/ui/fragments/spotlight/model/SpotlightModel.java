package io.djnr.backdrop.ui.fragments.spotlight.model;

import android.util.Log;

import java.util.List;

import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.remote.SoundcloudAPI;
import io.djnr.backdrop.ui.fragments.spotlight.ISpotlight;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dj on 8/22/2016.
 */
public class SpotlightModel implements ISpotlight.ProvidedModel{
    private static final String TAG = "SpotlightModel";
    private ISpotlight.RequiredPresenter mPresenter;

    public SpotlightModel(ISpotlight.RequiredPresenter presenter){
        this.mPresenter = presenter;
    }

    @Override
    public void getSpotlightPlaylists() {
        SoundcloudAPI.Factory.getInstance().playlistSpotlight().enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                mPresenter.setSpotlightPlaylists(response.body());
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getCause());
            }
        });
    }
}
