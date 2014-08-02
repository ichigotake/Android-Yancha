package net.ichigotake.android.yancha.app.chat;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import net.ichigotake.android.common.os.BundleMerger;
import net.ichigotake.android.yancha.app.R;

public class ChatMessageInputFragment extends Fragment {

    private final String KEY_INPUT_TEXT = "input_text";
    private EditText inputTextArea;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_messages_input, parent, false);
        inputTextArea = (EditText)view.findViewById(R.id.fragment_chat_messages_input_text_area);
        inputTextArea.setOnEditorActionListener((v, actionId, event) -> {
            String text = v.getText().toString().trim();
            if (EditorInfo.IME_ACTION_SEND == actionId && !TextUtils.isEmpty(text)) {
                ((SocketIoClientActivity)getActivity()).getSocketIoClient()
                        .emit(SocketIoEvent.USER_MESSAGE, text);
                v.setText("");
            }
            return false;
        });
        inputTextArea.setText(BundleMerger.merge(savedInstanceState).getString(KEY_INPUT_TEXT, ""));
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_INPUT_TEXT,
                inputTextArea != null ? inputTextArea.getText().toString() : "");
    }

}
