package fsail.jp.otanews.fragment;


import android.app.Activity;
import android.os.Bundle;
import fsail.jp.otanews.activity.WebViewActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fsail.jp.otanews.R;

public class WebViewHeaderFragment extends HeaderFragment {

    private WebViewActivity webViewActivity;
    private View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       v = inflater.inflate(R.layout.fragment_webview_header, container, false);
       return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        webViewActivity = (WebViewActivity)activity;
    }

    @Override
    public void onStart(){
        super.onStart();

        // 戻るボタン
        TextView backbtn = (TextView)v.findViewById(R.id.webViewBackbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webViewActivity.finish();
            }
        });

    }

}
