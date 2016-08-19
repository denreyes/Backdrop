package io.djnr.backdrop.remote;

import java.util.List;

import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.utils.Constants;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Dj on 8/18/2016.
 */
public interface SoundcloudAPI {
    public static final String BACKDROP_USER_ID = "user-936530439";
    public static final String BASE_URL = "http://api.soundcloud.com/";

    @GET("users/" + BACKDROP_USER_ID + "/playlists?client_id="+ Constants.SC_CLIENT_KEY)
    Call<List<Playlist>> playlistSpotlight();

    class Factory{
        private static SoundcloudAPI service;

        public static SoundcloudAPI getInstance(){

            if(service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                service = retrofit.create(SoundcloudAPI.class);
            }

            return service;
        }
    }

}
