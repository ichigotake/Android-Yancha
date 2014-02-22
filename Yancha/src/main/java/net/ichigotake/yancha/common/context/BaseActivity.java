package net.ichigotake.yancha.common.context;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import net.ichigotake.colorfulsweets.common.activity.ActivityTransit;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.SearchActivity;

public abstract class BaseActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.yc_main);
    }
     
     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          getMenuInflater().inflate(R.menu.searchable, menu);

          return super.onCreateOptionsMenu(menu);
     }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_search_icon:
                new ActivityTransit(this, SearchActivity.class)
                    .transition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
