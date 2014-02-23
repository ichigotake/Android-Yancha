package net.ichigotake.yancha.common.widget;

public interface SectionIndexerCreator<T> {

    public String create(int index, T item);
}