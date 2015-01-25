package com.techtalk4geeks.datacalc;

import java.text.DecimalFormat;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.os.Build;

public class CalcActivity extends ActionBarActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		i.getExtras();
		double myTotal = i.getDoubleExtra("total", 0);
		setContentView(R.layout.activity_calc);
		setTitle("Data Calc");
		TextView estimate = (TextView) findViewById(R.id.dataEstimate);
		estimate.bringToFront();
		DecimalFormat df = new DecimalFormat("#.##");
		estimate.setText((df.format(myTotal)));
		RelativeLayout rl = (RelativeLayout) (findViewById(R.id.image_container));
		renderGaugeGraphic(myTotal);

		estimate.bringToFront();
		if (savedInstanceState == null)
		{
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	private void veryRoughEstimateRender(double myTotal, TextView estimate)
	{
		RelativeLayout rl;
		if (myTotal <= 0.5)
		{
			final ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));

			// ImageView point5 = new ImageView(this);
			// point5.setLayoutParams(new
			// LayoutParams(LayoutParams.MATCH_PARENT,
			// LayoutParams.MATCH_PARENT));
			// Drawable arch5 = this.getResources().getDrawable(
			// R.drawable.data_calc_graphic_point_5_small);
			// TODO Draw transparent arch over arch5
			// float drawOver = (float) (1 - myTotal / 0.5) * 45;
			float drawOver = 39;
			// TODO Get canvas object
			// Bitmap five = Bitmap.createBitmap(arch5.getMinimumWidth(),
			// arch5.getMinimumHeight(), Bitmap.Config.ALPHA_8);
			// Canvas c = new Canvas(five);
			// TODO Draw arch5 onto canvas
			// TODO Draw arch of transparent pixels onto canvas

			// int radius = five.getWidth() / 2;

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

			// int centerX = (int) (dataCalcGraphic.getX() + radius);
			// int centerY = (int) (dataCalcGraphic.getY() + radius);
			// RectF oval = new RectF(centerX - radius, centerY - radius,
			// centerX
			// + radius, centerY + radius);
			Paint paint = new Paint();
			paint.setColor(Color.RED); // Transparent
			// c.drawArc(oval, 45, drawOver, true, paint);
			// TODO Get modified arch back out of canvas
			// point5.setImageDrawable(arch5);
			// rl.addView(point5);
			estimate.bringToFront();

			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources()
					.getDrawable(R.drawable.data_calc_graphic_front_small));
			rl = (RelativeLayout) (findViewById(R.id.image_container));
			rl.addView(dataCalcGraphicFront);
		} else
		{
			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources()
					.getDrawable(R.drawable.data_calc_graphic_front_small));
			rl = (RelativeLayout) (findViewById(R.id.image_container));
			rl.addView(dataCalcGraphicFront);
		}
		if (myTotal > 1)
		{
			ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));

			ImageView one = new ImageView(this);
			one.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
			one.setImageDrawable(this.getResources().getDrawable(
					R.drawable.data_calc_graphic_1_small));
			rl.addView(one);
			// dataCalcGraphic.bringToFront();
			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources()
					.getDrawable(R.drawable.data_calc_graphic_front_small));
			rl = (RelativeLayout) (findViewById(R.id.image_container));
			rl.addView(dataCalcGraphicFront);
		}
		if (myTotal > 1.5)
		{
			ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));

			ImageView onePointFive = new ImageView(this);
			onePointFive.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			onePointFive.setImageDrawable(this.getResources().getDrawable(
					R.drawable.data_calc_graphic_1_point_5_small));
			rl.addView(onePointFive);
			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources()
					.getDrawable(R.drawable.data_calc_graphic_front_small));
			rl = (RelativeLayout) (findViewById(R.id.image_container));
			rl.addView(dataCalcGraphicFront);
		}
		if (myTotal > 2)
		{
			ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));

			ImageView onePointFive = new ImageView(this);
			onePointFive.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			onePointFive.setImageDrawable(this.getResources().getDrawable(
					R.drawable.data_calc_graphic_2_small));
			rl.addView(onePointFive);
			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources()
					.getDrawable(R.drawable.data_calc_graphic_front_small));
			rl = (RelativeLayout) (findViewById(R.id.image_container));
			rl.addView(dataCalcGraphicFront);
		}
		if (myTotal > 2.5)
		{
			ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));

			ImageView onePointFive = new ImageView(this);
			onePointFive.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			onePointFive.setImageDrawable(this.getResources().getDrawable(
					R.drawable.data_calc_graphic_2_point_5_small));
			rl.addView(onePointFive);
			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources()
					.getDrawable(R.drawable.data_calc_graphic_front_small));
			rl = (RelativeLayout) (findViewById(R.id.image_container));
			rl.addView(dataCalcGraphicFront);
		}
		if (myTotal > 3)
		{
			ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));

			ImageView onePointFive = new ImageView(this);
			onePointFive.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			onePointFive.setImageDrawable(this.getResources().getDrawable(
					R.drawable.data_calc_graphic_3_small));
			rl.addView(onePointFive);
			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources()
					.getDrawable(R.drawable.data_calc_graphic_front_small));
			rl = (RelativeLayout) (findViewById(R.id.image_container));
			rl.addView(dataCalcGraphicFront);
		}
		if (myTotal > 3.5)
		{
			ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));

			ImageView onePointFive = new ImageView(this);
			onePointFive.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			onePointFive.setImageDrawable(this.getResources().getDrawable(
					R.drawable.data_calc_graphic_3_point_5_small));
			rl.addView(onePointFive);
			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources()
					.getDrawable(R.drawable.data_calc_graphic_front_small));
			rl = (RelativeLayout) (findViewById(R.id.image_container));
			rl.addView(dataCalcGraphicFront);
		}
		if (myTotal > 4)
		{
			ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));

			ImageView onePointFive = new ImageView(this);
			onePointFive.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			onePointFive.setImageDrawable(this.getResources().getDrawable(
					R.drawable.data_calc_graphic_3_point_5_plus_small));
			rl.addView(onePointFive);
			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources()
					.getDrawable(R.drawable.data_calc_graphic_front_small));
			rl = (RelativeLayout) (findViewById(R.id.image_container));
			rl.addView(dataCalcGraphicFront);
		}
	}

	public Carrier getATTRate(double estimate)
	{
		return new Carrier("AT&T", "N/A", "N/A");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
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

	// @Override
	// public void onBackPressed()
	// {
	// Intent start = new Intent(this, StartActivity.class);
	// start.addCategory(Intent.CATEGORY_HOME);
	// start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	// startActivity(start);
	// overridePendingTransition(R.anim.anim_in_up, R.anim.anim_out_down);
	// }

	public void renderGaugeGraphic(double myTotal)
	{
		TextView estimate = (TextView) findViewById(R.id.dataEstimate);
		estimate.bringToFront();
		DecimalFormat df = new DecimalFormat("#.##");
		estimate.setText((df.format(myTotal)));
		RelativeLayout rl = (RelativeLayout) (findViewById(R.id.image_container));
		// if (myTotal <= 0.5)
		{
			final ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));

			// ImageView point5 = new ImageView(this);
			// point5.setLayoutParams(new
			// LayoutParams(LayoutParams.MATCH_PARENT,
			// LayoutParams.MATCH_PARENT));
			// TODO Draw transparent arch over arch5
			// float drawOver = (float) (1 - myTotal / 0.5) * 45;
			float drawOver = 39;
			// TODO Get canvas object
			// Canvas c = new Canvas(five);
			// TODO Draw arch5 onto canvas
			// TODO Draw arch of transparent pixels onto canvas

			// int radius = five.getWidth() / 2;

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

			Point size = getScreenSize();
			int width = size.x;
			int height = size.y;

			// int centerX = (int) (dataCalcGraphic.getX() + radius);
			// int centerY = (int) (dataCalcGraphic.getY() + radius);
			// RectF oval = new RectF(centerX - radius, centerY - radius,
			// centerX
			// + radius, centerY + radius);
			Paint paint = new Paint();
			paint.setColor(Color.RED); // Transparent
			// c.drawArc(oval, 45, drawOver, true, paint);
			// TODO Get modified arch back out of canvas
			// point5.setImageDrawable(arch5);
			// rl.addView(point5);
			estimate.bringToFront();

			TestCanvasCalcView tccv = new TestCanvasCalcView(this);
			tccv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
			tccv.setX((int) (width / 20.571428571)); // 35
			tccv.setY((int) (height / 36.571428571));
			rl.addView(tccv);

			if (myTotal <= 1)
			{
				tccv.setGreenAngle((int) (myTotal * 90) - 1);
				tccv.setYellowAngle((int) (0));
				tccv.setRedAngle((int) (0));
			} else if (myTotal <= 2)
			{
				tccv.setGreenAngle((int) (90));
				tccv.setYellowAngle((int) ((myTotal - 1) * 90));
				tccv.setRedAngle((int) (0));
			} else if (myTotal > 2 && myTotal <= 4)
			{
				tccv.setGreenAngle((int) (90));
				tccv.setYellowAngle((int) (90));
				tccv.setRedAngle((int) ((myTotal - 2) * 90));
			} else
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
			Log.d("DC", "graphic width: " + dataCalcGraphicFront.getWidth());
		}
	}

	private Point getScreenSize()
	{
		Display d = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		d.getSize(size);
		return size;
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment
	{

		public PlaceholderFragment()
		{
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.fragment_calc, container,
					false);
			return rootView;
		}
	}

}
