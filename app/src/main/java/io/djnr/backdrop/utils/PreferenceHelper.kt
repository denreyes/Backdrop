package io.djnr.backdrop.utils

import android.content.Context
import android.util.Log

class PreferenceHelper(private val mContext: Context) : PreferenceBase(mContext) {
    private val AMBIENCE = "ambience"

    fun setAmbience(value: String) {
        if (Constants.ARR_AMBIENCE.contains(value.toLowerCase()))
            setPreference(AMBIENCE, value.toLowerCase())
    }

    fun getAmbience(): String {
        var ambience = getStringPreference(AMBIENCE)
        if (ambience == null || ambience == "")
            ambience = "rain"
        Log.i("PreferenceHelper", "stored ambience: $ambience")
        return ambience
    }
}
