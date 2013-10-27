package net.ichigotake.yancha.common.user;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class JoinTagList {

	final private String KEY_TAG_LIST = "tag_list";
	
	final private SharedPreferences mPref;
	
	public JoinTagList(Context context) {
		mPref = context.getSharedPreferences("join_tag_list", Context.MODE_PRIVATE);
	}
	
	public void setAll(Map<String, Integer> tags) {
		Editor editor = mPref.edit();
		editor.putStringSet(KEY_TAG_LIST, tags.keySet());
		editor.commit();
	}
	
	public Map<String, Integer> getAll() {
		Set<String> prefTags = mPref.getStringSet(KEY_TAG_LIST, new HashSet<String>());
		Map<String, Integer> tags = new HashMap<String, Integer>();
		for (String name : prefTags) {
			tags.put(name, 0);
		}
		return tags;
	}
	
}
