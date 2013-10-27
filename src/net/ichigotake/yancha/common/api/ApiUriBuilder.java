package net.ichigotake.yancha.common.api;

import android.net.Uri;

/**
 * API���N�G�X�g�ɕK�v�ȃp�����[�^�[���܂� {@link Uri} �𐶐�
 */
public class ApiUriBuilder {

	final private Uri.Builder mBulder;

	public ApiUriBuilder() {
		mBulder = new Uri.Builder();
	}

	public ApiUriBuilder appendQueryParameter(YanchaApiField field, String value) {
		mBulder.appendQueryParameter(field.getKey(), value);
		return this;
	}

	public ApiUriBuilder appendQueryParameter(YanchaApiField field, ApiUri uri) {
		mBulder.appendQueryParameter(field.getKey(), uri.toString());
		return this;
	}

	public ApiUriBuilder setScheme(String scheme) {
		mBulder.scheme(scheme);
		return this;
	}

	public ApiUriBuilder setAuthrity(String authority) {
		mBulder.encodedAuthority(authority);
		return this;
	}

	public ApiUriBuilder setPath(ApiUri uri) {
		return setPath(uri.toString());
	}

	public ApiUriBuilder setPath(String path) {
		mBulder.encodedPath(path);
		return this;
	}

	public ApiUriBuilder setScheme(ApiUri uri) {
		mBulder.scheme(uri.toString());
		return this;
	}

	public Uri build() {
		return mBulder.build();
	}

}