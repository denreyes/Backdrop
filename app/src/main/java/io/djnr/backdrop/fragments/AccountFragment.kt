package io.djnr.backdrop.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.djnr.backdrop.R
import kotlinx.android.synthetic.main.fragment_account.view.*
import kotlinx.android.synthetic.main.toolbar.view.*

class AccountFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fragment_account, container, false)
        v.tvName.typeface = Typeface.createFromAsset(activity!!.assets, "montserrat_bold.ttf")
        v.tvToolbarTitle.typeface = Typeface.createFromAsset(activity!!.assets, "montserrat_bold.ttf")
        v.tvToolbarTitle.text = "Account"

        return v
    }
}
