package com.ms.awe.msshowtime.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ms.awe.msshowtime.R;
import com.ms.awe.msshowtime.mvp.model.entity.Book;
import com.ms.awe.msshowtime.mvp.presenter.BookPresenter;
import com.ms.awe.msshowtime.mvp.view.BookView;
import com.ms.awe.msshowtime.widget.FlodableButton;
import com.ms.awe.msshowtime.widget.guide.HoleBean;
import com.ms.awe.msshowtime.widget.guide.NewbieGuideManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.ll_item_book)
    RelativeLayout llItemBook;
    @BindView(R.id.btn_material_design)
    Button btnMdActivity;
    @BindView(R.id.btn_widget_activity)
    Button btnWidgetActivity;
    @BindView(R.id.btn_we_chat)
    Button btnWeChat;
    @BindView(R.id.btn_retrofit_request)
    Button btnRetrofitRequest;
    @BindView(R.id.tv_book_title)
    TextView tvBookTitle;
    @BindView(R.id.tv_book_author)
    TextView tvBookAuthor;
    @BindView(R.id.tv_book_publisher)
    TextView tvBookPublisher;
    @BindView(R.id.iv_left_img)
    ImageView ivLeftImg;
    @BindView(R.id.iv_close_item)
    ImageView ivCloseItem;

    private FlodableButton flodableButton;
    private BookPresenter mBookPresenter = new BookPresenter(this);
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);              //返回一个Unbinder对象

        flodableButton = findViewById(R.id.flodable_button);
        flodableButton.setOnClickListener(new FlodableButton.OnClickListener() {
            @Override
            public void onClick(FlodableButton sfb) {
                flodableButton.startScroll();
            }
        });
        flodableButton.setFoldListener(new FlodableButton.FoldListener() {
            @Override
            public void onFold(boolean isIncrease, FlodableButton sfb) {
                String text = isIncrease ? "展开了" : "折叠了";
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });

        mBookPresenter.onCreate();
        mBookPresenter.attachView(mBookView);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (NewbieGuideManager.isNeverShowed(this, NewbieGuideManager.TYPE_MS)) {
            new NewbieGuideManager(this, NewbieGuideManager.TYPE_MS).addView(btnMdActivity, HoleBean.TYPE_RECTANGLE).show();
        }
    }

    @OnClick({R.id.btn_material_design, R.id.btn_widget_activity, R.id.btn_we_chat,
            R.id.btn_retrofit_request, R.id.ll_item_book, R.id.iv_close_item})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_material_design:
                startActivity(new Intent(MainActivity.this, MDActivity.class));
                break;
            case R.id.btn_widget_activity:
                startActivity(new Intent(MainActivity.this, WidgetActivity.class));
                break;
            case R.id.btn_we_chat:
                startActivity(new Intent(MainActivity.this, WeChatActivity.class));
                break;
            case R.id.btn_retrofit_request:
                mBookPresenter.getSearchBooks("金瓶梅", null, 0, 1);
                llItemBook.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_item_book:
                Toast.makeText(MainActivity.this, "想看书吗小老弟", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_close_item:
                llItemBook.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    private BookView mBookView = new BookView() {
        @Override
        public void onSuccess(Book mBook) {
            Log.e("musixiaoge", mBook.toString());
            tvBookTitle.setText(Html.fromHtml(getString(R.string.main_book_title,mBook.getBooks().get(0).getTitle())));
            tvBookAuthor.setText(Html.fromHtml(getString(R.string.main_book_author,mBook.getBooks().get(0).getAuthor())));
            tvBookPublisher.setText(Html.fromHtml(getString(R.string.main_book_publisher,mBook.getBooks().get(0).getPublisher())));
            Glide.with(MainActivity.this)
                    .load(mBook.getBooks().get(0).getImages().getLarge())
                    .override(600,390)
                    .into(ivLeftImg);

        }

        @Override
        public void onError(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        mBookPresenter.onStop();
    }
}
