package net.ichigotake.yancha.chat.socketio;

import com.google.common.base.Optional;

import net.ichigotake.yancha.common.api.EmptyResponseException;
import net.ichigotake.yancha.common.api.socketio.listener.MessageEventListener;
import net.ichigotake.yancha.common.api.socketio.response.AnnounsementResponse;
import net.ichigotake.yancha.common.api.socketio.response.DeleteUserMessageResponse;
import net.ichigotake.yancha.common.api.socketio.response.UserMessageResponse;
import net.ichigotake.yancha.sdk.model.ChatMessage;
import net.ichigotake.yancha.sdk.model.ChatMessageFactory;

import org.json.JSONException;

public class MessageListener implements MessageEventListener {

    final private ChatHolder mParameter;

    MessageListener(ChatHolder parameter) {
        mParameter = parameter;
    }

    @Override
    public void onEvent(AnnounsementResponse response) {

    }

    @Override
    public void onEvent(DeleteUserMessageResponse response)
            throws EmptyResponseException {

        Optional<String> body = response.getResponseBody();
        if (! body.isPresent()) {
            throw new EmptyResponseException();
        }

        try {
            final ChatMessage message = ChatMessageFactory.create(body.get());

            mParameter.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mParameter.getContainer().removeMessage(message);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEvent(UserMessageResponse response) throws EmptyResponseException {
        final Optional<String> body = response.getResponseBody();
        if (! body.isPresent()) {
            throw new EmptyResponseException();
        }

        try {
            final ChatMessage message = ChatMessageFactory.create(body.get());

            mParameter.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    mParameter.getContainer().addMessage(message);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
