package com.ms.awe.msshowtime.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ms.awe.msshowtime.R;
import com.ms.awe.msshowtime.utils.ToastUtil;

/**
 * Created By Musi
 * on 2019/4/2
 */
public class HomeFragment extends Fragment{

    private View rootView;
    private RelativeLayout btnTab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //避免重复创建
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_home, container, false);
            btnTab = rootView.findViewById(R.id.btn_to_tab);
            btnTab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.showShort(getContext(),"啥都没瞎点啥");
                }
            });
        }

        return rootView;
    }

}
