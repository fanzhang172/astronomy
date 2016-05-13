package com.fz172.twilight.permissions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Host activity of permission error message.
 */
public final class PermissionErrorDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            new PermissionErrorDialogFragment()
                    .show(getSupportFragmentManager(), "permission_dialog");
        }
    }

    public static final class IntentBuilder {
        private final Intent mIntent;

        public IntentBuilder(Context context) {
            mIntent = new Intent(context, PermissionErrorDialogActivity.class);
        }

        public Intent build() {
            return mIntent;
        }
    }
}