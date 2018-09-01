package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice10HistogramView extends View {

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Paint paint = new Paint();

    private RectF rectF = new RectF();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        canvas.drawLine(50, 50, 50, 500, paint);
        canvas.drawLine(50, 500, 1000, 500, paint);

        paint.setTextSize(20);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("Froyo", 75 + 50, 525, paint);
        canvas.drawText("GB", 200 + 50, 525, paint);
        canvas.drawText("ICS", 325 + 50, 525, paint);
        canvas.drawText("JB", 450 + 50, 525, paint);
        canvas.drawText("Kitkat", 575 + 50, 525, paint);
        canvas.drawText("L", 700 + 50, 525, paint);
        canvas.drawText("M", 825 + 50, 525, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        rectF.set(200, 450, 300, 500);
        canvas.drawRect(rectF, paint);

        rectF.left += 125;
        rectF.right += 125;
        canvas.drawRect(rectF, paint);

        rectF.left += 125;
        rectF.right += 125;
        rectF.top -= 200;
        canvas.drawRect(rectF, paint);

        rectF.left += 125;
        rectF.right += 125;
        rectF.top -= 100;
        canvas.drawRect(rectF, paint);

        rectF.left += 125;
        rectF.right += 125;
        rectF.top -= 50;
        canvas.drawRect(rectF, paint);

        rectF.left += 125;
        rectF.right += 125;
        rectF.top += 250;
        canvas.drawRect(rectF, paint);
    }
}
