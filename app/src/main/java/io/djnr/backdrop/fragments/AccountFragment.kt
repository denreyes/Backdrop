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

        v.webView.loadDataWithBaseURL("file:///android_asset/", "<html style=\"" +
                "    background-image: url('rainy-jeff.gif');\n" +
                "    background-repeat: no-repeat;\n" +
                "    background-attachment: fixed;\n" +
                "    background-position: 0% 20%;\n" +
                "    background-size: 107%;;\"></html>", "text/html", "utf-8", "")

        return v
    }
}
