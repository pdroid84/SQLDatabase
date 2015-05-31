package com.example.sqldatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseOps {
	Context context;
	DatabaseSchema dbs;
	
	public DatabaseOps (Context context){
		Toast.makeText(context, "DatabaseOps Constructor is called", Toast.LENGTH_LONG).show();
		this.context = context;
		dbs = new DatabaseSchema(context);
	}
	
	public long insertSql(String name,String country) {
		// TODO Auto-generated method stub
		SQLiteDatabase sqlDb = dbs.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(DatabaseSchema.COLUMN_NAME, name);
		cv.put(DatabaseSchema.COLUMN_COUNTRY, country);
		long rc = sqlDb.insert(DatabaseSchema.TABLE_NAME, null, cv);
		return rc;
	}
	public String getAllDetails() {
		// TODO Auto-generated method stub
		SQLiteDatabase sqlDb = dbs.getReadableDatabase();
		StringBuffer buffer = new StringBuffer();
		String[] columns={DatabaseSchema.COLUMN_ID,DatabaseSchema.COLUMN_NAME,DatabaseSchema.COLUMN_COUNTRY};
		Cursor cursor = sqlDb.query(DatabaseSchema.TABLE_NAME, columns, null, null, null, null, null);
		while(cursor.moveToNext())
		{
			int id = cursor.getInt(cursor.getColumnIndex(DatabaseSchema.COLUMN_ID));
			String name = cursor.getString(cursor.getColumnIndex(DatabaseSchema.COLUMN_NAME));
			String country = cursor.getString(cursor.getColumnIndex(DatabaseSchema.COLUMN_COUNTRY));
			buffer.append(id +" "+name+" "+country+"\n");
		}
		return buffer.toString();
	}
	public Cursor getAllDetailsCurr() {
		// TODO Auto-generated method stub
		SQLiteDatabase sqlDb = dbs.getReadableDatabase();
		StringBuffer buffer = new StringBuffer();
		String[] columns={DatabaseSchema.COLUMN_ID,DatabaseSchema.COLUMN_NAME,DatabaseSchema.COLUMN_COUNTRY};
		Cursor cursor = sqlDb.query(DatabaseSchema.TABLE_NAME, columns, null, null, null, null, null);
		return cursor;
	}
	public String getName(String name) {
		// TODO Auto-generated method stub
		SQLiteDatabase sqlDb = dbs.getReadableDatabase();
		StringBuffer buffer = new StringBuffer();
		String[] columns={DatabaseSchema.COLUMN_ID,DatabaseSchema.COLUMN_NAME,DatabaseSchema.COLUMN_COUNTRY};
		Cursor cursor = sqlDb.query(DatabaseSchema.TABLE_NAME, columns, DatabaseSchema.COLUMN_NAME+"= '"+name+"'", null, null, null, null);
		while(cursor.moveToNext())
		{
			int id = cursor.getInt(cursor.getColumnIndex(DatabaseSchema.COLUMN_ID));
			String name_2 = cursor.getString(cursor.getColumnIndex(DatabaseSchema.COLUMN_NAME));
			String country = cursor.getString(cursor.getColumnIndex(DatabaseSchema.COLUMN_COUNTRY));
			buffer.append(id +" "+name_2+" "+country+"\n");
		}
		return buffer.toString();
	}
	public int updateSql(String oldName, String newName) {
		// TODO Auto-generated method stub
		SQLiteDatabase sqlDb = dbs.getWritableDatabase();
		ContentValues cv = new ContentValues();
		String[] whereArgs={oldName};
		cv.put(DatabaseSchema.COLUMN_NAME, newName);
		int count=sqlDb.update(DatabaseSchema.TABLE_NAME, cv, DatabaseSchema.COLUMN_NAME +" =?", whereArgs);
		return count;
	}
	public int deleteSql(String name) {
		// TODO Auto-generated method stub
		SQLiteDatabase sqlDb = dbs.getWritableDatabase();
		String[] whereArgs={name};
		int count=sqlDb.delete(DatabaseSchema.TABLE_NAME, DatabaseSchema.COLUMN_NAME+" =?", whereArgs);
		return count;
	}
	class DatabaseSchema extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "DEBASHIS_DB";
	private static final int DATABASE_VERSION = 2;
	private static final String TABLE_NAME = "debtable";
	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_COUNTRY = "counry";
	private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
			" (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME +
			" VARCHAR(255), " + COLUMN_COUNTRY + " VARCHAR(255))";
	private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
	private Context context;
	
	public DatabaseSchema(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		Toast.makeText(context, "DatabaseSchema Constructor is called", Toast.LENGTH_LONG).show();
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		try {
			db.execSQL(CREATE_TABLE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Toast.makeText(context, "OnCreate is called", Toast.LENGTH_LONG).show();
		Log.d("DEB", "OnCreate is called");
	}
	public String getId () {
		return COLUMN_ID;
	}
	public String grtName() {
		return COLUMN_NAME;
	}
	public String getCountry () {
		return COLUMN_COUNTRY;
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		try {
			db.execSQL(DROP_TABLE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Toast.makeText(context, "OnUpgrade is called."+ "DB Old ver="+oldVersion+" ,DB New ver="+newVersion, Toast.LENGTH_LONG).show();
		Log.d("DEB", "OnUpgrade is called."+ "DB Old ver="+oldVersion+" ,DB New ver="+newVersion);
		onCreate(db);
	}
	@Override
		public void onDowngrade(SQLiteDatabase db, int oldVersion,
				int newVersion) {
			// TODO Auto-generated method stub
			//super.onDowngrade(db, oldVersion, newVersion);
			Toast.makeText(context, "OnDowngrade is called."+ "DB Old ver="+oldVersion+" ,DB New ver="+newVersion, Toast.LENGTH_LONG).show();
			Log.d("DEB", "OnDowngrade is called."+ "DB Old ver="+oldVersion+" ,DB New ver="+newVersion);
		}
 } // End of Inner Class
} // End of Outer class
