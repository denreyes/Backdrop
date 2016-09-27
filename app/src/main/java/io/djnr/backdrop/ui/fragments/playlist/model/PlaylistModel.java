package io.djnr.backdrop.ui.fragments.playlist.model;

import io.djnr.backdrop.ui.fragments.playlist.IPlaylist;

/**
 * Created by Dj on 8/20/2016.
 */
public class PlaylistModel implements IPlaylist.ProvidedModel{
    private IPlaylist.RequiredPresenter mPresenter;

    public PlaylistModel(IPlaylist.RequiredPresenter presenter){
        this.mPresenter = presenter;
    }
}
