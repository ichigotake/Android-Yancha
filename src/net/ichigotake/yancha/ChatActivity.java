package net.ichigotake.yancha;

import net.ichigotake.colorfulsweets.lib.fragment.FragmentTransit;
import net.ichigotake.yancha.chat.ChatFragment;
import net.ichigotake.yancha.common.context.AppContext;
import net.ichigotake.yancha.common.context.BaseFragmentActivity;
import android.os.Bundle;

/**
 * �`���b�g��ʂ̃A�N�e�B�r�e�B
 */
public class ChatActivity extends BaseFragmentActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		new FragmentTransit(this)
			.setAddBackStack(false)
			.toReplace(AppContext.FRAGMENT_ID_CONTENT, ChatFragment.newInstance());
	}
}
