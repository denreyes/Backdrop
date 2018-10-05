package io.djnr.backdrop.utils

import com.spotify.android.appremote.api.SpotifyAppRemote

class Constants {

    companion object {
        val AMBIENCE_RAIN = "rain"
        val AMBIENCE_CAFE = "cafe"
        val AMBIENCE_STORM = "storm"
        val AMBIENCE_BONFIRE = "bonfire"
        val AMBIENCE_WAVES = "waves"
        val AMBIENCE_WOODS = "woods"
        val ARR_AMBIENCE = arrayOf(AMBIENCE_RAIN, AMBIENCE_CAFE, AMBIENCE_STORM,
                AMBIENCE_BONFIRE, AMBIENCE_WAVES, AMBIENCE_WOODS)

        var sSpotifyAppRemote : SpotifyAppRemote? = null
    }
}