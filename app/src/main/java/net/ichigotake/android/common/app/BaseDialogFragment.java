package net.ichigotake.android.common.app;

import android.app.DialogFragment;

public abstract class BaseDialogFragment extends DialogFragment {

    private boolean willDismiss;

    @Override
    public void onResume() {
        super.onResume();
        if (willDismiss) {
            dismiss();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        willDismiss = true;
    }

}
