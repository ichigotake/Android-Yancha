package net.ichigotake.android.common.widget;

import android.util.SparseArray;
import android.widget.BaseAdapter;

import java.util.Collection;

public abstract class SparseArrayAdapter<E> extends BaseAdapter {

    protected SparseArray<E> objects = new SparseArray<E>();

    public void add(int key, E object) {
        objects.put(key, object);
    }

    abstract public void addAll(Collection<E> collection);

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public E getItem(int position) {
        return objects.valueAt(position);
    }

    @Override
    public long getItemId(int position) {
        return objects.valueAt(position).hashCode();
    }


}
