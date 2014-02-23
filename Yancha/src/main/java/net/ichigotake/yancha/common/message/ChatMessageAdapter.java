package net.ichigotake.yancha.common.message;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import net.ichigotake.colorfulsweets.common.os.AsyncRunnableTask;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.api.LruImageCache;
import net.ichigotake.yancha.common.api.socketio.YanchaEmitter;
import net.ichigotake.yancha.common.view.ChatMessageView;
import net.ichigotake.yancha.sdk.model.ChatMessage;

/**
 * 発言のアダプタ
 *
 * TODO 発言の削除があること、順番がID順である事を考えると {@link java.util.LinkedList} がいいのかもしれない
 */
public class ChatMessageAdapter extends ArrayAdapter<ChatMessage> {

    final private YanchaEmitter mEmitter;
    final private ImageLoader mImageLoader;

    public ChatMessageAdapter(Context context, YanchaEmitter emitter) {
        super(context, R.layout.yc_common_message_cell);
        mEmitter = emitter;
        mImageLoader = new ImageLoader(Volley.newRequestQueue(context), new LruImageCache());
    }

    public void update(ChatMessage message) {
        int lastIndex = getCount()-1;
        for (int i=lastIndex; i>=0; i--) {
            ChatMessage _message = getItem(i);
            if (message.getId() == _message.getId()) {
                remove(_message);
                insert(message, i);
                break;
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(final int position, View contentView, ViewGroup parent) {
        final ChatMessage message = getItem(position);

        if (contentView == null) {
            contentView = new ChatMessageView(getContext(), mImageLoader);
        }
        ChatMessageView messageView = (ChatMessageView)contentView;

        messageView.setNickname(message.getNickname());
        messageView.setMessage(message.getMessage());
        messageView.setPlusplus(message.getPlusplus());
        messageView.setTimestamp(message.getCreatedTime());
        messageView.setProfileImageUrl(message.getProfileImageUrl());
        messageView.setOnClickListener(new OnPlusplusClickListener(mEmitter, message));

        return contentView;
    }

    /**
     * ++をするためのクリックリスナ
     */
    private static class OnPlusplusClickListener implements View.OnClickListener {

        final private int mMessageId;
        final private YanchaEmitter mEmitter;

        OnPlusplusClickListener(YanchaEmitter emitter, ChatMessage item) {
            mEmitter = emitter;
            mMessageId = item.getId();
        }

        @Override
        public void onClick(View view) {
            new AsyncRunnableTask().execute(sendPlusplus());
        }

        private Runnable sendPlusplus() {
            return new Runnable() {
                @Override
                public void run() {
                    mEmitter.emitPlusplus(mMessageId);
                }
            };
        }
    }
}
