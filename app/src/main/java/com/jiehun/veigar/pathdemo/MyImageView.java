package com.jiehun.veigar.pathdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;


/**
 * @description: 底部为弧形的 imageView
 * @author: houwj
 * @date: 2019/4/3
 */
public class MyImageView extends ImageView {
    private float mRadius;

    public MyImageView(Context context) {
        this(context,null);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray array = context.obtainStyledAttributes(
                attrs, R.styleable.Custom_Round_Image_View, defStyleAttr, defStyleRes);
        mRadius = array.getDimension(R.styleable.Custom_Round_Image_View_radius, 100f);
        init();
    }

    private void init() {


    }


    @Override
    protected void onDraw(Canvas canvas) {
        if(mRadius>getHeight()){
            throw new IllegalArgumentException("半径不能大于view的高度");
        }

        Path path = new Path();
        RectF rect = new RectF(0,0,getWidth(),getHeight()-mRadius);
        path.addRect(rect,Path.Direction.CW);
        path.moveTo(0,getHeight()-mRadius);
        Log.e("main","height==="+getHeight());
        path.quadTo(getWidth()/2,getHeight()+100,getWidth(),getHeight()-mRadius);
        canvas.clipPath(path);

        super.onDraw(canvas);
    }
}
