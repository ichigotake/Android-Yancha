package net.ichigotake.yancha.common.api;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import net.ichigotake.yancha.BaseActivity;

public class RequestManager {

    static private RequestQueue sQueue;
    static private ImageLoader sImageLoader;

    public static RequestQueue get() {
        if (sQueue == null) {
            sQueue = Volley.newRequestQueue(BaseActivity.getContext());
        }
        return sQueue;
    }

    public static void cancelAll(RequestQueue.RequestFilter filter) {
        if (sQueue != null) {
            sQueue.cancelAll(filter);
        }
    }

    public static void start() {
        if (sQueue != null) {
            sQueue.start();
        }
    }

    public static void stop() {
        if (sQueue != null) {
            sQueue.stop();
        }
    }

    public static ImageLoader getImageLoader() {
        if (sImageLoader == null) {
            if (sQueue == null) {
                sQueue = Volley.newRequestQueue(BaseActivity.getContext());
            }
            sImageLoader = new ImageLoader(sQueue, new LruImageCache());
        }
        return sImageLoader;
    }
}
