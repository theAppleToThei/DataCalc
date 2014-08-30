package com.techtalk4geeks.datacalc;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

public class YouTubeActivity extends ActionBarActivity
{
	EditText mURL;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setTitle("YouTube Calculator");
		getActionBar().setIcon(R.drawable.youtube_calc_image);
		setContentView(R.layout.activity_you_tube);
		mURL = (EditText) findViewById(R.id.youtubeURL);

		if (savedInstanceState == null)
		{
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public void onBackPressed() {
	   Intent start = new Intent(this, StartActivity.class);
	   start.addCategory(Intent.CATEGORY_HOME);
	   start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   startActivity(start);
	   overridePendingTransition(R.anim.anim_in_up, R.anim.anim_out_down);
	}

	public long getVidTime(String URL)
	{
		MediaMetadataRetriever retriever = new MediaMetadataRetriever();
		retriever.setDataSource(URL);
		String time = retriever
				.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
		long timeInmillisec = Long.parseLong(time);
		long duration = timeInmillisec;
		long hours = duration / 3600;
		long minutes = (duration - hours * 3600) / 60;
		long seconds = duration - (hours * 3600 + minutes * 60);
		return duration;
	}
	
	public void calculate(long l) {
		Intent calc = new Intent(this, CalcActivity.class);
		calc.putExtra("total", l);
		startActivity(calc);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.you_tube, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.calc)
		{
			String url = mURL.getText().toString();
			long time = getVidTime(url);
			calculate(time);
			return true;
		}
		if (id == R.id.help_button)
		{
			createYouTubeHelpDialog();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public Dialog createYouTubeHelpDialog()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Set the dialog title
		builder.setTitle(R.string.help);
		// Specify the list array, the items to be selected by default (null for
		// none),
		// and the listener through which to receive callbacks when items are
		// selected
		builder.setMessage("When you're data conscious and need to use it sparingly, use the YouTube Calc. It allows you to paste a link to any YouTube video and get an estimate of how much data you'll use over cellular data.");
		// Set the action buttons
		builder.setNeutralButton(R.string.close,
				new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int id)
					{

					}
				});

		return builder.create();
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment
	{

		public PlaceholderFragment()
		{
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.fragment_you_tube,
					container, false);
			return rootView;
		}
	}

}
