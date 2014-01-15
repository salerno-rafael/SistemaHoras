package com.example.sistemahoras.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.sistemahoras.DbHelper;

public class UserDao {

	public void saveData(Context context, String desc, String date) {
		DbHelper dbHelper = new DbHelper(context);
		SQLiteDatabase database = dbHelper.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put("DESCRICAO", desc);
		cv.put("DATE", date);

		database.insert("ATIVIDADE", null, cv);
		dbHelper.close();
	}

	public List<Atividades> listData(Context context) {
		DbHelper dbHelper = new DbHelper(context);
		SQLiteDatabase database = dbHelper.getWritableDatabase();

		Cursor cursor = database.query("ATIVIDADE", new String[] { "DESCRICAO", "DATE" }, null, null, null, null, null);
		cursor.moveToFirst();

		StringBuilder text = new StringBuilder();
		
		List<Atividades> list = new ArrayList<Atividades>();

		while (!cursor.isAfterLast()) {
			list.add(new Atividades(cursor.getString(0),cursor.getString(1)));
			
			text.append(cursor.getString(0));
			text.append(cursor.getString(1));
			cursor.moveToNext();
		}

		dbHelper.close();

		Log.d("LOG", text.toString());
		
		return list;
	}

	public void deleteData(Context context) {
		DbHelper dbHelper = new DbHelper(context);
		SQLiteDatabase database = dbHelper.getWritableDatabase();

		database.delete("ATIVIDADE", null, null);

		dbHelper.close();
		
		Toast toast = Toast.makeText(context, "Delete Records.", Toast.LENGTH_SHORT);
		toast.show();

	}

}
