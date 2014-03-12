package net.ichigotake.yancha.common.chat;

import android.net.Uri;

import net.ichigotake.yancha.sdk.api.BasicEndpoint;
import net.ichigotake.yancha.sdk.api.SearchApiBuilder;


public class SearchOptionBuilder {

    final private String mAuthority;
    
    private int mOffset;
    
    private int mLimit;
    
    public SearchOptionBuilder(String authority) {
        mAuthority = authority;
    }
    
    public Uri build() {
        return new SearchApiBuilder()
                .setFormat(SearchApiBuilder.Format.JSON)
                .setOrder("-created_at_ms")
                .setLimit(mLimit, mOffset)
                .get()
                .scheme("http")
                .authority(mAuthority)
                .path(BasicEndpoint.SEARCH.getPath())
                .build();
    }

    public SearchOptionBuilder setOffset(int offset) {
        mOffset = offset;
        return this;
    }
    
    public SearchOptionBuilder setLimit(int limit) {
        mLimit = limit;
        return this;
    }
    
}
