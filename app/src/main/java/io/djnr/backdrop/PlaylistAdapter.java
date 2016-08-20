package io.djnr.backdrop;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.djnr.backdrop.models.soundcloud.Track;

/**
 * Created by Dj on 8/20/2016.
 */
public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    List<Track> mTracks;

    public PlaylistAdapter(List<Track> tracks) {
        this.mTracks = tracks;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.text_title)
        TextView mTextTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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
