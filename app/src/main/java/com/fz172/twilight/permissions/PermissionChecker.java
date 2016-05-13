package com.fz172.twilight.permissions;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.fz172.twilight.utils.LogUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Checks and requests app permission. Displays error message when necessary.
 */
public final class PermissionChecker {
    private static final String TAG = "PermissionChecker";
    public static final int REQUEST_CODE_RUNTIME_PERMISSIONS = 0;
    private static final String KEY_STATE_REQUEST_PERMISSION = "key_request_state";
    @IntDef
    @Retention(RetentionPolicy.SOURCE)
    private @interface RequestState {
        int NOT_STARTED = 0;
        int IN_PROGRESS = 1;
        int COMPLETED = 2;
    }
    private final String[] mRequiredPermissions;
    @RequestState
    private int mRequestPermissionState = RequestState.NOT_STARTED;
    public PermissionChecker(String... requiredPermissions) {
        mRequiredPermissions = requiredPermissions;
    }
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_STATE_REQUEST_PERMISSION, mRequestPermissionState);
    }
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Only update the permission requesting state if currently is not requesting permission,
        // this is because we might assign value to mIsRequestingPermission in onStart(), we
        // don't want the new value to be overridden by saved instance state here.
        if (mRequestPermissionState == RequestState.NOT_STARTED && savedInstanceState != null) {
            mRequestPermissionState = savedInstanceState.getInt(KEY_STATE_REQUEST_PERMISSION);
        }
    }
    public void setRequestPermissionCompleted() {
        mRequestPermissionState = RequestState.COMPLETED;
    }
    public boolean isPermissionGranted(String[] permissions, int[] grantResults) {
        boolean allPermissionsGranted = true;
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                LogUtils.i(TAG, "Permission denied: %s.", permissions[i]);
                allPermissionsGranted = false;
            }
        }
        return allPermissionsGranted;
    }
    /**
     * Starts a UI to request permissions from user.
     */
    public void checkAndRequestPermission(Activity activity) {
        if (mRequestPermissionState != RequestState.NOT_STARTED) {
            // Request permission is in progress or already done, early terminate.
            return;
        }
        final String[] missingPermissions = getMissingPermissions(activity);
        if (missingPermissions.length != 0) {
            // Don't have enough permission, request missing ones by launching permission dialog.
            mRequestPermissionState = RequestState.IN_PROGRESS;
            ActivityCompat.requestPermissions(activity, missingPermissions,
                    REQUEST_CODE_RUNTIME_PERMISSIONS);
        }
    }
    public String[] getMissingPermissions(Activity activity) {
        final List<String> needPermissions = new ArrayList<>();
        for (String permission : mRequiredPermissions) {
            if (ContextCompat.checkSelfPermission(activity, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                needPermissions.add(permission);
            }
        }
        return needPermissions.toArray(new String[needPermissions.size()]);
    }
    public void showErrorMessage(Activity activity) {
        LogUtils.w(TAG, "Not all permissions are granted, display error and finish activity.");
        activity.startActivity(new PermissionErrorDialogActivity.IntentBuilder(activity).build());
        activity.finish();
    }
}
