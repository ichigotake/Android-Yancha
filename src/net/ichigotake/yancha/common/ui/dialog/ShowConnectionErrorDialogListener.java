package net.ichigotake.yancha.common.ui.dialog;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.core.api.ApiErrorEvent;
import net.ichigotake.yancha.core.api.ApiEventListener;
import android.content.Context;

import com.google.common.eventbus.Subscribe;

public class ShowConnectionErrorDialogListener implements ApiEventListener {

	final private MessageDialogBuilder mBuilder;
	
	public ShowConnectionErrorDialogListener(Context context) {
		mBuilder = new MessageDialogBuilder(context)
				.setDefaultPositiveText()
				.setMessage(R.string.yc_connection_failed);
		
	}
	
	@Subscribe
	public void onError(ApiErrorEvent event) {
		mBuilder.show();
	}
}
