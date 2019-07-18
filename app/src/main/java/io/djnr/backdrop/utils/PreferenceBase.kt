package io.djnr.backdrop.utils

import android.content.Context
import android.content.SharedPreferences

abstract class PreferenceBase(private val mContext: Context) {
    val PREF_KEY = "backdrop_pref"
    var mPreferences : SharedPreferences? = null

    init {
        mPreferences = mContext.getSharedPreferences(PREF_KEY, 0)
    }

    protected fun setPreference(key: String, value: String) {
        mPreferences!!.edit().putString(key, value).apply()
    }

    protected fun setPreference(key: String, value: Int) {
        mPreferences!!.edit().putInt(key, value).apply()
    }

    protected fun setPreference(key: String, value: Boolean) {
        mPreferences!!.edit().putBoolean(key, value).apply()
    }

    protected fun setPreference(key: String, value: Float) {
        mPreferences!!.edit().putFloat(key, value).apply()
    }

    protected fun getStringPreference(key: String) : String {
        return mPreferences!!.getString(key, "")
    }

    protected fun getIntPreference(key: String) : Int {
        return mPreferences!!.getInt(key, -1)
    }

    protected fun getIntPreference(key: String, default: Int) : Int {
        return mPreferences!!.getInt(key, default)
    }

    protected fun getFloatPreference(key: String) : Float {
        return mPreferences!!.getFloat(key, (-1).toFloat())
    }

    protected fun getFloatPreference(key: String, default: Float) : Float {
        return mPreferences!!.getFloat(key, default)
    }
}
