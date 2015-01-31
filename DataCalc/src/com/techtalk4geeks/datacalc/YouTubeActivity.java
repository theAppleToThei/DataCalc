package com.techtalk4geeks.datacalc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.graphics.Point;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.joda.time.Minutes;
import org.joda.time.Period;
import org.joda.time.Seconds;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

import com.android.vending.billing.IInAppBillingService;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ActionBar.LayoutParams;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.Build;

public class YouTubeActivity extends ActionBarActivity
{
	public static YouTubeActivity youtubeActivity;
	EditText mURL;
	String infourl = "";
	static final String API_KEY = "AIzaSyAdEJJkaHnPcgTBogJMIHQdoPd6V94Qaao";
	Boolean isYouTubeAuto = false;
	

//	IInAppBillingService mService;
	public ProgressBar progressBar;
//
//	ServiceConnection mServiceConn = new ServiceConnection()
//	{
//		@Override
//		public void onServiceDisconnected(ComponentName name)
//		{
//			mService = null;
//		}
//
//		@Override
//		public void onServiceConnected(ComponentName name, IBinder service)
//		{
//			mService = IInAppBillingService.Stub.asInterface(service);
//		}
//	};

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		isYouTubeAuto = false;
		super.onCreate(savedInstanceState);

		youtubeActivity = this;
		setTitle("YouTube Calculator");
		getActionBar().setIcon(R.drawable.youtube_calc_image);
		setContentView(R.layout.activity_you_tube);

		// progressBar = (ProgressBar) findViewById(R.id.progressBar);
		// mURL = (EditText) findViewById(R.id.youtubeURL);
		// progressBar.setVisibility(0);

		// querySkus.putStringArrayList(“youtubeUpgrade”, skuList);

		// ClipboardManager clipboard = (ClipboardManager)
		// getSystemService(Context.CLIPBOARD_SERVICE);
		//
		// String pasteData = "";
		//
		// // Gets the ID of the "paste" menu item
		// MenuItem mPasteItem = menu.findItem(R.id.menu_paste);
		//
		// // If the clipboard doesn't contain data, disable the paste menu
		// item.
		// // If it does contain data, decide if you can handle the data.
		// if (!(clipboard.hasPrimaryClip())) {
		//
		// mPasteItem.setEnabled(false);
		//
		// } else if
		// (!(clipboard.getPrimaryClipDescription().hasMimeType(MIMETYPE_TEXT_PLAIN)))
		// {
		//
		// // This disables the paste menu item, since the clipboard has data
		// but it is not plain text
		// mPasteItem.setEnabled(false);
		// } else {
		//
		// // This enables the paste menu item, since the clipboard contains
		// plain text.
		// mPasteItem.setEnabled(true);
		// }

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
				try
				{
					handleSendText(intent);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}

	}

