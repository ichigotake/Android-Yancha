package net.ichigotake.yancha.chat;

import com.google.common.base.Optional;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.ChatStatus;
import net.ichigotake.yancha.common.ui.ViewContainer;
import android.view.View;
import android.widget.TextView;

class StatusContainer implements ViewContainer {

	final private TextView mStatusView;
	
	StatusContainer(View view) {
		mStatusView = (TextView) view.findViewById(R.id.chatStatus);
	}
	
	void updateStatus(ChatStatus status) {
		final Optional<Integer> message = ChatStatus.valueOfMessage(status);
		if (message.isPresent()) {
			mStatusView.setText(message.get());
		}
	}
}
