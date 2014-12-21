package com.techtalk4geeks.datacalc;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TestCanvasCalcView extends View
{
	float drawOver = 39;
	int greenAngle = 90;
	int yellowAngle = 90;
	int redAngle = 180;

	public TestCanvasCalcView(Context context)
	{
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		Paint paint = new Paint();
		// paint.setColor(Color.TRANSPARENT);
		// paint.setStyle(Style.FILL);
		// canvas.drawPaint(paint);

		RectF oval = new RectF(3, 165, 649, 808);
		paint.setColor(Color.GREEN);
		canvas.drawArc(oval, -90, greenAngle, true, paint);
		paint.setColor(Color.YELLOW);
		canvas.drawArc(oval, 0, yellowAngle, true, paint);
		paint.setColor(Color.RED);
		canvas.drawArc(oval, 90, redAngle, true, paint);

		canvas.save();
	}
	
	public void setGreenAngle(int setTo) {
		greenAngle = setTo;
	}
	
	public void setYellowAngle(int setTo) {
		yellowAngle = setTo;
	}
	
	public void setRedAngle(int setTo) {
		redAngle = setTo;
	}

}
