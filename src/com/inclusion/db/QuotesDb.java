package com.inclusion.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class QuotesDb extends SQLiteOpenHelper {
	// Database Name
	public static final String DATABASE_NAME = "YouQuote.sqlite";
	// quotes table name
	private static final String QUOTES_TABLE_NAME = "tbl_addquotes_one";

	private static String QUOTE_ID = "id";
	private static String QUOTE = "quote";
	private static String AUTHORITY = "authority";
	private static String DATE = "date";

	public QuotesDb(Context context, String DATABASE_NAME,
			CursorFactory factory, int DATABASE_VERSION) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_USER_TABLE = "CREATE TABLE " + QUOTES_TABLE_NAME + "("
				+ QUOTE_ID + " INTEGER PRIMARY KEY," + QUOTE + " TEXT,"
				+ AUTHORITY + " TEXT," + DATE + " DATE" + ")";

		db.execSQL(CREATE_USER_TABLE);
		Log.i("DB", "Creating DataBase: " + DATABASE_NAME);
		db.close();

	}

	// Adding new quote
	public int addQuote(Quote q) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(QUOTE, q.getQuote());
		values.put(AUTHORITY, q.getAuthority());
		values.put(DATE, q.getDate());
		// Inserting Row
		int id = (int) db.insert(QUOTES_TABLE_NAME, null, values);
		db.close(); // Closing database connection
		return id;
	}

	public String[] getQuotes() {
		ArrayList<String> usersList = new ArrayList<String>();
		String selectQuery = "SELECT * FROM " + QUOTES_TABLE_NAME;
		try {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);
			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					String user = cursor.getString(1) + " \n  ~ "
							+ cursor.getString(2) + ", ";
					usersList.add(user);
				} while (cursor.moveToNext());
			}

			String[] users = new String[usersList.size()];
			db.close();
			return (usersList.toArray(users));
		} catch (Exception e) {
			Log.d("Error in getting users from DB", e.getMessage());
			return null;
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + QUOTES_TABLE_NAME);

		Log.i("onUpgrade", "From Version " + oldVersion + " to version "
				+ newVersion);
		/*
		 * switch (oldVersion) { case 0:
		 * db.execSQL("DROP TABLE IF EXISTS wine;"); db.execSQL("create table "
		 * + QUOTES_TABLE_NAME + " ( _id integer primary key autoincrement, " +
		 * COL_1 + " text, " + COL_2 + " text, " + COL_3 + " text, " + COL_4 +
		 * " integer, " + COL_5 + " integer, " + COL_6 + " text );"); case 1:
		 * db.execSQL("ALTER TABLE " + QUOTES_TABLE_NAME + " ADD COLUMN " +
		 * COL_6 + " text;"); // comment was not in the original // database
		 * break;
		 * 
		 * }
		 */

	}
}