package net.ichigotake.yancha.chat;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import net.ichigotake.colorfulsweets.common.view.inputmethod.SoftInput;
import net.ichigotake.yancha.common.api.socketio.YanchaEmitter;

/**
 * メッセージを送信する
 */
class MessagePost implements View.OnClickListener {

    final private YanchaEmitter mEmitter;
    final private EditText mMessageView;
    final private ChatTagContainer mTagContainer;
    private boolean mIsSingleLine = true;

    MessagePost(YanchaEmitter emitter, EditText messageView, ChatTagContainer tagContainer) {
        mEmitter = emitter;
        mMessageView = messageView;
        mMessageView.setOnEditorActionListener(new OnSubmitActionListener());
        mTagContainer = tagContainer;
    }

    void submit() {
        String message = mMessageView.getText().toString();
        if (message.trim().length() > 0) {
            String sendMessage = new StringBuilder()
                    .append(message)
                    .append(" ")
                    .append(mTagContainer.getText())
                    .toString();
            mEmitter.emitUserMessage(sendMessage);
            //TODO 投稿失敗時のテキストを履歴として取っておきたい
            mMessageView.setText("");
            SoftInput.hide(mMessageView);

        }

    }

    void setMultipleLineInputMode() {
        mIsSingleLine = false;
    }

    void setSingleLineInputMode() {
        mIsSingleLine = true;
    }

    @Override
    public void onClick(View view) {
        submit();
    }

    /**
     * 送信イベントを拾ってメッセージを送信する
     */
    private class OnSubmitActionListener implements TextView.OnEditorActionListener {

        @Override
        public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
            final boolean handled;
            if (mIsSingleLine) {
                submit();
                handled = true;
            } else {
                handled = false;
            }
            return handled;
        }
    }
}
