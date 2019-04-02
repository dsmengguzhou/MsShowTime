package com.ms.awe.msshowtime.widget.gangedrecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created By Musi
 * on 2019/3/27
 */
public abstract class RvHolder<T> extends RecyclerView.ViewHolder{

    protected RvListener mListener;

    public RvHolder(View itemView,int type,RvListener listener) {
        super(itemView);
        this.mListener = listener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(view.getId(),getAdapterPosition());
            }
        });
    }

    public abstract void bindHolder(T t,int position);
}
