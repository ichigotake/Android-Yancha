package net.ichigotake.yancha.chat;

import java.util.ArrayList;
import java.util.Iterator;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.ui.ViewContainer;
import net.ichigotake.yancha.core.ChatStatus;
import net.ichigotake.yancha.core.message.MessageCell;
import net.ichigotake.yancha.core.message.MessageListAdapter;
import net.ichigotake.yancha.core.message.SendMessage;
import net.ichigotake.yancha.core.message.SendMessageListener;
import net.ichigotake.yancha.users.JoinUserListAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.base.Optional;
import com.google.common.eventbus.EventBus;
import com.haarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;

/**
 * チャット画面を出力するビューコンテナ
 */
public class ChatContainer implements ViewContainer {

	final private EventBus eventBus = new EventBus();
	
	final private Fragment fragment;
	
	final private Handler handler;
	
	private ListView messageListView;
	
	private SparseArray<MessageCell> messages;
	
	private MessageListAdapter messageListAdapter;
	
	private ListView joinUserListView;
	
	private TextView chatJoinUsersCountView;
	
	private JoinUserListAdapter joinUserListAdapter;
	
	private TextView statusView;
	
	public ChatContainer(Fragment fragment) {
		this.fragment = fragment;
		this.messages = new SparseArray<MessageCell>();
		handler = new Handler();
	}

	@Override
	public void initializeView(View view) {
		chatJoinUsersCountView = (TextView) view.findViewById(R.id.chatJoinUsersCount);

		joinUserListAdapter = new JoinUserListAdapter(fragment.getActivity(), new ArrayList<String>());
		joinUserListView = (ListView) view.findViewById(R.id.joinUsersList);
		joinUserListView.setAdapter(joinUserListAdapter);
		
		ArrayList<MessageCell> messages = new ArrayList<MessageCell>();
		messageListAdapter = new MessageListAdapter(fragment.getActivity(), messages);
		ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(messageListAdapter);
		messageListView = (ListView) view.findViewById(R.id.messageList);
		animationAdapter.setAbsListView(messageListView);
		messageListView.setAdapter(animationAdapter);
		
		final EditText viewMessage = (EditText) view.findViewById(R.id.chatSendMessageText);
		Button viewSubmit = (Button) view.findViewById(R.id.chatSendMessageSend);
		viewSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String message = viewMessage.getText().toString();
				if (message.isEmpty()) {
					return ;
				}
				
				eventBus.post(new SendMessage(message));
				//TODO 発言失敗等での再利用用に発言ヒストリーを取っておきたい
				viewMessage.setText("");
			}
		});
		
		statusView = (TextView) view.findViewById(R.id.chatStatus);
	}
	
	public void updateStatus(ChatStatus status) {
		final Optional<Integer> message = ChatStatus.valueOfMessage(status);
		if (message.isPresent()) {
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					statusView.setText(message.get());
				}
			});
		}
	}
	
	public void registerListener(SendMessageListener listener) {
		eventBus.register(listener);
	}
	
	public void addMessage(JSONObject json) {		
		try {
			if (messages.get(json.getInt("id")) != null) {
				return ;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return ;
		}
		
		MessageCell message = new MessageCell(json);
		
		messages.put(message.getId(), message);
		messageListAdapter.add(message);
	
		messageListAdapter.notifyDataSetChanged();
		messageListView.invalidateViews();
		
		scrollBottomWhenNewMessage();
	}
	
	public void updateJoinUsers(String response) {
		JSONObject json;
		try {
			json = new JSONObject(response);
		} catch (JSONException e) {
			e.printStackTrace();
			return ;
		}

		ArrayList<String> joinUserList = new ArrayList<String>();
		Iterator<?> iter = json.keys();
		while (iter.hasNext()) {
			joinUserList.add((String)iter.next());
		}
		
		joinUserListAdapter.clear();
		joinUserListAdapter.addAll(joinUserList);
		joinUserListAdapter.notifyDataSetChanged();
		
		updateJoinUsersCount(joinUserList.size());
	}
	
	public void updateJoinUsersCount(int count) {
		chatJoinUsersCountView.setText(count + "人");
	}
	
	private void scrollBottomWhenNewMessage() {
		messageListView.post(new Runnable() {			
			@Override
			public void run() {
				final int bottomPosition = messages.size()-1;
				if (bottomPosition == messageListView.getLastVisiblePosition()+1) {
					messageListView.setSelection(bottomPosition);
				}
			}
		});
	}
}
