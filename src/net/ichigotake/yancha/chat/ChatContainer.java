package net.ichigotake.yancha.chat;

import java.util.ArrayList;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.message.MessageCell;
import net.ichigotake.yancha.message.MessageListAdapter;
import net.ichigotake.yancha.ui.FragmentTransit;
import net.ichigotake.yancha.ui.ViewContainer;

import org.json.JSONException;
import org.json.JSONObject;

import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class ChatContainer implements ViewContainer {

	private Fragment fragment;
	
	private ListView messageListView;
	
	private SparseArray<MessageCell> messages;
	
	private MessageListAdapter adapter;
	
	public ChatContainer(Fragment fragment) {
		this.fragment = fragment;
		this.messages = new SparseArray<MessageCell>();
	}

	@Override
	public void initializeView(View view) {
		ArrayList<MessageCell> messages = new ArrayList<MessageCell>();
		adapter = new MessageListAdapter(fragment.getActivity(), messages);
		messageListView = (ListView) view.findViewById(R.id.common_message_list);
		messageListView.setAdapter(adapter);
		
		view.findViewById(R.id.textView_linkSearch).setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View view) {
				new FragmentTransit(fragment).toNext(ChatLogSearchFragment.createInstance());
			}
		});

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
		adapter.add(message);
	
		adapter.notifyDataSetChanged();
		messageListView.invalidateViews();
	}
	
}
