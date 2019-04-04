package com.ms.awe.msshowtime.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ms.awe.msshowtime.R;

/**
 * Created By Musi
 * on 2019/4/2
 */
public class MineFragment extends Fragment{

    private View rootView;
    private TextView tvTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //避免重复创建
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_mine, container, false);
            tvTitle = rootView.findViewById(R.id.tv_title);
            tvTitle.setText(R.string.mine);
        }
        return rootView;
    }

}
