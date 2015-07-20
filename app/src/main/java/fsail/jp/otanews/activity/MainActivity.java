package fsail.jp.otanews.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import fsail.jp.otanews.R;
import fsail.jp.otanews.api.DataManager;
import fsail.jp.otanews.listener.PageChangeListener;
import fsail.jp.otanews.fragment.ContentFragment;
import fsail.jp.otanews.model.Login;
import fsail.jp.otanews.model.TabItem;


public class MainActivity extends Activity {

    // インジケータのオフセット
    private static final int INDICATOR_OFFSET = 48; // 48dp
    private int mIndicatorOffset;

    // タブ部分のスクローラ
    private HorizontalScrollView mTrackScroller;
    // タブのコンテナ
    private ViewGroup mTrack;
    // インジケータ
    private View mIndicator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //画面サイズ取得
        WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        // INDICATOR_OFFSET・・・ タブのindicatorの幅
        final float density = getResources().getDisplayMetrics().density;
        //Androidの機種に対応したタブスクロール幅に設定
        mIndicatorOffset = (int) (INDICATOR_OFFSET * density);

        // Viewを取得
        mTrackScroller = (HorizontalScrollView) findViewById(R.id.track_scroller);
        mTrack = (ViewGroup) findViewById(R.id.track);
        mIndicator = findViewById(R.id.indicator);

        // ViewPagerのセットアップ
        // adapterは自由に作成
        PagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        final ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new PageChangeListener(size, mTrackScroller, mTrack, mIndicator, mIndicatorOffset));

        // タブをコンテナに追加
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < adapter.getCount(); i++) {
            final int position = i;
            TextView tv = (TextView) inflater.inflate(R.layout.tab_item, mTrack, false);

            tv.setText(adapter.getPageTitle(position));
            // タブタップ時対応したviewPagerを表示
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pager.setCurrentItem(position);
                }
            });
            mTrack.addView(tv);
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        //初回ログイン時はユーザ登録を行う
        if(!DataManager.getInstance().isUuid()){
            Login.getAdId(this);
        }
    }

    // 設定に遷移する
    public void settingTransition(){
        Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
        startActivity(intent);
    }

    // 後読みに遷移する
    public void laterTransition(){
        Intent intent = new Intent(MainActivity.this, LaterActivity.class);
        startActivity(intent);
    }


    // コンテンツのWebViewに遷移する
    public void webViewTransition(String uri) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("uri", uri);
        startActivity(intent);
    }

    private static class ViewPagerAdapter extends FragmentPagerAdapter {
        TabItem tab = new TabItem();

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d("Fragmentの生成", String.valueOf(position));
            return ContentFragment.getInstance(position);
        }

        @Override
        public int getCount() {
            return tab.getTabList().length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tab.getTabList()[position];
        }
    }
}



