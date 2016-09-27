package io.djnr.backdrop.ui.fragments.ambient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.djnr.backdrop.R;

/**
 * Created by Dj on 8/25/2016.
 */
public class AmbientFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ambient, container, false);

        return view;
    }
}
