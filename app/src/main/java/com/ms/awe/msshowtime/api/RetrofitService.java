package com.ms.awe.msshowtime.api;


import com.ms.awe.msshowtime.mvp.model.entity.Book;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created By Musi
 * on 2019/2/21
 */
public interface RetrofitService {

    @GET("book/search")
    Observable<Book> getSearchBooks(@Query("q") String name,
                                    @Query("tag") String tag,
                                    @Query("start") int start,
                                    @Query("count") int count);

}
