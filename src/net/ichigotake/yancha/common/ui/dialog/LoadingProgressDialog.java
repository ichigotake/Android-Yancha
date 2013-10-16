package net.ichigotake.yancha.common.ui.dialog;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * 読み込み中のダイアログ
 */
public class LoadingProgressDialog {

	final private ProgressDialog mDialog;
	
	public LoadingProgressDialog(Context context) {
		mDialog = new ProgressDialog(context);
	}
	
	public void show() {
		mDialog.show();
	}
	
	public void dismiss() {
		mDialog.dismiss();
	}
	
}
