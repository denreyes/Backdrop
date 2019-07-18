package io.djnr.backdrop.fragments

import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.djnr.backdrop.R
import io.djnr.backdrop.activities.BaseActivity
import io.djnr.backdrop.mediaplayers.AmbiencePlayer
import io.djnr.backdrop.utils.Constants
import io.djnr.backdrop.utils.PreferenceHelper
import kotlinx.android.synthetic.main.fragment_ambience.view.*
import kotlinx.android.synthetic.main.toolbar.view.*
import io.djnr.backdrop.dialogs.VolumeFragment


class AmbienceFragment : Fragment() {
    var mPreferenceHelper: PreferenceHelper? = null
    var mAmbiencePlayer: AmbiencePlayer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fragment_ambience, container, false)
        mPreferenceHelper = (activity as BaseActivity).getPreferenceHelper()
        mAmbiencePlayer = AmbiencePlayer(context)

        v.tvToolbarTitle.typeface = Typeface.createFromAsset(activity!!.assets, "montserrat_bold.ttf")
        v.tvToolbarTitle.text = "Ambience"
        v.ivToolbarVolume.visibility = View.VISIBLE
        v.ivToolbarVolume.setOnClickListener {
            openVolumeDialog()
        }

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

    override fun onStop() {
        super.onStop()
        stopAmbience()
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

        var prevAmbience = mPreferenceHelper!!.getAmbience()

        if (ambience.equals(prevAmbience)) {
            mPreferenceHelper?.clearAmbience()
            stopAmbience()
        } else if (ambience == Constants.AMBIENCE_CAFE) {
            v.ivCafe.drawable!!.setColorFilter(colorSelected, PorterDuff.Mode.SRC_IN)
            mPreferenceHelper?.setAmbience(Constants.AMBIENCE_CAFE)
            playAmbience(R.raw.cafe)
        } else if (ambience == Constants.AMBIENCE_STORM) {
            v.ivStorm.drawable!!.setColorFilter(colorSelected, PorterDuff.Mode.SRC_IN)
            mPreferenceHelper?.setAmbience(Constants.AMBIENCE_STORM)
            playAmbience(R.raw.storm)
        } else if (ambience == Constants.AMBIENCE_WOODS) {
            v.ivWoods.drawable!!.setColorFilter(colorSelected, PorterDuff.Mode.SRC_IN)
            mPreferenceHelper?.setAmbience(Constants.AMBIENCE_WOODS)
            playAmbience(R.raw.woods)
        } else if (ambience == Constants.AMBIENCE_WAVES) {
            v.ivWaves.drawable!!.setColorFilter(colorSelected, PorterDuff.Mode.SRC_IN)
            mPreferenceHelper?.setAmbience(Constants.AMBIENCE_WAVES)
            playAmbience(R.raw.waves)
        } else if (ambience == Constants.AMBIENCE_BONFIRE) {
            v.ivBonfire.drawable!!.setColorFilter(colorSelected, PorterDuff.Mode.SRC_IN)
            mPreferenceHelper?.setAmbience(Constants.AMBIENCE_BONFIRE)
            playAmbience(R.raw.bonfire)
        } else {
            v.ivRain.drawable!!.setColorFilter(colorSelected, PorterDuff.Mode.SRC_IN)
            mPreferenceHelper?.setAmbience(Constants.AMBIENCE_RAIN)
            playAmbience(R.raw.rain)
        }
    }

    private fun playAmbience(resId: Int) {
        mAmbiencePlayer!!.release()
        mAmbiencePlayer!!.loadMedia(resId)
        mAmbiencePlayer!!.play()
        setAmbienceVolume(mPreferenceHelper!!.getAmbienceVolume())
    }

    private fun stopAmbience() {
        if (mAmbiencePlayer != null && mAmbiencePlayer!!.isPlaying) {
            mAmbiencePlayer!!.release()
        }
    }

    private fun openVolumeDialog() {
        val ft = fragmentManager!!.beginTransaction()
        val prev = fragmentManager!!.findFragmentByTag("dialog")
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)
        val dialogFragment = VolumeFragment()
        dialogFragment.setTargetFragment(this, 0)
        dialogFragment.show(ft, "dialog")
    }

    fun setAmbienceVolume(volume: Int) {
        if (mAmbiencePlayer!!.isPlaying)
            mAmbiencePlayer!!.setVolume(volume)
    }
}
