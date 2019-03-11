package com.ms.awe.msshowtime.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.ms.awe.msshowtime.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created By Musi
 * on 2019/3/7
 */
public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_adapter_array)
    Button btnAdapterArray;
    @BindView(R.id.btn_adapter_simple)
    Button btnAdapterSimple;
    @BindView(R.id.list_view)
    ListView listView;
    private Unbinder unbinder;

    private String[] arr1 = {"Python","C","C++",".NET","PHP"};
    private String[] names = {"王朝","马汉","张龙","赵虎"};
    private String[] desc = {"文无第一","武无第二","乱世英雄","治国能臣"};
    private int[] imageIds = new int[]{R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round};
    private List<Map<String,Object>> listItems = new ArrayList<Map<String, Object>>();
    private ArrayAdapter<String> adapter;
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        unbinder = ButterKnife.bind(this);

        adapter = new ArrayAdapter<String>(this,R.layout.item_array,arr1);

        for (int i = 0; i < names.length; i++) {
            Map<String,Object> listItem = new HashMap<String,Object>();
            listItem.put("header",imageIds[i]);
            listItem.put("personName",names[i]);
            listItem.put("desc",desc[i]);
            listItems.add(listItem);
        }

        simpleAdapter = new SimpleAdapter(this,listItems,R.layout.item_simple,
                new String[]{"personName","header","desc"},
                new int[]{R.id.name,R.id.header,R.id.desc});

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListActivity.this,names[i] +"被点击了",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.btn_adapter_array, R.id.btn_adapter_simple})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_adapter_array:
                listView.setAdapter(adapter);
                break;
            case R.id.btn_adapter_simple:
                listView.setAdapter(simpleAdapter);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
