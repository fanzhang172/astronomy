package com.fz172.twilight;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.fz172.twilight.fragments.PlacesFragment;
import com.fz172.twilight.permissions.PermissionChecker;

/**
 * Main class for the app.
 */
public final class MainActivity extends AppCompatActivity {

    private final PermissionChecker mPermissionChecker = new PermissionChecker(
            Manifest.permission.ACCESS_FINE_LOCATION);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_fragment_activity);
        mPermissionChecker.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPermissionChecker.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPermissionChecker.checkAndRequestPermission(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPermissionChecker.getMissingPermissions(this).length > 0) {
            return;
        }
        final Fragment fragment = getCurrentFragment();
        if (fragment == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new PlacesFragment())
                    .commit();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mPermissionChecker.setRequestPermissionCompleted();
        if (!mPermissionChecker.isPermissionGranted(permissions, grantResults)) {
            mPermissionChecker.showErrorMessage(this);
        }
    }

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    }
}
