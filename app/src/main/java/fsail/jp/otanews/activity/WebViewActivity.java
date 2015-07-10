package fsail.jp.otanews.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import fsail.jp.otanews.R;

public class WebViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        // WebViewの表示
        WebView webView = (WebView)findViewById(R.id.webView1);
        Intent intent = getIntent();
        webView.loadUrl(intent.getStringExtra("uri"));
    }
}
