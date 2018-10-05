package io.djnr.backdrop.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import io.djnr.backdrop.R
import io.djnr.backdrop.models.Playlist
import io.djnr.backdrop.utils.Constants


class PlaylistAdapter(var list: ArrayList<Playlist>) : RecyclerView.Adapter<PlaylistAdapter.PlaylistHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_item_spotlight, parent, false)

        return PlaylistHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PlaylistHolder, position: Int) {
        var item = list[position]
        holder.ivImage?.setImageBitmap(item.bitmap)
        holder.itemView.setOnClickListener {
            Constants.sSpotifyAppRemote?.playerApi?.play(item?.item?.uri)?.setResultCallback { empty -> Log.i("NIGGUH", "Content item played!") }
        }
    }

    fun add(item: Playlist) {
        list.add(item)
        notifyDataSetChanged()
    }

    inner class PlaylistHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivImage: ImageView? = null

        init {
            ivImage = view.findViewById(R.id.ivImage)
        }
    }
}