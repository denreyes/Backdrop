package io.djnr.backdrop.dagger.module;

import dagger.Module;
import dagger.Provides;
import io.djnr.backdrop.dagger.scope.FragmentScope;
import io.djnr.backdrop.ui.fragments.playlist.IPlaylist;
import io.djnr.backdrop.ui.fragments.playlist.model.PlaylistModel;
import io.djnr.backdrop.ui.fragments.playlist.presenter.PlaylistPresenter;
import io.djnr.backdrop.ui.fragments.playlist.view.PlaylistFragment;

/**
 * Created by Dj on 8/21/2016.
 */
@Module
public class PlaylistFragmentModule {
    private PlaylistFragment fragment;

    public PlaylistFragmentModule(PlaylistFragment fragment){
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    PlaylistFragment providesPlaylistFragment(){
        return fragment;
    }

    @Provides
    @FragmentScope
    IPlaylist.ProvidedPresenter providedPresenter(){
        PlaylistPresenter presenter = new PlaylistPresenter(fragment);
        PlaylistModel model = new PlaylistModel(presenter);
        presenter.setModel(model);
        return presenter;
    }
}
