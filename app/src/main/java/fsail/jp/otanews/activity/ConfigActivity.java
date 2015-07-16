package fsail.jp.otanews.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import fsail.jp.otanews.R;

public class ConfigActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        Log.d("設定に", "遷移したよ");
    }

}
