package com.techtalk4geeks.datacalc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class BFragment extends Fragment
{
	StartActivity start = new StartActivity();
	public EditText emails;
	EditText emailsAttach;
	Button calcButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment_container
		// return inflater.inflate(R.layout.bfragment, container, false);

		if (container == null)
		{
			return null;
		}

		LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.bfragment,
				container, false);
		emails = (EditText) ll.findViewById(R.id.emailNum);
		emailsAttach = (EditText) ll.findViewById(R.id.emailAttachNum);
		return ll;
	}
	
	public void calculateB(View v)
	{
		int emailInt;
		if (emails.getText().toString() == null
				|| emails.getText().toString().isEmpty())
		{
			emailInt = 0;
		}
		emailInt = Integer.parseInt(String.valueOf(emails.getText()));
//		double kiloTotal = (int) (getEmailSize(device) * emailInt);
//		kiloTotal = Math.pow(kiloTotal, -6);
//		double total = kiloTotal;
//		estimate.setText(String.valueOf(total) + " GB");
	}

}
