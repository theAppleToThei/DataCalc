package com.techtalk4geeks.datacalc;

import java.text.DecimalFormat;

import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.app.AlertDialog;
import android.app.ActionBar.LayoutParams;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TestCalcActivity extends ActionBarActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_calc);
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Set Data Amount");
		alert.setMessage("In GB");

		// Set an EditText view to get user input
		final EditText input = new EditText(this);
		alert.setView(input);
		input.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				double valueNum;
				if (input.getText().length() != 0)
				{
					String value = input.getText().toString();
					valueNum = Double.parseDouble(value);
				} else
				{
					valueNum = 0.5;
				}
				renderGaugeGraphic(valueNum);
			}
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				throw new NullPointerException();
			}
		});

		alert.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_calc, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void renderGaugeGraphic(double myTotal)
	{
		TextView estimate = (TextView) findViewById(R.id.dataEstimateTest);
		estimate.bringToFront();
		DecimalFormat df = new DecimalFormat("#.##");
		estimate.setText((df.format(myTotal)));
		RelativeLayout rl = (RelativeLayout) (findViewById(R.id.image_container_test));
//		if (myTotal <= 0.5)
		{
			final ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));

			ImageView point5 = new ImageView(this);
			point5.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
			// TODO Draw transparent arch over arch5
			// float drawOver = (float) (1 - myTotal / 0.5) * 45;
			float drawOver = 39;
			// TODO Get canvas object
//			Canvas c = new Canvas(five);
			// TODO Draw arch5 onto canvas
			// TODO Draw arch of transparent pixels onto canvas

//			int radius = five.getWidth() / 2;

			dataCalcGraphic.getViewTreeObserver().addOnGlobalLayoutListener(
					new ViewTreeObserver.OnGlobalLayoutListener()
					{

						@Override
						public void onGlobalLayout()
						{
							// Ensure you call it only once :
							dataCalcGraphic.getViewTreeObserver()
									.removeGlobalOnLayoutListener(this);

							// Here you can get the size :)
						}
					});

//			int centerX = (int) (dataCalcGraphic.getX() + radius);
//			int centerY = (int) (dataCalcGraphic.getY() + radius);
//			RectF oval = new RectF(centerX - radius, centerY - radius, centerX
//					+ radius, centerY + radius);
			Paint paint = new Paint();
			paint.setColor(Color.RED); // Transparent
//			c.drawArc(oval, 45, drawOver, true, paint);
			// TODO Get modified arch back out of canvas
