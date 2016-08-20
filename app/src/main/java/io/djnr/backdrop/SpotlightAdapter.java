package io.djnr.backdrop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.djnr.backdrop.models.soundcloud.Playlist;

/**
 * Created by Dj on 8/19/2016.
 */
public class SpotlightAdapter extends RecyclerView.Adapter<SpotlightAdapter.ViewHolder> {
    private static final String TAG = "SpotlightAdapter";
    List<Playlist> mList;

    public SpotlightAdapter(List<Playlist> list){
        this.mList = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{
        @BindView(R.id.text_title)
        TextView mTextTitle;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, PlaylistActivity.class);
            intent.putExtra(PlaylistFragment.SC_PLAYLIST, mList.get(getPosition()));
            context.startActivity(intent);
        }
    }

    @Override
    public SpotlightAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_spotlight, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SpotlightAdapter.ViewHolder holder, int position) {
        holder.mTextTitle.setText(mList.get(position).getTitle());
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
