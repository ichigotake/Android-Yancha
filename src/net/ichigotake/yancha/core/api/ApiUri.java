package net.ichigotake.yancha.core.api;

/**
 * API‚ÌURI
 */
public class ApiUri {

	public String getScheme() {
		return "http";
	}
	
	public String getAuthority() {
		return "192.168.0.4:3000";
	}
	
	public String getAbsoluteUrl() {
		return String.format("%s://%s", getScheme(), getAuthority());
	}
	
	public String getSimpleLoginPath() {
		return "/login";
	}

}