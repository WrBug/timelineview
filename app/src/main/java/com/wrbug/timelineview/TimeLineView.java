package com.wrbug.timelineview;

import android.view.View;

/**
 * TimeLineView
 *
 * @author WrBug
 * @since 2017/2/21
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TimeLineView extends View {
    private Paint mPaint;
    private List<String> mPointTxt;
    private int mStep = 3;
    private int[] mXpoints;
    private int normalTimeColor;
    private int pastTimeColor;
    private int radius = 10;
    private int textSize = 20;

    public TimeLineView(Context paramContext) {
        super(paramContext);
        init();
    }

    public TimeLineView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        init();
    }

    public TimeLineView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        init();
    }


    private void init() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.pastTimeColor = Color.BLUE;
        this.normalTimeColor = Color.GRAY;
        this.mPointTxt = new ArrayList();
        this.mPointTxt.add("等候支付");
        this.mPointTxt.add("等候商家接单");
        this.mPointTxt.add("等候配送");
        this.mPointTxt.add("等候送达");
    }

    public void draw(Canvas paramCanvas) {
        if ((this.mXpoints == null) || (this.mXpoints.length != this.mPointTxt.size())) {
            int len = this.mPointTxt.size();
            this.mXpoints = new int[len];
            if (len > 1) {
                int strlen = this.mPointTxt.get(0).length() / 2 * this.textSize;
                this.mXpoints[0] = Math.max(strlen, this.radius);
                strlen = this.mPointTxt.get(len - 1).length() / 2 * this.textSize;
                this.mXpoints[len - 1] = getWidth() - Math.max(strlen, this.radius);
                int dx = (this.mXpoints[len - 1] - this.mXpoints[0]) / (len - 1);
                for (int i = 1; i < this.mXpoints.length - 1; i++) {
                    this.mXpoints[i] = mXpoints[0] + dx * i;
                }
                super.draw(paramCanvas);
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas, this.mStep >= 1, this.mPointTxt.get(0), this.mXpoints[0]);
        for (int i = 1; i < mPointTxt.size(); i++) {
            drawLine(canvas, this.mStep > i, this.mXpoints[(i - 1)], this.mXpoints[i]);
            drawCircle(canvas, this.mStep > i, this.mPointTxt.get(i), this.mXpoints[i]);

        }
    }

    private void drawCircle(Canvas paramCanvas, boolean b, String text, int dx) {
        this.mPaint.setColor(b ? pastTimeColor : normalTimeColor);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(2.0F);
        paramCanvas.drawCircle(dx, getHeight() - this.radius - 1, this.radius, this.mPaint);
        this.mPaint.setStyle(Paint.Style.FILL);
        paramCanvas.drawCircle(dx, getHeight() - this.radius - 1, this.radius - 5, this.mPaint);
        this.mPaint.setTextSize(this.textSize);
        paramCanvas.drawText(text, dx - text.length() / 2.0F * this.textSize, getHeight() - this.radius * 2 - 15, this.mPaint);
    }

    private void drawLine(Canvas paramCanvas, boolean b, int startX, int endX) {
        this.mPaint.setColor(b ? pastTimeColor : normalTimeColor);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setStrokeWidth(5.0F);
        paramCanvas.drawLine(this.radius * 1.5F + startX, getHeight() - this.radius - 1, endX - this.radius * 1.5F, getHeight() - this.radius - 1, this.mPaint);
    }

    public void setData(List<String> paramList, int paramInt) {
        if (paramList == null) {
            this.mPointTxt.clear();
        } else {
            this.mPointTxt = new ArrayList(paramList);
            this.mStep = paramInt;
        }
        invalidate();
    }

    public void setStep(int paramInt) {
        this.mStep = paramInt;
        invalidate();
    }
}
