package net.ichigotake.yancha.common.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public interface SectionListViewConnector<T extends BaseAdapter> {

    boolean isSection(AdapterEvent<T> event);
    View createSectionView(AdapterEvent<T> event, ViewGroup parent);
    View createItemView(AdapterEvent<T> event, ViewGroup parent);
    View getSectionView(AdapterEvent<T> event, View convertView, ViewGroup parent);
    View getItemView(AdapterEvent<T> event, View convertView, ViewGroup parent);
}
