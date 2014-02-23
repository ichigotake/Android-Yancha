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
                return request != null && request.getTag().toString().equals(getRequestTag());
            }
        });
        super.onDestroy();
    }

    protected String getRequestTag() {
        return getClass().getName();
    }
}
