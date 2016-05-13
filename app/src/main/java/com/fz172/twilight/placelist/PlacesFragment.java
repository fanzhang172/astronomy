package com.fz172.twilight.placelist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fz172.twilight.R;

/**
 * Fragment that contains a list of places.
 */
public final class PlacesFragment extends Fragment {

    private View mContentView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.places_fragment, container,
                false /* attachToRoot */);
        final FloatingActionButton fab = (FloatingActionButton) mContentView.findViewById(R.id.fab);
        fab.setOnClickListener(new FabOnClickListener());
        return mContentView;
    }

    private final class FabOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }
}
