package net.ichigotake.yancha.common.ui;

public interface SectionIndexerCreator<T> {

    public String create(int index, T item);
}