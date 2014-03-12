package net.ichigotake.yancha.common.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.List;

public abstract class SectionListAdapter<T> extends ArrayAdapter<T> implements ListAdapter {

    public SectionListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public SectionListAdapter(Context context, int textViewResourceId, T[] objects) {
        super(context, textViewResourceId, objects);
    }

    public SectionListAdapter(Context context, int textViewResourceId, List<T> objects) {
        super(context, textViewResourceId, objects);
    }

    public abstract boolean isSection(int position);
    protected abstract View createSectionView(int position, ViewGroup parent);
    protected abstract View createItemView(int position, ViewGroup parent);
    protected abstract View getSectionView(int position, View convertView, ViewGroup parent);
    protected abstract View getItemView(int position, View convertView, ViewGroup parent);

    @Override
    public boolean isEnabled(int position) {
        return isSection(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final boolean isSection = isSection(position);

        if (isSection) {
            convertView = createSectionView(position, parent);
        } else {
            convertView = createItemView(position, parent);
        }

        if (isSection) {
            convertView = getSectionView(position, convertView, parent);
        } else {
            convertView = getItemView(position, convertView, parent);
        }

        return convertView;
    }

}
