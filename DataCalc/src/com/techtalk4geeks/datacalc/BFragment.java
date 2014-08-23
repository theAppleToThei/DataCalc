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

	private String device;
	public static final String SMARTPHONE = "smartphone";
	public static final String TABLET = "tablet";
	public static final String LAPTOP = "laptop";
	public static final String HOTSPOT = "hotspot";

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

	public double calculateB(String device)
	{
		int emailInt;
		int emailAttachInt;
		if (emails.getText().toString() == null
				|| emails.getText().toString().isEmpty())
		{
			emailInt = 0;
		} else
		{
			emailInt = Integer.parseInt(String.valueOf(emails.getText()));
		}
		if (emailsAttach.getText().toString() == null
				|| emailsAttach.getText().toString().isEmpty())
		{
			emailAttachInt = 0;
		} else
		{
			emailAttachInt = Integer.parseInt(String.valueOf(emailsAttach.getText()));
		}
		double kiloTotal = (int) (getEmailSize(device) * emailInt);
		kiloTotal += (int) (getEmailSizeAttachment() * emailAttachInt);
		kiloTotal = Math.pow(kiloTotal, -6);
		double total = kiloTotal;
		return total;
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

}
