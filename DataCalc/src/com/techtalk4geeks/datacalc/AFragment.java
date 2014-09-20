package com.techtalk4geeks.datacalc;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class AFragment extends Fragment
{

	RadioButton smartphone;
	RadioButton tablet;
	RadioButton laptop;
	RadioButton hotspot;

	public static final String SMARTPHONE = "smartphone";
	public static final String TABLET = "tablet";
	public static final String LAPTOP = "laptop";
	public static final String HOTSPOT = "hotspot";

	StartActivity sa;
	ActionBar.Tab tab;
	Boolean isVariableDone = false;

	public void setActivity(StartActivity sa, ActionBar.Tab tab)
	{
		this.sa = sa;
		this.tab = tab;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		Log.i("DC", "AFragment: OnCreateView");
		LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.afragment,
				container, false);
		// Inflate the layout for this fragment
		smartphone = (RadioButton) ll.findViewById(R.id.smartphone);
		isVariableDone = false;
		Log.d("DC", "smartphone = " + smartphone);

		OnCheckedChangeListener oc = new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked)
			{
				Log.i("DC", "onCheckChanged / ButtonView: " + buttonView
						+ " / isChecked: " + isChecked);
				if (isChecked && !isVariableDone)
				{
					 sa.getActionBar().selectTab(tab);
					 isVariableDone = true;
				}
			}
		};

		tablet = (RadioButton) ll.findViewById(R.id.tablet);
		laptop = (RadioButton) ll.findViewById(R.id.laptop);
		hotspot = (RadioButton) ll.findViewById(R.id.hotspot);
		smartphone.setOnCheckedChangeListener(oc);
		tablet.setOnCheckedChangeListener(oc);
		laptop.setOnCheckedChangeListener(oc);
		hotspot.setOnCheckedChangeListener(oc);
		return ll;
	}

	public String calculateA()
	{
		if (smartphone.isChecked())
		{
			return SMARTPHONE;
		}
		if (tablet.isChecked())
		{
			return TABLET;
		}
		if (laptop.isChecked())
		{
			return LAPTOP;
		}
		if (hotspot.isChecked())
		{
			return HOTSPOT;
		}
		return null;
	}
}
