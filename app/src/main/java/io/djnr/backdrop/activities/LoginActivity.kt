package io.djnr.backdrop.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import io.djnr.backdrop.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        webView.loadDataWithBaseURL("file:///android_asset/", "<html style=\"" +
                "    background-image: url('rainy-jeff.gif');\n" +
                "    background-repeat: no-repeat;\n" +
                "    background-attachment: fixed;\n" +
                "    background-position: center;\"></html>", "text/html", "utf-8", "")

        tvTitle.typeface = Typeface.createFromAsset(assets, "montserrat_medium.ttf")

        btnSignin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
