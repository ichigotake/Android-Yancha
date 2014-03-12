package net.ichigotake.yancha.common.view;

import android.view.View;
import android.widget.AbsListView;

public class OnClickToScrollBottomListener implements View.OnClickListener {

    final private AbsListView mListView;

    public OnClickToScrollBottomListener(AbsListView listView) {
        mListView = listView;
    }

    @Override
    public void onClick(View v) {
        mListView.setSelection(mListView.getBottom());
    }
}