//	@Override
//	public void onDestroy()
//	{
//		super.onDestroy();
//		if (mService != null)
//		{
//			unbindService(mServiceConn);
//		}
//	}

	void handleSendText(Intent intent) throws Exception
	{
		isYouTubeAuto = true;
		Log.d("DC", "handleSendText() called");
		String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
		if (sharedText != null)
		{
			Log.d("DC", "sharedText is not null: " + sharedText);
			// startActivity(intent);
			// overridePendingTransition(R.anim.anim_in_up, R.anim.anim_out_up);
			for (int i = 0; i < sharedText.length() - 8; i++)
			{
				int substring = 0;
				if (sharedText.substring(i, i + 8).equalsIgnoreCase("youtu.be"))
				{
					Log.d("DC", "Found 'youtu.be' in sharedText");
					substring = i + 9;
				} else if (sharedText.substring(i, i + 12).equalsIgnoreCase(
						"youtube.com/"))
				{
					Log.d("DC", "Found 'youtube.com/' in sharedText");
					substring = i + 12;
				} else
				{
					// Screw them
					// Log.d("DC", "Unexpected sharedText = " + sharedText);
					continue;
				}
				String youtubeURLContentDetails = "https://www.googleapis.com/youtube/v3/videos?id="
						+ sharedText.substring(substring)
						+ "&key="
						+ API_KEY
						+ "&part=contentDetails";
				String youtubeURLSnippet = "https://www.googleapis.com/youtube/v3/videos?id="
						+ sharedText.substring(substring)
						+ "&key="
						+ API_KEY
						+ "&part=snippet";
				Log.d("DC", youtubeURLContentDetails);
				Log.d("DC", "YouTube ID: " + sharedText.substring(substring));
				new YouTubeAPIOperations().execute(youtubeURLContentDetails);
				// progressBar.setVisibility(100);
				Log.d("DC", "after execute");

				// long length = getVidTime(youtubeURL);
				// calculate(length);
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

	// @Override
	// public void onBackPressed()
	// {
	// if (!isYouTubeAuto)
	// {
	// Intent start = new Intent(this, StartActivity.class);
	// start.addCategory(Intent.CATEGORY_HOME);
	// start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	// startActivity(start);
	// overridePendingTransition(R.anim.anim_in_up, R.anim.anim_out_down);
	// } else {
	// return;
	// }
	// }

	public String getYouTubeContentDetails(String youTubeURL) throws Exception
	{
		URL url = new URL(youTubeURL);
		URLConnection connection;
		connection = url.openConnection();

		HttpURLConnection httpConnection = (HttpURLConnection) connection;

		int responseCode = httpConnection.getResponseCode();
		Log.d("DC", "responseCode:\n" + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK)
		{

			InputStream in = httpConnection.getInputStream();
			String jsonStr = getStringFromInputStream(in);
			Log.d("DC", "jsonStr:\n" + jsonStr);
			JSONObject jsonOb = new JSONObject(jsonStr);
			JSONArray items = jsonOb.getJSONArray("items");
			JSONObject zeroElement = items.getJSONObject(0);
			JSONObject uglyEncodedDuration = zeroElement
					.getJSONObject("contentDetails");
			String duration = uglyEncodedDuration.getString("duration");
			Log.d("DC", "duration:\n" + duration);
			return duration;
		} else
		{
			return "Error";
		}
	}

	public String getYouTubeSnippet(String youTubeURL) throws Exception
	{
		URL url = new URL(youTubeURL);
		URLConnection connection;
		connection = url.openConnection();

		HttpURLConnection httpConnection = (HttpURLConnection) connection;

		int responseCode = httpConnection.getResponseCode();
		Log.d("DC", "responseCode:\n" + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK)
		{

			InputStream in = httpConnection.getInputStream();
			String jsonStr = getStringFromInputStream(in);
			Log.d("DC", "jsonStr:\n" + jsonStr);
			JSONObject jsonOb = new JSONObject(jsonStr);
			JSONArray items = jsonOb.getJSONArray("items");
			JSONObject zeroElement = items.getJSONObject(0);
			JSONObject uglyEncodedDuration = zeroElement
					.getJSONObject("snippet");
			String title = uglyEncodedDuration.getString("title");
			Log.d("DC", "title:\n" + title);
			return title;
		} else
		{
			return "Error";
		}
	}

	private static String getStringFromInputStream(InputStream is)
	{

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try
		{

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null)
			{
				sb.append(line);
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (br != null)
			{
				try
				{
					br.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

	public void showCalculation(double estimate)
	{
		// progressBar.setVisibility(0);
		setContentView(R.layout.activity_calc);

		overridePendingTransition(R.anim.anim_in_up, R.anim.anim_out_down);

		ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));
		// TextView dataEstimateText = (TextView)
		// (findViewById(R.id.dataEstimate));
		RelativeLayout rl = (RelativeLayout) (findViewById(R.id.image_container));

		if (estimate > 5)
		{
			ImageView point5 = new ImageView(this);
			point5.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
			point5.setImageDrawable(this.getResources().getDrawable(
					R.drawable.data_calc_graphic_point_5_small));
			rl.addView(point5);
		}
		if (estimate > 10)
		{
			ImageView one = new ImageView(this);
			one.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
			one.setImageDrawable(this.getResources().getDrawable(
					R.drawable.data_calc_graphic_1_small));
			rl.addView(one);
		}
		if (estimate > 15)
		{
			ImageView onePointFive = new ImageView(this);
			onePointFive.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			onePointFive.setImageDrawable(this.getResources().getDrawable(
					R.drawable.data_calc_graphic_1_point_5_small));
			rl.addView(onePointFive);
		}
		if (estimate > 20)
		{
			ImageView onePointFive = new ImageView(this);
			onePointFive.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			onePointFive.setImageDrawable(this.getResources().getDrawable(
					R.drawable.data_calc_graphic_2_small));
			rl.addView(onePointFive);
		}
		if (estimate > 25)
		{
			ImageView onePointFive = new ImageView(this);
			onePointFive.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			onePointFive.setImageDrawable(this.getResources().getDrawable(
					R.drawable.data_calc_graphic_2_point_5_small));
			rl.addView(onePointFive);
		}
		if (estimate > 30)
		{
			ImageView onePointFive = new ImageView(this);
			onePointFive.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			onePointFive.setImageDrawable(this.getResources().getDrawable(
					R.drawable.data_calc_graphic_3_small));
			rl.addView(onePointFive);
		}
		if (estimate > 33)
		{
			ImageView onePointFive = new ImageView(this);
			onePointFive.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			onePointFive.setImageDrawable(this.getResources().getDrawable(
					R.drawable.data_calc_graphic_3_point_5_small));
			rl.addView(onePointFive);
		}
		if (estimate > 35)
		{
			ImageView onePointFive = new ImageView(this);
			onePointFive.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			onePointFive.setImageDrawable(this.getResources().getDrawable(
					R.drawable.data_calc_graphic_3_point_5_plus_small));
			rl.addView(onePointFive);
		}

		ImageView dataCalcGraphicFront = new ImageView(this);
		TextView dataEstimateText = new TextView(this);
		TextView titleText = new TextView(this);
		dataCalcGraphicFront.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		dataCalcGraphicFront.setImageDrawable(this.getResources().getDrawable(
				R.drawable.youtube_calc_graphic_corrected_small));
		dataEstimateText.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		dataEstimateText.setTextSize(40);
		dataEstimateText.setGravity(Gravity.CENTER);
		titleText.setTextSize(20);
		rl = (RelativeLayout) (findViewById(R.id.image_container));
		rl.addView(dataCalcGraphicFront);
		rl.addView(dataEstimateText);
		rl.addView(titleText);
		DecimalFormat df = new DecimalFormat("#.###");
		dataEstimateText.setText(String.valueOf(df.format(estimate)));
		// titleText.setText(title);
	}

	public long getVidTime(String url)
	{
		Uri uri = Uri.parse(url);
		MediaMetadataRetriever retriever = new MediaMetadataRetriever();
		retriever.setDataSource(this, uri);
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
		// if (id == R.id.calc)
		// {
		// if (mURL != null)
		// {
		// String url = mURL.getText().toString();
		// try
		// {
		// getYouTubeContentDetails(url);
		// } catch (Exception e)
		// {
		// e.printStackTrace();
		// }
		// return true;
		// } else
		// {
		// setContentView(R.layout.activity_calc);
		// overridePendingTransition(R.anim.anim_in_up, R.anim.anim_out_up);
		// }
		// }
		// if (id == R.id.help_button)
		// {
		// createYouTubeHelpDialog();
		// return true;
		// }
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

	public void renderGaugeGraphic(double myTotal)
	{
		setContentView(R.layout.activity_calc);
		overridePendingTransition(R.anim.anim_in_up, R.anim.anim_out_down);
		TextView estimate = (TextView) findViewById(R.id.dataEstimate);
		estimate.bringToFront();
		DecimalFormat df = new DecimalFormat("#.##");
		estimate.setText((df.format(myTotal)));
		RelativeLayout rl = (RelativeLayout) (findViewById(R.id.image_container));
		// if (myTotal <= 0.5)
		{
			final ImageView dataCalcGraphic = (ImageView) (findViewById(R.id.data_calc_graphic));

			// ImageView point5 = new ImageView(this);
			// point5.setLayoutParams(new
			// LayoutParams(LayoutParams.MATCH_PARENT,
			// LayoutParams.MATCH_PARENT));
			// TODO Draw transparent arch over arch5
			// float drawOver = (float) (1 - myTotal / 0.5) * 45;
			float drawOver = 39;
			// TODO Get canvas object
			// Canvas c = new Canvas(five);
			// TODO Draw arch5 onto canvas
			// TODO Draw arch of transparent pixels onto canvas

			// int radius = five.getWidth() / 2;

			dataCalcGraphic.getViewTreeObserver().addOnGlobalLayoutListener(
					new ViewTreeObserver.OnGlobalLayoutListener()
					{

						@Override
						public void onGlobalLayout()
						{
							// Ensure you call it only once :
							dataCalcGraphic.getViewTreeObserver()
									.removeGlobalOnLayoutListener(this);

							// Here you can get the size :)
						}
					});

			Point size = getScreenSize();
			int width = size.x;
			int height = size.y;

			// int centerX = (int) (dataCalcGraphic.getX() + radius);
			// int centerY = (int) (dataCalcGraphic.getY() + radius);
			// RectF oval = new RectF(centerX - radius, centerY - radius,
			// centerX
			// + radius, centerY + radius);
			Paint paint = new Paint();
			paint.setColor(Color.RED); // Transparent
			// c.drawArc(oval, 45, drawOver, true, paint);
			// TODO Get modified arch back out of canvas
			// point5.setImageDrawable(arch5);
			// rl.addView(point5);
			

			YouTubeCanvasCalcView yccv = new YouTubeCanvasCalcView(this);
			yccv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
			rl.addView(yccv);

			if (myTotal <= 1)
			{
				yccv.setGreenAngle((int) (myTotal * 90) - 1);
				yccv.setYellowAngle((int) (0));
				yccv.setRedAngle((int) (0));
			} else if (myTotal <= 2)
			{
				yccv.setGreenAngle((int) (90));
				yccv.setYellowAngle((int) ((myTotal - 1) * 90));
				yccv.setRedAngle((int) (0));
			} else if (myTotal > 2 && myTotal <= 4)
			{
				yccv.setGreenAngle((int) (90));
				yccv.setYellowAngle((int) (90));
				yccv.setRedAngle((int) ((myTotal - 2) * 90));
			} else
			{
				yccv.setGreenAngle((int) (90));
				yccv.setYellowAngle((int) (90));
				yccv.setRedAngle((int) (180));
			}

			ImageView dataCalcGraphicFront = new ImageView(this);
			dataCalcGraphicFront.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			dataCalcGraphicFront.setImageDrawable(this.getResources()
					.getDrawable(R.drawable.data_calc_graphic_front_small));
			rl.addView(dataCalcGraphicFront);

			ImageView dataCalcGraphicTop = new ImageView(this);
			dataCalcGraphicTop.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			dataCalcGraphicTop.setImageDrawable(this.getResources()
					.getDrawable(R.drawable.data_calc_graphic_top_small));
			rl.addView(dataCalcGraphicTop);
			estimate.bringToFront();
			Log.d("DC", "graphic width: " + dataCalcGraphicFront.getWidth());
		}

	}

	public Point getScreenSize()
	{
		Display d = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		d.getSize(size);
		return size;
	}

	private class YouTubeAPIOperations extends
			AsyncTask<String, String, String>
	{

		@Override
		protected void onPreExecute()
		{
			Log.i("DC", "Made it to onPreExecute()");
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params)
		{
			Log.i("DC", "Made it to doInBackground()");
			try
			{
				String result = getYouTubeContentDetails(params[0]);
				// final String title = getYouTubeSnippet(params[0]);
				PeriodFormatter formatter = ISOPeriodFormat.standard();
				Period p = formatter.parsePeriod(result);
				Log.i("DC",
						String.valueOf("formatter seconds: "
								+ p.toStandardSeconds().getSeconds()));
				int durationSeconds = p.toStandardSeconds().getSeconds();

				final double videoMegaMinute = 2;
				final double videoMegaSecond = videoMegaMinute / 60.0;
				final double total = durationSeconds * videoMegaSecond;
				Log.i("DC", String.valueOf(total));
				runOnUiThread(new Runnable()
				{
					@Override
					public void run()
					{
						Log.i("DC", "Made it to showCalculation()");
						// showCalculation(total);
						renderGaugeGraphic(total);
					}
				});

			} catch (Exception e)
			{
				Log.e("DC", "Error After Program Start: " + e);
			}

			return "Error";
		}

		@Override
		protected void onPostExecute(String result)
		{
			Log.d("DC", "Reached onPostExecute, Result = " + result);
		}

		@Override
		protected void onProgressUpdate(String... values)
		{
		}
	}
}
