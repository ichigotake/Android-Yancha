package net.ichigotake.yancha.common.model;

import android.net.Uri;

import net.ichigotake.colorfulsweets.common.net.UriBuilder;


public class SearchOptionBuilder {

    final private String mAuthority;
    
    private int mOffset;
    
    private int mLimit;
    
    public SearchOptionBuilder(String authority) {
        mAuthority = authority;
    }
    
    public SearchOption build() {
        return new SearchOption();
    }
    
    public class SearchOption {
        
        SearchOption() {
        }
        
        public int getOffset() {
            return mOffset;
        }
        
        public int getLimit() {
            return mLimit;
        }
        
        public Uri toUri() {
            return new UriBuilder()
                .setScheme("http")
                .setAuthrity(mAuthority)
                .setPath("/api/search")
                .appendQueryParameter("limit", mLimit + "," + mOffset)
                .appendQueryParameter("order", "-created_at_ms")
                .build();
        }
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
