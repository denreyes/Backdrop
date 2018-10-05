package io.djnr.backdrop.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import io.djnr.backdrop.R
import io.djnr.backdrop.utils.Constants
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

            var connectionParams = ConnectionParams.Builder(CLIENT_ID)
                    .setRedirectUri(REDIRECT_URI)
                    .setPreferredImageSize(resources.getDimension(R.dimen.image_size).toInt())
                    .showAuthView(true)
                    .build()


            SpotifyAppRemote.CONNECTOR.connect(this, connectionParams,
                    object : Connector.ConnectionListener {
                        override fun onConnected(spotifyAppRemote: SpotifyAppRemote) {
                            Log.d("NIGGUH", "Connected! Yay!")
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))

                            // Now you can start interacting with App Remote
                            Constants.sSpotifyAppRemote = spotifyAppRemote
//                          connected()
                        }

                        override fun onFailure(throwable: Throwable) {
                            Log.e("NIGGUH", throwable.message, throwable)
                        }
                    })
        }
    }

    override fun onStart() {
        super.onStart()
    }
}
