package com.wrbug.opensources;

import android.content.res.TypedArray;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * TimeLineView
 *
 * @author WrBug
 * @since 2017/2/21
 */
public class TimeLineView extends View {
    private Paint mPaint;
    /**
     * 状态文本
     */
    private List<String> mPointTxt;
    /**
     * 步数
     */
    private int mStep = 1;
    /**
     * 圆形组x坐标
     */
    private int[] mXpoints;

    private int mPreLineColor;
    private int mStartedLineColor;

    private int mStartedCircleColor;
    private int mUnderwayCircleColor;
    private int mPreCircleColor;

    private int mStartedStringColor;
    private int mUnderwayStringColor;
    private int mPreStringColor;

    private int mRadius = 10;
    private int mTextSize = 20;

    private OnStepChangedListener mOnStepChangedListener;

    public TimeLineView(Context paramContext) {
        this(paramContext, null);
    }

    public TimeLineView(Context paramContext, AttributeSet paramAttributeSet) {
        this(paramContext, paramAttributeSet, 0);
    }

    public TimeLineView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        init();
        initAttrs(paramAttributeSet);
    }

    private void initAttrs(AttributeSet paramAttributeSet) {
        if (paramAttributeSet != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.TimeLineView);
            mStartedLineColor = typedArray.getColor(R.styleable.TimeLineView_startedLineColor, Color.BLUE);
            mPreLineColor = typedArray.getColor(R.styleable.TimeLineView_preLineColor, Color.GRAY);

            mStartedCircleColor = typedArray.getColor(R.styleable.TimeLineView_startedCircleColor, Color.BLUE);
            mUnderwayCircleColor = typedArray.getColor(R.styleable.TimeLineView_underwayCircleColor, Color.BLUE);
            mPreCircleColor = typedArray.getColor(R.styleable.TimeLineView_preCircleColor, Color.GRAY);

            mStartedStringColor = typedArray.getColor(R.styleable.TimeLineView_startedStringColor, Color.BLUE);
            mUnderwayStringColor = typedArray.getColor(R.styleable.TimeLineView_underwayStringColor, Color.BLUE);
            mPreStringColor = typedArray.getColor(R.styleable.TimeLineView_preStringColor, Color.GRAY);

            mTextSize = (int) typedArray.getDimension(R.styleable.TimeLineView_textSize, 20);
        }
    }


    private void init() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mStartedLineColor = Color.BLUE;
        this.mPreLineColor = Color.GRAY;
        this.mStartedCircleColor = mStartedLineColor;
        this.mUnderwayCircleColor = mStartedCircleColor;
        this.mPreCircleColor = mPreLineColor;
        this.mStartedStringColor = mStartedLineColor;
        this.mUnderwayStringColor = mStartedStringColor;
        this.mPreStringColor = mPreLineColor;
        this.mPointTxt = new ArrayList();
    }

    public void setPointStrings(@NonNull List<String> pointStringList, @IntRange(from = 1) int step) {
        if (pointStringList == null || pointStringList.isEmpty()) {
            this.mPointTxt.clear();
            this.mStep = 0;
        } else {
            this.mPointTxt = new ArrayList(pointStringList);
            this.mStep = Math.min(step, this.mPointTxt.size());
        }
        invalidate();
    }

    public void setStep(@IntRange(from = 1) int step) {
        this.mStep = Math.min(step, this.mPointTxt.size());
        invalidate();
    }

    public boolean nextStep() {
        if (mStep + 1 > mPointTxt.size()) {
            return false;
        } else {
            mStep++;
            invalidate();
            return true;
        }
    }

    public int getStep() {
        return mStep;
    }

    public void setOnStepChangedListener(OnStepChangedListener listener) {
        this.mOnStepChangedListener = listener;
    }

    public void draw(Canvas canvas) {
        int len = this.mPointTxt.size();
        this.mXpoints = new int[len];
        if (len > 1) {
            int strlen = (int) (this.mPointTxt.get(0).length() / 2.0 * this.mTextSize);
            this.mXpoints[0] = Math.max(strlen, this.mRadius);
            strlen = (int) (this.mPointTxt.get(len - 1).length() / 2.0 * this.mTextSize);
            this.mXpoints[len - 1] = getWidth() - Math.max(strlen, this.mRadius);
            int dx = (this.mXpoints[len - 1] - this.mXpoints[0]) / (len - 1);
            for (int i = 1; i < this.mXpoints.length - 1; i++) {
                this.mXpoints[i] = mXpoints[0] + dx * i;
            }
            super.draw(canvas);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas, this.mStep >= 1, this.mPointTxt.get(0), this.mXpoints[0]);
        if (mOnStepChangedListener != null) {
            mOnStepChangedListener.onchanged(this, this.mStep, this.mPointTxt.get(mStep - 1));
        }
        for (int i = 1; i < mPointTxt.size(); i++) {
            drawLine(canvas, this.mStep > i, this.mXpoints[(i - 1)], this.mXpoints[i]);
            drawCircle(canvas, this.mStep > i, this.mPointTxt.get(i), this.mXpoints[i]);

        }
    }

    private void drawCircle(Canvas canvas, boolean isStart, String text, int dx) {
        this.mPaint.setColor(isStart ? mStartedCircleColor : mPreCircleColor);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(2.0F);
        canvas.drawCircle(dx, getHeight() - this.mRadius - 1, this.mRadius, this.mPaint);
        this.mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(dx, getHeight() - this.mRadius - 1, this.mRadius - 5, this.mPaint);
        this.mPaint.setColor(isStart ? mStartedStringColor : mPreStringColor);
        this.mPaint.setTextSize(this.mTextSize);
        canvas.drawText(text, dx - text.length() / 2.0F * this.mTextSize, getHeight() - this.mRadius * 2 - 15, this.mPaint);
    }

    private void drawLine(Canvas paramCanvas, boolean b, int startX, int endX) {
        this.mPaint.setColor(b ? mStartedLineColor : mPreLineColor);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setStrokeWidth(5.0F);
        paramCanvas.drawLine(this.mRadius * 1.5F + startX, getHeight() - this.mRadius - 1, endX - this.mRadius * 1.5F, getHeight() - this.mRadius - 1, this.mPaint);
    }

    public interface OnStepChangedListener {
        void onchanged(TimeLineView view, int step, String stepStr);
    }
}
