package io.djnr.backdrop.fragments

import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.djnr.backdrop.R
import io.djnr.backdrop.activities.BaseActivity
import io.djnr.backdrop.utils.Constants
import io.djnr.backdrop.utils.PreferenceHelper
import kotlinx.android.synthetic.main.fragment_ambience.view.*
import kotlinx.android.synthetic.main.toolbar.view.*

class AmbienceFragment : Fragment() {
    var mPreferenceHelper: PreferenceHelper? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fragment_ambience, container, false)
        mPreferenceHelper = (activity as BaseActivity).getPreferenceHelper()
        v.tvToolbarTitle.typeface = Typeface.createFromAsset(activity!!.assets, "montserrat_bold.ttf")
        v.tvToolbarTitle.text = "Ambience"

        switchAmbience(v, mPreferenceHelper!!.getAmbience())

        v.ivRain.setOnClickListener {
            switchAmbience(v, Constants.AMBIENCE_RAIN)
        }
        v.ivCafe.setOnClickListener {
            switchAmbience(v, Constants.AMBIENCE_CAFE)
        }
        v.ivStorm.setOnClickListener {
            switchAmbience(v, Constants.AMBIENCE_STORM)
        }
        v.ivBonfire.setOnClickListener {
            switchAmbience(v, Constants.AMBIENCE_BONFIRE)
        }
        v.ivWaves.setOnClickListener {
            switchAmbience(v, Constants.AMBIENCE_WAVES)
        }
        v.ivWoods.setOnClickListener {
            switchAmbience(v, Constants.AMBIENCE_WOODS)
        }

        return v
    }

    private fun switchAmbience(v: View, ambience: String) {
        val colorUnselected = ContextCompat.getColor(activity!!, android.R.color.white)
        val colorSelected = ContextCompat.getColor(activity!!, R.color.colorAccent)

        v.ivRain.drawable!!.setColorFilter(colorUnselected, PorterDuff.Mode.SRC_IN)
        v.ivCafe.drawable!!.setColorFilter(colorUnselected, PorterDuff.Mode.SRC_IN)
        v.ivStorm.drawable!!.setColorFilter(colorUnselected, PorterDuff.Mode.SRC_IN)
        v.ivWoods.drawable!!.setColorFilter(colorUnselected, PorterDuff.Mode.SRC_IN)
        v.ivWaves.drawable!!.setColorFilter(colorUnselected, PorterDuff.Mode.SRC_IN)
        v.ivBonfire.drawable!!.setColorFilter(colorUnselected, PorterDuff.Mode.SRC_IN)

        if (ambience == Constants.AMBIENCE_CAFE) {
            v.ivCafe.drawable!!.setColorFilter(colorSelected, PorterDuff.Mode.SRC_IN)
            mPreferenceHelper?.setAmbience(Constants.AMBIENCE_CAFE)
        } else if (ambience == Constants.AMBIENCE_STORM) {
            v.ivStorm.drawable!!.setColorFilter(colorSelected, PorterDuff.Mode.SRC_IN)
            mPreferenceHelper?.setAmbience(Constants.AMBIENCE_STORM)
        } else if (ambience == Constants.AMBIENCE_WOODS) {
            v.ivWoods.drawable!!.setColorFilter(colorSelected, PorterDuff.Mode.SRC_IN)
            mPreferenceHelper?.setAmbience(Constants.AMBIENCE_WOODS)
        } else if (ambience == Constants.AMBIENCE_WAVES) {
            v.ivWaves.drawable!!.setColorFilter(colorSelected, PorterDuff.Mode.SRC_IN)
            mPreferenceHelper?.setAmbience(Constants.AMBIENCE_WAVES)
        } else if (ambience == Constants.AMBIENCE_BONFIRE) {
            v.ivBonfire.drawable!!.setColorFilter(colorSelected, PorterDuff.Mode.SRC_IN)
            mPreferenceHelper?.setAmbience(Constants.AMBIENCE_BONFIRE)
        } else {
            v.ivRain.drawable!!.setColorFilter(colorSelected, PorterDuff.Mode.SRC_IN)
            mPreferenceHelper?.setAmbience(Constants.AMBIENCE_RAIN)
        }
    }
}
