package io.djnr.backdrop.ui.fragments.max_track.model;

import io.djnr.backdrop.ui.fragments.max_track.IMaxTrack;

/**
 * Created by Dj on 9/27/2016.
 */
public class MaxTrackModel implements IMaxTrack.ProvidedModel{
    private IMaxTrack.RequiredPresenter mPresenter;

    public MaxTrackModel(IMaxTrack.RequiredPresenter presenter){
        this.mPresenter = presenter;
    }
}
