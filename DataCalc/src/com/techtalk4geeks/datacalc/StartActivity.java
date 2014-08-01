package com.techtalk4geeks.datacalc;

import org.w3c.dom.Text;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
	public static final String SMARTPHONE = "smartphone";
	public static final String TABLET = "tablet";
	public static final String LAPTOP = "laptop";
	public static final String HOTSPOT = "hotspot";
	
	EditText emails;
	EditText emailsAttach;
	TextView estimate;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		appContext = getApplicationContext();

		emails = (EditText) findViewById(R.id.emailNum);
		emailsAttach = (EditText) findViewById(R.id.emailAttachNum);
		estimate = (TextView) findViewById(R.id.dataEstimate);
		
		// ActionBar
		ActionBar actionbar = getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		ActionBar.Tab DevicesTab = actionbar.newTab().setText("Devices");
		ActionBar.Tab VariablesTab = actionbar.newTab().setText("Variables");
		ActionBar.Tab ResultsTab = actionbar.newTab().setText("Estimate");

		Fragment PlayerFragment = new AFragment();
		Fragment StationsFragment = new BFragment();
		Fragment ResultsFragment = new CFragment();

		DevicesTab.setTabListener(new MyTabsListener(PlayerFragment));
		VariablesTab.setTabListener(new MyTabsListener(StationsFragment));
		ResultsTab.setTabListener(new MyTabsListener(ResultsFragment));

		actionbar.addTab(DevicesTab);
		actionbar.addTab(VariablesTab);
		actionbar.addTab(ResultsTab);

		smartphone = (RadioButton) findViewById(R.id.smartphone);
		tablet = (RadioButton) findViewById(R.id.tablet);
		laptop = (RadioButton) findViewById(R.id.laptop);
		hotspot = (RadioButton) findViewById(R.id.hotspot);

		setTitle("Data Calc");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.youtube_calc)
		{
			Intent youtubeCalc = new Intent(this, YouTubeActivity.class);
			startActivity(youtubeCalc);
			return true;
		}
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

	public int getEmailSize(String d)
	{
		if (d == SMARTPHONE)
		{
			return 20;
		} else if (d == TABLET)
		{
			return 20;
		} else if (d == LAPTOP)
		{
			return 35;
		} else if (d == HOTSPOT)
		{
			return 20;
		} else
		{
			return (Integer) null;
		}
	}

	public int getEmailSizeAttachment()
	{
		return 300;
	}

	public int getMinuteMusicStream()
	{
		return 500;
	}
	
	public void calculateTotal()
	{
		int emailInt;
//		if (emails.getText().toString() == null || emails.getText().toString().isEmpty()) {
//			emailInt = 0;
//		}
		emailInt = Integer.parseInt(String.valueOf(emails.getText()));
		double kiloTotal = (int) (getEmailSize(device) * emailInt);
		kiloTotal = Math.pow(kiloTotal, -6);
		double total = kiloTotal;
		estimate.setText(String.valueOf(total) + " GB");
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
		Toast.makeText(StartActivity.appContext, "Reselected!",
				Toast.LENGTH_LONG).show();
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft)
	{
		ft.replace(R.id.fragment_container, fragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft)
	{
		ft.remove(fragment);
	}


}