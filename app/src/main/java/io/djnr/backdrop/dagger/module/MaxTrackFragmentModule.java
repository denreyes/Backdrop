package io.djnr.backdrop.dagger.module;

import dagger.Module;
import dagger.Provides;
import io.djnr.backdrop.dagger.scope.FragmentScope;
import io.djnr.backdrop.ui.fragments.max_track.IMaxTrack;
import io.djnr.backdrop.ui.fragments.max_track.model.MaxTrackModel;
import io.djnr.backdrop.ui.fragments.max_track.presenter.MaxTrackPresenter;
import io.djnr.backdrop.ui.fragments.max_track.view.MaxTrackFragment;

/**
 * Created by Dj on 9/27/2016.
 */
@Module
public class MaxTrackFragmentModule {
    MaxTrackFragment fragment;

    public MaxTrackFragmentModule(MaxTrackFragment fragment){
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    MaxTrackFragment providesMaxTrackFragment(){
        return fragment;
    }

    @Provides
    @FragmentScope
    IMaxTrack.ProvidedPresenter providedPresenter(){
        MaxTrackPresenter presenter = new MaxTrackPresenter(fragment);
        MaxTrackModel model = new MaxTrackModel(presenter);
        presenter.setModel(model);
        return presenter;
    }
}
