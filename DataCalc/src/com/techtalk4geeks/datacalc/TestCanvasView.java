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
		RectF oval = new RectF(centerX, centerY, centerX, centerY);
		paint.setColor(Color.RED); // Transparent
		canvas.drawArc(oval, 45, drawOver, true, paint);

		paint.setColor(Color.RED);
		canvas.drawText("Something is working!", 50, 85, paint);
		canvas.save();
	}

}