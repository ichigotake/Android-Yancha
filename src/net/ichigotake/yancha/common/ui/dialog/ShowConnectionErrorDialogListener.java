package net.ichigotake.yancha.common.ui.dialog;

import net.ichigotake.colorfulsweets.lib.net.http.HttpAccessErrorEvent;
import net.ichigotake.colorfulsweets.lib.net.http.HttpAccessEventListener;
import net.ichigotake.yancha.R;
import android.content.Context;

import com.google.common.eventbus.Subscribe;

public class ShowConnectionErrorDialogListener implements HttpAccessEventListener {

	final private MessageDialogBuilder mBuilder;
	
	public ShowConnectionErrorDialogListener(Context context) {
		mBuilder = new MessageDialogBuilder(context)
				.setDefaultPositiveText()
				.setMessage(R.string.yc_connection_failed);
		
	}
	
	@Subscribe
	public void onError(HttpAccessErrorEvent event) {
		mBuilder.show();
	}
}
