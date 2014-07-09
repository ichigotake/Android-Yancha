package net.ichigotake.android.common.os;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

public final class RestoredBundle {

    private final Bundle bundle;

    public RestoredBundle(Intent intent, Bundle savedInstanceState) {
        this((intent != null) ? intent.getExtras() : null, savedInstanceState);
    }

    public RestoredBundle(Fragment fragment, Bundle savedInstanceState) {
        this(fragment.getArguments(), savedInstanceState);
    }

    public RestoredBundle(Bundle bundle) {
        this.bundle = bundle != null ? bundle : new Bundle();
    }

    private RestoredBundle(Bundle arguments, Bundle savedInstanceState) {
        this(merge(arguments, savedInstanceState));
    }

    private static Bundle merge(Bundle arguments, Bundle savedInstanceState) {
        Bundle mergedBundle;
        if (arguments != null && savedInstanceState != null) {
            arguments.putAll(savedInstanceState);
            mergedBundle = arguments;
        } else if (arguments != null) {
            mergedBundle = arguments;
        } else if (savedInstanceState != null) {
            mergedBundle = savedInstanceState;
        } else {
            mergedBundle = new Bundle();
        }
        return mergedBundle;
    }

    public Integer getInteger(String key) {
        int restoreObject;
        if (bundle.containsKey(key)) {
            restoreObject = bundle.getInt(key);
        } else {
            restoreObject = 0;
        }
        return restoreObject;
    }

}