//			point5.setImageDrawable(arch5);
			rl.addView(point5);
			estimate.bringToFront();

			TestCanvasCalcView tccv = new TestCanvasCalcView(this);
			tccv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
			rl.addView(tccv);

			if (myTotal <= 1)
			{
				tccv.setGreenAngle((int) (myTotal * 90) - 1);
				tccv.setYellowAngle((int) (0));
				tccv.setRedAngle((int) (0));
			}
			else if (myTotal <= 2) 
			{
				tccv.setGreenAngle((int) (90));
				tccv.setYellowAngle((int) ((myTotal - 1) * 90)); 
				tccv.setRedAngle((int) (0));
			}
			else if (myTotal > 2 && myTotal <= 4) 
			{
				tccv.setGreenAngle((int) (90));
				tccv.setYellowAngle((int) (90));
				tccv.setRedAngle((int) ((myTotal - 2) * 90));
			}
			else
			{
				tccv.setGreenAngle((int) (90));
				tccv.setYellowAngle((int) (90));
				tccv.setRedAngle((int) (180));
			}

			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources()
					.getDrawable(R.drawable.data_calc_graphic_front_small));
			rl.addView(dataCalcGraphicFront);

			ImageView dataCalcGraphicTop = new ImageView(this);
			dataCalcGraphicTop.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			dataCalcGraphicTop.setImageDrawable(this.getResources()
					.getDrawable(R.drawable.data_calc_graphic_top_small));
			rl.addView(dataCalcGraphicTop);

		}
		// else
		// {
		// ImageView dataCalcGraphicFront = new ImageView(this);
		// dataCalcGraphicFront.setLayoutParams(new LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		// dataCalcGraphicFront.setImageDrawable(this.getResources()
		// .getDrawable(R.drawable.data_calc_graphic_front_small));
		// rl = (RelativeLayout) (findViewById(R.id.image_container_test));
		// rl.addView(dataCalcGraphicFront);
		// }
		// if (myTotal > 1)
		// {
		// ImageView dataCalcGraphic = (ImageView)
		// (findViewById(R.id.data_calc_graphic));
		//
		// ImageView one = new ImageView(this);
		// one.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
		// LayoutParams.MATCH_PARENT));
		// one.setImageDrawable(this.getResources().getDrawable(
		// R.drawable.data_calc_graphic_1_small));
		// rl.addView(one);
		// // dataCalcGraphic.bringToFront();
		// ImageView dataCalcGraphicFront = new ImageView(this);
		// dataCalcGraphicFront.setLayoutParams(new LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		// dataCalcGraphicFront.setImageDrawable(this.getResources()
		// .getDrawable(R.drawable.data_calc_graphic_front_small));
		// rl = (RelativeLayout) (findViewById(R.id.image_container_test));
		// rl.addView(dataCalcGraphicFront);
		// }
		// if (myTotal > 1.5)
		// {
		// ImageView dataCalcGraphic = (ImageView)
		// (findViewById(R.id.data_calc_graphic));
		//
		// ImageView onePointFive = new ImageView(this);
		// onePointFive.setLayoutParams(new LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		// onePointFive.setImageDrawable(this.getResources().getDrawable(
		// R.drawable.data_calc_graphic_1_point_5_small));
		// rl.addView(onePointFive);
		// ImageView dataCalcGraphicFront = new ImageView(this);
		// dataCalcGraphicFront.setLayoutParams(new LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		// dataCalcGraphicFront.setImageDrawable(this.getResources()
		// .getDrawable(R.drawable.data_calc_graphic_front_small));
		// rl = (RelativeLayout) (findViewById(R.id.image_container_test));
		// rl.addView(dataCalcGraphicFront);
		// }
		// if (myTotal > 2)
		// {
		// ImageView dataCalcGraphic = (ImageView)
		// (findViewById(R.id.data_calc_graphic));
		//
		// ImageView onePointFive = new ImageView(this);
		// onePointFive.setLayoutParams(new LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		// onePointFive.setImageDrawable(this.getResources().getDrawable(
		// R.drawable.data_calc_graphic_2_small));
		// rl.addView(onePointFive);
		// ImageView dataCalcGraphicFront = new ImageView(this);
		// dataCalcGraphicFront.setLayoutParams(new LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		// dataCalcGraphicFront.setImageDrawable(this.getResources()
		// .getDrawable(R.drawable.data_calc_graphic_front_small));
		// rl = (RelativeLayout) (findViewById(R.id.image_container_test));
		// rl.addView(dataCalcGraphicFront);
		// }
		// if (myTotal > 2.5)
		// {
		// ImageView dataCalcGraphic = (ImageView)
		// (findViewById(R.id.data_calc_graphic));
		//
		// ImageView onePointFive = new ImageView(this);
		// onePointFive.setLayoutParams(new LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		// onePointFive.setImageDrawable(this.getResources().getDrawable(
		// R.drawable.data_calc_graphic_2_point_5_small));
		// rl.addView(onePointFive);
		// ImageView dataCalcGraphicFront = new ImageView(this);
		// dataCalcGraphicFront.setLayoutParams(new LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		// dataCalcGraphicFront.setImageDrawable(this.getResources()
		// .getDrawable(R.drawable.data_calc_graphic_front_small));
		// rl = (RelativeLayout) (findViewById(R.id.image_container_test));
		// rl.addView(dataCalcGraphicFront);
		// }
		// if (myTotal > 3)
		// {
		// ImageView dataCalcGraphic = (ImageView)
		// (findViewById(R.id.data_calc_graphic));
		//
		// ImageView onePointFive = new ImageView(this);
		// onePointFive.setLayoutParams(new LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		// onePointFive.setImageDrawable(this.getResources().getDrawable(
		// R.drawable.data_calc_graphic_3_small));
		// rl.addView(onePointFive);
		// ImageView dataCalcGraphicFront = new ImageView(this);
		// dataCalcGraphicFront.setLayoutParams(new LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		// dataCalcGraphicFront.setImageDrawable(this.getResources()
		// .getDrawable(R.drawable.data_calc_graphic_front_small));
		// rl = (RelativeLayout) (findViewById(R.id.image_container_test));
		// rl.addView(dataCalcGraphicFront);
		// }
		// if (myTotal > 3.5)
		// {
		// ImageView dataCalcGraphic = (ImageView)
		// (findViewById(R.id.data_calc_graphic));
		//
		// ImageView onePointFive = new ImageView(this);
		// onePointFive.setLayoutParams(new LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		// onePointFive.setImageDrawable(this.getResources().getDrawable(
		// R.drawable.data_calc_graphic_3_point_5_small));
		// rl.addView(onePointFive);
		// ImageView dataCalcGraphicFront = new ImageView(this);
		// dataCalcGraphicFront.setLayoutParams(new LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		// dataCalcGraphicFront.setImageDrawable(this.getResources()
		// .getDrawable(R.drawable.data_calc_graphic_front_small));
		// rl = (RelativeLayout) (findViewById(R.id.image_container_test));
		// rl.addView(dataCalcGraphicFront);
		// }
		// if (myTotal > 4)
		// {
		// ImageView dataCalcGraphic = (ImageView)
		// (findViewById(R.id.data_calc_graphic));
		//
		// ImageView onePointFive = new ImageView(this);
		// onePointFive.setLayoutParams(new LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		// onePointFive.setImageDrawable(this.getResources().getDrawable(
		// R.drawable.data_calc_graphic_3_point_5_plus_small));
		// rl.addView(onePointFive);
		// ImageView dataCalcGraphicFront = new ImageView(this);
		// dataCalcGraphicFront.setLayoutParams(new LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		// dataCalcGraphicFront.setImageDrawable(this.getResources()
		// .getDrawable(R.drawable.data_calc_graphic_front_small));
		// rl = (RelativeLayout) (findViewById(R.id.image_container_test));
		// rl.addView(dataCalcGraphicFront);
		// }
	}
}
