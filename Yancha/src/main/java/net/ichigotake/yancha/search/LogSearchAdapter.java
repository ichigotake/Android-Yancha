package net.ichigotake.yancha.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.api.LruImageCache;
import net.ichigotake.yancha.common.view.ChatMessageView;
import net.ichigotake.yancha.common.widget.SectionListAdapter;
import net.ichigotake.yancha.sdk.model.ChatMessage;

class LogSearchAdapter extends SectionListAdapter<ChatMessage> {

    final private LayoutInflater mInflater;
    final private ImageLoader mImageLoader;

    LogSearchAdapter(Context context) {
        super(context, R.layout.yc_common_message_cell);
        mInflater = LayoutInflater.from(context);
        mImageLoader = new ImageLoader(Volley.newRequestQueue(context), new LruImageCache());
    }

    @Override
    public boolean isSection(int position) {
        return (position != 0) && (position%50 == 0);
    }

    @Override
    protected View createSectionView(int position, ViewGroup parent) {
        return mInflater.inflate(R.layout.yc_list_section, null);
    }

    @Override
    protected View createItemView(int position, ViewGroup parent) {
        return new ChatMessageView(getContext(), mImageLoader);
    }

    @Override
    protected View getSectionView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView)convertView;
        view.setText(getContext().getString(R.string.yc_count_separator, position));
        return view;
    }

    @Override
    protected View getItemView(int position, View convertView, ViewGroup parent) {
        ChatMessage message = getItem(position);
        ChatMessageView view = (ChatMessageView)convertView;
        view.setNickname(message.getNickname());
        view.setMessage(message.getMessage());
        view.setPlusplus(message.getPlusplus());
        view.setTimestamp(message.getCreatedTime());
        view.setProfileImageUrl(message.getProfileImageUrl());
        return view;
    }

}
