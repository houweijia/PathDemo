package com.jiehun.veigar.pathdemo.pathmeasure;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jiehun.veigar.pathdemo.R;

/**
 * @description:
 * @author: houwj
 * @date: 2019/4/2
 */
public class PathMeasureView extends View {
    private Paint  mPaint;
    private Paint  mLinePaint;
    private Bitmap mBitmap;
    private Matrix mMatrix;
    private float  s;

    public PathMeasureView(Context context) {
        this(context, null);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(Color.RED);
        mLinePaint.setStrokeWidth(4);
        mLinePaint.setStyle(Paint.Style.STROKE);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.arrow, options);
        mMatrix = new Matrix();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.translate(getWidth()/2,0);
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), mLinePaint);
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, mLinePaint);
//
        canvas.translate(getWidth() / 2, getHeight() / 2);
//        mPaint.setColor(Color.GRAY);
        //canvas.drawRect(0,0,200,200,mPaint);


//        /**
//         * pathMeasure需要关联一个创建好的path forceClosed会影响测量结果
//         */
//        PathMeasure pathMeasure = new PathMeasure();
//        Path path = new Path();
//        path.lineTo(0,200);
//        path.lineTo(200,200);
//        path.lineTo(200,0);
//        pathMeasure.setPath(path,true);//
//        Log.e("<<<","length="+pathMeasure.getLength());
//
//        PathMeasure pathMeasure1 = new PathMeasure(path,false);
//        Log.e("<<<","length="+pathMeasure1.getLength());
//
//        //如果path进行了调整，需要重新调用setPath进行关联
//        path.lineTo(200,-200);
//        pathMeasure1.setPath(path,false);
//        Log.e("<<<","length="+pathMeasure1.getLength());


//        Path path = new Path();
//        path.addRect(-200,-200,200,200,Path.Direction.CW);
//        Path dst = new Path();
//        dst.lineTo(-300,-300);
//        PathMeasure pathMeasure = new PathMeasure(path,false);
//
//        //pathMeasure.getSegment(200,1000,dst,false);
//        //截取一部分存入dst中，当startWithMoveTo传true时 使用moveTo保持截取得到的Path第一个点位置不变。
//        pathMeasure.getSegment(200,1000,dst,true);
//
//        canvas.drawPath(path,mPaint);
//        canvas.drawPath(dst,mLinePaint);

        /**
         * nextContour 表示跳转到下一条path
         */

//        Path path = new Path();
//        path.addRect(-100,-100,100,100,Path.Direction.CW);
//        path.addOval(-200,-200,200,200,Path.Direction.CW);
//        canvas.drawPath(path,mPaint);
//
//        PathMeasure pathMeasure = new PathMeasure(path,false);
//
//        //pathMeasure.getLength()并不是表示整个path的长度
//        Log.e("<<<","length="+pathMeasure.getLength());
//        //跳转到下一条曲线
//        pathMeasure.nextContour();
//        Log.e("<<<","length="+pathMeasure.getLength());


        s += 0.01;
        if (s >= 1) {
            s = 0;
        }

        mPath.addCircle(0, 0, 200, Path.Direction.CW);
        //mPath.addRect(-200,-200,200,200,Path.Direction.CCW);
        canvas.drawPath(mPath, mPaint);
//        PathMeasure pathMeasure = new PathMeasure(mPath,false);
//        pathMeasure.getPosTan(pathMeasure.getLength()*s,pos,tan);
//        Log.e("<<<","pos="+pos[0]+"------"+"pos="+pos[1]);//pos[0] 为点x的坐标  pos[1]为点y的坐标
//        Log.e("<<<","tan="+tan[0]+"------"+"tan="+tan[1]);//tan[0] 为sinθ tan[2] 为cosθ
//        //计算出当前切线与x轴夹角的角度 atan2 极坐标
//        double degrees = Math.atan2(tan[1],tan[0])*180.0/Math.PI;
//        Log.e("<<<","degrees="+degrees);

//        mMatrix.reset();
//        //进行角度旋转-
//        mMatrix.postRotate((float) degrees,mBitmap.getWidth()/2,mBitmap.getHeight()/2);
//        //将图片绘制点中心与当前重回哪个
//        mMatrix.postTranslate(pos[0]-mBitmap.getWidth()/2,pos[1]-mBitmap.getHeight()/2);
//        canvas.drawBitmap(mBitmap,mMatrix,mPaint);


        PathMeasure pathMeasure = new PathMeasure(mPath, false);
        //将pos信息和tan信息保存在mMatrix中
        pathMeasure.getMatrix(pathMeasure.getLength() * s, mMatrix, PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG);
        //将图片的旋转坐标调整到图片中心位置
        //mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);
        mMatrix.postTranslate(mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        //mMatrix.setTranslate(mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);

        //invalidate();

    }

    private float[] pos   = new float[2];
    private float[] tan   = new float[2];
    private Path    mPath = new Path();
}
