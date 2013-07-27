package net.ichigotake.yancha.message;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * î≠åæÉfÅ[É^
 */
public class MessageCell {
	
	private int id;
	
	private int plusplus;
	
	private long createdTime;

	private String userKey;
	
	private String nickname;
	
	private String profileImageUrl;
	
	private String message;

	private ArrayList<String> tags;
	
	public MessageCell(JSONObject json) {
		try {
			setId(json.getInt("id"));
			setProfileImageUrl(json.getString("profile_image_url"));
			setCreatedTime(json.getLong("created_at_ms"));
			setMessage(json.getString("text"));
			setNickname(json.getString("nickname"));
			
			ArrayList<String> tags = new ArrayList<String>();
			JSONArray argsTags = json.getJSONArray("tags");
			int tagLength = argsTags.length();
			for (int i=0; i<tagLength; i++) {
				String tag = argsTags.getString(i);
				tags.add(tag);
			}
			setTags(tags);
	
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public int getPlusplus() {
		return plusplus;
	}

	public void setPlusplus(int plusplus) {
		this.plusplus = plusplus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
