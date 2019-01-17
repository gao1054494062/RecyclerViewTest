package com.example.lenovo.viewpagergridview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

public class IndicatorView extends View {

    private int indicatorCount = 0;
    private int currentIndicator = 0;
    private int indicatorWidth = 0;
    private int marginWidth = 0;

    private Bitmap mLightBitmap;
    private Bitmap mNormalBitmap;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x12) {
                invalidate();
            }
        }
    };

    public IndicatorView(Context context) {
        super(context);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mLightBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.guide_light);
        mNormalBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.guide_normal);
        indicatorWidth = mNormalBitmap.getWidth();
        marginWidth = (int) getResources().getDimension(R.dimen.indicator_margin_width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        int totalWidth = indicatorWidth * indicatorCount + marginWidth * (indicatorCount - 1);

        if (indicatorCount <= 1) {
            return;
        }

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        int top = (viewHeight - indicatorWidth) / 2;
        for (int i = 0; i < indicatorCount; i++) {
            int left = (viewWidth - totalWidth) / 2 + (indicatorWidth + marginWidth) * i;
            if (i == currentIndicator) {
                canvas.drawBitmap(mLightBitmap, left, top, paint);
            } else {
                canvas.drawBitmap(mNormalBitmap, left, top, paint);
            }
        }
    }

    public void setIndicatorCount(int indicatorCount) {
        this.indicatorCount = indicatorCount;
    }

    public void setCurrentIndicator(int currentIndicator) {
        this.currentIndicator = currentIndicator;
        handler.sendEmptyMessage(0x12);
    }
}