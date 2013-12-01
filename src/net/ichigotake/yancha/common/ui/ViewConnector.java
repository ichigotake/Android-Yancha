package net.ichigotake.yancha.common.ui;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by ichigotake on 2013/12/01.
 */
public interface ViewConnector<T extends ViewHolder, E> {

    public View generatView(LayoutInflater inflater);

    public void connectView(int position, T holder, E item);
}
