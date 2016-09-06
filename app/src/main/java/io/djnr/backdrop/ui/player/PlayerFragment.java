package io.djnr.backdrop.ui.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.djnr.backdrop.R;

/**
 * Created by Dj on 9/5/2016.
 */
public class PlayerFragment extends Fragment {
    @BindView(R.id.img_album_art)
    ImageView mImageArt;
    @BindView(R.id.text_title)
    TextView mTextTitle;
    @BindView(R.id.text_artist)
    TextView mTextArtist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        ButterKnife.bind(this, view);
        Glide.with(this).load("https://consequenceofsound.files.wordpress.com/2015/12/the-1975-cover.jpg").centerCrop().crossFade().into(mImageArt);
        mTextTitle.setText("Nana");
        mTextArtist.setText("The 1975");

        return view;
    }
}
