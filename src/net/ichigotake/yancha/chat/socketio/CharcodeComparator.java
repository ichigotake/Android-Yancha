package net.ichigotake.yancha.chat.socketio;

import java.util.Comparator;

public class CharcodeComparator implements Comparator<String> {

    @Override
    public int compare(String s, String s2) {
        return s.compareTo(s2);
    }
}
