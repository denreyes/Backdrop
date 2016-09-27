package io.djnr.backdrop.ui.activities.main.model;

import io.djnr.backdrop.ui.activities.main.IMain;

/**
 * Created by Dj on 9/27/2016.
 */
public class MainModel implements IMain.ProvidedModel{
    private IMain.RequiredPresenter presenter;

    public MainModel(IMain.RequiredPresenter presenter){
        this.presenter = presenter;
    }
}
