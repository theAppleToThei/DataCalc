package com.techtalk4geeks.datacalc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class TestCanvasView extends View
{
	float drawOver = 39;

	public TestCanvasView(Context context, AttributeSet set)
	{
		super(context, set);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setColor(Color.TRANSPARENT);
		paint.setStyle(Style.FILL);
		canvas.drawPaint(paint);

		int centerX = (int) (360);
		int centerY = (int) (640);
		RectF oval = new RectF(centerX, centerY, centerX + 200, centerY + 200);
		paint.setColor(Color.GREEN);
		canvas.drawArc(oval, 0, 45, true, paint);
		paint.setColor(Color.RED);
		canvas.drawArc(oval, 45, drawOver, true, paint);
		paint.setColor(Color.YELLOW);
		canvas.drawArc(oval, 84, 276, true, paint);

		paint.setTextSize(45);
		paint.setColor(Color.RED);
		canvas.drawText("Something is working!", 50, 85, paint);
		paint.setColor(Color.YELLOW);
		canvas.drawText("Something is working!", 50, 135, paint);
		paint.setColor(Color.GREEN);
		canvas.drawText("Something is working!", 50, 175, paint);
		paint.setColor(Color.BLUE);
		canvas.drawText("Something is working!", 50, 215, paint);
		paint.setColor(Color.MAGENTA);
		canvas.drawText("Something is working!", 50, 255, paint);
		paint.setColor(Color.GRAY);
		canvas.drawText("Something is working!", 50, 295, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText("Something is working!", 50, 335, paint);
		
		canvas.drawCircle(50, 500, 5, paint);
		canvas.drawCircle(65, 500, 10, paint);
		canvas.drawCircle(90, 500, 15, paint);
		canvas.drawCircle(120, 500, 20, paint);
		canvas.save();
	}

}