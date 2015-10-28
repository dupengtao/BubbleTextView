package com.example.dpt.bubbletextview.helper;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.example.dpt.bubbletextview.widget.LeBubbleTextView;
import com.example.dpt.bubbletextview.widget.LeBubbleView;

/**
 * Created by dupengtao on 15/10/9.
 */
public class LeBubbleTextViewHelper {

    private LeBubbleView mBubbleView;
    private PopupWindow mBubblePopupWindow;
    private View mAnchor;
    private int mBubbleViewWidth;
    private int mBubbleViewHeight;

    public LeBubbleTextViewHelper() {
    }

    public LeBubbleTextViewHelper(LeBubbleView bubbleView) {
        mBubbleView = bubbleView;
    }

    /**
     * 初始化
     * @param anchor 目标的view
     * @param bubbleViewLayoutRes bubbleTextView资源文件
     */
    public void init(final View anchor, final int bubbleViewLayoutRes) {

        mBubbleView = (LeBubbleView) LayoutInflater.from(anchor.getContext()).inflate(
                bubbleViewLayoutRes, null);
        mAnchor = anchor;
        mBubblePopupWindow = new PopupWindow(mBubbleView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mBubblePopupWindow.setFocusable(false);
        mBubblePopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mBubbleView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        mBubbleViewWidth = mBubbleView.getMeasuredWidth();
        mBubbleViewHeight = mBubbleView.getMeasuredHeight();
    }

    /**
     * 显示
     */
    public void show() {
        show(0, 0);
    }

    /**
     * 显示
     * @param xCustomOffset x轴偏移量
     * @param yCustomOffset y轴偏移量
     */
    public void show(int xCustomOffset, int yCustomOffset) {

        int[] location = new int[2];
        mAnchor.getLocationInWindow(location);
        LeBubbleTextView.ArrowDirection arrowDirection = mBubbleView.getArrowDirection();
        int xOffset = 0, yOffset = 0;
        int anchorWidth = mAnchor.getWidth();
        int anchorHeight = mAnchor.getHeight();
        switch (arrowDirection) {
            case LEFT: {
                xOffset = anchorWidth;
                int arrowOffset = (mBubbleViewHeight / 2) - (int) (mBubbleViewHeight * mBubbleView.getRelative());
                yOffset = -(mBubbleViewHeight - anchorHeight) / 2 + arrowOffset;
            }
            break;
            case TOP: {
                yOffset = anchorHeight;
                int arrowOffset = (mBubbleViewWidth / 2) - (int) (mBubbleViewWidth * mBubbleView.getRelative());
                xOffset = -(mBubbleViewWidth - anchorWidth) / 2 + arrowOffset;
            }
            break;
            case BOTTOM: {
                yOffset = -mBubbleViewHeight;
                int arrowOffset = (mBubbleViewWidth / 2) - (int) (mBubbleViewWidth * mBubbleView.getRelative());
                xOffset = -(mBubbleViewWidth - anchorWidth) / 2 + arrowOffset;
            }
            break;
            default: {
                xOffset = -mBubbleViewWidth - 10;
                int arrowOffset = (mBubbleViewHeight / 2) - (int) (mBubbleViewHeight * mBubbleView.getRelative());
                yOffset = -(mBubbleViewHeight - anchorHeight) / 2 + arrowOffset;
            }
        }
        mBubblePopupWindow.showAtLocation(mAnchor, Gravity.NO_GRAVITY, location[0] + xOffset + xCustomOffset, location[1] + yOffset + yCustomOffset);
    }

    public PopupWindow getBubblePopupWindow() {
        return mBubblePopupWindow;
    }

    /**
     * 消失
     */
    public void dismissBubblePopupWindow() {
        mBubblePopupWindow.dismiss();
    }

    /**
     * @see LeBubbleTextViewHelper#getBubbleView()
     * @return
     */
    @Deprecated
    public LeBubbleTextView getBubbleTextView() {
        if(mBubbleView instanceof LeBubbleTextView) {
            return (LeBubbleTextView)mBubbleView;
        }else {
            return null;
        }
    }

    public LeBubbleView getBubbleView() {
        return mBubbleView;
    }
}
