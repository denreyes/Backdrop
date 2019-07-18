package io.djnr.backdrop.utils

import android.content.Context

class PreferenceHelper(private val mContext: Context) : PreferenceBase(mContext) {
    private val AMBIENCE = "ambience"
    private val AMBIENCE_VOLUME = "ambience_volume"

    fun setAmbience(value: String) {
        if (Constants.ARR_AMBIENCE.contains(value.toLowerCase()))
            setPreference(AMBIENCE, value.toLowerCase())
    }

    fun setAmbienceVolume(value: Int) {
        setPreference(AMBIENCE_VOLUME, value)
    }

    fun clearAmbience() {
        setPreference(AMBIENCE, "")
    }

    fun getAmbience(): String {
        return getStringPreference(AMBIENCE)
    }

    fun getAmbienceVolume(): Int {
        return getIntPreference(AMBIENCE_VOLUME, 100)
    }
}
