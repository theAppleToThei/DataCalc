package com.techtalk4geeks.datacalc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.os.Build;

public class YouTubeActivity extends ActionBarActivity
{
	EditText mURL;
	String infourl = "";

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

		Intent intent = getIntent();
		String action = intent.getAction();
		String type = intent.getType();

		if (Intent.ACTION_SEND.equals(action) && type != null)
		{
			if ("text/plain".equals(type))
			{
				handleSendText(intent); // Handle text being sent
			}
		} 
	}

	void handleSendText(Intent intent)
	{
		Log.d("DC", "handleSendText() called");
		String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
		if (sharedText != null)
		{
			Log.d("DC", "sharedText is not null");
			// startActivity(intent);
			// overridePendingTransition(R.anim.anim_in_up, R.anim.anim_out_up);
			for (int i = 0; i < sharedText.length() - 8; i++)
			{
				if (sharedText.substring(i, i + 8).equalsIgnoreCase("youtu.be"))
				{
					Log.d("DC", "Found 'youtu.be' in sharedText");
					int substring = i + 9;
					String youtubeURL = "www.youtube.com/watch?v="
							+ sharedText.substring(substring);
					long length = getVidTime(youtubeURL);
					calculate(length);
				}
			}
		}
		Log.e("DC", "sharedText IS null");
	}
	
	public void calculate(long l)
	{
		Intent calc = new Intent(this, CalcActivity.class);
		calc.putExtra("total", l);
		startActivity(calc);
	}
	
	@Override
	public void onBackPressed()
	{
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

	// public int Youtube_Fetchinfo(Context context,String url,int timeout){
	// if (!url.toLowerCase().contains("youtube"))
	// return (Integer) null;
	//
	// //get video ID
	// Pattern p = Pattern.compile(".+v=([^&\"]*)");
	// Matcher m = p.matcher(url);
	// if (m.find()) {
	// infourl = "http://gdata.youtube.com/feeds/api/videos/"+m.group(1);
	// } else
	// return (Integer) null;
	// }
	//
	// int responseCode = 0;
	// int duration = 0;
	// String sduration="";
	// String title="";
	// String movietitle = new String();
	//
	// //Get video information
	// String defaultUA =
	// "Mozilla/5.0 (Linux; U; Android 2.1; en-us) AppleWebKit/522+ (KHTML, like Gecko) Safari/419.3";
	// HttpClient httpClient = new DefaultHttpClient();
	// HttpGet httpGet = new HttpGet(infourl);
	// HttpResponse httpResponse = null;
	//
	//
	// httpClient.getParams().setParameter("http.connection.timeout", new
	// Integer(timeout));
	// HttpParams params1 = httpClient.getParams();
	// HttpConnectionParams.setConnectionTimeout(params1, timeout);
	// HttpConnectionParams.setSoTimeout(params1, timeout);
	// params1.setParameter(HttpProtocolParams.USER_AGENT, defaultUA);
	// httpResponse = httpClient.execute(httpGet);
	// responseCode = httpResponse.getStatusLine().getStatusCode();
	//
	// if (responseCode == HttpStatus.SC_OK) {
	// InputStream istream = httpResponse.getEntity().getContent();
	// InputStreamReader reader = new InputStreamReader(istream);
	// BufferedReader objBuf = new BufferedReader(reader);
	// StringBuilder builder = new StringBuilder();
	// String sLine;
	//
	// while((sLine = objBuf.readLine()) != null){
	// builder.append(sLine);}
	// String html = builder.toString();
	// istream.close();
	//
	// //find duration info
	// Pattern dp = Pattern.compile("seconds='(\\d+?)'");
	// Matcher dm = dp.matcher(html);
	// return duration;
	// }

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