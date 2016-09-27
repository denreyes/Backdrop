package io.djnr.backdrop.dagger.module;

import dagger.Module;
import dagger.Provides;
import io.djnr.backdrop.dagger.scope.FragmentScope;
import io.djnr.backdrop.ui.fragments.spotlight.ISpotlight;
import io.djnr.backdrop.ui.fragments.spotlight.model.SpotlightModel;
import io.djnr.backdrop.ui.fragments.spotlight.presenter.SpotlightPresenter;
import io.djnr.backdrop.ui.fragments.spotlight.view.SpotlightFragment;

/**
 * Created by Dj on 8/22/2016.
 */
@Module
public class SpotlightFragmentModule {
    private SpotlightFragment fragment;

    public SpotlightFragmentModule(SpotlightFragment fragment){
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    SpotlightFragment providesSpotlightFragment(){
        return fragment;
    }

    @Provides
    @FragmentScope
    ISpotlight.ProvidedPresenter providedPresenter(){
        SpotlightPresenter presenter = new SpotlightPresenter(fragment);
        SpotlightModel model = new SpotlightModel(presenter);
        presenter.setModel(model);
        return presenter;
    }
}
