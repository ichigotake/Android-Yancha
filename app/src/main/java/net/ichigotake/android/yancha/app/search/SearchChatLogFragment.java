package net.ichigotake.android.yancha.app.search;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.ichigotake.android.yancha.app.R;

public class SearchChatLogFragment extends Fragment {

    public static SearchChatLogFragment newInstance() {
        return new SearchChatLogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_chat_log, parent, false);
    }
}
