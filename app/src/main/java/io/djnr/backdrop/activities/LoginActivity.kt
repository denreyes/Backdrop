package io.djnr.backdrop.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.Gson
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.spotify.sdk.android.player.*
import io.djnr.backdrop.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), ConnectionStateCallback, Player.NotificationCallback {

    private var mPlayer : SpotifyPlayer? = null

    private val CLIENT_ID = ""
    private val REDIRECT_URI = "backdrop.io/callback"
    private val REQUEST_CODE = 1337

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
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//            val builder = AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)
//            builder.setScopes(arrayOf("user-read-private", "streaming"))
//            val request = builder.build()
//
//            AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, intent)

            Log.i("LoginActivity", Gson().toJson(response))

            when (response.type) {
            // Response was successful and contains auth token
                AuthenticationResponse.Type.TOKEN -> {
                    Log.i("LoginActivity", "is equals to TOKEN")

                    val playerConfig = Config(this, response.accessToken, CLIENT_ID)
                    Spotify.getPlayer(playerConfig, this, object : SpotifyPlayer.InitializationObserver {
                        override fun onInitialized(spotifyPlayer: SpotifyPlayer) {
                            mPlayer = spotifyPlayer
                            mPlayer!!.addConnectionStateCallback(this@LoginActivity)
                            mPlayer!!.addNotificationCallback(this@LoginActivity)
                        }

                        override fun onError(throwable: Throwable) {
                            Log.e("LoginActivity", "Could not initialize player: " + throwable.message)
                        }
                    })
                }

            // Auth flow returned an error
                AuthenticationResponse.Type.ERROR -> {
                    Log.i("LoginActivity", "ERROR!: $response.error")
                }

                AuthenticationResponse.Type.EMPTY -> {
                    Log.i("LoginActivity", "EMPTY!")
                }
            }
        }
    }

    override fun onLoggedIn() {
        Log.d("LoginActivity", "User logged in")

        // This is the line that plays a song.
        mPlayer!!.playUri(null, "spotify:track:2TpxZ7JUBn3uw46aR7qd6V", 0, 0)
    }

    override fun onLoggedOut() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnectionMessage(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoginFailed(p0: Error?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTemporaryError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPlaybackError(p0: Error?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPlaybackEvent(p0: PlayerEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
