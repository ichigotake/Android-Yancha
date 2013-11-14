package net.ichigotake.yancha.chat.socketio;

import com.google.common.base.Optional;
import com.google.common.eventbus.Subscribe;

import net.ichigotake.yancha.common.api.EmptyResponseException;
import net.ichigotake.yancha.common.api.socketio.listener.MessageEventListener;
import net.ichigotake.yancha.common.api.socketio.response.AnnounsementResponse;
import net.ichigotake.yancha.common.api.socketio.response.DeleteUserMessageResponse;
import net.ichigotake.yancha.common.api.socketio.response.UserMessageResponse;
import net.ichigotake.yanchasdk.lib.model.PostMessageBuilder;
import net.ichigotake.yanchasdk.lib.model.PostMessageFactory;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ichigotake on 2013/11/02.
 */
public class MessageListener implements MessageEventListener {

    final private ChatMediator mParameter;

    MessageListener(ChatMediator parameter) {
        mParameter = parameter;
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
            final PostMessageBuilder.PostMessage message = PostMessageFactory.create(json);

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
            final PostMessageBuilder.PostMessage message;
            message = PostMessageFactory.create(json);

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
