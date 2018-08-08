package io.djnr.backdrop.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.djnr.backdrop.utils.PreferenceHelper


open class BaseActivity : AppCompatActivity() {
    var mPreferenceHelper: PreferenceHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPreferenceHelper = PreferenceHelper(this)
    }

    fun getPreferenceHelper(): PreferenceHelper? {
        return mPreferenceHelper
    }

}