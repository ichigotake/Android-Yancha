package net.ichigotake.yancha.common.ui;

import android.view.LayoutInflater;
import android.view.View;

public interface ViewConnector<T extends ViewHolder, E> {

    public boolean isEnabled(int position, E item);

    public int getItemPosition(int position);

    public View generateView(LayoutInflater inflater, int position, E item);

    public void connectView(int position, T holder, E item);
}
