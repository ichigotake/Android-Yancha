package net.ichigotake.yancha;

import net.ichigotake.yancha.chat.ChatFragment;
import net.ichigotake.yancha.common.context.BaseFragmentActivity;
import net.ichigotake.yancha.common.ui.FragmentTransit;
import android.os.Bundle;

/**
 * チャット画面のアクティビティ
 */
public class ChatActivity extends BaseFragmentActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		new FragmentTransit(this)
			.setIsAddBackStack(false)
			.toNext(ChatFragment.newInstance());
	}
}
