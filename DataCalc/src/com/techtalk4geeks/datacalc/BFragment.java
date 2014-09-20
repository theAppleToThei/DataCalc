package com.techtalk4geeks.datacalc;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class BFragment extends Fragment
{
	StartActivity start = new StartActivity();
	public EditText emails;
	Spinner emailDate;
	Spinner musicDate;
	EditText emailsAttach;
	EditText musics;
	Spinner musicMinHour;
	EditText webs;
	Spinner websDate;
	EditText photos;
	Spinner photosDate;
	EditText videos;
	Spinner videosMinHour;
	Spinner videosDate;

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
		Log.i("DC", "BFragment: onCreateView");
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
		musics = (EditText) ll.findViewById(R.id.musicNum);
		emailDate = (Spinner) ll.findViewById(R.id.emailMonth);
		musicDate = (Spinner) ll.findViewById(R.id.musicMonth);
		musicMinHour = (Spinner) ll.findViewById(R.id.musicMinHour);
		webs = (EditText) ll.findViewById(R.id.webNum);
		websDate = (Spinner) ll.findViewById(R.id.webMonth);
		photos = (EditText) ll.findViewById(R.id.photoNum);
		photosDate = (Spinner) ll.findViewById(R.id.photoMonth);
		videos = (EditText) ll.findViewById(R.id.vidNum);
		videosMinHour = (Spinner) ll.findViewById(R.id.vidMinHour);
		videosDate = (Spinner) ll.findViewById(R.id.vidMonth);
		// start.overridePendingTransition(R.anim.anim_in_right,
		// R.anim.anim_out_right);
		return ll;
	}

	public double calculateB(String device)
	{
		Log.i("DC", "Calculate B");
		int emailInt;
		int emailAttachInt;
		int emailDateNum = 0;
		int websDateNum = 0;
		int websInt = 0;
		int musicDateNum = 0;
		int musicMinHourNum = 0;
		int musicInt;
		int photosInt = 0;
		int photosDateNum = 0;
		int videosInt = 0;
		int videosMinHourNum = 0;
		int videosDateNum = 0;
		if (emails == null || emails.getText().toString().isEmpty())
		{
			emailInt = 0;
		} else
		{
			emailInt = Integer.parseInt(String.valueOf(emails.getText()));
		}
		if (videos == null || videos.getText().toString().isEmpty())
		{
			videosInt = 0;
		} else
		{
			videosInt = Integer.parseInt(String.valueOf(videos.getText()));
		}
		if (emailsAttach == null || emailsAttach.getText().toString().isEmpty())
		{
			emailAttachInt = 0;
		} else
		{
			emailAttachInt = Integer.parseInt(String.valueOf(emailsAttach
					.getText()));
		}
		if (musics == null || musics.getText().toString().isEmpty())
		{
			musicInt = 0;
		} else
		{
			musicInt = Integer.parseInt(String.valueOf(musics.getText()));
		}
		if (webs == null || webs.getText().toString().isEmpty())
		{
			websInt = 0;
		} else
		{
			websInt = Integer.parseInt(String.valueOf(webs.getText()));
		}
		if (photos == null || photos.getText().toString().isEmpty())
		{
			photosInt = 0;
		} else
		{
			photosInt = Integer.parseInt(String.valueOf(photos.getText()));
		}
		if (emailDate == null
				|| emailDate.getSelectedItem().toString().equals("a month"))
		{
			emailDateNum = 1;
		} else if (emailDate.getSelectedItem().toString().equals("a week"))
		{
			emailDateNum = 5;
		} else if (emailDate.getSelectedItem().toString().equals("a day"))
		{
			emailDateNum = 30;
		}
		if (musicDate == null
				|| musicDate.getSelectedItem().toString().equals("a month"))
		{
			musicDateNum = 1;
		} else if (musicDate.getSelectedItem().toString().equals("a week"))
		{
			musicDateNum = 5;
		} else if (musicDate.getSelectedItem().toString().equals("a day"))
		{
			musicDateNum = 30;
		}
		if (musicMinHour == null
				|| musicMinHour.getSelectedItem().toString().equals("minutes"))
		{
			musicMinHourNum = 1;
		} else if (musicDate.getSelectedItem().toString().equals("hours"))
		{
			musicMinHourNum = 60;
		}
		if (websDate == null
				|| websDate.getSelectedItem().toString().equals("a month"))
		{
			websDateNum = 1;
		} else if (websDate.getSelectedItem().toString().equals("a week"))
		{
			websDateNum = 5;
		} else if (websDate.getSelectedItem().toString().equals("a day"))
		{
			websDateNum = 30;
		}
		if (videosDate == null
				|| videosDate.getSelectedItem().toString().equals("a month"))
		{
			videosDateNum = 1;
		} else if (videosDate.getSelectedItem().toString().equals("a week"))
		{
			videosDateNum = 5;
		} else if (videosDate.getSelectedItem().toString().equals("a day"))
		{
			videosDateNum = 30;
		}
		if (photosDate == null
				|| photosDate.getSelectedItem().toString().equals("a month"))
		{
			photosDateNum = 1;
		} else if (photosDate.getSelectedItem().toString().equals("a week"))
		{
			photosDateNum = 5;
		} else if (photosDate.getSelectedItem().toString().equals("a day"))
		{
			photosDateNum = 30;
		}
		if (videosMinHour == null
				|| videosMinHour.getSelectedItem().toString().equals("minutes"))
		{
			videosMinHourNum = 1;
		} else if (videosMinHour.getSelectedItem().toString().equals("hours"))
		{
			videosMinHourNum = 60;
		}

		double kiloTotal = 0;
		kiloTotal += (int) (getEmailSize(device) * emailInt * emailDateNum);
		kiloTotal += (int) (getMinuteMusicStream() * musicInt * musicDateNum * musicMinHourNum);
		kiloTotal += (int) (getPhotoPost() * photosInt * photosDateNum);
		double megaTotal = 0;
		megaTotal += (int) (getHourWeb() * websInt * websDateNum);
		megaTotal += (int) (getVideoMin(device) * videosInt * videosDateNum);
		kiloTotal += (int) (getEmailSizeAttachment() * emailAttachInt);
		kiloTotal = kiloTotal / 1000000;
		megaTotal = megaTotal / 1000;
		double total = kiloTotal + megaTotal;
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

	public int getHourWeb()
	{
		return 15;
	}

	public int getPhotoPost()
	{
		return 350;
	}

	public double getVideoMin(String d)
	{
		if (d != HOTSPOT)
		{
			return 5.1;
		} else if (d == HOTSPOT)
		{
			return 15;
		} else
		{
			return (Integer) null;
		}
	}
}
