package io.djnr.backdrop.ui.fragments.ambient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.djnr.backdrop.R;
import io.djnr.backdrop.services.AmbientService;

/**
 * Created by Dj on 8/25/2016.
 */
public class AmbientFragment extends Fragment{
    @BindView(R.id.img_rain)
    ImageView mImageRain;
    @BindView(R.id.img_cafe)
    ImageView mImageCafe;
    @BindView(R.id.img_storm)
    ImageView mImageStorm;
    @BindView(R.id.img_park)
    ImageView mImagePark;
    @BindView(R.id.img_waves)
    ImageView mImageWaves;
    @BindView(R.id.img_diner)
    ImageView mImageDiner;

    private static int KEY_RAIN = 0;
    private static int KEY_CAFE = 1;
    private static int KEY_STORM = 2;
    private static int KEY_PARK = 3;
    private static int KEY_WAVES = 4;
    private static int KEY_DINER = 5;

    private SharedPreferences mPrefAmbience;

    private int[] mAmbientOnResIds = {R.drawable.img_activated_rain, R.drawable.img_activated_cafe, R.drawable.img_activated_storm,
            R.drawable.img_activated_park, R.drawable.img_activated_waves, R.drawable.img_activated_diner};
    private int[] mAmbientOffResIds = {R.drawable.img_ambient_rain, R.drawable.img_ambient_cafe, R.drawable.img_ambient_storm,
            R.drawable.img_ambient_park, R.drawable.img_ambient_waves, R.drawable.img_ambient_diner};
    private String[] mAmbientUrls;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ambient, container, false);
        ButterKnife.bind(this, view);
        mAmbientUrls = getResources().getStringArray(R.array.ambient_urls);

        mPrefAmbience = getActivity().getSharedPreferences("AMBIENCE_PREF", getActivity().MODE_PRIVATE);
        int key = getAmbientKeyFromPref(mPrefAmbience);
        loadAmbientImage(key, true);

        return view;
    }


    @OnClick(R.id.img_rain)
    public void onRain() {
        switchAmbient(KEY_RAIN);
    }

    @OnClick(R.id.img_cafe)
    public void onCafe() {
        switchAmbient(KEY_CAFE);
    }

    @OnClick(R.id.img_storm)
    public void onStorm() {
        switchAmbient(KEY_STORM);
    }

    @OnClick(R.id.img_park)
    public void onPark() {
        switchAmbient(KEY_PARK);
    }

    @OnClick(R.id.img_waves)
    public void onWaves() {
        switchAmbient(KEY_WAVES);
    }

    @OnClick(R.id.img_diner)
    public void onDiner() {
        switchAmbient(KEY_DINER);
    }

    private int getAmbientKeyFromPref(SharedPreferences prefAmbience){
        return mPrefAmbience.getInt("AMBIENCE", -1);
    }

    private void setAmbientKeyOnPref(SharedPreferences prefAmbience, int value){
        mPrefAmbience.edit().putInt("AMBIENCE", value).apply();
    }

    private void switchAmbient(int amb) {
        int prevAmb = getAmbientKeyFromPref(mPrefAmbience);
        loadAmbientImage(prevAmb, false);

        loadAmbientImage(amb, true);
        setAmbientKeyOnPref(mPrefAmbience, amb);

        if(amb != -1){
            Intent i = new Intent(getActivity(), AmbientService.class);
            i.putExtra("AUDIO_LINK", mAmbientUrls[amb]);
            getActivity().startService(i);
        }
    }

    private void loadAmbientImage(int key, boolean activate){
        int[] resId;
        if(activate)
            resId = mAmbientOnResIds;
        else
            resId = mAmbientOffResIds;

        switch (key){
            case 0: mImageRain.setImageResource(resId[key]);break;
            case 1: mImageCafe.setImageResource(resId[key]);break;
            case 2: mImageStorm.setImageResource(resId[key]);break;
            case 3: mImagePark.setImageResource(resId[key]);break;
            case 4: mImageWaves.setImageResource(resId[key]);break;
            case 5: mImageDiner.setImageResource(resId[key]);break;
        }
    }
}
