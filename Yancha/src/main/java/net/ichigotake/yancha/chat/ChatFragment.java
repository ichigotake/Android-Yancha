package net.ichigotake.yancha.chat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.ichigotake.colorfulsweets.lib.fragment.FragmentTransit;
import net.ichigotake.colorfulsweets.lib.os.BundleSimple;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.chat.socketio.YanchaCallbackListener;
import net.ichigotake.yancha.common.api.rest.ApiUri;
import net.ichigotake.yancha.common.api.socketio.Chat;
import net.ichigotake.yancha.common.context.AppContext;
import net.ichigotake.yancha.login.LoginFragment;

import java.net.MalformedURLException;

/**
 * チャット画面
 */
public class ChatFragment extends Fragment {

    final static private String KEY_CONNECT_URI = "key_connect_uri";
    private Chat chat;

    public static Fragment newInstance(ApiUri connectUri) {
        Fragment fragment = new ChatFragment();
        BundleSimple store = new BundleSimple();
        store.put(KEY_CONNECT_URI, connectUri);
        fragment.setArguments(store.toBundle());
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.yc_chat_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BundleSimple store = new BundleSimple(getArguments());
        ApiUri uri = (ApiUri)store.getSerializable(KEY_CONNECT_URI);
        try {
            chat = new Chat(uri.getAbsoluteUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            onConnectionError();
            return ;
        }

        YanchaCallbackListener yanchaListener =
                new YanchaCallbackListener(chat.getEmitter(), getActivity(), getView());
        yanchaListener.initialize();
        chat.setCallbackListener(yanchaListener);
        chat.connect();
    }

    @Override
    public void onDestroy() {
        if (chat != null) {
            chat.disconnect();
        }
        super.onDestroy();
    }

    private void onConnectionError() {
        Toast.makeText(getActivity(),
                "無効なURLです",
                Toast.LENGTH_SHORT).show();
        new FragmentTransit(getActivity())
                .toReplace(AppContext.FRAGMENT_ID_CONTENT, LoginFragment.newInstance());
    }
}
