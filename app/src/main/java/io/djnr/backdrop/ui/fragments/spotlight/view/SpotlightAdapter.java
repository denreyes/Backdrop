package io.djnr.backdrop.ui.fragments.spotlight.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.djnr.backdrop.R;
import io.djnr.backdrop.models.soundcloud.Playlist;
import io.djnr.backdrop.ui.fragments.playlist.view.PlaylistFragment;
import io.djnr.backdrop.ui.activities.main.view.MainActivity;

/**
 * Created by Dj on 8/19/2016.
 */
public class SpotlightAdapter extends RecyclerView.Adapter<SpotlightAdapter.ViewHolder> {
    private static final String TAG = "SpotlightAdapter";
    List<Playlist> mList;

    public SpotlightAdapter(List<Playlist> list) {
        this.mList = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //        @BindView(R.id.text_title)
//        TextView mTextTitle;
//        @BindView(R.id.text_count)
//        TextView mTextCount;
        @BindView(R.id.thumbnail)
        ImageView mThumbnail;
        Context context;
        FragmentManager fragmentManager;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            fragmentManager = ((MainActivity) context).getSupportFragmentManager();
        }

        @OnClick(R.id.thumbnail)
        public void onClickCard() {
            Bundle args = new Bundle();
            args.putParcelable(PlaylistFragment.SC_PLAYLIST, mList.get(getPosition()));

            PlaylistFragment playlistFragment = new PlaylistFragment();
            playlistFragment.setArguments(args);
            fragmentManager.beginTransaction().add(R.id.container, playlistFragment)
                    .addToBackStack(playlistFragment.getClass().getName())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
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
        } catch (NullPointerException e) {
        }
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
