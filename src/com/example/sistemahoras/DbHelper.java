package com.example.sistemahoras;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "wallet.db";
	private static final int DATABASE_VERSION = 1;
	private static final String SQL_CACHE_TABLE = "CREATE TABLE IF NOT EXISTS ATIVIDADE (DESCRICAO VARCHAR, DATE VARCHAR)";

	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CACHE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS ATIVIDADE");
		onCreate(db);

	}

}
