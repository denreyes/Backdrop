package io.djnr.backdrop.ui.fragments.min_track.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.djnr.backdrop.R;
import io.djnr.backdrop.dagger.module.MinTrackFragmentModule;
import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.ui.App;
import io.djnr.backdrop.ui.fragments.max_track.view.MaxTrackFragment;
import io.djnr.backdrop.ui.fragments.min_track.IMinTrack;
import io.djnr.backdrop.ui.fragments.playlist.view.PlaylistFragment;
import jp.wasabeef.blurry.Blurry;

/**
 * Created by Dj on 9/5/2016.
 */
public class MinTrackFragment extends Fragment implements IMinTrack.RequiredView,
        PlaylistFragment.PlayerUpdater, MaxTrackFragment.ControlUpdater {
    private static final String TAG = "MinTrackFragment";

    @BindView(R.id.img_album_art)
    ImageView mImageArt;
    @BindView(R.id.text_title)
    TextView mTextTitle;
    @BindView(R.id.text_artist)
    TextView mTextArtist;
    @BindView(R.id.img_btn_control)
    ImageView mImageControl;
    @BindView(R.id.seekBar)
    SeekBar mSeekbar;

    @Inject
    IMinTrack.ProvidedPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track_min, container, false);
        ButterKnife.bind(this, view);
        setupComponent();
        mSeekbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mPresenter.initTrackServiceCallback();
        return view;
    }

    private void setupComponent() {
        App.get(getActivity())
                .getAppComponent()
                .getMinTrackFragmentComponent(new MinTrackFragmentModule(this))
                .inject(this);
    }

    @OnClick({R.id.img_btn_control, R.id.frame_control})
    public void onControlClicked() {
        mPresenter.playPause();
    }

    @OnClick(R.id.img_btn_up)
    public void onUpClicked() {
        mPresenter.switchToMaxView();
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
    public void setMaxProgress(int mediaMax_new, int mediaPos_new) {
        mSeekbar.setMax(mediaMax_new);
        mSeekbar.setProgress(mediaPos_new);
    }

    @Override
    public void setBackgroundArt(Bitmap bitmap) {
        mImageArt.setImageBitmap(bitmap);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Blurry.with(getActivity()).radius(6).sampling(4)
                        .color(Color.argb(204, 0, 0, 0))
                        .animate(500)
                        .async().capture(mImageArt).into(mImageArt);
            }
        });
    }

    @Override
    public void setViews(String title, String username) {
        mTextTitle.setText(title);
        mTextArtist.setText(username);
        mSeekbar.setProgress(0);
        mImageControl.setImageResource(R.drawable.ic_pause);
    }

    @Override
    public void setImagePausePlay(boolean b) {
        if (b) {
            //play
            mImageControl.setImageResource(R.drawable.ic_play);
        } else {
            //pause
            mImageControl.setImageResource(R.drawable.ic_pause);
        }
    }

    @Override
    public void launchMaxFragment(MaxTrackFragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_out_up, R.anim.slide_in_up,
                        R.anim.slide_out_up, R.anim.slide_in_up)
                .add(R.id.container, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    //Callbacks
    @Override
    public void updateOnPause(boolean isPlaying) {
        mPresenter.updateOnPause(isPlaying);
    }

    @Override
    public void updateOnSkip(int currentPos) {
        mPresenter.updateOnSkip(currentPos);
    }

    @Override
    public void updatePlayer(Playlist playlist, int currentPos) {
        mPresenter.updatePlayer(playlist, currentPos);
    }
}
