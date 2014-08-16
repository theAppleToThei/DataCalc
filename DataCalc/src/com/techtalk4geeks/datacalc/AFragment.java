package com.techtalk4geeks.datacalc;

import android.app.Fragment;
import android.os.Bundle;
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.bfragment,
				container, false);
		// Inflate the layout for this fragment
		smartphone = (RadioButton) ll.findViewById(R.id.smartphone);
		tablet = (RadioButton) ll.findViewById(R.id.tablet);
		laptop = (RadioButton) ll.findViewById(R.id.laptop);
		hotspot = (RadioButton) ll.findViewById(R.id.hotspot);
		return ll;
	}

	public RadioButton calculateA(View v)
	{
		if (smartphone.isChecked())
		{
			return smartphone;
		}
		if (tablet.isChecked())
		{
			return tablet;
		}
		return null;
	}
}
