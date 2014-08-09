package net.ichigotake.android.common.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class ArrayAdapter<ITEM, TAG> extends android.widget.ArrayAdapter<ITEM> {

    private final LayoutInflater inflater;

    public ArrayAdapter(Context context) {
        super(context, -1);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getViewInternal(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getViewInternal(position, convertView, parent);
    }

    private View getViewInternal(int position, View convertView, ViewGroup parent) {
        TAG tag;
        if (convertView == null) {
            convertView = generateView(inflater, position, parent);
            tag = generateTag(position, convertView);
            convertView.setTag(tag);
        } else {
            tag = (TAG) convertView.getTag();
        }
        bindView(position, convertView, tag);
        return convertView;
    }

    protected abstract View generateView(LayoutInflater inflater, int position, ViewGroup parent);

    protected abstract void bindView(int position, View convertView, TAG tag);

    protected abstract TAG generateTag(int position, View convertView);

}
