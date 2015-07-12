package fsail.jp.otanews.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import fsail.jp.otanews.R;
import fsail.jp.otanews.activity.WebViewActivity;

public class WebViewFragment extends Fragment {

    private View v;
    private WebViewActivity webViewActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_webview, container, false);
        return v;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.webViewActivity = (WebViewActivity)activity;
    }


    @Override
    public void onStart(){
        super.onStart();

        // WebViewの表示
        WebView webView = (WebView)v.findViewById(R.id.webView1);
        webView.loadUrl(webViewActivity.getIntent().getStringExtra("uri"));
    }

}
