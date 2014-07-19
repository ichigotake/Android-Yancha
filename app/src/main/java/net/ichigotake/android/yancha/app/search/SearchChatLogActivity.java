package net.ichigotake.android.yancha.app.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import net.ichigotake.android.yancha.app.R;

public class SearchChatLogActivity extends Activity {

    public static Intent createIntent(Context context) {
        return new Intent(context, SearchChatLogActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, SearchChatLogFragment.newInstance())
                .commit();
    }

}
