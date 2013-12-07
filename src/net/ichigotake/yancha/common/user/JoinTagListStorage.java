package net.ichigotake.yancha.common.user;

import java.util.HashSet;
import java.util.Set;

import net.ichigotake.yancha.sdk.model.ChatTagList;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class JoinTagListStorage {

    final private String KEY_TAG_LIST = "tag_list";
    
    final private SharedPreferences mPref;
    
    public JoinTagListStorage(Context context) {
        mPref = context.getSharedPreferences("join_tag_list", Context.MODE_PRIVATE);
    }
    
    public void putAll(ChatTagList tags) {
        Editor editor = mPref.edit();
        Set<String> tagSet = new HashSet<String>();
        for (String tag : tags.toMap().keySet()) {
            tagSet.add(tag);
        }
        editor.putStringSet(KEY_TAG_LIST, tagSet);
        editor.commit();
    }
    
    public ChatTagList getAll() {
        Set<String> prefTags = mPref.getStringSet(KEY_TAG_LIST, new HashSet<String>());
        ChatTagList tags = new ChatTagList();
        for (String name : prefTags) {
            tags.add(name);
        }
        return tags;
    }
    
}
