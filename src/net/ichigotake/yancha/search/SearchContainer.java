package net.ichigotake.yancha.search;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.haarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.ui.ViewContainer;
import net.ichigotake.yancha.common.user.User;

class SearchContainer implements ViewContainer {

    final private ListView mMessageListView;
    
    final private AutoPagingSearchListener mPagingListener;
    
    SearchContainer(View view) {
        Context context = view.getContext();
        mMessageListView = (ListView) view.findViewById(R.id.messageList);
        mPagingListener = new AutoPagingSearchListener(context, new User(context).getApiUri());
    }
    
    void initialize() {
        ScaleInAnimationAdapter animationAdapter =
                new ScaleInAnimationAdapter(mPagingListener.getAdapter());
        animationAdapter.setAbsListView(mMessageListView);
        mMessageListView.setAdapter(animationAdapter);
        
        mMessageListView.setOnScrollListener(mPagingListener);
    }
    
    
}