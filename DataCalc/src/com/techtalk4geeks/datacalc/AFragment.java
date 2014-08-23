package com.techtalk4geeks.datacalc;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.afragment,
				container, false);
		// Inflate the layout for this fragment
		smartphone = (RadioButton) ll.findViewById(R.id.smartphone);
		Log.d("DC", "smartphone = " + smartphone);
		tablet = (RadioButton) ll.findViewById(R.id.tablet);
		laptop = (RadioButton) ll.findViewById(R.id.laptop);
		hotspot = (RadioButton) ll.findViewById(R.id.hotspot);
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
