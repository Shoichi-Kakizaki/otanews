package fsail.jp.otanews;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import fsail.jp.otanews.listener.PageChangeListener;
import fsail.jp.otanews.fragment.ContentFragment;


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
        }
    }


    private static class ViewPagerAdapter extends FragmentPagerAdapter {
        // タブの項目
        private static final String[] sTabs = { "Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto" };

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return ContentFragment.getInstance(position);
        }

        @Override
        public int getCount() {
            return sTabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return sTabs[position];
        }
    }
}



