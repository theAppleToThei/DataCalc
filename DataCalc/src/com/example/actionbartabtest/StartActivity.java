package com.example.actionbartabtest;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class StartActivity extends Activity
{
	public static Context appContext;

	RadioButton smartphone;
	RadioButton tablet;
	RadioButton laptop;

	private String device;
	public static final String SMARTPHONE = "smartphone";
	public static final String TABLET = "tablet";
	public static final String LAPTOP = "laptop";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		appContext = getApplicationContext();

		// ActionBar
		ActionBar actionbar = getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		ActionBar.Tab PlayerTab = actionbar.newTab().setText("Devices");
		ActionBar.Tab StationsTab = actionbar.newTab().setText("Variables");

		Fragment PlayerFragment = new AFragment();
		Fragment StationsFragment = new BFragment();

		PlayerTab.setTabListener(new MyTabsListener(PlayerFragment));
		StationsTab.setTabListener(new MyTabsListener(StationsFragment));

		actionbar.addTab(PlayerTab);
		actionbar.addTab(StationsTab);

		smartphone = (RadioButton) findViewById(R.id.smartphone);
		tablet = (RadioButton) findViewById(R.id.tablet);
		laptop = (RadioButton) findViewById(R.id.laptop);

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
		switch (item.getItemId())
		{
		}
		return false;
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
		} else {
			return (Integer) null;
		}
	}
	
	public int getEmailSizeAttachment(String d)
	{
		return 300;
	}
	
	public int getMinuteMusicStream(String d)
	{
		return 500;
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