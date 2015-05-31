package com.example.sqldatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	EditText et1, et2, et3, et4, et5;
	String name, country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  DatabaseOps dbo = new DatabaseOps(this); // Create object for new class
      //  DatabaseSchema dbs = dbo.new DatabaseSchema(this); // create object for inner class
       // SQLiteDatabase sqliteDB = dbs.getWritableDatabase();
        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        et5 = (EditText) findViewById(R.id.editText5);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void insertData(View v){
    	name = et1.getText().toString();
    	country= et2.getText().toString();
    	DatabaseOps dbo = new DatabaseOps(this);
    	long sqlCode = dbo.insertSql(name, country);    
    	if (sqlCode != -1) {
    		Toast.makeText(this, "Record inserted,  return code = " + sqlCode,Toast.LENGTH_LONG).show();
    		Log.d("DEB","Record inserted successfully,  return code = " + sqlCode);
    	}
    	else {
    		Toast.makeText(this, "Record NOT inserted,  return code = " + sqlCode,Toast.LENGTH_LONG).show();
    		Log.d("DEB","Record NOT inserted,  return code = " + sqlCode);
    	}
    	}
    public void showAll(View v) {
		// TODO Auto-generated method stub
    	Intent intent = new Intent(this,DisplayData.class);
    	startActivity(intent);
    	/*DatabaseOps dbo = new DatabaseOps(this);
    	String data = dbo.getAllDetails();
    	Toast.makeText(this, data,Toast.LENGTH_LONG).show();
		Log.d("DEB",data); */
	}
    public void getName(View v) {
		// TODO Auto-generated method stub
    	DatabaseOps dbo = new DatabaseOps(this);
    	String name = et3.getText().toString();
    	String data = dbo.getName(name);
    	Toast.makeText(this, data,Toast.LENGTH_LONG).show();
		Log.d("DEB",data);
	}
    public void updateName(View v) {
		// TODO Auto-generated method stub
    	DatabaseOps dbo = new DatabaseOps(this);
    	String old_name = et4.getText().toString();
    	String new_name = et5.getText().toString();
    	int count = dbo.updateSql(old_name, new_name);
    	Toast.makeText(this, "Total record updated, count = "+count,Toast.LENGTH_LONG).show();
		Log.d("DEB","Total record updated, count = "+count);
	}
    public void deleteName(View v) {
		// TODO Auto-generated method stub
    	DatabaseOps dbo = new DatabaseOps(this);
    	String name = et4.getText().toString();
    	int count = dbo.deleteSql(name);
    	Toast.makeText(this, "Total record deleted, count = "+count,Toast.LENGTH_LONG).show();
		Log.d("DEB","Total record deleted, count = "+count);
	}
}
