package io.djnr.backdrop.mediaplayers

interface IPlayer {

    val isPlaying: Boolean

    fun loadMedia(resourceId: Int)

    fun release()

    fun play()

    fun reset()

    fun pause()

    fun initializeProgressCallback()

    fun seekTo(position: Int)
}
