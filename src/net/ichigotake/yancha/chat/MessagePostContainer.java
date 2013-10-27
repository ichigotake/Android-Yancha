package net.ichigotake.yancha.chat;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.api.YanchaEmitter;
import net.ichigotake.yancha.common.ui.ViewContainer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

class MessagePostContainer implements ViewContainer {

	final private EditText mMessageView;
	
	final private YanchaEmitter mEmitter;
	
	MessagePostContainer(View view, YanchaEmitter emitter) {
		mEmitter = emitter;
		mMessageView = (EditText) view.findViewById(R.id.chatSendMessageText);
		Button viewSubmit = (Button) view.findViewById(R.id.chatSendMessageSend);
		viewSubmit.setOnClickListener(new OnSubmitButtonClickListener());
	}
	
	private class OnSubmitButtonClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			String message = mMessageView.getText().toString();
			if (message.isEmpty()) {
				return ;
			}
			
			mEmitter.emitUserMessage(message);
			//TODO 発言失敗等での再利用用に発言ヒストリーを取っておきたい
			mMessageView.setText("");
		}
		
	}
}
