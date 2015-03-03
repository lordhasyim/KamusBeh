package com.hasyim.kamusbeh;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	/**
	 * deklarasi konstanta - konstanta yang digunakan pada database, seperti
	 * nama tabel, nama kolom, nama database, dan versi dari database
	 * 
	 * */

	public static final String TABLE_NAME = "data_kamus";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_INDONESIA = "indonesia";
	public static final String COLUMN_INGGRIS = "inggris";
	public static final String COLUMN_JERMAN = "jerman";
	private static final String db_name = "kamusbeh.db";
	private static final int db_version = 1;

	// perintah sql untuk membuat tabel database baru
	private static final String db_create = "CREATE TABLE " + TABLE_NAME + "("
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_INDONESIA + " VARCHAR(50) NOT NULL, " + COLUMN_INGGRIS
			+ " VARCHAR(50) NOT NULL, " + COLUMN_JERMAN
			+ " VARCHAR(50) NOT NULL);";

	public DBHelper(Context context) {
		super(context, db_name, null, db_version);

	}

	// eksekusi perintah sql di atas utk membuat db dan tabel baru
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(db_create);
	}

	// dijalankan apabil ingin mengupgrade database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DBHelper.class.getName(), "Upgrading database from version "
				+ oldVersion + "to" + newVersion
				+ "which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
		onCreate(db);
	}

}
