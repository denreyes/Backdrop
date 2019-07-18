package io.djnr.backdrop.mediaplayers;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.djnr.backdrop.utils.Constants;

public final class AmbiencePlayer implements IPlayer {

	public static final int PLAYBACK_POSITION_REFRESH_INTERVAL_MS = 1000;

	private final Context mContext;
	private MediaPlayer mMediaPlayer;
	private int mResourceId;
	private PlaybackInfoListener mPlaybackInfoListener;
	private ScheduledExecutorService mExecutor;
	private Runnable mSeekbarPositionUpdateTask;

	public AmbiencePlayer(Context context) {
		mContext = context.getApplicationContext();
	}

	/**
	 * Once the {@link MediaPlayer} is released, it can't be used again, and another one has to be
	 * created. In the onStop() method of the {@link MainActivity} the {@link MediaPlayer} is
	 * released. Then in the onStart() of the {@link MainActivity} a new {@link MediaPlayer}
	 * object has to be created. That's why this method is private, and called by load(int) and
	 * not the constructor.
	 */
	private void initializeMediaPlayer() {
		if (mMediaPlayer == null) {
			mMediaPlayer = new MediaPlayer();
			mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mediaPlayer) {
					stopUpdatingCallbackWithPosition(true);
					logToUI("MediaPlayer playback completed");
					if (mPlaybackInfoListener != null) {
						mPlaybackInfoListener.onStateChanged(PlaybackInfoListener.State.COMPLETED);
						mPlaybackInfoListener.onPlaybackCompleted();
					}
				}
			});
			logToUI("mMediaPlayer = new MediaPlayer()");
		}
	}

	public void setPlaybackInfoListener(PlaybackInfoListener listener) {
		mPlaybackInfoListener = listener;
	}

	// Implements PlaybackControl.
	@Override
	public void loadMedia(int resourceId) {
		mResourceId = resourceId;

		initializeMediaPlayer();

		AssetFileDescriptor assetFileDescriptor =
				mContext.getResources().openRawResourceFd(mResourceId);
		try {
			logToUI("load() {1. setDataSource}");
			mMediaPlayer.setDataSource(assetFileDescriptor);
		} catch (Exception e) {
			logToUI(e.toString());
		}

		try {
			logToUI("load() {2. prepare}");
			mMediaPlayer.prepare();
		} catch (Exception e) {
			logToUI(e.toString());
		}

		initializeProgressCallback();
		logToUI("initializeProgressCallback()");
	}

	@Override
	public void release() {
		if (mMediaPlayer != null) {
			logToUI("release() and mMediaPlayer = null");
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}

	@Override
	public boolean isPlaying() {
		if (mMediaPlayer != null) {
			return mMediaPlayer.isPlaying();
		}
		return false;
	}

	@Override
	public void play() {
		if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
			logToUI(String.format("playbackStart() %s",
					mContext.getResources().getResourceEntryName(mResourceId)));
			mMediaPlayer.setLooping(true);
			mMediaPlayer.start();
			if (mPlaybackInfoListener != null) {
				mPlaybackInfoListener.onStateChanged(PlaybackInfoListener.State.PLAYING);
			}
			startUpdatingCallbackWithPosition();
		}
	}

	@Override
	public void reset() {
		if (mMediaPlayer != null) {
			logToUI("playbackReset()");
			mMediaPlayer.reset();
			loadMedia(mResourceId);
			if (mPlaybackInfoListener != null) {
				mPlaybackInfoListener.onStateChanged(PlaybackInfoListener.State.RESET);
			}
			stopUpdatingCallbackWithPosition(true);
		}
	}

	@Override
	public void pause() {
		if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
			mMediaPlayer.pause();
			if (mPlaybackInfoListener != null) {
				mPlaybackInfoListener.onStateChanged(PlaybackInfoListener.State.PAUSED);
			}
			logToUI("playbackPause()");
		}
	}

	@Override
	public void seekTo(int position) {
		if (mMediaPlayer != null) {
			mMediaPlayer.seekTo(position);
		}
	}

	/**
	 * Processes the seekbar position (Max 100) into a float value that the media player can
	 * accept as a media volume
	 */
	public void setVolume(int progress) {
		final int maxVol = Constants.Companion.getVOLUME_MAX();

		progress = progress > maxVol ? maxVol : progress;
		float vol = (float) (1 - (Math.log(maxVol - progress) / Math.log(maxVol)));
		setVolume(vol);
	}

	/**
	 * Sets media player volume given the right float value
	 */
	private void setVolume(float volume) {
		mMediaPlayer.setVolume(volume, volume);
	}

	/**
	 * Syncs the mMediaPlayer position with mPlaybackProgressCallback via recurring task.
	 */
	private void startUpdatingCallbackWithPosition() {
		if (mExecutor == null) {
			mExecutor = Executors.newSingleThreadScheduledExecutor();
		}
		if (mSeekbarPositionUpdateTask == null) {
			mSeekbarPositionUpdateTask = new Runnable() {
				@Override
				public void run() {
					updateProgressCallbackTask();
				}
			};
		}
		mExecutor.scheduleAtFixedRate(
				mSeekbarPositionUpdateTask,
				0,
				PLAYBACK_POSITION_REFRESH_INTERVAL_MS,
				TimeUnit.MILLISECONDS
		);
	}

	// Reports media playback position to mPlaybackProgressCallback.
	private void stopUpdatingCallbackWithPosition(boolean resetUIPlaybackPosition) {
		if (mExecutor != null) {
			mExecutor.shutdownNow();
			mExecutor = null;
			mSeekbarPositionUpdateTask = null;
			if (resetUIPlaybackPosition && mPlaybackInfoListener != null) {
				mPlaybackInfoListener.onPositionChanged(0);
			}
		}
	}

	private void updateProgressCallbackTask() {
		if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
			int currentPosition = mMediaPlayer.getCurrentPosition();
			if (mPlaybackInfoListener != null) {
				mPlaybackInfoListener.onPositionChanged(currentPosition);
			}
		}
	}

	@Override
	public void initializeProgressCallback() {
		final int duration = mMediaPlayer.getDuration();
		if (mPlaybackInfoListener != null) {
			mPlaybackInfoListener.onDurationChanged(duration);
			mPlaybackInfoListener.onPositionChanged(0);
		}
	}

	private void logToUI(String message) {
		if (mPlaybackInfoListener != null) {
			mPlaybackInfoListener.onLogUpdated(message);
		}
	}

}
