package com.zfg.privacyview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

/**
 * @author zhongfg
 */
public class TermsActivity extends Activity {

    private static final String TAG = TermsActivity.class.getSimpleName();

    private FrameLayout web_view_container;
    private WebView web_view;

    private final String LANGUAGE_CN = "zh-CN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_terms);

        initView();
    }

    private void initView() {

        web_view_container = findViewById(R.id.web_view_container);
        web_view = new WebView(getApplicationContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        web_view.setLayoutParams(params);
        web_view.setWebViewClient(new WebViewClient());
        //动态添加WebView，解决在xml引用WebView持有Activity的Context对象，导致内存泄露
        web_view_container.addView(web_view);

        String language = AppUtil.getLanguage(TermsActivity.this);
        Log.i(TAG, "当前语言：" + language);

        if (LANGUAGE_CN.equals(language)) {
            web_view.loadUrl("file:///android_asset/user_agreement.html");
        } else {
            web_view.loadUrl("file:///android_asset/user_agreement.html");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        web_view_container.removeAllViews();
        web_view.destroy();
    }
}
