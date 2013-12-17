package net.ichigotake.yancha.common.user;

import android.widget.ImageView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class ProfileImageViewHelper {

    public void setDrawable(ImageView view, String profileImageUrl) {
        if (!profileImageUrl.isEmpty()) {
            UrlImageViewHelper.setUrlDrawable(view, profileImageUrl);
        } else {
            view.setImageResource(android.R.drawable.sym_action_chat);
        }
    }
}
