package io.djnr.backdrop.ui.fragments.min_track.model;

import io.djnr.backdrop.ui.fragments.min_track.IMinTrack;

/**
 * Created by Dj on 9/27/2016.
 */
public class MinTrackModel implements IMinTrack.ProvidedModel{
    private IMinTrack.RequiredPresenter mPresenter;

    public MinTrackModel(IMinTrack.RequiredPresenter presenter){
        this.mPresenter = presenter;
    }
}
