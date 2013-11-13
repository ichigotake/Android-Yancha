package net.ichigotake.yancha.chat;

import android.view.View;

/**
 * 発言をポストするクリックリスナ
 */
class OnSubmitButtonClickListener implements View.OnClickListener {

    final private MessagePost mPost;

    OnSubmitButtonClickListener(MessagePost post) {
        mPost = post;
    }

    @Override
    public void onClick(View view) {
        mPost.submit();
    }
}
