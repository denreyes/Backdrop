package io.djnr.backdrop.dialogs

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.*
import android.widget.SeekBar
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import io.djnr.backdrop.R
import io.djnr.backdrop.activities.BaseActivity
import io.djnr.backdrop.fragments.AmbienceFragment
import io.djnr.backdrop.utils.PreferenceHelper
import kotlinx.android.synthetic.main.dialog_volume.view.*


class VolumeFragment : DialogFragment() {
	var mPreferenceHelper: PreferenceHelper? = null

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
							  savedInstanceState: Bundle?): View? {
		var v = inflater.inflate(R.layout.dialog_volume, container, false)

		mPreferenceHelper = (activity as BaseActivity).getPreferenceHelper()

		val window = dialog.window
		window.attributes.windowAnimations = R.style.DialogAnimation
		window.setGravity(Gravity.CENTER_VERTICAL or Gravity.END)
		window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
		window.requestFeature(Window.FEATURE_NO_TITLE)

		v.tvVolume.typeface = Typeface.createFromAsset(activity!!.assets, "montserrat_bold.ttf")

		val params = window.attributes
		params.x = 32
		params.y = -32
		window.attributes = params

		if(targetFragment is AmbienceFragment){
			var parentFrag = targetFragment as AmbienceFragment
			v.sbAmbienceVolume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
				override fun onProgressChanged(sb: SeekBar?, pos: Int, bool: Boolean) {
					mPreferenceHelper!!.setAmbienceVolume(pos)
					parentFrag.setAmbienceVolume(pos)
				}

				override fun onStartTrackingTouch(sb: SeekBar?) {
					TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
				}

				override fun onStopTrackingTouch(sb: SeekBar?) {
					TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
				}

			})
		}

		v.sbAmbienceVolume.progress = mPreferenceHelper!!.getAmbienceVolume()

		return v
	}
}
