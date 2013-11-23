package net.ichigotake.yancha.chat;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import net.ichigotake.colorfulsweets.lib.ui.SoftInput;
import net.ichigotake.yancha.common.api.socketio.YanchaEmitter;

/**
 * メッセージを送信する
 */
class MessagePost implements View.OnClickListener {

    final private YanchaEmitter mEmitter;

    final private EditText mMessageView;

    private boolean mIsSingleLine = true;

    MessagePost(YanchaEmitter emitter, EditText messageView) {
        mEmitter = emitter;
        mMessageView = messageView;
        mMessageView.setOnEditorActionListener(new OnSubmitActionListener());
    }

    void submit() {
        String message = mMessageView.getText().toString();
        if (! message.isEmpty()) {
            mEmitter.emitUserMessage(message);
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
