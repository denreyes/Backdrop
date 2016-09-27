package io.djnr.backdrop.interfaces;

import io.djnr.backdrop.services.TrackService;

/**
 * Created by Dj on 9/21/2016.
 */
public interface TrackServiceProvider {
    public TrackService getTrackService();
}
