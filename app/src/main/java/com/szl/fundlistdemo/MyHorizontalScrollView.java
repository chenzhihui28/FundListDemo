package com.szl.fundlistdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by SZLHaojian on 2016/5/9 0009.
 */
public class MyHorizontalScrollView extends HorizontalScrollView{
    private OnScrollChangedCallback mOnScrollChangedCallback;


    public MyHorizontalScrollView(Context context) {
        super(context);

    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedCallback != null) {
            mOnScrollChangedCallback.onScroll(l,t,oldl,oldt);
        }
    }


    public OnScrollChangedCallback getOnScrollChangedCallback() {
        return mOnScrollChangedCallback;
    }

    public void setOnScrollChangedCallback(
            final OnScrollChangedCallback onScrollChangedCallback) {
        mOnScrollChangedCallback = onScrollChangedCallback;
    }

    public interface OnScrollChangedCallback {
        void onScroll(int l, int t, int oldl, int oldt);
    }
}
