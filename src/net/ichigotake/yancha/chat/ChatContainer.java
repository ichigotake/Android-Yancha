package net.ichigotake.yancha.chat;

import java.util.ArrayList;
import java.util.Iterator;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.data.JoinUserListAdapter;
import net.ichigotake.yancha.message.MessageCell;
import net.ichigotake.yancha.message.MessageListAdapter;
import net.ichigotake.yancha.ui.FragmentTransit;
import net.ichigotake.yancha.ui.SendMessage;
import net.ichigotake.yancha.ui.SendMessageListener;
import net.ichigotake.yancha.ui.ViewContainer;

import org.json.JSONException;
import org.json.JSONObject;

import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.eventbus.EventBus;

public class ChatContainer implements ViewContainer {

	final private EventBus eventBus = new EventBus();
	
	final private Fragment fragment;
	
	private ListView messageListView;
	
	private SparseArray<MessageCell> messages;
	
	private MessageListAdapter messageListAdapter;
	
	private ListView joinUserListView;
	
	private TextView chatJoinUsersCountView;
	
	private JoinUserListAdapter joinUserListAdapter;
	
	public ChatContainer(Fragment fragment) {
		this.fragment = fragment;
		this.messages = new SparseArray<MessageCell>();
	}

	@Override
	public void initializeView(View view) {
		chatJoinUsersCountView = (TextView) view.findViewById(R.id.chatJoinUsersCount);

		joinUserListAdapter = new JoinUserListAdapter(fragment.getActivity(), new ArrayList<String>());
		joinUserListView = (ListView) view.findViewById(R.id.joinUsersList);
		joinUserListView.setAdapter(joinUserListAdapter);
		
		ArrayList<MessageCell> messages = new ArrayList<MessageCell>();
		messageListAdapter = new MessageListAdapter(fragment.getActivity(), messages);
		messageListView = (ListView) view.findViewById(R.id.messageList);
		messageListView.setAdapter(messageListAdapter);
		
		view.findViewById(R.id.chatLinkSearch).setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View view) {
				new FragmentTransit(fragment).toNext(ChatLogSearchFragment.createInstance());
			}
		});

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
	
}
