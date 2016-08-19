package io.djnr.backdrop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.remote.SoundcloudAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dj on 8/18/2016.
 */
public class SpotlightFragment extends Fragment{
    private static final String TAG = "SpotlightFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spotlight, container, false);
        ButterKnife.bind(this, view);
        Log.i(TAG, "onCreateView: "+"HALLO");

        SoundcloudAPI.Factory.getInstance().playlistSpotlight().enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                Log.i(TAG, "onResponse: "+response.body().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getCause());
            }
        });

        return view;
    }
}
