package net.ichigotake.yancha.common.ui.dialog;

import net.ichigotake.yancha.R;
import android.app.AlertDialog;
import android.content.Context;

/**
 * ���b�Z�[�W��\������ {@link MessageDialog} �𐶐�
 */
public class MessageDialogBuilder {

	final private AlertDialog.Builder mBuilder;
	
	public MessageDialogBuilder(Context context) {
		mBuilder = new AlertDialog.Builder(context);
	}
	
	public MessageDialog show() {
		return new MessageDialog(mBuilder.show());
	}
	
	/**
	 * ���b�Z�[�W��\������_�C�A���O
	 */
	public class MessageDialog {
		
		final private AlertDialog mDialog;
		
		MessageDialog(AlertDialog dialog) {
			mDialog = dialog;
		}
		
		public void show() {
			mDialog.show();
		}
		
		public void dismiss() {
			mDialog.dismiss();
		}
		
	}
	
	public MessageDialogBuilder setMessage(int message) {
		mBuilder.setMessage(message);
		return this;
	}
	
	public MessageDialogBuilder setDefaultPositiveText() {
		mBuilder.setPositiveButton(R.string.yc_common_ok, null);
		return this;
	}
	
}
