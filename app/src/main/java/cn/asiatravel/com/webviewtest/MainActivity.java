package cn.asiatravel.com.webviewtest;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.asiatravel.com.webviewtest.fragment.HomeFragment;
import cn.asiatravel.com.webviewtest.fragment.MeFragment;
import cn.asiatravel.com.webviewtest.fragment.OrderFragment;
import cn.asiatravel.com.webviewtest.fragment.ServiceFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    // 加载assets资产目录的html:android_asset:assets
//    private static final String DEFAULT_URL = "file:///android_asset/test.html";
//    private static final String DEFAULT_URL = "http://cn.asiatravel.com/mobile/";
    private static final String DEFAULT_URL = "http://10.2.21.231:8080";

    private TextView btnHome, btnOrder, btnService, btnMe;
    private TextView[] mTabs;
    private BaseFragment[] fragments;
    private HomeFragment homeFragment;
    private OrderFragment orderFragment;
    private ServiceFragment serviceFragment;
    private MeFragment meFragment;

    private int index;
    private int currentIndex;//当前fragment的下标


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHome = (TextView) findViewById(R.id.btn_home);
        btnOrder = (TextView) findViewById(R.id.btn_order);
        btnService = (TextView) findViewById(R.id.btn_service);
        btnMe = (TextView) findViewById(R.id.btn_me);

        btnHome.setOnClickListener(this);
        btnOrder.setOnClickListener(this);
        btnService.setOnClickListener(this);
        btnMe.setOnClickListener(this);

        homeFragment = new HomeFragment();
        orderFragment = new OrderFragment();
        serviceFragment = new ServiceFragment();
        meFragment = new MeFragment();
        mTabs = new TextView[4];
        mTabs[0] = btnHome;
        mTabs[1] = btnOrder;
        mTabs[2] = btnService;
        mTabs[3] = btnMe;
        //设置第一个选中
        mTabs[0].setSelected(true);

        fragments = new BaseFragment[]{homeFragment, orderFragment, serviceFragment, meFragment};

        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, homeFragment, null).
                add(R.id.frame_container, orderFragment, null).hide(orderFragment).show(homeFragment).
                commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home:

                index = 0;
                break;
            case R.id.btn_order:

                index = 1;
                break;
            case R.id.btn_service:
                index = 2;

                break;
            case R.id.btn_me:
                index = 3;

                break;


        }
        if (currentIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.frame_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentIndex].setSelected(false);
        // 把当前tab设为选中状态
        mTabs[index].setSelected(true);
        currentIndex = index;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

            return super.onKeyDown(keyCode, event);
    }
}

