package net.ichigotake.yancha.data;

import java.util.ArrayList;

import net.ichigotake.yancha.R;
import android.content.Context;
import android.widget.ArrayAdapter;

public class JoinUserListAdapter extends ArrayAdapter<String> {

	public JoinUserListAdapter(Context context, ArrayList<String> users) {
		super(context, R.layout.yc_join_users_cell, users);
	}

}
