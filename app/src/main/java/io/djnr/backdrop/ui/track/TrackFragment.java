package io.djnr.backdrop.ui.track;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.djnr.backdrop.R;
import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.models.soundcloud.Track;
import io.djnr.backdrop.ui.MainActivity;

/**
 * Created by Dj on 9/7/2016.
 */
public class TrackFragment extends Fragment {
    @BindView(R.id.img_track_art)
    CircleImageView mImageArt;
    @BindView(R.id.txt_title)
    TextView mTextTitle;
    @BindView(R.id.txt_artist)
    TextView mTextArtist;
    @BindView(R.id.txt_track_title)
    TextView mTextTrackTitle;
    @BindView(R.id.txt_track_artist)
    TextView mTextTrackArtist;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private Playlist mPlaylist;
    private int currentPos;

    private static final float ROTATE_FROM = 0.0f;
    private static final float ROTATE_TO = -10.0f * 360.0f;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track, container, false);
        ButterKnife.bind(this, view);
        ((MainActivity) getActivity()).hideMusicController();

        ((MainActivity) getActivity()).setSupportActionBar(mToolbar);
        mPlaylist = getArguments().getParcelable("PLAYLIST");
        currentPos = getArguments().getInt("CURRENT_POS");
        Track track = mPlaylist.getTracks().get(currentPos);

        mTextTitle.setText(mPlaylist.getTitle());
        mTextArtist.setText(mPlaylist.getUser().getUsername());
        mTextTrackTitle.setText(track.getTitle());
        mTextTrackArtist.setText(track.getUser().getUsername());
        Glide.with(this).load(track.getArtworkUrl().replace("large.jpg", "t500x500.jpg"))

                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        Log.i("GLIDEE", "EXCEPTION");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Animation rotation = AnimationUtils.loadAnimation(getActivity(), R.anim.disc_spin);
                                mImageArt.startAnimation(rotation);
                            }
                        }, 1000);
                        return false;
                    }
                })
                .into(mImageArt);
        return view;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.trackfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.min_player) {
            getActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MainActivity) getActivity()).showMusicController();
    }
}
