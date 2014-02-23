package net.ichigotake.yancha.chat;

import android.view.View;

/**
 * 発言をポストするクリックリスナ
 */
class OnSubmitButtonClickListener implements View.OnClickListener {

    final private ChatMessagePost mPost;

    OnSubmitButtonClickListener(ChatMessagePost post) {
        mPost = post;
    }

    @Override
    public void onClick(View view) {
        mPost.submit();
    }
}
