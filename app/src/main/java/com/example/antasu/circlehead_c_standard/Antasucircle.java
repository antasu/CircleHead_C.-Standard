package com.example.antasu.circlehead_c_standard;

/**
 * @author antasu
 * web:http://www.antasu.com
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;


public class antasucircle extends AppCompatImageView {
    private float width;
    private float height;
    private float radius;
    private Paint paint;
    private Paint paint2;
    private Matrix matrix;

    public antasucircle(Context context) {
        this(context, null);
    }

    public antasucircle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public antasucircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint2 = new Paint();
        paint.setAntiAlias(true);
        paint2.setAntiAlias(true);
        matrix = new Matrix();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        radius = Math.min(width, height) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setShader(initBitmapShader());
        paint2.setAlpha(0);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        int count = canvas.saveLayer(0.0F, 0.0F, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
//        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        canvas.drawCircle(width / 2, height / 2, radius, paint);
        //设置重叠的部分用来抠取圆形图
        canvas.drawCircle((width / 10) * 8, (height / 10) * 8, radius / 5 * 2, paint2);
        if (!isInEditMode())
            canvas.restoreToCount(count);
    }


    private BitmapShader initBitmapShader() {
        if (getDrawable() != null) {
            Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
            BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            float scale = Math.max(width / bitmap.getWidth(), height / bitmap.getHeight());
            matrix.setScale(scale, scale);
            bitmapShader.setLocalMatrix(matrix);
            return bitmapShader;
        } else {
            return null;
        }
    }
}


