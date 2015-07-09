package fsail.jp.otanews;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.TextView;



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

        // INDICATOR_OFFSET・・・ タブのindicatorの幅
        final float density = getResources().getDisplayMetrics().density;
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
        pager.setOnPageChangeListener(new PageChangeListener());

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

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        private int mScrollingState = ViewPager.SCROLL_STATE_IDLE;

        @Override
        public void onPageSelected(int position) {
            // スクロール中はonPageScrolled()で描画するのでここではしない
            if (mScrollingState == ViewPager.SCROLL_STATE_IDLE) {
                updateIndicatorPosition(position, 0);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            mScrollingState = state;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            updateIndicatorPosition(position, positionOffset);
        }

        private void updateIndicatorPosition(int position, float positionOffset) {
            // 現在の位置のタブのView
            final View view = mTrack.getChildAt(position);
            // 現在の位置の次のタブのView、現在の位置が最後のタブのときはnull
            final View view2 = position == (mTrack.getChildCount() - 1) ? null
                    : mTrack.getChildAt(position + 1);

            // 現在の位置のタブの左端座標取得
            int left = view.getLeft();

            // 現在の位置のタブの横幅
            int width = view.getWidth();
            // 現在の位置の次のタブの横幅
            int width2 = view2 == null ? width : view2.getWidth();

            // インディケータの幅
            // width2 × スライドした割合 ＋ (width × スライドした割合 - 1)
            int indicatorWidth = (int) (width2 * positionOffset + width
                    * (1 - positionOffset));
            // インディケータの左端の位置
            // 今選択中のタブの左端 + width * スライドした割合
            int indicatorLeft = (int) (left + positionOffset * width);

            // インディケータの幅と左端の位置をセット
            final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mIndicator
                    .getLayoutParams();
            layoutParams.width = indicatorWidth;
            layoutParams.setMargins(indicatorLeft, 0, 0, 0);
            mIndicator.setLayoutParams(layoutParams);

            // インディケータが画面に入るように、タブの領域をスクロール
            mTrackScroller.scrollTo(indicatorLeft - mIndicatorOffset, 0);
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
            return SimpleFragment.getInstance(position);
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

    public static class SimpleFragment extends Fragment {

        public static SimpleFragment getInstance(int position) {
            SimpleFragment f = new SimpleFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            f.setArguments(args);
            return f;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_content, container, false);
        }
    }
}



