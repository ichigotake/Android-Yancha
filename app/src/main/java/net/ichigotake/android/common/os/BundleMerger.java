package net.ichigotake.android.common.os;

import android.os.Bundle;

public final class BundleMerger {

    private BundleMerger() {}

    public static Bundle merge(Bundle... bundles) {
        Bundle mergedBundle = new Bundle();
        for (Bundle item : bundles) {
            if (item == null) {
                continue;
            }
            mergedBundle.putAll(item);
        }
        return mergedBundle;
    }

}
