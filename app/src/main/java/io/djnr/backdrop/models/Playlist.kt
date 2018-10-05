package io.djnr.backdrop.models

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName
import com.spotify.protocol.types.ListItem

data class Playlist(val item: ListItem?, val bitmap: Bitmap?)