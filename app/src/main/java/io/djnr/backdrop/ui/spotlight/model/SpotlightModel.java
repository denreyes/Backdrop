package io.djnr.backdrop.ui.spotlight.model;

import io.djnr.backdrop.ui.spotlight.ISpotlight;

/**
 * Created by Dj on 8/22/2016.
 */
public class SpotlightModel implements ISpotlight.ProvidedModel{
    ISpotlight.RequiredPresenter mPresenter;

    public SpotlightModel(ISpotlight.RequiredPresenter presenter){
        this.mPresenter = presenter;
    }
}
