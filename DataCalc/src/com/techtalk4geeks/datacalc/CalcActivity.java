package com.techtalk4geeks.datacalc;

import java.text.DecimalFormat;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
		if (myTotal > 0.5)
		{
			ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));
			
			ImageView point5 = new ImageView(this);
			point5.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			point5.setImageDrawable(this.getResources().getDrawable(R.drawable.data_calc_graphic_point_5_small));
			rl.addView(point5);
//			dataCalcGraphic.bringToFront();
			
			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources().getDrawable(R.drawable.data_calc_graphic_front_small));
			rl = (RelativeLayout) (findViewById(R.id.image_container));
			rl.addView(dataCalcGraphicFront);
		}
		else {
			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources().getDrawable(R.drawable.data_calc_graphic_front_small));
			rl = (RelativeLayout) (findViewById(R.id.image_container));
			rl.addView(dataCalcGraphicFront);
			TextView dataCalculation = new TextView(this);
			dataCalculation.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			dataCalculation.setTextSize(15);
			dataCalculation.setText(df.format(myTotal));
//			TextView dataCalculation = (TextView) (findViewById(R.id.dataEstimate));
			rl.addView(dataCalculation);
		}
		if (myTotal > 1)
		{
			ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));
			
			ImageView one = new ImageView(this);
			one.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			one.setImageDrawable(this.getResources().getDrawable(R.drawable.data_calc_graphic_1_small));
			rl.addView(one);
//			dataCalcGraphic.bringToFront();
			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources().getDrawable(R.drawable.data_calc_graphic_front_small));
			rl = (RelativeLayout) (findViewById(R.id.image_container));
			rl.addView(dataCalcGraphicFront);
		}
		if (myTotal > 1.5)
		{
			ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));
			
			ImageView onePointFive = new ImageView(this);
			onePointFive.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			onePointFive.setImageDrawable(this.getResources().getDrawable(R.drawable.data_calc_graphic_1_point_5_small));
			rl.addView(onePointFive);
			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources().getDrawable(R.drawable.data_calc_graphic_front_small));
			rl = (RelativeLayout) (findViewById(R.id.image_container));
			rl.addView(dataCalcGraphicFront);
		}
		if (myTotal > 2)
		{
			ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));
			
			ImageView onePointFive = new ImageView(this);
			onePointFive.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			onePointFive.setImageDrawable(this.getResources().getDrawable(R.drawable.data_calc_graphic_2_small));
			rl.addView(onePointFive);
			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources().getDrawable(R.drawable.data_calc_graphic_front_small));
			rl = (RelativeLayout) (findViewById(R.id.image_container));
			rl.addView(dataCalcGraphicFront);
		}
		if (myTotal > 2.5)
		{
			ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));
			
			ImageView onePointFive = new ImageView(this);
			onePointFive.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			onePointFive.setImageDrawable(this.getResources().getDrawable(R.drawable.data_calc_graphic_2_point_5_small));
			rl.addView(onePointFive);
			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources().getDrawable(R.drawable.data_calc_graphic_front_small));
			rl = (RelativeLayout) (findViewById(R.id.image_container));
			rl.addView(dataCalcGraphicFront);
		}
		if (myTotal > 3)
		{
			ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));
			
			ImageView onePointFive = new ImageView(this);
			onePointFive.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			onePointFive.setImageDrawable(this.getResources().getDrawable(R.drawable.data_calc_graphic_3_small));
			rl.addView(onePointFive);
			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources().getDrawable(R.drawable.data_calc_graphic_front_small));
			rl = (RelativeLayout) (findViewById(R.id.image_container));
			rl.addView(dataCalcGraphicFront);
		}
		if (myTotal > 3.5)
		{
			ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));
			
			ImageView onePointFive = new ImageView(this);
			onePointFive.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			onePointFive.setImageDrawable(this.getResources().getDrawable(R.drawable.data_calc_graphic_3_point_5_small));
			rl.addView(onePointFive);
			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources().getDrawable(R.drawable.data_calc_graphic_front_small));
			rl = (RelativeLayout) (findViewById(R.id.image_container));
			rl.addView(dataCalcGraphicFront);
		}
		if (myTotal > 4)
		{
			ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));
			
			ImageView onePointFive = new ImageView(this);
			onePointFive.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			onePointFive.setImageDrawable(this.getResources().getDrawable(R.drawable.data_calc_graphic_3_point_5_plus_small));
			rl.addView(onePointFive);
			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources().getDrawable(R.drawable.data_calc_graphic_front_small));
			rl = (RelativeLayout) (findViewById(R.id.image_container));
			rl.addView(dataCalcGraphicFront);
		}

		if (savedInstanceState == null)
		{
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
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
	
//	@Override
//	public void onBackPressed()
//	{
//		Intent start = new Intent(this, StartActivity.class);
//		start.addCategory(Intent.CATEGORY_HOME);
//		start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		startActivity(start);
//		overridePendingTransition(R.anim.anim_in_up, R.anim.anim_out_down);
//	}

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
