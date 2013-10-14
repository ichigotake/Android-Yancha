package net.ichigotake.yancha.common.context;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class AppContext {

	final private Context mContext;
	
	public AppContext(Context context) {
		mContext = context;
	}
	
	/**
	 * "x.x.x" 形式でバージョンネームを返す
	 * @return
	 */
	public String getVersionName() {
		String versionName;
		try {
			PackageInfo info = mContext.getPackageManager()
					.getPackageInfo(mContext.getPackageName(), PackageManager.GET_META_DATA);
			versionName = info.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			versionName = "";
		}
		return versionName;
	}
	
	/**
	 * "v x.x.x" 形式でバージョンネームを返す
	 * @return
	 */
	public String getFullVersionName() {
		String versionName = getVersionName();
		
		final String fullName;
		if (! "".equals(versionName)) {
			fullName = "v " + versionName;
		} else {
			fullName = "";
		}
		
		return fullName;
	}
	
}
