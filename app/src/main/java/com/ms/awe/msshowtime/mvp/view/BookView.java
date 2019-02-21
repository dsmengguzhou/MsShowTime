package com.ms.awe.msshowtime.mvp.view;

import com.ms.awe.msshowtime.mvp.model.entity.Book;

/**
 * Created By Musi
 * on 2019/2/21
 */
public interface BookView extends View{

    void onSuccess(Book mBook);
    void onError(String result);
}
