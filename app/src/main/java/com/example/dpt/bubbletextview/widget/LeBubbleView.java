package com.example.dpt.bubbletextview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
public class LeBubbleView extends RelativeLayout implements Runnable {

    public static final int STYLE_DARK=1;
    public static final int STYLE_LIGHT=2;
    public static final int STYLE_OTHER=3;

    protected Context mContext;
    protected ArrowDirection arrowDirection;
    protected float relative;
    protected RelativeLayout conRl;
    protected ImageView arrowImage;
    protected int pressBackgroundColor, backgroundColor;
    protected TintedBitmapDrawable norDrawable ,pressDrawable;
    protected int mArrowOffset;
    protected int curStyle;


    public LeBubbleView(Context context) {
        super(context);
        initialize(context, null, 0);
    }

    public LeBubbleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs, 0);
    }

    public LeBubbleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr);
    }

    private void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        //TODO custom attribute
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LeBubbleTextView, defStyleAttr, R.style.LeBubbleTextView_Dark);
        float radius = a.getDimension(R.styleable.LeBubbleTextView_bubbleCornerRadius, 0);
        backgroundColor = a.getColor(R.styleable.LeBubbleTextView_bubbleBackgroundColor, 0);

        pressBackgroundColor = a.getColor(R.styleable.LeBubbleTextView_bubbleBackgroundPressColor, 0);
        int textColor = a.getColor(R.styleable.LeBubbleTextView_bubbleTextColor, 0);
        float textSize = a.getDimension(R.styleable.LeBubbleTextView_bubbleTextSize, 0);
        String contentText = a.getString(R.styleable.LeBubbleTextView_bubbleText);

        int intDirection = a.getInt(R.styleable.LeBubbleTextView_bubbleArrowDirection, 3);
        setCurDirection(intDirection);

        float relativePosition = a.getFraction(R.styleable.LeBubbleTextView_relativePosition, 1, 1, 0.3f);

        setRelativePosition(relativePosition);
        setCurThemeStyle(textColor);
        onInitialize(attrs, defStyleAttr, a);
        a.recycle();
        initContent(radius, backgroundColor, textColor, textSize, contentText);

    }

    protected void onInitialize(AttributeSet attrs, int defStyleAttr, TypedArray a) {

    }

    public void setCurThemeStyle(int textColor) {

        int darkColor = mContext.getResources().getColor(R.color.bubbleView_dark_text_color);
        int lightColor = mContext.getResources().getColor(R.color.bubbleView_light_text_color);
        if(textColor==darkColor){
            curStyle=STYLE_DARK;
        }else if(textColor==lightColor){
            curStyle=STYLE_LIGHT;
        }else {
            curStyle=STYLE_OTHER;
        }
    }

    private void setRelativePosition(float relativePosition) {
        if (relativePosition < 0.2f) {
            relative = 0.2f;
        } else if (relativePosition > 0.8f) {
            relative = 0.8f;
        } else {
            relative = relativePosition;
        }
    }

    private void setCurDirection(int intDirection) {
        switch (intDirection) {
            case 1: {
                arrowDirection = ArrowDirection.LEFT;
            }
            break;
            case 2: {
                arrowDirection = ArrowDirection.TOP;
            }
            break;
            case 3: {
                arrowDirection = ArrowDirection.RIGHT;
            }
            break;
            case 4: {
                arrowDirection = ArrowDirection.BOTTOM;
            }
        }
    }

    private void initContent(float radius, final int backgroundColor, int textColor, float textSize, String content) {



        conRl = new RelativeLayout(mContext);
        conRl.setId(View.generateViewId());
        LayoutParams conRlParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //conRl.setLayoutParams(conRlParams);
        LeRoundRectDrawable2 roundRectDrawable = new LeRoundRectDrawable2(backgroundColor, radius);
        conRl.setBackground(roundRectDrawable);

        initChildView(radius,backgroundColor,textColor,textSize,content);
        arrowImage = new ImageView(mContext);
        arrowImage.setId(View.generateViewId());
        LayoutParams arrowParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        int r;
        switch (arrowDirection) {
            case LEFT: {
                r = 180;
                conRlParams.addRule(RelativeLayout.END_OF, arrowImage.getId());
            }
            break;
            case TOP: {
                r = 270;
                conRlParams.addRule(RelativeLayout.BELOW, arrowImage.getId());
            }
            break;
            case BOTTOM: {
                r = 90;
                arrowParams.addRule(RelativeLayout.BELOW, conRl.getId());
            }
            break;
            default: {
                r = 0;
                arrowParams.addRule(RelativeLayout.END_OF, conRl.getId());
            }
        }

        //int arrowRes = backgroundColor == Color.parseColor("#B3000000")?R.drawable.le_bubble_arrow_light:R.drawable.le_bubble_arrow_light;
        int arrowRes = R.drawable.le_bubble_arrow_light;
        Bitmap source = BitmapFactory.decodeResource(this.getResources(), arrowRes);

        Bitmap rotateBitmap = rotateBitmap(source, r);

        norDrawable = new TintedBitmapDrawable(mContext.getResources(),rotateBitmap,backgroundColor);
        pressDrawable = new TintedBitmapDrawable(mContext.getResources(),rotateBitmap,pressBackgroundColor);

        arrowImage.setImageDrawable(norDrawable);
        this.addView(arrowImage, arrowParams);
        this.addView(conRl, conRlParams);

        arrowImage.setImageTintList(ColorStateList.valueOf(backgroundColor));

        setBackground(new LeStateColorDrawable(Color.TRANSPARENT) {
            @Override
            protected void onIsPressed(boolean isPressed) {
                LeRoundRectDrawable2 conRlBackground = (LeRoundRectDrawable2) conRl.getBackground();
                if (isPressed) {
                    conRlBackground.getPaint().setColor(pressBackgroundColor);
                    //arrowImage.setImageTintList(ColorStateList.valueOf(pressBackgroundColor));
                    arrowImage.setImageDrawable(pressDrawable);
                } else {
                    conRlBackground.getPaint().setColor(backgroundColor);
                    //arrowImage.setImageTintList(ColorStateList.valueOf(backgroundColor));
                    arrowImage.setImageDrawable(norDrawable);
                }
                conRl.invalidate();
                arrowImage.invalidate();
            }
        });

        conRl.post(this);

        this.setClickable(true);

    }

    protected void initChildView(float radius, int backgroundColor, int textColor, float textSize, String content) {

    }

    public Bitmap rotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    protected int dip2px(float dpValue) {
        final float scale = mContext.getResources()
                .getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void run() {
        int conRlw = conRl.getWidth();
        int conRlh = conRl.getHeight();
        LayoutParams params = (LayoutParams) arrowImage.getLayoutParams();
        switch (arrowDirection) {
            case TOP:
            case BOTTOM:
                mArrowOffset =(int) ((conRlw * relative) - arrowImage.getWidth() / 2);
                params.setMargins(mArrowOffset, 0, 0, 0);
                break;
            case LEFT:
            default:
                mArrowOffset =(int) ((conRlh * relative) - arrowImage.getHeight() / 2);
                params.setMargins(0, mArrowOffset, 0, 0);
        }

        onPostCallBack(conRlw,conRlh);
    }

    protected void onPostCallBack(int conRlw, int conRlh) {

    }

    public ArrowDirection getArrowDirection() {
        return arrowDirection;
    }

    public int getArrowOffset() {
        return mArrowOffset;
    }

    public float getRelative() {
        return relative;
    }

    public enum ArrowDirection {
        LEFT, TOP, RIGHT, BOTTOM
    }
}
