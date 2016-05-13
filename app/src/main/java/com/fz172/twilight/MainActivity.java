package com.fz172.twilight;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.fz172.twilight.fragments.PlacesFragment;

/**
 * Main class for the app.
 */
public final class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_fragment_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Fragment fragment = getCurrentFragment();
        if (fragment == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new PlacesFragment())
                    .commit();
        }
    }

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    }
}
