package net.ichigotake.yancha.common.context;

import net.ichigotake.colorfulsweets.lib.context.ActivityTransit;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.SearchActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends ActionBarActivity {

     @Override
     public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
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
                new ActivityTransit(this)
                    .toNext(SearchActivity.class);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
