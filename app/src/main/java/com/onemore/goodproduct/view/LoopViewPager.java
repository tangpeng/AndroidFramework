/*
 * Copyright (C) 2013 Leszek Mzyk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onemore.goodproduct.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import com.onemore.goodproduct.util.MyLog;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoopViewPager extends ViewPager {
    private final  String TAG="LOOPVIEWPAGER";

    OnPageChangeListener mOuterPageChangeListener;
    private LoopPagerAdapterWrapper mAdapter;

    private MyHandler mHandler;
    private final static int HANDLE_LOOP_MSG = 101;
    private AtomicBoolean isAutoLoop = new AtomicBoolean();

    @Override
    public void setAdapter(PagerAdapter adapter) {
        mAdapter = new LoopPagerAdapterWrapper(adapter);
        super.setAdapter(mAdapter);
        isAutoLoop.set(false);
        setCurrentItem(0, false);
    }

    @Override
    public PagerAdapter getAdapter() {
        return mAdapter != null ? mAdapter.getRealAdapter() : mAdapter;
    }

    /**
     * 获取当前真实的item
     *
     * @return
     */
    public int getRealItem() {
        return mAdapter != null ? mAdapter.toRealPosition(super.getCurrentItem()) : 0;
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        int realItem = mAdapter.toInnerPosition(item);
        super.setCurrentItem(realItem, smoothScroll);
    }


    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mOuterPageChangeListener = listener;
    }

    public LoopViewPager(Context context) {
        super(context);
        init();
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        super.setOnPageChangeListener(onPageChangeListener);
        try {
            Field scrollerField = ViewPager.class.getDeclaredField("mScroller");
            scrollerField.setAccessible(true);
            Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);

            Scroller scroller = new Scroller(getContext(), (Interpolator) interpolator.get(null)) {
                @Override
                public void startScroll(int startX, int startY, int dx, int dy, int duration) {
                    //控制滑动速度
                    super.startScroll(startX, startY, dx, dy, (int) (1300 * (double) Math.abs(dx) / getWidth(getContext())));
                }
            };
            scrollerField.set(this, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void autoLoop(boolean isAuto) {
        if (mHandler == null) {
            mHandler = new MyHandler(getContext());
        }
        if (isAuto) {
            mHandler.sendEmptyMessageDelayed(HANDLE_LOOP_MSG, 3000);
        } else {
            mHandler.removeCallbacksAndMessages(null);
        }
        isAutoLoop.set(isAuto);
    }

    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        private float mPreviousOffset = -1;
        private float mPreviousPosition = -1;

        @Override
        public void onPageSelected(int position) {

            int realPosition = mAdapter.toRealPosition(position);
            if (mPreviousPosition != realPosition) {
                mPreviousPosition = realPosition;
                if (mOuterPageChangeListener != null) {
                    mOuterPageChangeListener.onPageSelected(realPosition);
                }
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {

            int realPosition = position;
            if (mAdapter != null) {
                realPosition = mAdapter.toRealPosition(position);
                MyLog.i(TAG,"realPosition="+realPosition);
                if (positionOffset == 0
                        && mPreviousOffset == 0
                        && (position == 0 || position == mAdapter.getCount() - 1)) {
                    setCurrentItem(realPosition, false);
                }
            }

            mPreviousOffset = positionOffset;
            if (mOuterPageChangeListener != null) {
                if (realPosition != mAdapter.getRealCount() - 1) {
                    mOuterPageChangeListener.onPageScrolled(realPosition,
                            positionOffset, positionOffsetPixels);
                } else {
                    if (positionOffset > .5) {
                        mOuterPageChangeListener.onPageScrolled(0, 0, 0);
                    } else {
                        mOuterPageChangeListener.onPageScrolled(realPosition,
                                0, 0);
                    }
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            MyLog.i(TAG,"state="+state);
            switch (state) {
                case SCROLL_STATE_DRAGGING:
                    if (isAutoLoop.get()) {
                        mHandler.removeCallbacksAndMessages(null);
                    }
                    break;
                case SCROLL_STATE_IDLE:
                    if (isAutoLoop.get()) {
                        mHandler.sendEmptyMessageDelayed(HANDLE_LOOP_MSG, 3000);
                    }
                    break;
                case SCROLL_STATE_SETTLING:
                    break;
            }
            if (mOuterPageChangeListener != null) {
                mOuterPageChangeListener.onPageScrollStateChanged(state);
            }
        }
    };




    private class MyHandler extends Handler {

        WeakReference<Context> mWeakReference;

        public MyHandler(Context context) {
            mWeakReference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            final Context context = mWeakReference.get();
            if (context == null) {
                return;
            }
            switch (msg.what) {
                case HANDLE_LOOP_MSG:
                    int curItem = getCurrentItem();
                    setCurrentItem(++curItem);
                    break;
            }
        }
    }


    public int getWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
