package fsail.jp.otanews.activity;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import fsail.jp.otanews.R;

public class WebViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
    }
}
