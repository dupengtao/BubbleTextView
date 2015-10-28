package com.example.dpt.bubbletextview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;


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
public class LeBubbleTextView extends LeBubbleView implements Runnable {

    private TextView tvContent;

    public LeBubbleTextView(Context context) {
        super(context);
    }

    public LeBubbleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LeBubbleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initChildView(float radius, int backgroundColor, int textColor, float textSize, String content) {
        super.initChildView(radius, backgroundColor, textColor, textSize, content);
        tvContent = new TextView(mContext);
        tvContent.setId(View.generateViewId());
        tvContent.setTextColor(textColor);
        tvContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        tvContent.setText(content);
        int px22 = dip2px(21);
        int px16 = dip2px(15);
        tvContent.setPaddingRelative(px22, px16, px22, px16);
        conRl.addView(tvContent);
    }

    public TextView getContentTextView(){
        return tvContent;
    }


}
