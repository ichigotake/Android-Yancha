package net.ichigotake.yancha.common.widget;

import android.widget.BaseAdapter;

public class AdapterEvent<T extends BaseAdapter> {

    final private T mAdapter;
    final private int mPosition;

    public AdapterEvent(T adapter, int position) {
        mAdapter = adapter;
        mPosition = position;
    }

    public T getAdapter() {
        return mAdapter;
    }

    public int getPosition() {
        return mPosition;
    }

}
