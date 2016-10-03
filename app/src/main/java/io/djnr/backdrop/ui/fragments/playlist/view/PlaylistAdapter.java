package io.djnr.backdrop.ui.fragments.playlist.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.djnr.backdrop.R;
import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.models.soundcloud.Track;
import io.djnr.backdrop.services.TrackService;
import io.djnr.backdrop.ui.activities.main.view.MainActivity;

/**
 * Created by Dj on 8/20/2016.
 */
public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    private static final String TAG = "PlaylistAdapter";
    Playlist mPlaylist;
    List<Track> mTracks;
    TrackService mTrackService;
    PlaylistFragment.PlayerUpdater mUpdater;

    public PlaylistAdapter(Playlist playlist, TrackService trackService, PlaylistFragment.PlayerUpdater updater) {
        this.mPlaylist = playlist;
        this.mTracks = playlist.getTracks();
        this.mTrackService = trackService;
        this.mUpdater = updater;
    }

    public PlaylistAdapter(Playlist playlist, TrackService trackService){
        this.mPlaylist = playlist;
        this.mTracks = playlist.getTracks();
        this.mTrackService = trackService;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        @BindView(R.id.text_title)
        TextView mTextTitle;
        @BindView(R.id.text_artist)
        TextView mTextArtist;
        @BindView(R.id.thumbnail)
        ImageView mThumbnail;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mTrackService.setList(mTracks);
            mTrackService.setSong(getPosition());
            mTrackService.playSong();

            if (mUpdater != null){
                ((MainActivity) context).showMinController();
                mUpdater.updatePlayer(mPlaylist, getPosition());
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
        holder.mTextArtist.setText(mTracks.get(position).getUser().getUsername());
        Glide.with(holder.context).load(mTracks.get(position).getArtworkUrl())
                .placeholder(R.drawable.no_img).into(holder.mThumbnail);
    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }
}
