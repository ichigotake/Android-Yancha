package net.ichigotake.yancha;

import android.os.Bundle;
import android.util.Log;

import net.ichigotake.yancha.common.context.BaseActivity;
import net.ichigotake.yancha.sdk.model.ChatMessage;
import net.ichigotake.yancha.search.CheckedCallback;

public class MessageQuotationActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CheckedCallback.ChatMessages messages = (CheckedCallback.ChatMessages)savedInstanceState
                .getSerializable("messages");
        for (ChatMessage message : messages.toList()) {
            Log.d(getClass().getSimpleName(), "message: " + message.getMessage());
        }
    }
}
