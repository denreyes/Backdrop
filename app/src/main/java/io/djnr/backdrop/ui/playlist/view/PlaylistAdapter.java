package io.djnr.backdrop.ui.playlist.view;

import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.djnr.backdrop.R;
import io.djnr.backdrop.models.soundcloud.Track;
import io.djnr.backdrop.utils.Config;

/**
 * Created by Dj on 8/20/2016.
 */
public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    private static final String TAG = "PlaylistAdapter";
    List<Track> mTracks;
    MediaPlayer mMediaPlayer;

    public PlaylistAdapter(List<Track> tracks, MediaPlayer mediaPlayer) {
        this.mTracks = tracks;
        this.mMediaPlayer = mediaPlayer;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        @BindView(R.id.text_title)
        TextView mTextTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
                mMediaPlayer.reset();
            }

            try {
                mMediaPlayer.setDataSource(mTracks.get(getPosition()).getStreamUrl() + "?client_id=" + Config.SC_CLIENT_KEY);
                mMediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_playlist , parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextTitle.setText(mTracks.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }
}
