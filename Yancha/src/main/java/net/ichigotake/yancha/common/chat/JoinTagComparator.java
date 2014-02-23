package net.ichigotake.yancha.common.chat;

import java.util.Comparator;

/**
 * タグをソートする
 */
public class JoinTagComparator implements Comparator<String> {

    @Override
    public int compare(String s, String s2) {
        return s.compareTo(s2);
    }
}
