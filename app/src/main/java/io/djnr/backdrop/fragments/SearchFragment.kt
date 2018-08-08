package io.djnr.backdrop.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.djnr.backdrop.R
import kotlinx.android.synthetic.main.toolbar.view.*

class SearchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fragment_search, container, false)
        v.tvToolbarTitle.typeface = Typeface.createFromAsset(activity!!.assets, "montserrat_bold.ttf")
        v.tvToolbarTitle.text = "Search"

        return v
    }
}
