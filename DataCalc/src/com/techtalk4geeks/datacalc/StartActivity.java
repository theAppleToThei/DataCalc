package com.techtalk4geeks.datacalc;

import java.io.File;

import org.w3c.dom.Text;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends Activity
{
	public static Context appContext;

	RadioButton smartphone;
	RadioButton tablet;
	RadioButton laptop;
	RadioButton hotspot;

	final int DAY = 30;
	final int WEEK = 5;
	final int MONTH = 1;

	private String device;
	public double total;
	public static final String SMARTPHONE = "smartphone";
	public static final String TABLET = "tablet";
	public static final String LAPTOP = "laptop";
	public static final String HOTSPOT = "hotspot";

	EditText emails;
	EditText emailsAttach;
	TextView estimate;

	AFragment mAFragment;
	BFragment mBFragment;
	CFragment mCFragment;

	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setTitle("Data Calc");
//		Intent drawer = new Intent(this, DrawerTest.class);
//		startActivity(drawer);
		setContentView(R.layout.main);
		appContext = getApplicationContext();

		// Intent drawer = new Intent(this, DrawerActivity.class);
		// startActivity(drawer);

		emails = (EditText) findViewById(R.id.emailNum);
		emailsAttach = (EditText) findViewById(R.id.emailAttachNum);
		estimate = (TextView) findViewById(R.id.dataEstimate);

		// ActionBar
		ActionBar actionbar = getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		ActionBar.Tab devicesTab = actionbar.newTab().setText("Devices");
		ActionBar.Tab variablesTab = actionbar.newTab().setText("Variables");
		ActionBar.Tab resultsTab = actionbar.newTab().setText("Estimate");

		Log.i("DC", "Creating fragments");
		mAFragment = new AFragment();
		mAFragment.setActivity(this, variablesTab);
		mBFragment = new BFragment();
		mCFragment = new CFragment();
		Log.i("DC", "Fragments created");

		devicesTab.setTabListener(new MyTabsListener(mAFragment));
		variablesTab.setTabListener(new MyTabsListener(mBFragment));
		resultsTab.setTabListener(new MyTabsListener(mCFragment));

		actionbar.addTab(devicesTab);
		actionbar.addTab(variablesTab);
		// actionbar.addTab(ResultsTab);
		
		smartphone = (RadioButton) findViewById(R.id.smartphone);
		tablet = (RadioButton) findViewById(R.id.tablet);
		laptop = (RadioButton) findViewById(R.id.laptop);
		hotspot = (RadioButton) findViewById(R.id.hotspot);

		setTitle("Data Calc");

		Intent intent = getIntent();
		String action = intent.getAction();
		String type = intent.getType();

		if (Intent.ACTION_SEND.equals(action) && type != null)
		{
			if (type.startsWith("image/"))
			{
				handleSendImage(intent); // Handle single image being sent
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.calc_button)
		{
			calculateStart();
			overridePendingTransition(R.anim.anim_in_up, R.anim.anim_out_down);
			return true;
		}
		if (id == R.id.youtube_calc)
		{
			Intent youtubeCalc = new Intent(this, YouTubeActivity.class);
			startActivity(youtubeCalc);
			overridePendingTransition(R.anim.anim_in_up, R.anim.anim_out_up);
			return true;
		}
		if (id == R.id.help)
		{
			
			return true;
		}
//		if (id == R.id.about)
//		{
//			Intent about = new Intent(this, About.class);
//			startActivity(about);
//			overridePendingTransition(R.anim.anim_in_up, R.anim.anim_out_up);
//			setTitle("About");
//			// getActionBar().setIcon(R.drawable.about);
//			return true;
//		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
	}

	public void onRadioButtonClicked(View v)
	{
		if (v.equals(smartphone))
		{
			device = SMARTPHONE;
		} else if (v.equals(tablet))
		{
			device = TABLET;
		} else if (v.equals(laptop))
		{
			device = LAPTOP;
		} else if (v.equals(hotspot))
		{
			device = HOTSPOT;
		}
	}

	public void calculateTotal()
	{
		int emailInt;
		// if (emails.getText().toString() == null ||
		// emails.getText().toString().isEmpty()) {
		// emailInt = 0;
		// }
		// emailInt = Integer.parseInt(String.valueOf(getEmails().getText()));
		// double kiloTotal = (int) (getEmailSize(device) * emailInt);
		// kiloTotal = Math.pow(kiloTotal, -6);
		// double total = kiloTotal;
		// estimate.setText(String.valueOf(total) + " GB");
	}

	public void calculateStart()
	{
		device = mAFragment.calculateA();
		total = mBFragment.calculateB(device);
		Intent calc = new Intent(this, CalcActivity.class);
		calc.putExtra("device", device);
		calc.putExtra("total", total);
		startActivity(calc);
		// estimate.setText(String.valueOf(total) + " GB");
	}

	public void calculate(long l)
	{
		Intent calc = new Intent(this, CalcActivity.class);
		calc.putExtra("total", l);
		startActivity(calc);
	}

	void handleSendImage(Intent intent)
	{
		Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
		File image = new File(imageUri.getPath());
		long length = image.length();
		length = length / 1024;
		calculatePhoto(length);
		overridePendingTransition(R.anim.anim_in_up, R.anim.anim_out_down);
	}
	
	public void calculatePhoto(long l)
	{
		setContentView(R.layout.calculatephoto);
		TextView dataEstimatePhoto = (TextView) findViewById(R.id.dataEstimatePhoto);
		dataEstimatePhoto.setText(String.valueOf(l));
	}

}

class MyTabsListener implements ActionBar.TabListener
{
	public Fragment fragment;

	public MyTabsListener(Fragment fragment)
	{
		this.fragment = fragment;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft)
	{
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft)
	{
		Log.i("DC", "onTabSelected");
		ft.replace(R.id.fragment_container, fragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft)
	{
		ft.remove(fragment);
	}

}