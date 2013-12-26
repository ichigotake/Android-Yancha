package net.ichigotake.yancha.chat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.ichigotake.colorfulsweets.lib.fragment.FragmentTransit;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.chat.socketio.YanchaCallbackListener;
import net.ichigotake.yancha.common.api.rest.ApiUri;
import net.ichigotake.yancha.common.api.socketio.Chat;
import net.ichigotake.yancha.common.context.AppContext;
import net.ichigotake.yancha.common.user.AppUser;
import net.ichigotake.yancha.login.LoginFragment;

import java.net.MalformedURLException;

/**
 * チャット画面
 */
public class ChatFragment extends Fragment {

    private Chat chat;

    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.yc_chat_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        ApiUri uri = new AppUser(getActivity()).getApiUri();
        try {
            chat = new Chat(uri.getAbsoluteUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            onConnectionError();
        }

        YanchaCallbackListener yanchaListener =
                new YanchaCallbackListener(chat.getEmitter(), getActivity(), getView());
        chat.setCallbackListener(yanchaListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            chat.reconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            onConnectionError();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (chat != null) {
            chat.disconnect();
        }
    }

    private void onConnectionError() {
        Toast.makeText(getActivity(),
                "無効なURLです",
                Toast.LENGTH_SHORT).show();
        new FragmentTransit(getActivity())
                .toReplace(AppContext.FRAGMENT_ID_CONTENT, LoginFragment.newInstance());
    }
}
