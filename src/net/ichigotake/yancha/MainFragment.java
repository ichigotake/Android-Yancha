package net.ichigotake.yancha;
import net.ichigotake.yancha.chat.ChatFragment;
import net.ichigotake.yancha.ui.FragmentTransit;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;


public class MainFragment extends Fragment {

	public MainFragment () {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_login, container, false);
		Log.d("MainActivity", "onCreateView");

		view.findViewById(R.id.button_loginSimple).setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				new FragmentTransit(MainFragment.this).toNext(ChatFragment.newInstance());
			}
		});

		view.findViewById(R.id.button_loginTwitter).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new FragmentTransit(MainFragment.this).toNext(ChatFragment.newInstance());
			}
		});

		return view;
	}
	
}
