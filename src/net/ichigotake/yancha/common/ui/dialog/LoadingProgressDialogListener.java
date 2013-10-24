package net.ichigotake.yancha.common.ui.dialog;

import net.ichigotake.colorfulsweets.lib.net.http.BeforeHttpRequestEvent;
import net.ichigotake.colorfulsweets.lib.net.http.HttpAccessErrorEvent;
import net.ichigotake.colorfulsweets.lib.net.http.HttpAccessEventListener;
import net.ichigotake.colorfulsweets.lib.net.http.HttpAccessResponse;
import android.content.Context;

import com.google.common.eventbus.Subscribe;

/**
 * �ǂݍ��ݒ��_�C�A���O��\������C�x���g���X�i
 */
public class LoadingProgressDialogListener implements HttpAccessEventListener {

	final private LoadingProgressDialog mDialog;
	
	public LoadingProgressDialogListener(Context context) {
		mDialog = new LoadingProgressDialog(context);
	}
	
	@Subscribe
	public void before(BeforeHttpRequestEvent event) {
		mDialog.show();
	}
	
	@Subscribe
	public void onSuccess(HttpAccessResponse response) {
		mDialog.dismiss();
	}
	
	@Subscribe
	public void onError(HttpAccessErrorEvent event) {
		mDialog.dismiss();
	}
	
}
