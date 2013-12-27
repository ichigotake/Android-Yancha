package net.ichigotake.yancha.common.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import net.ichigotake.yancha.sdk.model.ChatTags;

import java.util.HashSet;
import java.util.Set;

public class JoinTagStorage {

    final private String KEY_TAG_LIST = "tag_list";
    
    final private SharedPreferences mPref;
    
    public JoinTagStorage(Context context) {
        mPref = context.getSharedPreferences("join_tag_list", Context.MODE_PRIVATE);
    }
    
    public void putAll(ChatTags tags) {
        Editor editor = mPref.edit();
        Set<String> tagSet = new HashSet<String>();
        for (String tag : tags.toMap().keySet()) {
            tagSet.add(tag);
        }
        editor.putStringSet(KEY_TAG_LIST, tagSet);
        editor.commit();
    }
    
    public ChatTags getAll() {
        Set<String> prefTags = mPref.getStringSet(KEY_TAG_LIST, new HashSet<String>());
        ChatTags tags = new ChatTags();
        for (String name : prefTags) {
            tags.add(name);
        }
        return tags;
    }
    
}
