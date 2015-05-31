package com.example.sqldatabase;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListView;

public class DisplayData extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_data);
		DatabaseOps dbo = new DatabaseOps(this);
    	Cursor curr = dbo.getAllDetailsCurr();
    	String id = dbo.dbs.getId();
    	String name = dbo.dbs.grtName();
    	String country = dbo.dbs.getCountry();
		String[] mFromCols = {id,name,country};
		int[] mToCols = {R.id.idView,R.id.nameView,R.id.countryView};
		SimpleCursorAdapter scAdapter = new SimpleCursorAdapter(this, R.layout.single_row_data, curr, mFromCols, mToCols,0);
		ListView lView = (ListView) findViewById(R.id.listView1);
		lView.setAdapter(scAdapter);
	}
}
