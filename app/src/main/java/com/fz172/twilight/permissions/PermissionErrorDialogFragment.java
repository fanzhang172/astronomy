package com.fz172.twilight.permissions;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.fz172.twilight.BuildConfig;
import com.fz172.twilight.R;

/**
 * {@link DialogFragment} to display permission error.
 */
public final class PermissionErrorDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Activity activity = getActivity();
        return new AlertDialog.Builder(activity)
                .setMessage(R.string.dialog_message_permission_check_fail)
                .setCancelable(false)
                .setPositiveButton(R.string.settings_label,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final Uri packageUri =
                                        Uri.parse("package:" + BuildConfig.APPLICATION_ID);
                                activity.startActivity(
                                        new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                                .addCategory(Intent.CATEGORY_DEFAULT)
                                                .setData(packageUri));
                                activity.finish();
                            }
                        })
                .setNegativeButton(R.string.dialog_button_dismiss,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                activity.finish();
                            }
                        })
                .create();
    }
}