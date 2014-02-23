package net.ichigotake.yancha;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import net.ichigotake.colorfulsweets.ics.fragment.AutoPagingFragment;
import net.ichigotake.yancha.common.api.RequestManager;

public abstract class BaseAutoPagingFragment extends AutoPagingFragment {

    @Override
    public void onResume() {
        super.onResume();
        RequestManager.start();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        RequestManager.stop();
    }

    @Override
    public void onDestroy() {
        RequestManager.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                if (request != null) {
                    final Object tag = request.getTag();
                    if (tag != null) {
                        return tag.toString().equals(getRequestTag());
                    }
                }
                return false;
            }
        });
        super.onDestroy();
    }

    protected String getRequestTag() {
        return getClass().getName();
    }
}
