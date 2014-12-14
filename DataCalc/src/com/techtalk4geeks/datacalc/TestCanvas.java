package com.techtalk4geeks.datacalc;

import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class TestCanvas extends ActionBarActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_canvas);
		
		RelativeLayout rl = (RelativeLayout) (findViewById(R.id.test_canvas_layout));

		ImageView point5 = new ImageView(this);
		point5.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		Drawable arch5 = this.getResources().getDrawable(
				R.drawable.data_calc_graphic_point_5_small);
		Bitmap five = Bitmap.createBitmap(arch5.getMinimumWidth(),
				arch5.getMinimumHeight(), Bitmap.Config.ALPHA_8);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_canvas, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.up)
		{
			startActivity(getIntent());
			overridePendingTransition(R.anim.anim_in_up, R.anim.anim_out_up);
			return true;
		}
		if (id == R.id.down)
		{
			startActivity(getIntent());
			overridePendingTransition(R.anim.anim_in_down, R.anim.anim_out_down);
			return true;
		}
		if (id == R.id.right)
		{
			startActivity(getIntent());
			overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_right);
			return true;
		}
		if (id == R.id.flip)
		{
			startActivity(getIntent());
			overridePendingTransition(R.anim.anim_in_flip, R.anim.anim_out_flip);
			return true;
		}
		if (id == R.id.testCalculation)
		{
			Intent testCalc = new Intent(this, TestCalcActivity.class);
			startActivity(testCalc);
			overridePendingTransition(R.anim.anim_in_up, R.anim.anim_out_down);
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
}

