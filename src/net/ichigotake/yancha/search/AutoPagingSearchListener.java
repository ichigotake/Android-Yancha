package net.ichigotake.yancha.search;

import net.ichigotake.colorfulsweets.lib.model.PagingParameter;
import net.ichigotake.colorfulsweets.lib.net.http.AsyncHttpAccessor;
import net.ichigotake.colorfulsweets.lib.net.http.AutoPagingListener;
import net.ichigotake.yancha.common.api.rest.SearchApiRequest;
import net.ichigotake.yancha.common.message.PostMessageAdapter;
import net.ichigotake.yancha.common.model.SearchOptionBuilder;
import net.ichigotake.yancha.common.user.User;
import net.ichigotake.yanchasdk.lib.model.PostMessage;

import android.content.Context;
import android.widget.ArrayAdapter;

class AutoPagingSearchListener extends AutoPagingListener<PostMessage> {

    final private SearchOptionBuilder mBuilder;
    
    public AutoPagingSearchListener(Context context, User user) {
        super(context);
        mBuilder = new SearchOptionBuilder(user.getConnectServerAuthority())
            .setLimit(getPerPage());
    }
    
    @Override
    protected int getPerPage() {
        return 100;
    }

    @Override
    protected ArrayAdapter<PostMessage> createArrayAdapter(Context context) {
        return new PostMessageAdapter(context);
    }

    @Override
    protected AsyncHttpAccessor createHttpAccessor(
            ArrayAdapter<PostMessage> adapter, PagingParameter parameter) {
        
        mBuilder.setOffset(parameter.getOffset());
        AsyncHttpAccessor accessor = new SearchApiRequest(mBuilder.build());
        accessor.registerListener(new OnApiResponseListener(adapter));
        return accessor;
    }

}
