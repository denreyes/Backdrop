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
        mPreferences!!.edit().putInt(PREF_KEY, value).apply()
    }

    protected fun setPreference(key: String, value: Boolean) {
        mPreferences!!.edit().putBoolean(PREF_KEY, value).apply()
    }

    protected fun getStringPreference(key: String) : String {
        return mPreferences!!.getString(PREF_KEY, "")
    }

    protected fun getIntPreference(key: String) : Int {
        return mPreferences!!.getInt(PREF_KEY, -1)
    }
}
