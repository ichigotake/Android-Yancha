package net.ichigotake.android.common.os;

public interface Job<T> {

    void run(T value);

}
