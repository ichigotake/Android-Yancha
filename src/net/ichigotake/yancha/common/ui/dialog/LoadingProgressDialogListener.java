package net.ichigotake.yancha.common.ui.dialog;

import net.ichigotake.yancha.core.api.ApiErrorEvent;
import net.ichigotake.yancha.core.api.ApiEventListener;
import net.ichigotake.yancha.core.api.ApiResponse;
import net.ichigotake.yancha.core.api.BeforeApiRequestEvent;
import android.content.Context;

import com.google.common.eventbus.Subscribe;

/**
 * 読み込み中ダイアログを表示するイベントリスナ
 */
public class LoadingProgressDialogListener implements ApiEventListener {

	final private LoadingProgressDialog mDialog;
	
	public LoadingProgressDialogListener(Context context) {
		mDialog = new LoadingProgressDialog(context);
	}
	
	@Subscribe
	public void before(BeforeApiRequestEvent event) {
		mDialog.show();
	}
	
	@Subscribe
	public void onSuccess(ApiResponse response) {
		mDialog.dismiss();
	}
	
	@Subscribe
	public void onError(ApiErrorEvent event) {
		mDialog.dismiss();
	}
	
}
