package com.techtalk4geeks.datacalc;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class YouTubeCanvasCalcView extends View
{
	float drawOver = 39;
	int greenAngle = 90;
	int yellowAngle = 90;
	int redAngle = 180;

	public YouTubeCanvasCalcView(Context context)
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

		int screenHeight = YouTubeActivity.youtubeActivity.getScreenSize().y;

		int getWidth = canvas.getWidth();
		int x = (int) (getWidth * 0.01);

		int y = (int) (((canvas.getHeight() - getWidth) / 2.0) + x);
		int proportionalWidth = getWidth - (2 * x);
		int proportionalHeight = proportionalWidth;

		RectF oval = new RectF(x, y, x + proportionalWidth, y
				+ proportionalHeight); // (3,
		// 163,
		// 658,
		// 814)
		paint.setColor(Color.GREEN);
		canvas.drawArc(oval, -90, greenAngle, true, paint);
		paint.setColor(Color.YELLOW);
		canvas.drawArc(oval, 0, yellowAngle, true, paint);
		paint.setColor(Color.RED);
		canvas.drawArc(oval, 90, redAngle, true, paint);

		canvas.save();
	}
	
	public void setGreenAngle(int setTo)
	{
		greenAngle = setTo;
	}

	public void setYellowAngle(int setTo)
	{
		yellowAngle = setTo;
	}

	public void setRedAngle(int setTo)
	{
		redAngle = setTo;
	}

}
