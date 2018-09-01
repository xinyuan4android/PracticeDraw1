package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Practice11PieChartView extends View {

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Paint paint = new Paint();

    private RectF rectF = new RectF(100 + 200, 50, 600 + 200, 550);

    private float startAngle = 0.0f;
    private float spacingAngle = 2.0f;
    private int number = 6;
    private int total = 18;
    private float perAngle = (float) ((360.0 - spacingAngle * number) / total);

    private float circle_x = 350 + 200;//圆点 横坐标
    private float circle_y = 300;//圆点 纵坐标
    private float r = 250;//半径
    private float s = 40;//斜线
    private float l = 100;//直线

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        //画弧
        float startAngle1 = startAngle;
        float angle1 = perAngle * 1;

        canvas.drawArc(rectF, startAngle1, angle1, true, paint);

        paint.setColor(Color.BLUE);
        float startAngle2 = startAngle1 + angle1 + spacingAngle;
        float angle2 = perAngle * 1;
        canvas.drawArc(rectF, startAngle2, angle2, true, paint);

        paint.setColor(Color.LTGRAY);
        float startAngle3 = startAngle2 + angle2 + spacingAngle;
        float angle3 = perAngle * 3;
        canvas.drawArc(rectF, startAngle3, angle3, true, paint);

        paint.setColor(Color.GREEN);
        float startAngle4 = startAngle3 + angle3 + spacingAngle;
        float angle4 = perAngle * 5;
        canvas.drawArc(rectF, startAngle4, angle4, true, paint);

        paint.setColor(Color.RED);
        float startAngle5 = startAngle4 + angle4 + spacingAngle;
        float angle5 = perAngle * 5;
        canvas.drawArc(rectF, startAngle5, angle5, true, paint);

        paint.setColor(Color.YELLOW);
        float startAngle6 = startAngle5 + angle5 + spacingAngle;
        float angle6 = perAngle * 3;
        canvas.drawArc(rectF, startAngle6, angle6, true, paint);

        //画线
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        paint.setTextSize(30);
        float angle_line1 = startAngle1 + angle1 / 2;
        drawLine(canvas, angle_line1);

        float angle_line2 = startAngle2 + angle2 / 2;
        drawLine(canvas, angle_line2);

        float angle_line3 = startAngle3 + angle3 / 2;
        drawLine(canvas, angle_line3);

        float angle_line4 = startAngle4 + angle4 / 2;
        drawLine(canvas, angle_line4);

        float angle_line5 = startAngle5 + angle5 / 2;
        drawLine(canvas, angle_line5);

        float angle_line6 = startAngle6 + angle6 / 2;
        drawLine(canvas, angle_line6);
    }

    private void drawLine(Canvas canvas, float angle_line1) {
        canvas.drawLine(calculateX(angle_line1, r), calculateY(angle_line1, r),
                calculateX(angle_line1, r + s), calculateY(angle_line1, r + s), paint);
        float real_l = 0;
        if ((angle_line1 >= 0 && angle_line1 < 90) || (angle_line1 > 270 && angle_line1 < 360)) {
            real_l = l;
            paint.setTextAlign(Paint.Align.LEFT);
        } else if ((angle_line1 > 90 && angle_line1 <= 180) || (angle_line1 > 180 && angle_line1 < 270)) {
            real_l = -l;
            paint.setTextAlign(Paint.Align.RIGHT);
        }
        canvas.drawLine(calculateX(angle_line1, r + s), calculateY(angle_line1, r + s),
                calculateX(angle_line1, r + s) + real_l, calculateY(angle_line1, r + s), paint);
        canvas.drawText("Froyo", calculateX(angle_line1, r + s) + real_l, calculateY(angle_line1, r + s), paint);
    }

    private float calculateX(float angle, float r) {
        //转弧度制
        float rad = (float) (2 * Math.PI * angle / 360.0);
        if ((angle >= 0 && angle < 90) || (angle > 270 && angle < 360)) {
            return (float) (circle_x + Math.cos(rad) * r);
        } else if ((angle > 90 && angle <= 180) || (angle > 180 && angle < 270)) {
            return (float) (circle_x + Math.cos(rad) * r);
        }
        return r;
    }

    private float calculateY(float angle, float r) {
        //转弧度制
        float rad = (float) (2 * Math.PI * angle / 360.0);
        if (angle == 90 || angle == 270) {
            return r;
        }
        if (angle >= 0 && angle <= 180) {
            return (float) (circle_y + Math.sin(rad) * r);
        }
        if (angle > 180 && angle < 360) {
            return (float) (circle_y + Math.sin(rad) * r);
        }
        return r;
    }

    private float lastX = 0;
    private float lastY = 0;
    private float x = 0;
    private float y = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getX();
                lastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
                float v = calculateLine(lastX, lastY, circle_x, circle_y);
                float v1 = calculateLine(x, y, circle_x, circle_y);
                double acos = 0;
                if (v > v1) {
                    acos = Math.acos(v1 / v);
                } else {
                    acos = Math.acos(v / v1);
                }
                startAngle += (acos * 180) / Math.PI;
                Log.e("view", "lastX == " + lastX + " lastY == " + lastY);
                Log.e("view", "x == " + x + "  y== " + y + " v == " + v +
                        "  v1 == " + v1 + "  acos == " + acos);
                lastX = x;
                lastY = y;
                postInvalidate();
                break;
        }
        return true;
    }

    private float calculateLine(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(Math.abs((x1 - x2) * (x1 - x2)) + Math.abs((y1 - y2) * (y1 - y2)));
    }
}
