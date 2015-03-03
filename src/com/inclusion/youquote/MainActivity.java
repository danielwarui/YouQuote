package com.inclusion.youquote;

import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.widget.DrawerLayout;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	TextView quotetext;
	String quotesarray[] = { "A Stitch in time saves nine", "Pepperoni",
			"Black Olives" };;
	int randomint;

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		quotetext = (TextView) findViewById(R.id.quotetext);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

		quotetext.setOnClickListener(new TextView.OnClickListener() {
			public void onClick(View v) {
				Log.v("onTouch", "Somebody touched");
				onTap(v);
			}
		});
		/*
		View addbutton = findViewById(R.id.addquote);
		addbutton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						AddQuoteActivity.class);
				startActivity(intent);

			}
		});
		*/
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.share) {

			String quote = quotetext.getText().toString();

			Intent sendIntent = new Intent(Intent.ACTION_SEND);
			// sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, "innclusion.pro " + "\n"
					+ quote);
			sendIntent.setType("text/plain");
			startActivity(sendIntent);
			return true;

		}
		return super.onOptionsItemSelected(item);
	}

	public void onTap(View v) {

		Random rnd = new Random();
		int randomnum = rnd.nextInt(quotesarray.length);

		quotetext.setText(quotesarray[randomnum]);

	}

	public void addQuote(View v) {
		Intent addquote = new Intent(MainActivity.this, AddQuoteActivity.class);
		startActivity(addquote);

	}

	public String[] getQuotesarray() {
		return quotesarray;
	}

	public void setQuotesarray(String[] quotesarray) {
		this.quotesarray = quotesarray;
	}

	public int getRandomint() {
		return randomint;
	}

	public void setRandomint(int randomint) {
		this.randomint = randomint;
	}

	public CharSequence getmTitle() {
		return mTitle;
	}

	public void setmTitle(CharSequence mTitle) {
		this.mTitle = mTitle;
	}

}
