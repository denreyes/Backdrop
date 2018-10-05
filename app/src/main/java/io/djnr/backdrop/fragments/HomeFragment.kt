package io.djnr.backdrop.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spotify.android.appremote.api.ContentApi
import com.spotify.protocol.types.ListItem
import io.djnr.backdrop.R
import io.djnr.backdrop.adapters.PlaylistAdapter
import io.djnr.backdrop.models.Playlist
import io.djnr.backdrop.utils.Constants
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.toolbar.view.*


class HomeFragment : Fragment() {
    var mAdapter: PlaylistAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fragment_home, container, false)
        v.tvToolbarTitle.typeface = Typeface.createFromAsset(activity!!.assets, "montserrat_bold.ttf")
        v.tvToolbarTitle.text = "Home"
        v.rvPlaylist.layoutManager = GridLayoutManager(activity, 2)

        mAdapter = PlaylistAdapter(ArrayList())
        v.rvPlaylist.adapter = mAdapter

        onGetFitnessChild()
        return v
    }

    private fun onGetFitnessChild() {
        Constants.sSpotifyAppRemote?.contentApi
                ?.getRecommendedContentItems(ContentApi.ContentType.DEFAULT)
                ?.setResultCallback { listItems ->
                    Constants.sSpotifyAppRemote?.contentApi
                            ?.getChildrenOfItem(listItems.items[0], 10, 0)
                            ?.setResultCallback { childListItems ->
                                Log.i("NIGGUH", "Item size: ${childListItems.items.size}")
                                var item: ListItem? = null
                                for (i in 0..childListItems.items.size) {
                                    item = childListItems.items[i]
                                    if (item!!.playable) {
                                        Log.i("NIGGUH", "i: " + item.title)
                                        Constants.sSpotifyAppRemote?.imagesApi?.getImage(item.imageUri)?.setResultCallback { bitmap ->
                                            Log.i("NIGGUH", "image added " + bitmap.toString())
                                            mAdapter?.add(Playlist(item, bitmap))
                                        }?.setErrorCallback { error ->
                                            Log.i("NIGGUH", "image error $error")
                                        }
//                                        TODO
//                                        Constants.sSpotifyAppRemote?.playerApi?.play(item.uri)?.setResultCallback { empty -> Log.i("NIGGUH", "Content item played!") }
//                                        break
                                    } else {
                                        item = null
                                    }
                                }
                            }
                }
    }
}
