package net.ichigotake.yancha.users;

import java.util.ArrayList;

import net.ichigotake.yancha.R;
import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * �Q�����[�U�[�ꗗ�̃��X�g�A�_�v�^�[
 */
public class JoinUserListAdapter extends ArrayAdapter<String> {

	public JoinUserListAdapter(Context context, ArrayList<String> users) {
		super(context, R.layout.yc_join_users_cell, users);
	}

}
