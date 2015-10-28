package com.example.dpt.bubbletextview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.dpt.bubbletextview.R;


/**
 * Created by dupengtao on 15/7/25.
 * <p/>
 * <declare-styleable name="LeBubbleTextView">
 * <!-- Corner radius for LeBubbleTextView. -->
 * <attr name="bubbleCornerRadius" format="dimension"/>
 * <!-- Background color for LeBubbleTextView. -->
 * <attr name="bubbleBackgroundColor" format="color"/>
 * <!-- text size for LeBubbleTextView. -->
 * <attr name="bubbleTextSize" format="dimension"/>
 * <!-- text color for LeBubbleTextView. -->
 * <attr name="bubbleTextColor" format="color"/>
 * <!-- text for LeBubbleTextView. -->
 * <attr name="bubbleText" format="string"/>
 * <p/>
 * <!-- direction for arrow. -->
 * <attr name="bubbleArrowDirection">
 * <enum name="left" value="1"/>
 * <enum name="top" value="2"/>
 * <enum name="right" value="3"/>
 * <enum name="bottom" value="4"/>
 * </attr>
 * <!-- direction for arrow. -->
 * <attr name = "relativePosition" format = "fraction" />
 * <!-- Press state Background color for LeBubbleTextView. -->
 * <attr name="bubbleBackgroundPressColor" format="color"/>
 * </declare-styleable>
 */
public class LeBubbleTitleTextView extends LeBubbleView implements Runnable {

    private ImageView cancelImage;
    private TextView titleTextView;
    private TextView contentTextView;
    private String titleText;


    public LeBubbleTitleTextView(Context context) {
        super(context);
    }

    public LeBubbleTitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LeBubbleTitleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onInitialize(AttributeSet attrs, int defStyleAttr, TypedArray a) {
        titleText = a.getString(R.styleable.LeBubbleTextView_bubbleTitleText);
    }

    private void initCancelImageView() {
        cancelImage = new ImageView(mContext);
    }

    private void initContentTextView(int textColor, String content) {
        contentTextView = new TextView(mContext);
        contentTextView.setId(View.generateViewId());
        LayoutParams contentTvParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        contentTvParams.setMargins(dip2px(22), dip2px(1), dip2px(22), dip2px(15));
        //contentTvParams.addRule(RelativeLayout.ALIGN_TOP,titleTextView.getId());
        contentTvParams.addRule(RelativeLayout.BELOW, titleTextView.getId());
        contentTextView.setLayoutParams(contentTvParams);
        //dark
        contentTextView.setTextColor(textColor);
        contentTextView.setTextSize(14);

        contentTextView.setText(content);
    }

    private void initTitleTextView(int textColor) {
        titleTextView = new TextView(mContext);
        titleTextView.setId(View.generateViewId());
        LayoutParams titleTvParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        titleTvParams.setMargins(dip2px(22), dip2px(15), dip2px(22), 0);
        titleTextView.setLayoutParams(titleTvParams);
        titleTextView.setSingleLine(true);
        //dark
        if (curStyle == STYLE_DARK) {
            titleTextView.setTextColor(Color.WHITE);
        } else {
            titleTextView.setTextColor(Color.BLACK);
        }
        titleTextView.setTextSize(17);

        titleTextView.setText(titleText);

        System.out.print(1);
    }

    private void initCancelView(int conRlw) {
        cancelImage.setId(View.generateViewId());
        LayoutParams cancelParams = new LayoutParams(dip2px(13), dip2px(13));
        //cancelParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        cancelParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        cancelParams.setMargins(conRlw - dip2px(22), dip2px(8), dip2px(8), 0);
        cancelImage.setLayoutParams(cancelParams);
        //cancelImage.setImageResource(R.drawable.le_bubble_cancel);
        int cancel = R.drawable.le_bubble_cancel;
        Bitmap source = BitmapFactory.decodeResource(this.getResources(), cancel);
        int color;
        if (curStyle == STYLE_DARK) {
            color = Color.parseColor("#99FFFFFF");
        } else {
            color = Color.parseColor("#99000000");
        }
        TintedBitmapDrawable cancelBitmapDrawable = new TintedBitmapDrawable(mContext.getResources(), source, color);
        cancelImage.setImageDrawable(cancelBitmapDrawable);
        increaseClickArea();
    }

    private void increaseClickArea() {
        Rect conRlRect = new Rect();
        conRl.getHitRect(conRlRect);
        Rect rect = new Rect(conRlRect);
        //cancelImage.getHitRect(rect);
        //rect.top -= 300;    // increase top hit area
        rect.left = rect.right / 2;   // increase left hit area
        //rect.bottom += 300; // increase bottom hit area
        //rect.right += 300;  // increase right hit area
        conRl.setTouchDelegate(new TouchDelegate(rect, cancelImage));
    }


    @Override
    protected void initChildView(float radius, int backgroundColor, int textColor, float textSize, String content) {
        super.initChildView(radius, backgroundColor, textColor, textSize, content);
        //cancel view
        initCancelImageView();
        //title view
        initTitleTextView(textColor);
        conRl.addView(titleTextView);
        //content view
        initContentTextView(textColor, content);
        conRl.addView(contentTextView);
    }

    public void setCancelImageOnClickListener(OnClickListener l) {
        cancelImage.setOnClickListener(l);
    }

    @Override
    protected void onPostCallBack(int conRlw, int conRlh) {
        super.onPostCallBack(conRlw, conRlh);
        //add cancel image view
        initCancelView(conRlw);
        conRl.addView(cancelImage);
    }

    public View getCancelImage() {
        return cancelImage;
    }
}
