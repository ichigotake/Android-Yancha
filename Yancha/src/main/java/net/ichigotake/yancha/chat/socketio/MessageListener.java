package net.ichigotake.yancha.chat.socketio;

import com.google.common.base.Optional;
import com.google.common.eventbus.Subscribe;

import net.ichigotake.yancha.common.api.EmptyResponseException;
import net.ichigotake.yancha.common.api.socketio.listener.MessageEventListener;
import net.ichigotake.yancha.common.api.socketio.response.AnnounsementResponse;
import net.ichigotake.yancha.common.api.socketio.response.DeleteUserMessageResponse;
import net.ichigotake.yancha.common.api.socketio.response.UserMessageResponse;
import net.ichigotake.yancha.sdk.model.ChatMessage;
import net.ichigotake.yancha.sdk.model.ChatMessageFactory;

import org.json.JSONException;
import org.json.JSONObject;

public class MessageListener implements MessageEventListener {

    final private ChatMediator mParameter;
    final private ChatMessageFactory mFactory;

    MessageListener(ChatMediator parameter) {
        mParameter = parameter;
        mFactory = new ChatMessageFactory();
    }

    @Override @Subscribe
    public void onAnnouncement(AnnounsementResponse response) {

    }

    @Override @Subscribe
    public void onDeleteUserMessage(DeleteUserMessageResponse response)
            throws EmptyResponseException {

        Optional<String> body = response.getResponseBody();
        if (! body.isPresent()) {
            throw new EmptyResponseException();
        }

        try {
            final JSONObject json = new JSONObject(body.get());
            final ChatMessage message = mFactory.create(json);

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

    @Override @Subscribe
    public void onUserMessage(UserMessageResponse response) throws EmptyResponseException {
        final Optional<String> body = response.getResponseBody();
        if (! body.isPresent()) {
            throw new EmptyResponseException();
        }

        try {
            final JSONObject json = new JSONObject(body.get());
            final ChatMessage message = mFactory.create(json);

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
