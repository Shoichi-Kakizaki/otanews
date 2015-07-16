package fsail.jp.otanews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import fsail.jp.otanews.R;

public class LaterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_later);
    }

    // WebViewに遷移する
    public void laterTransition(String uri){
        Intent intent = new Intent(LaterActivity.this, WebViewActivity.class);
        intent.putExtra("uri", uri);
        startActivity(intent);
    }

}
