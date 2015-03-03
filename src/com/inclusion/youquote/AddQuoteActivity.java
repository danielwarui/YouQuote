package com.inclusion.youquote;

import java.sql.Timestamp;

import com.inclusion.db.Quote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddQuoteActivity extends Activity implements OnClickListener {
	String data, receiveddata;
	Intent i;
	Bundle b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addquote);
		Button AddQouteButton = (Button) findViewById(R.id.submitQuote);
		AddQouteButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.submitQuote:
			register();
			break;

		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	private void register() {

		String quote = ((EditText) findViewById(R.id.quoteText)).getText()
				.toString();
		String authority = ((EditText) findViewById(R.id.authority)).getText()
				.toString();
		int time = (int) System.currentTimeMillis();
		Timestamp tsTemp = new Timestamp(time);
		String ts = tsTemp.toString();
		String source = "local";

		com.inclusion.db.QuotesDb db = new com.inclusion.db.QuotesDb(this,
				source, null, time);
		int id = db.addQuote(new Quote(quote, authority, ts));
		db.close();
		if (id > 0) {
			Toast toast = Toast.makeText(getApplicationContext(),
					"User registered at id " + id, Toast.LENGTH_SHORT);
			toast.show();
		} else {
			Toast toast = Toast.makeText(getApplicationContext(),
					"User registration failed", Toast.LENGTH_SHORT);
			toast.show();
		}
		/*
		 * ((EditText)findViewById(R.id.quoteText)).setText("");
		 * ((EditText)findViewById(R.id.authority)).setText(""); //
		 * ((EditText)findViewById(R.id.editTextRegPhone)).setText("");
		 */
	}

	public void insertNewQuotes() {

	}

}
