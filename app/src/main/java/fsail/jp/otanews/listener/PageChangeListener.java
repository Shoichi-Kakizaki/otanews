package fsail.jp.otanews.listener;

import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;

/**
 * Created by kakizaki_shoichi on 2015/07/09.
 */
public class PageChangeListener implements ViewPager.OnPageChangeListener {
    private int mScrollingState = ViewPager.SCROLL_STATE_IDLE;
    // タブ部分のスクローラ
    private HorizontalScrollView mTrackScroller;
    // タブのコンテナ
    private ViewGroup mTrack;
    // インジケータ
    private View mIndicator;
    private int mIndicatorOffset;
    // 画面サイズ
    private Point size;

    public PageChangeListener(Point size, HorizontalScrollView mTrackScroller, ViewGroup mTrack, View mIndicator, int mIndicatorOffset){
        this.mTrackScroller = mTrackScroller;
        this.mTrack = mTrack;
        this.mIndicator = mIndicator;
        this.mIndicatorOffset = mIndicatorOffset;
        this.size = size;

    }

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
        final View view2 = position == (mTrack.getChildCount() - 1) ? null : mTrack.getChildAt(position + 1);

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
        mTrackScroller.scrollTo(indicatorLeft - (size.x - width)/2 , 0);
    }
}
