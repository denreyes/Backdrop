package io.djnr.backdrop.adapters

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
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
//        holder.tvTitle?.text = item.item?.title
//        holder.tvSubtitle?.text = item.item?.subtitle

        holder.tvTitle?.typeface = Typeface.createFromAsset(holder.itemView.context!!.assets, "montserrat_bold.ttf")

        if (item.bitmap != null) {
            Palette.from(item.bitmap!!).generate { palette ->
                if (palette?.darkVibrantSwatch != null)
                    holder.llInfo?.setBackgroundColor(getColorWithAlpha(palette.darkVibrantSwatch!!.rgb!!, 0.7f))
                else if (palette?.mutedSwatch != null)
                    holder.llInfo?.setBackgroundColor(getColorWithAlpha(palette.mutedSwatch!!.rgb!!, 0.7f))
            }
        }

        holder.itemView.setOnClickListener {
            //            try {
//                Constants.sSpotifyAppRemote?.playerApi?.play(item?.item?.uri)?.setResultCallback { empty -> Log.i("NIGGUH", "Content item played!") }
//            } catch (e: IllegalArgumentException) {
//                Log.i("NIGGUH", "error: $e")
//            }
        }
    }

    fun getColorWithAlpha(color: Int, ratio: Float): Int {
        var newColor = 0
        val alpha = Math.round(Color.alpha(color) * ratio)
        val r = Color.red(color)
        val g = Color.green(color)
        val b = Color.blue(color)
        newColor = Color.argb(alpha, r, g, b)
        return newColor
    }

    fun add(item: Playlist) {
        list.add(item)
        notifyDataSetChanged()
    }

    inner class PlaylistHolder(view: View) : RecyclerView.ViewHolder(view) {
        var llInfo: LinearLayout? = null
        var ivImage: ImageView? = null
        var tvTitle: TextView? = null
        var tvSubtitle: TextView? = null

        init {
            llInfo = view.findViewById(R.id.llInfo)
            ivImage = view.findViewById(R.id.ivImage)
            tvTitle = view.findViewById(R.id.tvTitle)
            tvSubtitle = view.findViewById(R.id.tvSubtitle)
        }
    }
}