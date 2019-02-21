package com.ms.awe.msshowtime.manager;

import android.content.Context;

import com.ms.awe.msshowtime.api.RetrofitHelper;
import com.ms.awe.msshowtime.api.RetrofitService;
import com.ms.awe.msshowtime.mvp.model.entity.Book;

import rx.Observable;

/**
 * Created By Musi
 * on 2019/2/21
 */
public class DataManager {

    private RetrofitService mRetrofitService;
    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }
    public Observable<Book> getSearchBooks(String name, String tag, int start, int count){
        return mRetrofitService.getSearchBooks(name,tag,start,count);
    }

}
