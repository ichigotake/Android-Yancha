package net.ichigotake.yancha.common;

import net.ichigotake.yancha.R;

import com.google.common.base.Optional;

/**
 * チャットの接続ステータス
 */
public enum ChatStatus {
	OFFLINE(R.string.yc_chat_status_offline),
	ONLINE(R.string.yc_chat_status_online);
	
	private final int message;
	
	private ChatStatus(int message) {
		this.message = message;
	}
	
	public int getMessage() {
		return message;
	}
	
	public static Optional<Integer> valueOfMessage(ChatStatus status) {
		ChatStatus[] statuses = values();
		for (ChatStatus _status : statuses) {
			if (_status == status) {
				return Optional.of(_status.getMessage());
			}
		}
		
		return Optional.absent();
	}
}
