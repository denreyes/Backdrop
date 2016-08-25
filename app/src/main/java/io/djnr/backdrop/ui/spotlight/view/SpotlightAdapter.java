package io.djnr.backdrop.ui.spotlight.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.djnr.backdrop.R;
import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.ui.playlist.PlaylistActivity;
import io.djnr.backdrop.ui.playlist.view.PlaylistFragment;

/**
 * Created by Dj on 8/19/2016.
 */
public class SpotlightAdapter extends RecyclerView.Adapter<SpotlightAdapter.ViewHolder> {
    private static final String TAG = "SpotlightAdapter";
    List<Playlist> mList;

    public SpotlightAdapter(List<Playlist> list){
        this.mList = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
//        @BindView(R.id.text_title)
//        TextView mTextTitle;
//        @BindView(R.id.text_count)
//        TextView mTextCount;
        @BindView(R.id.thumbnail)
        ImageView mThumbnail;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        @OnClick(R.id.thumbnail)
        public void onClickCard() {
            Intent intent = new Intent(context, PlaylistActivity.class);
            intent.putExtra(PlaylistFragment.SC_PLAYLIST, mList.get(getPosition()));
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity)context, (View)mThumbnail, "album_art");
            context.startActivity(intent, options.toBundle());
        }
    }

    @Override
    public SpotlightAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_spotlight, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SpotlightAdapter.ViewHolder holder, int position) {
//        holder.mTextTitle.setText(mList.get(position).getTitle());
//        holder.mTextCount.setText(mList.get(position).getTrackCount() + " songs.");
        try {
            Glide.with(holder.context).load(mList.get(position).getArtworkUrl().toString().replace("large.jpg", "t500x500.jpg"))
                    .into(holder.mThumbnail);
        }catch (NullPointerException e){}
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
