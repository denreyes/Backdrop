package io.djnr.backdrop.dagger.module;

import dagger.Module;
import dagger.Provides;
import io.djnr.backdrop.dagger.scope.FragmentScope;
import io.djnr.backdrop.ui.fragments.min_track.IMinTrack;
import io.djnr.backdrop.ui.fragments.min_track.model.MinTrackModel;
import io.djnr.backdrop.ui.fragments.min_track.presenter.MinTrackPresenter;
import io.djnr.backdrop.ui.fragments.min_track.view.MinTrackFragment;

/**
 * Created by Dj on 9/27/2016.
 */
@Module
public class MinTrackFragmentModule {
    MinTrackFragment fragment;

    public MinTrackFragmentModule(MinTrackFragment fragment){
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    MinTrackFragment providesMinTrackFragment(){
        return fragment;
    }

    @Provides
    @FragmentScope
    IMinTrack.ProvidedPresenter providedPresenter(){
        MinTrackPresenter presenter = new MinTrackPresenter(fragment);
        MinTrackModel model = new MinTrackModel(presenter);
        presenter.setModel(model);
        return presenter;
    }
}
